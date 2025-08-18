import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Unit tests for the Solution class.
 */
public class SolutionTest
{
    private final Solution solution = new Solution();

    @Test
    void testExample1CountOnly()
    {
        int[] nums = {1, 1, 1};
        int k = 2;
        assertEquals(2, solution.subarraySum(nums, k));
    }

    @Test
    void testExample2CountOnly()
    {
        int[] nums = {1, 2, 3};
        int k = 3;
        assertEquals(2, solution.subarraySum(nums, k));
    }

    @Test
    void testExample3CountAndEnumeration()
    {
        int[] nums = {3, 4, 7, -2, 2, 1, 4, 2};
        int k = 7;

        // Count check
        assertEquals(6, solution.subarraySum(nums, k));

        // Enumeration check
        List<List<Integer>> actual = solution.enumerateSubarrays(nums, k);
        Set<String> actualSet = toCanonicalSet(actual);

        // Expected subarrays (as sequences)
        int[][] expectedArrays = new int[][] {
                {3, 4},           // (0..1)
                {7},              // (2..2)
                {7, -2, 2},       // (2..4)
                {-2, 2, 1, 4, 2}, // (3..7)
                {2, 1, 4},        // (4..6)
                {1, 4, 2}         // (5..7)
        };
        Set<String> expectedSet = toCanonicalSet(expectedArrays);

        assertEquals(expectedSet, actualSet, "Enumerated subarrays do not match expected.");
        assertEquals(6, actual.size(), "Enumerated subarray count mismatch.");
    }

    @Test
    void testNoSubarrayEmptyEnumeration()
    {
        int[] nums = {1, 2, 3};
        int k = 10;
        assertEquals(0, solution.subarraySum(nums, k));
        assertTrue(solution.enumerateSubarrays(nums, k).isEmpty());
    }

    @Test
    void testSingleElementCountAndEnumeration()
    {
        int[] nums = {5};
        int k = 5;
        assertEquals(1, solution.subarraySum(nums, k));

        List<List<Integer>> actual = solution.enumerateSubarrays(nums, k);
        assertEquals(1, actual.size());
        assertEquals(List.of(5), actual.get(0));
    }

    @Test
    void testNegativeNumbersCountAndEnumeration()
    {
        int[] nums = {1, -1, 1, -1, 1};
        int k = 0;

        // Count: there are 6 zero-sum subarrays in this sequence
        assertEquals(6, solution.subarraySum(nums, k));

        // We won't list all for brevity, but we can at least validate size and that each sums to 0
        List<List<Integer>> subs = solution.enumerateSubarrays(nums, k);
        assertEquals(6, subs.size());
        for (List<Integer> sub : subs)
        {
            int sum = sub.stream().mapToInt(Integer::intValue).sum();
            assertEquals(0, sum, "Subarray should sum to 0: " + sub);
        }
    }

    // ---------- Helpers ----------

    /**
     * Converts a list of integer lists into a canonical Set<String> for order-insensitive comparison.
     * Each subarray is represented as "[a,b,c]" with no spaces.
     */
    private static Set<String> toCanonicalSet(List<List<Integer>> lists)
    {
        return lists.stream()
                .map(list -> list.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(",", "[", "]")))
                .collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * Overload to convert an array-of-arrays expected representation into the same canonical Set<String>.
     */
    private static Set<String> toCanonicalSet(int[][] arrays)
    {
        Set<String> set = new HashSet<>();

        for (int[] arr : arrays)
        {
            String s = toCanonicalString(arr);
            set.add(s);
        }

        return set;
    }

    private static String toCanonicalString(int[] arr)
    {
        StringBuilder sb = new StringBuilder();
        sb.append('[');

        for (int i = 0; i < arr.length; i++)
        {
            if (i > 0)
            {
                sb.append(',');
            }

            sb.append(arr[i]);
        }

        sb.append(']');

        return sb.toString();
    }
}
