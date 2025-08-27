/*
 * @lc app=leetcode.cn id=560 lang=java
 *
 * [560] 和为 K 的子数组
 *
 * https://leetcode.cn/problems/subarray-sum-equals-k/description/
 *
 * algorithms
 * Medium (45.31%)
 * Likes:    2859
 * Dislikes: 0
 * Total Accepted:    815.4K
 * Total Submissions: 1.8M
 * Testcase Example:  '[1,1,1]\n2'
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 
 * 子数组是数组中元素的连续非空序列。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 2 * 10^4
 * -1000 <= nums[i] <= 1000
 * -10^7 <= k <= 10^7
 * 
 * 
 */

// @lc code=start
class Solution {
    public int subarraySum(int[] nums, int k) {
        int res = 0,preSum = 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);//key = 前缀和,value = 此前缀和出现次数
        for(int num : nums){
            preSum += num;
            if (map.containsKey(preSum - k)){
                res += map.get(preSum - k);
            }
            map.put(preSum,map.getOrDefault(preSum,0) + 1);
            //getOrDefault(key,defaultValue);无key则返回defaultValue
        }
        return res;
    }
}
// @lc code=end

