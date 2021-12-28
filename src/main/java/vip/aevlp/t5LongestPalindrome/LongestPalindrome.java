package vip.aevlp.t5LongestPalindrome;

import java.util.Arrays;

public class LongestPalindrome {

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len == 0 || len == 1) {
            return s;
        }
        //定义二维数组存储
        int[][] dp = new int[len][len];
        //回文字符串开始的位置
        int start = 0;
        //回文字符串最大长度
        int max = 1;
        //将长度为1和长度为2情况的回文字符串先确定下来 无非就是a  aa  这种没有其他情况
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
            if (i < len - 1 && s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = 1;
                start = i;
                max = 2;
            }
        }

        //长度为3及以上情况
        for (int i = 3; i <= len; i++) {
            for (int j = 0; j < len - i + 1; j++) {
                //子串结束的位置
                int k = j + i - 1;
                if (s.charAt(j) == s.charAt(k) && dp[j + 1][k - 1] == 1) {
                    //状态转移
                    dp[j][k] = 1;
                    start = j;
                    max = i;
                }
            }
        }
        return s.substring(start, start + max);
    }

    public String longestPalindrome2(String s) {
        if (s.length() < 2) {
            return s;
        }
        char[] chars = s.toCharArray();

        char[] news = new char[2 * chars.length + 1];
        news[0] = '#';
        System.err.println(Arrays.toString(chars));
        for (int i = 1; i < (news.length + 1) / 2; i++) {
            news[2 * i - 1] = chars[i - 1];
            news[2 * i] = '#';
        }
        System.err.println(Arrays.toString(news));
        //以预处理字符串下标 i 为中⼼的回⽂半径(奇数⻓度时不包括中⼼)
        int[] dp = new int[news.length];
        //通过中⼼扩散的⽅式能够扩散的最右边的下标
        int maxRight = 0;
        //与 maxRight 对应的中⼼字符的下标
        int center = 0;
        //记录最⻓回⽂串的半径
        int maxLen = 1;
        //记录最⻓回⽂串在起始串 s 中的起始下标
        int begin = 0;

        int left = 0;
        int right = 0;
        for (int i = 0; i < news.length; i++) {
            if (i < maxRight) {
                dp[i] = Math.min(maxRight - i, dp[2 * center - i]);
            }
            //中心扩散法更新dp[i]
            left = i - (1 + dp[i]);
            right = i + (1 + dp[i]);
            while (left >= 0 && right < news.length && news[left] == news[right]) {
                dp[i]++;
                left--;
                right++;
            }
            //更新maxRight,它是遍历过的i的i+dp[i]的最大者
            if (i + dp[i] > maxRight) {
                maxRight = i + dp[i];
                center = i;
            }
            //记录最长回文字符串的长度和相应它在原始字符串中的起点
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                //这里要除以2因为我们有插入的辅助字符#
                begin = (i - maxLen) / 2;
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    public static void main(String[] args) {
        LongestPalindrome palindrome = new LongestPalindrome();
        System.out.println(palindrome.longestPalindrome2("bb"));
    }

}
