import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * Solution class for finding the number of subarrays whose sum equals a given value k.
 *
 * <p>This implementation uses a prefix sum approach with a HashMap to store the frequency
 * of prefix sums encountered so far. For each new element in the array, it checks if
 * (current prefix sum - k) has been seen before. If so, those occurrences represent subarrays
 * that sum to k.</p>
 *
 * <p>Time Complexity: O(n) where n is the length of the input array.</p>
 * <p>Space Complexity: O(n) due to the HashMap storing prefix sums.</p>
 */
public class Solution
{

    /**
     * Counts the number of contiguous subarrays whose sum equals k.
     *
     * @param nums the input array of integers
     * @param k    the target sum
     * @return the number of subarrays whose sum equals k
     */
    public int subarraySum(int[] nums, int k)
    {
        int sum = 0, count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // Base case: prefix sum 0 appears once

        for (int num : nums)
        {
            sum += num;

            if (map.containsKey(sum - k))
            {
                count += map.get(sum - k);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    /**
     * Debug helper: Enumerates all subarrays whose sum equals k.
     *
     * @param nums the input array of integers
     * @param k    the target sum
     * @return a list of subarrays (each subarray represented as a list of integers)
     */
    public List<List<Integer>> enumerateSubarrays(int[] nums, int k)
    {
        List<List<Integer>> results = new ArrayList<>();

        for (int start = 0; start < nums.length; start++)
        {
            int sum = 0;
            for (int end = start; end < nums.length; end++)
            {
                sum += nums[end];
                if (sum == k)
                {
                    // Collect the subarray
                    List<Integer> subarray = new ArrayList<>();
                    for (int i = start; i <= end; i++)
                    {
                        subarray.add(nums[i]);
                    }
                    results.add(subarray);
                }
            }
        }

        return results;
    }
}
