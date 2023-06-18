package com.lyx.batch.config;

import java.util.*;

public class Leetcode {

    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] ans = new int[queries.length];
        int[] wordsMap = new int[words.length];
        Map<Character,Integer> map = new TreeMap<>();
        for(int i = 0 ; i < words.length ; i++){
            String str = words[i];
            map.clear();
            for(int j = 0 ; j < str.length() ; j++ ){
                map.put(str.charAt(j),map.getOrDefault(str.charAt(j),0)+1);
            }
            int len = new ArrayList<>(map.entrySet()).get(0).getValue();
            wordsMap[i] = len;
        }

        for(int i = 0 ; i < queries.length ; i++){
            String str = queries[i];
            map.clear();
            for(int j = 0 ; j < str.length() ; j++ ){
                map.put(str.charAt(j),map.getOrDefault(str.charAt(j),0)+1);
            }
            int len = new ArrayList<>(map.entrySet()).get(0).getValue();
            int curr = 0;
            for(int order : wordsMap){
                if(len < order){
                    curr++;
                }
            }
            ans[i] = curr;
        }
        return ans;
    }

    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        Map<Integer,ListNode> seen = new HashMap<>();
        int cur = 0;
        for (ListNode node = dummy; node != null; node = node.next){
            cur += dummy.val;
            seen.put(cur,node);
        }
        cur = 0;
        for(ListNode node = dummy ; node != null ; node = node.next){
            cur += node.val;
            node.next = seen.get(cur).next;
        }
        return dummy.next;
    }
}

