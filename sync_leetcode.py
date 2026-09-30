import os
import re
import time
from pathlib import Path

import requests

GRAPHQL_URL = "https://leetcode.com/graphql"

SESSION = os.environ["LEETCODE_SESSION"]
CSRF_TOKEN = os.environ["LEETCODE_CSRF_TOKEN"]

headers = {
    "Content-Type": "application/json",
    "x-csrftoken": CSRF_TOKEN,
    "Referer": "https://leetcode.com/progress/",
    "User-Agent": "Mozilla/5.0",
}

cookies = {
    "LEETCODE_SESSION": SESSION,
    "csrftoken": CSRF_TOKEN,
}


def graphql(query, variables=None):
    response = requests.post(
        GRAPHQL_URL,
        headers=headers,
        cookies=cookies,
        json={
            "query": query,
            "variables": variables or {},
        },
        timeout=30,
    )

    print(f"HTTP status: {response.status_code}")

    response.raise_for_status()

    data = response.json()

    if "errors" in data:
        print("LeetCode GraphQL errors:")
        print(data["errors"])
        raise RuntimeError(data["errors"])

    return data["data"]

print("Testing LeetCode authentication...")

auth_query = """
query {
    userStatus {
        username
        isSignedIn
    }
}
"""

auth_data = graphql(auth_query)

user_status = auth_data["userStatus"]

print(f"LeetCode username: {user_status['username']}")
print(f"Signed in: {user_status['isSignedIn']}")

if not user_status["isSignedIn"]:
    raise RuntimeError(
        "GitHub Actions is NOT authenticated to LeetCode. "
        "Check LEETCODE_SESSION and LEETCODE_CSRF_TOKEN secrets."
    )


# ============================================================
# 1. GET ALL SOLVED PROBLEMS
# ============================================================

solved_query = """
query userProgressQuestionList(
    $filters: UserProgressQuestionListInput
) {
    userProgressQuestionList(filters: $filters) {
        totalNum
        questions {
            frontendId
            title
            titleSlug
            difficulty
            lastSubmittedAt
        }
    }
}
"""

solved_problems = []

skip = 0
limit = 1000

while True:

    print(f"Fetching solved problems... skip={skip}")

    variables = {
        "filters": {
            "questionStatus": "SOLVED",
            "skip": skip,
            "limit": limit
        }
    }

    data = graphql(
        solved_query,
        variables
    )

    result = data["userProgressQuestionList"]

    questions = result["questions"]
    total = result["totalNum"]

    solved_problems.extend(questions)

    print(
        f"Received {len(questions)} solved problems "
        f"(total reported: {total})"
    )

    if len(solved_problems) >= total:
        break

    skip += limit

    time.sleep(0.5)


print()
print("=" * 50)
print(f"TOTAL SOLVED PROBLEMS: {len(solved_problems)}")
print("=" * 50)


# ============================================================
# 2. SUBMISSION QUERY
# ============================================================

submission_query = """
query submissionList(
    $offset: Int!,
    $limit: Int!,
    $lastKey: String,
    $questionSlug: String
) {
    submissionList(
        offset: $offset,
        limit: $limit,
        lastKey: $lastKey,
        questionSlug: $questionSlug
    ) {
        lastKey
        hasNext
        submissions {
            id
            statusDisplay
            lang
            timestamp
        }
    }
}
"""


# ============================================================
# 3. SOURCE CODE QUERY
# ============================================================

submission_details_query = """
query submissionDetails($submissionId: Int!) {
    submissionDetails(submissionId: $submissionId) {
        code
        lang {
            name
        }
    }
}
"""


extension_map = {
    "java": "java",
    "python": "py",
    "python3": "py",
    "cpp": "cpp",
    "c": "c",
    "csharp": "cs",
    "javascript": "js",
    "typescript": "ts",
    "kotlin": "kt",
    "go": "go",
    "rust": "rs",
}


# ============================================================
# 4. CREATE OUTPUT DIRECTORY
# ============================================================

output_dir = Path("solutions")
output_dir.mkdir(exist_ok=True)


# ============================================================
# 5. FETCH ONE ACCEPTED SOLUTION FOR EACH PROBLEM
# ============================================================

successful = 0
failed = 0

for index, problem in enumerate(
    solved_problems,
    start=1
):

    title = problem["title"]
    slug = problem["titleSlug"]

    print(
        f"\n[{index}/{len(solved_problems)}] {title}"
    )

    try:

        # --------------------------------------------
        # Find accepted submission
        # --------------------------------------------

        data = graphql(
            submission_query,
            {
                "offset": 0,
                "limit": 20,
                "lastKey": None,
                "questionSlug": slug,
            },
        )

        submissions = data["submissionList"]["submissions"]

        accepted = None

        for submission in submissions:

            if submission["statusDisplay"] == "Accepted":
                accepted = submission
                break

        if accepted is None:

            print("  No accepted submission found.")
            failed += 1
            continue

        submission_id = int(accepted["id"])

        # --------------------------------------------
        # Get actual source code
        # --------------------------------------------

        details_data = graphql(
            submission_details_query,
            {
                "submissionId": submission_id
            },
        )

        details = details_data["submissionDetails"]

        if not details:

            print("  Could not retrieve source code.")
            failed += 1
            continue

        code = details["code"]

        language = details["lang"]["name"].lower()

        extension = extension_map.get(language)

        if extension is None:

            print(
                f"  Unsupported language: {language}"
            )

            failed += 1
            continue

        # --------------------------------------------
        # Create filename
        # --------------------------------------------

        filename = re.sub(
            r"[^a-zA-Z0-9]+",
            "-",
            slug
        ).strip("-").lower()

        filepath = output_dir / f"{filename}.{extension}"

        # --------------------------------------------
        # Don't overwrite existing solutions
        # --------------------------------------------

        if filepath.exists():

            print(
                f"  Already exists: {filepath}"
            )

        else:

            filepath.write_text(
                code,
                encoding="utf-8"
            )

            print(
                f"  Added: {filepath}"
            )

        successful += 1

        time.sleep(0.5)

    except Exception as e:

        print(
            f"  ERROR: {e}"
        )

        failed += 1


# ============================================================
# 6. SUMMARY
# ============================================================

print()
print("=" * 50)
print("LEETCODE SYNC COMPLETE")
print("=" * 50)
print(f"Solved problems : {len(solved_problems)}")
print(f"Successful       : {successful}")
print(f"Failed           : {failed}")
print("=" * 50)