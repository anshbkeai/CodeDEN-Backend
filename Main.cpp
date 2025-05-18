def rotate(nums, k):
    n = len(nums)
    k %= n  # handle k >= n

    # Helper to reverse a portion of the list in-place
    def reverse(start, end):
        while start < end:
            nums[start], nums[end] = nums[end], nums[start]
            start += 1
            end -= 1

    reverse(0, n - 1)
    reverse(0, k - 1)
    reverse(k, n - 1)

# Test Case 1
arr1 = [1, 2, 3, 4, 5, 6, 7]
rotate(arr1, 3)
print(arr1)  # Output: [5, 6, 7, 1, 2, 3, 4]

# Test Case 2
arr2 = [1, 2]
rotate(arr2, 1)
print(arr2)  # Output: [2, 1]
