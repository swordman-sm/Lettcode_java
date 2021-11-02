package vip.aevlp.t3LengthOfLongestSubstring;

import java.util.HashMap;

public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        //字符及对应的index存储在hash中
        HashMap<Character, Integer> map = new HashMap<>();
        int length = 0;
        for (int start = 0, end = 0; end < chars.length; end++) {
            //滑动窗口移动end位
            char ce = chars[end];
            //char map中查找到end位相同的字符则将start位进行变更
            if (map.containsKey(ce)) {
                //取index位最大的值为start
                start = Math.max(map.get(ce), start);
            }
            //循环获取最大的length
            length = Math.max(length, end - start + 1);
            //map中不存在end位字符存储并进行下轮查找
            map.put(ce, end);
        }
        return length;
    }

}
