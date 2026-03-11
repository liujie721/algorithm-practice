给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。

 

示例 1:

输入: s = "cbaebabacd", p = "abc"
输出: [0,6]
解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 示例 2:

输入: s = "abab", p = "ab"
输出: [0,1,2]
解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 

提示:

1 <= s.length, p.length <= 3 * 104
s 和 p 仅包含小写字母

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        char[] s1 = s.toCharArray();
        char[] p1 = p.toCharArray();
        
        // 修正1：char数组获取长度用length（而非size()）
        int len1 = s1.length;
        int len2 = p1.length;
        
        // 修正2：频率统计改用int数组（放弃错误的List初始化）
        int[] m1 = new int[26], m2 = new int[26];
        
        // 补充：边界保护（s比p短直接返回）
        if (len1 < len2) return ans;
        
        // 统计p的字符频率（原逻辑保留，仅变量适配）
        for (char a : p1) m1[a - 'a']++;
        
        // 初始化窗口（统计s前len2个字符频率）
        for (int i = 0; i < len2; i++) m2[s1[i] - 'a']++;
        
        // 补充：检查初始窗口是否匹配（避免漏索引0）
        if (isEqual(m1, m2)) ans.add(0);
        
        // 滑动窗口逻辑
        for (int i = len2; i < len1; i++) {
            // 修正3：修改m2（s窗口频率）而非m1（p模板）
            m2[s1[i - len2] - 'a']--;
            m2[s1[i] - 'a']++;
            
            // 修正4：数组对比用自定义方法（而非直接==），添加元素用add()
            if (isEqual(m1, m2)) ans.add(i - len2 + 1);
        }
        return ans;
    }
    
    // 补充：数组逐元素对比的辅助方法
    private boolean isEqual(int[] arr1, int[] arr2) {
        for (int i = 0; i < 26; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }
}
