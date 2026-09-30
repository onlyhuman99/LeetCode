class Solution:
    def minAbsoluteDifference(self, nums: list[int]) -> int:
        res = 101
        for i in range(len(nums)):
            for j in range(len(nums)):
                if nums[i] == 1 and nums[j] == 2:
                    res = min(res, abs(i - j))
        
        return -1 if res == 101 else res