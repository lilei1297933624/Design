package com.lyx.batch.config;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Test {

    public boolean checkOnesSegment(String s){
        int i = 0;
        for(int j = 0; j < s.length() ; j++){
            if(s.charAt(j) == '1' && i == 2){
                return false;
            }
            if(s.charAt(j) == '1' && i == 0){
                i = 1;
            }
            if(s.charAt(j) == '0' && i == 1){
                i =2;
            }
        }
        return true;
    }

    //921、使括号有效的最少添加
    public int minAddToMakeValid(String s){
        int ans = 0;
        int leftCount = 0;
        int len = s.length();
        for(int i = 0; i < len ; i++){
            char c = s.charAt(i);
            if(c == '('){
                leftCount++;
            }else {
                if(leftCount > 0){
                    leftCount--;
                }else {
                    ans++;
                }
            }
        }
        ans += leftCount;
        return ans;
    }

    //368、最大整除子集
    public List<Integer> largestDivisibleSubset(int[] nums){
        int len = nums.length;
        Arrays.sort(nums);

        int[] dp = new int[len];
        Arrays.fill(dp,1);
        int maxSize = 1,maxVal = dp[0];
        for(int i = 1; i < len ; i++){
            for(int j = 0 ; j < i ;j++){
                if(nums[i] % nums[j] == 0){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            if(dp[i] > maxSize){
                maxSize = dp[i];
                maxVal = nums[i];
            }
        }
        List<Integer> ans = new ArrayList<>();
        if(maxSize == 1){
            ans.add(nums[0]);
            return ans;
        }

        for(int i = len-1; i >= 0 && maxSize > 0;i--){
            if(dp[i] == maxSize && maxVal % nums[i] == 0){
                ans.add(nums[i]);
                maxVal = nums[i];
                maxSize--;
            }
        }
        return ans;
    }

    public List<String> subdomainVisits(String[] cpdomains){
        List<String> ans = new ArrayList<>();
        Map<String,Integer> counts = new HashMap<>();
        for(String cpdomain : cpdomains){
            int space = cpdomain.indexOf(' ');
            int count = Integer.parseInt(cpdomain.substring(0,space));
            String domain = cpdomain.substring(space+1);
            counts.put(domain,counts.getOrDefault(domain,0)+count);
            for(int i = 0; i < domain.length() ; i++){
                if(domain.charAt(i) == '.'){
                    String subdomain = domain.substring(i+1);
                    counts.put(subdomain,counts.getOrDefault(subdomain,0)+count);
                }
            }
        }

        for(Map.Entry<String,Integer> entry : counts.entrySet()){
            String subdomain = entry.getKey();
            int count = entry.getValue();
            ans.add(count + " "+subdomain);
        }
        return ans;
    }

    //1774、最接近目标价格的甜点成本
    int res;
    public int closestCost(int[] baseCosts,int[] toppingCosts,int target){
        res = Arrays.stream(baseCosts).min().getAsInt();
        for(int b : baseCosts){
            dfsCost(toppingCosts,0,b,target);
        }
        return res;
    }

    public void dfsCost(int[] toppingCosts,int p,int curCost,int target){
        if(Math.abs(res-target) < curCost-target){
            return;
        }else {
            if(Math.abs(res-target) > Math.abs(curCost-target) ){
                res = curCost;
            }else {
                res = Math.min(res,curCost);
            }
        }
        if(p == toppingCosts.length){
            return;
        }
        dfsCost(toppingCosts,p+1,curCost+toppingCosts[p]*2,target);
        dfsCost(toppingCosts,p+1,curCost+toppingCosts[p],target);
        dfsCost(toppingCosts,p+1,curCost,target);
    }

    //2027、转换字符串的最少操作次数
    public int minimumMoves(String s){
        int cur = -1, ans = 0;
        for(int i = 0; i < s.length() ; i++){
            if(s.charAt(i) == 'X' && i > cur){
                ans++;
                cur = i+2;
            }
        }
        return ans;
    }

    //1034、边界着色
//    public int[][] colorBorder(int[][] grid,int row,int col,int color){
//
//    }

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("5");
        list1.add("6");

        List<String> list2 = new ArrayList<String>();
        list2.add("2");
        list2.add("3");
        list2.add("7");
        list2.add("8");

        // 交集
        List<String> intersection = list1.stream().filter(item -> list2.contains(item)).collect(toList());
        System.out.println("---交集 intersection---");
        intersection.parallelStream().forEach(System.out :: println);

        // 差集 (list1 - list2)
        List<String> reduce1 = list1.stream().filter(item -> !list2.contains(item)).collect(toList());
        System.out.println("---差集 reduce1 (list1 - list2)---");
        reduce1.parallelStream().forEach(System.out :: println);

        // 差集 (list2 - list1)
        List<String> reduce2 = list2.stream().filter(item -> !list1.contains(item)).collect(toList());
        System.out.println("---差集 reduce2 (list2 - list1)---");
        reduce2.parallelStream().forEach(System.out :: println);

        // 并集
        List<String> listAll = list1.parallelStream().collect(toList());
        List<String> listAll2 = list2.parallelStream().collect(toList());
        listAll.addAll(listAll2);
        System.out.println("---并集 listAll---");
        listAll.parallelStream().forEachOrdered(System.out :: println);

        // 去重并集
        List<String> listAllDistinct = listAll.stream().distinct().collect(toList());
        System.out.println("---得到去重并集 listAllDistinct---");
        listAllDistinct.parallelStream().forEachOrdered(System.out :: println);

        System.out.println("---原来的List1---");
        list1.parallelStream().forEachOrdered(System.out :: println);
        System.out.println("---原来的List2---");
        list2.parallelStream().forEachOrdered(System.out :: println);

    }

    public boolean wordBreak(String s,List<String> dict){
        boolean[] dp = new boolean[s.length()+1];
        for(int i = 1; i <= s.length(); i++){
            for(int j = 0; j <= i; j++){
                if(i == j){
                    dp[i] = dict.contains(s.substring(0,i));
                }else {
                    dp[i] = dp[i-j] && dict.contains(s.substring(i-j,i));
                    if(dp[i]){
                        break;
                    }
                }
            }
        }
        return dp[s.length()];
    }

    public int change(String s,int left,int right){
        int count = 0;
        while(left < right){
            if(s.charAt(left++) != s.charAt(right--)){
                count++;
            }
        }
        return count;
    }

    public int palindromePartition(String s,int k){
        int len = s.length();
        int[][] dp = new int[len+1][k+1];
        for(int i = 0 ; i < dp.length ; i++){
            Arrays.fill(dp[i],len);
        }

        for(int i = 1; i <= len; i++){
            int min = Math.min(i,k);
            for(int j = 1; j <= min ;j++){
                if(j == 1){
                    dp[i][j] = change(s,0,i-1);
                }else {
                    for(int m = j-1; m < i ; m++){
                        dp[i][j] = Math.min(dp[i][j],dp[m][j-1]+change(s,m,i-1));
                    }
                }
            }
        }
        return dp[len][k];
    }

    public boolean isPalindrome(String s){
        int i = 0 , j = s.length()-1;
        while (i < j){
            if(s.charAt(i++) != s.charAt(j--)){
                return false;
            }
        }
        return true;
    }

    public boolean checkPartitioning(String s){
        for(int i = 0 ; i < s.length(); i++){
            for(int j = i+1; j < s.length()-1;j++){
                String s1 = s.substring(0,i+1);
                String s2 = s.substring(i+1,j);
                String s3 = s.substring(j+1,s.length());
                if(isPalindrome(s1) && isPalindrome(s2) && isPalindrome(s3)){
                    return true;
                }
            }
        }
        return false;
    }

    public int lastStoneWeightII(int[] stones){
        int len = stones.length;
        int sum = 0;
        for(int num : stones){
            sum += num;
        }

        int cap = sum >> 1;
        int[][] dp = new int[len+1][cap+1];

        for(int i = 1; i <= len ; i++){
            for(int j = 1; j <= cap ; j++){
                if(j >= stones[i-1]){
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-stones[i-1]] + stones[i-1]);
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return sum-2*dp[len][cap];
    }

    public int maxUncrossedLines(int[] nums1,int[] nums2){
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m+1][n+1];
        for(int i = 1; i <= m ; i++){
            for(int j = 1; j <= n ;j++){
                if(nums1[i-1] == nums2[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }

    public int helper(List<Integer> list){
        if(list.isEmpty()){
            return 0;
        }
        int max = 0, size = list.size();
        for(int i = 0; i < size ;i++){
            int sum = 0;
            if(size == 1){
                sum = list.get(i);
            }else if(size == 2){
                sum = list.get(0) * list.get(1);
            }else {
                if( i == 0){
                    sum = list.get(i) * list.get(i+1);
                }else if(i == size-1){
                    sum = list.get(i) * list.get(i-1);
                }else {
                    sum = list.get(i-1) *list.get(i)*list.get(i+1);
                }
            }
            Integer num = list.remove(i);
            max = Math.max(max,sum+helper(list));
            list.add(i,num);
        }
        return max;
    }

    public int maxCoins(int[] nums){
        List<Integer> list = new LinkedList<>();
        for(int num : nums){
            list.add(num);
        }
        return helper(list);
    }

    public int helper2(int[] tmp , int left,int right){
        if(left + 1 == right){
            return 0;
        }
        int res = 0;
        for(int i = left+1; i < right ; i++){
            int sum = tmp[left] * tmp[i] * tmp[right] + helper2(tmp,left,i)
                    +helper2(tmp,i,right);
            res = Math.max(res,sum);
        }
        return res;
    }




    public int minCut(String s){
        int len = s.length();
        int[] dp = new int[len+1];
        Arrays.fill(dp,len);
        for(int i = 0 ; i < len;i++){
            if(palindrome(s,0,i)){
                dp[i] = 0;
            }else {
                for(int j = 1; j <= i;j++){
                    if(palindrome(s,j,i)){
                        dp[i] = Math.min(dp[i],dp[j-1]+1);
                    }
                }
            }
        }
        return dp[len-1];
    }

    public boolean palindrome(String s,int left,int right){
        while (left < right){
            if(s.charAt(left++) != s.charAt(right--)){
                return false;
            }
        }
        return true;
    }

    public int maximalSquare(char[][] matrix){
        int height = matrix.length;
        int width = matrix[0].length;
        int[][] dp = new int[height+1][width+1];
        int maxWide = 0;
        for(int i = 1; i <= height ; i++){
            for(int j = 1; j <= width; j++){
                if(matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                    maxWide = Math.max(maxWide,dp[i][j]);
                }
            }
        }
        return maxWide*maxWide;
    }

    public int countSquares(int[][] matrix){
        int count = 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m+1][n+1];
        for(int i = 1 ; i <= m ; i++){
            for(int j = 1; j <= n;j++){
                if(matrix[i][j] == 0){
                    continue;
                }
                dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                count += dp[i+1][j+1];
            }
        }
        return count;
    }

    public int lenLongestFibSubseq(int[] A){
        int size = A.length;
        Set<Integer> set = new HashSet<>();
        for(int num : A){
            set.add(num);
        }
        int ans = 0;
        for(int i = 0; i < size ;i++){
            for(int j = i+1; j < size; j++){
                int first = A[i],second = A[j];
                int len = 2;
                while (set.contains(first+second)){
                    second = first+second;
                    first = second-first;
                    len++;
                }
                ans = Math.max(ans,len);
            }
        }
        return ans >= 3 ? ans : 0;
    }

    public int maxValue(int[][] grid){
        if(grid == null || grid.length == 0){
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m+1][n+1];
        for(int i = 1; i <= m ;i++){
            for(int j = 1; j <= n;j++){
                dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1])+grid[i-1][j-1];
            }
        }
        return dp[m][n];
    }

    public int countSubstrings(String s){
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        int ans = 0;
        for(int i = length-1; i >= 0;i++){
            for(int j = i; j < length; j++){
                if(s.charAt(i) != s.charAt(j)){
                    continue;
                }
                dp[i][j] = j-i <=2 || dp[i+1][j-1];
                if(dp[i][j]){
                    ans++;
                }
            }
        }
        return ans;
    }

    public int longestPalindromeSubseq(String s){
        int len = s.length();
        int[][] dp = new int[len][len];
        for(int i = len-1; i >= 0 ; i--){
            dp[i][i] = 1;
            for(int j = i+1; j < len; j++){
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][len-1];
    }

    public int lengthOfLIS(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int max = 1;
        for(int i = 1; i < nums.length ; i++){
            for(int j = 0; j < i;j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                    max = Math.max(max,dp[i]);
                }
            }
        }
        return max;
    }

    public int maxEnvelopes(int[][] envelops){
        if(envelops == null || envelops.length == 0){
            return 0;
        }
        Arrays.sort(envelops,(int[] arr1,int[] arr2) -> {
            if(arr1[0] == arr2[0]){
                return arr2[1]-arr2[0];
            }else {
                return arr1[0] - arr2[0];
            }
        });
        return lengthLIS(envelops);
    }

    public int lengthLIS(int[][] nums){
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int max = 1;
        for(int i = 1; i < nums.length;i++){
            for(int j = 0; j < i; j++){
                if(nums[i][1] > nums[j][1]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    public boolean isPalindrome(String s,int start,int end){
        while (start < end){
            if(s.charAt(start++) != s.charAt(end--)){
                return false;
            }
        }
        return true;
    }

    public String longestPalindrome(String s){
        if(s.length() < 2){
            return s;
        }
        int start = 0,maxLen = 0;
        for(int i = 0 ; i < s.length()-1; i++){
            for(int j = i+1; j < s.length() ; j++){
                if(isPalindrome(s,i,j)){
                    if(maxLen < j-i+1){
                        maxLen = j-i+1;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start,start+maxLen);
    }

    public int maxProfit(int[] prices,int fee){
        if(prices == null || prices.length < 2){
            return 0;
        }
        int len = prices.length;
        int[][] dp = new int[len][2];
        dp[0][1] = -prices[0];
        dp[0][0] = 0;
        for(int i = 1; i < len ; i++){
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]-fee);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
        }
        return dp[len-1][0];
    }

    public int[] robHelper(TreeNode root){
        if(root == null){
            return new int[2];
        }
        int[] left = robHelper(root.left);
        int[] right = robHelper(root.right);
        return new int[]{Math.max(left[0],left[1])+Math.max(right[0],right[1]),
        root.val+left[0]+right[0]};
    }

    public int rob(TreeNode root){
        int[] robHelp = robHelper(root);
        return Math.max(robHelp[1],robHelp[0]);
    }

    public int maxProfit(int[] prices){
        if(prices == null || prices.length < 2){
            return 0;
        }
        int length = prices.length;
        int[][] dp = new int[length][2];
        dp[0][1] = -prices[0];
        dp[0][0] = 0;
        for(int i = 1; i < length ; i++){
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
        }
        return dp[length-1][0];
    }

    public int maxProfit2(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        int length = prices.length;
        int hold = -prices[0], noHold = 0;
        for(int i = 1; i < length ;i++){
            noHold = Math.max(noHold,hold+prices[i]);
            hold = Math.max(hold,-prices[i]);
        }
        return noHold;
    }

    public int maxProfit3(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        int maxPro = 0, min = prices[0];
        for(int i = 1; i < prices.length ;i++){
            min = Math.min(min,prices[i]);
            maxPro = Math.max(prices[i]-min,maxPro);
        }
        return maxPro;
    }

    public int maxProfit4(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(prices[0]);
        int max = 0;
        for(int i = 1; i < prices.length ;i++){
            if(stack.peek() > prices[i]){
                stack.pop();
                stack.push(prices[i]);
            }else {
                max = Math.max(max,prices[i]-stack.peek());
            }
        }
        return max;
    }

    public int maxProfit5(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        int maxPro = 0;
        for(int i = 0; i < prices.length ; i++){
            for(int j = i+1; j < prices.length ;j++){
                maxPro = Math.max(maxPro,prices[j] - prices[i]);
            }
        }
        return maxPro;
    }

    public int maxSubArray(int[] num){
        int length = num.length;
        int[] dp = new int[length];
        dp[0] = num[0];
        int max = dp[0];
        for(int i = 1; i < length ; i++){
            dp[i] = Math.max(dp[i-1],0)+num[i];
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    public int massage(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for(int i = 1; i < len ; i++){
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]);
            dp[i][1] = dp[i-1][0] + nums[i];
        }
        return Math.max(dp[len-1][0],dp[len-1][1]);
    }

    public int minimumTotal(List<List<Integer>> triangle,int line,int row, int total){
        if(line >= total || row >= total){
            return 0;
        }
        int left = minimumTotal(triangle,line+1,row,total);
        int right = minimumTotal(triangle,line+1,row+1,total);
        return triangle.get(line).get(row)+ (left > right ? right : left);

    }

    public int minimumTotal2(List<List<Integer>> triangle){
        int len = triangle.size();
        int[][] dp = new int[len+1][len+1];
        for(int i = len-1; i >= 0 ; i--){
            for(int j = 0 ; j < triangle.get(i).size() ; j++){
                dp[i][j] = Math.min(dp[i+1][j],dp[i+1][j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }




}

