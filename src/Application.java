public class Application
{
    public static void main(String[] args)
    {
        Solution solution = new Solution();

        // Example 1
        int[] nums1 = {1, 1, 1};
        int k1 = 2;

        System.out.println("Number of subarrays with sum " + k1 + " = " + solution.subarraySum(nums1, k1));
        System.out.println("Matching subarrays: " + solution.enumerateSubarrays(nums1, k1));

        // Example 2
        int[] nums2 = {1, 2, 3};
        int k2 = 3;
        System.out.println("Number of subarrays with sum " + k2 + " = " + solution.subarraySum(nums2, k2));
        System.out.println("Matching subarrays: " + solution.enumerateSubarrays(nums2, k2));

        // Example 3
        int[] nums3 = {3, 4, 7, -2, 2, 1, 4, 2};
        int k3 = 7;
        System.out.println("Number of subarrays with sum " + k3 + " = " + solution.subarraySum(nums3, k3));
        System.out.println("Matching subarrays: " + solution.enumerateSubarrays(nums3, k3));
    }
}