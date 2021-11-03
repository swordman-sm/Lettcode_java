package vip.aevlp.t4FindMedianSortedArrays;

public class FindMedianSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        //1.需要确定数组容量大小 保证下方的遍历时有序的
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }
        //2.数组合并条件下中间位置的index
        int k = (m + n + 1) >> 1;
        //3.初始化每个数组中间位置的index
        int nums1MidIndex = 0;
        int nums2MidIndex = 0;
        //本质上就是找到中位数左侧与右侧的数字,就可以确定中位数
        int beforeMidIndex = 0;
        int afterMidIndex = m;
        while (beforeMidIndex <= afterMidIndex) {
            //                          beforeMidIndex-->
            // nums1: ……………… nums1[MidIndex-1] | nums1[MidIndex] ……………………
            // nums2:                                            …………………… nums2[MidIndex-1] | nums2[MidIndex] ……………………
            //                                                                      <--afterMidIndex
            // beforeMidIndex 和 afterMidIndex移动缩小范围
            nums1MidIndex = beforeMidIndex + (afterMidIndex - beforeMidIndex) >> 1;
            nums2MidIndex = k - nums1MidIndex;
            // nums1 中的分界线划多了，要向左边移动
            if (nums1MidIndex > 0 && nums1[nums1MidIndex - 1] > nums2[nums2MidIndex]) {
                afterMidIndex = nums1MidIndex - 1;
            } else if (nums1MidIndex != m && nums1[nums1MidIndex] < nums2[nums2MidIndex - 1]) {
                // nums1中的分界线划少了，要向右边移动
                beforeMidIndex = nums1MidIndex + 1;
            } else {
                // 找到合适的划分了，需要输出最终结果了
                // 分为奇数偶数 2 种情况
                break;
            }
        }
        //奇偶数判断
        int midLeft, midRight = 0;
        if (nums1MidIndex == 0) {
            midLeft = nums2[nums2MidIndex - 1];
        } else if (nums2MidIndex == 0) {
            midLeft = nums1[nums1MidIndex - 1];
        } else {
            midLeft = Math.max(nums1[nums1MidIndex - 1], nums2[nums2MidIndex - 1]);
        }
        if (((m + n) & 1) == 1) {
            return midLeft;
        }
        if (nums1MidIndex == m) {
            midRight = nums2[nums2MidIndex];
        } else if (nums2MidIndex == n) {
            midRight = nums1[nums1MidIndex];
        } else {
            midRight = Math.min(nums1[nums1MidIndex], nums2[nums2MidIndex]);
        }
        return (midLeft + midRight) / 2;
    }

}
