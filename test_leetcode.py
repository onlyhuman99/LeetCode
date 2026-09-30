import os
import requests

url = "https://leetcode.com/graphql"

query = """
query {
    userStatus {
        username
        isSignedIn
    }
}
"""

headers = {
    "Content-Type": "application/json",
    "x-csrftoken": os.environ["LEETCODE_CSRF_TOKEN"],
    "Referer": "https://leetcode.com/",
    "User-Agent": "Mozilla/5.0"
}

cookies = {
    "LEETCODE_SESSION": os.environ["LEETCODE_SESSION"],
    "csrftoken": os.environ["LEETCODE_CSRF_TOKEN"]
}

response = requests.post(
    url,
    headers=headers,
    cookies=cookies,
    json={"query": query}
)

print("Status:", response.status_code)
print(response.text)