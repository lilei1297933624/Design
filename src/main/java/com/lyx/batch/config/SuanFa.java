package com.lyx.batch.config;

import org.omg.CORBA.INTERNAL;
import org.springframework.util.StringUtils;

import java.util.*;

public class SuanFa {

    public void preOrder(TreeNode tree){
        if(tree == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(tree);
        while (!stack.isEmpty()){
            TreeNode t1 = stack.pop();
            System.out.println(t1.val);
            if(t1.right != null){
                stack.push(t1.right);
            }
            if(t1.left != null ){
                stack.push(t1.left);
            }
        }
    }

    public void inOrderTraversal(TreeNode tree){
        Stack<TreeNode> stack = new Stack<>();
        while (tree != null || !stack.isEmpty()){
            while (tree != null){
                stack.push(tree);
                tree = tree.left;
            }
            if(!stack.isEmpty()){
                tree = stack.pop();
                System.out.println(tree.val);
                tree = tree.right;
            }
        }
    }

    public void postOrder(TreeNode tree){
        if(tree == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(tree);
        TreeNode c;
        while (!stack.isEmpty()){
            c = stack.peek();
            if(c.left != null && tree != c.left && tree != c.right){
                stack.push(c.left);
            }else if(c.right != null && tree != c.right){
                stack.push(c.right);
            }else {
                System.out.println(stack.pop().val);
                tree = c;
            }
        }
    }

    public void swap(int[] A,int i,int j){
        if(i != j){
            A[i] ^= A[j];
            A[j] ^= A[i];
            A[i] ^= A[j];
        }
    }

    public void bubbleSort(int [] array){
        int length = array.length;
        for(int i = 0; i < length ;i++){
            for(int j = i+1; j < length ;j++){
                if(array[j] < array[i]){
                    swap(array,i,j);
                }
            }
        }
    }

    public void bubble2(int [] array){
        int n = array.length;
        for(int i =1; i < n ; i++){
            for(int j = 0 ; j < n-i ;j++){
                if(array[j] > array[j+1]){
                    swap(array,j,j+1);
                }
            }
        }
    }

    public void selectSort(int[] array){
        for(int i = 0 ; i < array.length ;i++){
            int index = i;
            for(int j = i+1 ; j < array.length ;j++){
                if(array[index] > array[j]){
                    index = j;
                }
            }
            if( i != index){
                swap(array,i,index);
            }
        }
    }

    public void quickSort(int[] array,int start,int end){
        if(start < end){
            int key = array[start];
            int i = start;
            for(int j = start + 1; j <= end ;j++){
                if(key > array[j]){
                    swap(array,j,++i);
                }
            }
            array[start] = array[i];
            array[i] = key;
            quickSort(array,start,i-1);
            quickSort(array,i+1,end);
        }
    }

    public void maxHeapfy(int[] array,int i,int heapSize){
        int left = i * 2 +1;
        int right = i * 2 + 2;
        int largest = i;
        if(left < heapSize && array[left] > array[largest]){
            largest = left;
        }
        if(left < heapSize && array[right] > array[largest]){
            largest = right;
        }
        if(largest != i){
            swap(array,largest,i);
            maxHeapfy(array,largest,heapSize);
        }
    }

    public void buildMaxHeap(int[] array,int heapSize){
        for(int i = (heapSize - 2) >> 1; i >= 0 ; i--){
            maxHeapfy(array,i,heapSize);
        }
    }

    //577、数组中的最长连续子序列
    public int MLS(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int longest = 1,count = 1;
        Arrays.sort(arr);
        for(int i = 1; i < arr.length ;i++){
            if(arr[i] == arr[i-1]){
                continue;
            }
            if((arr[i] - arr[i-1]) == 1){
                count++;
            }else {
                count = 1;
            }
            longest = Math.max(longest,count);
        }
        return longest;
    }

    public int MLS2(int[] arr){
        Set<Integer> set = new HashSet<>();
        for(int num : arr){
            set.add(num);
        }
        int longest = 0;
        for(int num : arr){
            if(set.contains(num-1)){
                continue;
            }
            int currentNum = num;
            int count = 1;
            while (set.contains(currentNum+1)){
                currentNum++;
                count++;
            }
            longest = Math.max(longest,count);
        }
        return longest;
    }

    //571、山峰数组的峰顶索引
    public int peakIndexInMountainArray(int[] arr){
        for(int i = 0; i < arr.length-1 ; i++){
            if(arr[i] > arr[i+1]){
                return i;
            }
        }
        return 0;
    }

    public int peakIndexInMountainArray2(int[] arr){
        int left = 0, right = arr.length-1;
        while (left < right){
            int mid = left + (right-left) /2;
            if(arr[mid] < arr[mid+1]){
                left = mid+1;
            }else {
                right = mid;
            }
        }
        return left;
    }

    //569、多种方式解4的幂
    public boolean isPowerOfFour(int num){
        if(num <= 0)
            return false;
        if(num == 1)
            return true;
        if(num % 4 != 0)
            return false;
        return isPowerOfFour(num / 4);
    }

    //567、最后一块石头的重量
    public int lastStoneWeight(int[] stones){
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);
        for(int num : stones){
            pq.offer(num);
        }
        while (pq.size() > 1){
            int largest = pq.poll();
            int large = pq.poll();
            if(largest > large){
                pq.offer(largest-large);
            }
        }
        return pq.isEmpty() ?0 : pq.poll();
    }

    //562、数组中的最长山脉
    public int longestMountain(int[] A){
        int length = A.length;
        int[] up = new int[length];
        for(int i = 1; i < length ;i++){
            if(A[i] > A[i-1]){
                up[i] = up[i-1] +1;
            }
        }
        int[] down = new int[length];
        for(int i = length-1; i > 0 ; i--){
            if(A[i-1] > A[i]){
                down[i-1] = down[i] + 1;
            }
        }
        int max = 0;
        for(int i = 0 ; i < length ; i++){
            if(up[i] == 0 || down[i] == 0){
                continue;
            }
            max = Math.max(max,up[i]+down[i]+1);
        }
        return max;
    }

    //558、最长回文串
    public int longestPalindrome(String s){
        int[] map = new int[256];
        for(char ch : s.toCharArray()){
            map[ch]++;
        }
        int res = 0 ,  mask = -2;
        for(int count : map){
            res += count & mask;
        }
        return res < s.length() ? res+1 : res;
    }

    //550、旋转图像
    public void rotate(int[][] matrix){
        int length = matrix.length;
        for(int i = 0 ; i < length / 2;i++){
            int temp[] = matrix[i];
            matrix[i] = matrix[length-i-1];
            matrix[length-i-1] = temp;
        }

        for(int i = 0 ; i < length ; i++){
            for(int j = i+1; j < length ;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    //546、砖墙，哈希表解决
    public int leastBricks(List<List<Integer>> wall){
        Map<Integer,Integer> map = new HashMap<>();
        int maxGap = 0;
        for(List<Integer> row : wall){
            int gap = 0;
            for(int i = 0; i < row.size()-1;i++){
                gap += row.get(i);
                map.put(gap,map.getOrDefault(gap,0)+1);
                maxGap = Math.max(maxGap,map.get(gap));
            }
        }
        return wall.size() - maxGap;
    }

    //541、字符串压缩
    public String compressString(String s){
        if(s == null || s.length() == 0){
            return s;
        }
        StringBuffer sb = new StringBuffer();
        char curChar = s.charAt(0);
        int curCharCount = 1;
        for(int i = 1; i < s.length() ;i++){
            if(s.charAt(i) == curChar){
                curCharCount++;
                continue;
            }
            sb.append(curChar).append(curCharCount);
            curChar = s.charAt(i);
            curCharCount = 1;
        }
        sb.append(curChar).append(curCharCount);
        return sb.length() >= s.length() ? s : sb.toString();
    }

    //536、剑指Offer-构建乘积数组
    public int[] constructArr(int[] a){
        if(a == null || a.length == 0){
            return a;
        }
        int length = a.length;
        int [] resLeft = new int[length];
        int [] resRight = new int[length];
        resLeft[0] = 1;
        resRight[length-1] = 1;

        for(int i = 1; i < length ;i++){
            resLeft[i] = resLeft[i-1] * a[i-1];
        }
        for(int i = length-2 ; i >= 0 ; i--){
            resRight[i] = resRight[i+1] * a[i+1];
        }
        int[] res = new int[length];
        for(int i = 0; i < length ; i++){
            res[i] = resLeft[i] * resRight[i];
        }
        return res;
    }

    //535、扑克牌中的顺子
    public boolean isStraight(int[] nums){
        Arrays.sort(nums);
        int zero = 0;
        for(int i = 0 ; i < 5 ; i++){
            if(nums[i] == 0){
                zero++;
                continue;
            }
            if(i != 0 && nums[i] == nums[i-1]){
                return false;
            }
        }
        return nums[nums.length-1] - nums[zero] <= 4;
    }
    //533、最小的k个数
    public int[] getLeastNumbers(int[] arr,int k){
        if(k == 0){
            return new int[0];
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((num1,num2) -> num2-num1);
        for(int i =0; i < k ; i++){
            queue.offer(arr[i]);
        }

        for(int i = k ; i < arr.length ; i++){
            if(queue.peek() > arr[i]){
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        int[] res = new int[k];
        for(int i = 0; i < k;i++){
            res[i] = queue.poll();
        }
        return res;
    }

    //525、最富有客户的资产总量
    public int maximumWealth(int[][] accounts){
        int max = 0;
        for(int i = 0 ; i < accounts.length ; i++){
            int temp = 0;
            for(int j = 0 ; j < accounts[i].length ;j++){
                temp += accounts[i][j];
            }
            max = Math.max(max,temp);
        }
        return max;
    }

    //524、爱生气的书店老板
    public int maxSatisfied(int[] customers,int[] grumpy,int x){
        int satisfied = 0;
        int length = grumpy.length;
        int[] noSatisfied = new int[length];
        for(int i = 0; i < length;i++){
            if(grumpy[i] == 0){
                satisfied += customers[i];
            }else {
                noSatisfied[i] = customers[i];
            }
        }
        int left = 0,right = 0,max = 0,sum = 0;
        for(; right < left; right++){
            sum += noSatisfied[right];
            if(right-left >= x){
                sum -= noSatisfied[left++];
            }
            max = Math.max(max,sum);
        }
        return satisfied + max;
    }

    //521、滑动窗口解最大连续1的个数3
    public int longestOnes(int[] A,int k){
        int left = 0, maxWindow = 0, zeroCount = 0;
        for(int right = 0; right < A.length; right++){
            if(A[right] == 0){
                zeroCount++;
            }
            while (zeroCount > k){
                if(A[left++] == 0){
                    zeroCount--;
                }
            }
            maxWindow = Math.max(maxWindow,right-left+1);
        }
        return maxWindow;
    }

    //518、托普利茨矩阵
    public boolean isToeplitzMatrix(int[][] matrix){
        int row = matrix.length-1,column = matrix[0].length-1;
        for(int i = 0 ; i < row ; i++){
            for(int j =0 ; j < column; j++){
                if(matrix[i][j] != matrix[i+1][j+1]){
                    return false;
                }
            }
        }
        return true;
    }

    //511、独一无二的出现次数
    public boolean uniqueOccurrences(int[] arr){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i < arr.length ; i++){
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
        }
        return map.size() == new HashSet<>(map.values()).size();
    }

    //509、数组中的第k个最大元素
    public int findKthLargest(int[] nums,int k){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int val : nums){
            queue.add(val);
            if(queue.size() > k){
                queue.poll();
            }
        }
        return queue.peek();
    }

    //506、无重叠区间
    public int eraseOverlapIntervals(int[][] intervals){
        if(intervals.length == 0){
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int end = intervals[0][1];
        int count = 0;
        for(int i = 1; i < intervals.length;i++){
            if(intervals[i][0] < end){
                end = Math.min(end,intervals[i][1]);
                count++;
            }else {
                end = intervals[i][1];
            }
        }
        return count;
    }

    //504、旋转数组的3种解决方式
    public void rotate(int[] nums,int k){
        int length = nums.length;
        int[] temp = new int[length];
        for(int i = 0 ; i < length ;i++){
            temp[i] = nums[i];
        }
        for(int i = 0; i < length;i++){
            nums[(i+k) % length] = temp[i];
        }
    }

    //496、字符串中的第一个唯一字符
    public int firstUniqChar(String s){
        for(int i =0; i < s.length() ;i++){
            if(s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i)))
                return i;
        }
        return -1;
    }

    //487、重构字符串
    public String reorganizeString(String s){
        char[] alphaArr = s.toCharArray();
        int[] alphaCount = new int[26];
        int length = s.length(),max = 0,alpha = 0,threshold = (length+1) >> 1;
        for(int i = 0 ; i < length ; i++){
            alphaCount[alphaArr[i] - 'a']++;
            if(alphaCount[alphaArr[i]-'a'] > max){
                max = alphaArr[i]-'a';
                if(max > threshold){
                    return "";
                }
            }
        }
        char[] res = new char[length];
        int index = 0;
        while (alphaCount[alpha]-- > 0){
            res[index] = (char) (alpha+'a');
            index += 2;
        }
        for(int i = 0; i < alphaCount.length;i++){
            while (alphaCount[i]-- > 0){
                if(index >= res.length){
                    index = 1;
                }
                res[index] = (char) (i+'a');
                index += 2;
            }
        }
        return new String(res);
    }

    //484、打家劫舍2
    public int rob(int [] nums){
        if(nums.length == 1){
            return nums[0];
        }
        int robFirst = robHelper(nums,0,nums.length-2);
        int robLast = robHelper(nums,1,nums.length-1);
        return Math.max(robFirst,robLast);
    }

    public int robHelper(int[] num,int start,int end){
        int steal = 0,noSteal = 0;
        for(int i = start;i <= end;i++){
            int temp = steal;
            steal = noSteal + num[i];
            noSteal = Math.max(noSteal,temp);
        }
        return Math.max(steal,noSteal);
    }

    //482、上升下降字符串
    public String sortString(String s){
        int[] bucket = new int[26];
        char[] charArr = s.toCharArray();
        for(char c : charArr){
            bucket[c-'a']++;
        }
        char[] res = new char[s.length()];
        int index = 0;
        while (index < s.length()){
            for(int i =0; i < 26; i++){
                if(bucket[i] != 0){
                    res[index++] = (char)(i+'a');
                    bucket[i]--;
                }
            }

            for(int i = 25; i>= 0;i--){
                if(bucket[i] != 0){
                    res[index++] = (char)(i+'a');
                    bucket[i]--;
                }
            }
        }
        return new String(res);
    }

    //481、用最少数量的箭引爆气球
    public int findMinArrowShots(int[][] points){
        if(points == null || points.length == 0){
            return 0;
        }
        Arrays.sort(points,(a,b)->a[1]-b[1]);
        int last = points[0][1];
        int count = 1;
        for(int i = 1; i < points.length; i++){
            if(last < points[i][0]){
                last = points[i][1];
                count++;
            }
        }
        return count;
    }

    //480、移动零到数组末尾
    public void moveZeros(int[] nums){
        if(nums == null || nums.length == 0){
            return;
        }
        int index = 0;
        for(int i = 0; i < nums.length;i++){
            if(nums[i] != 0){
                nums[index++] = nums[i];
            }
        }
        while (index < nums.length){
            nums[index++] = 0;
        }
    }

    //475、有效的山脉数组
    public boolean validMountainArray(int[] A){
        int len = A.length,left = 0, right = len-1;
        while (left+1 < left && A[left] < A[left+1])
            left++;
        while (right > 0 && A[right-1] > A[right])
            right--;
        return left > 0 && right < len-1 && left == right;
    }

    //472、插入区间
    public int[][] insert(int[][] intervals,int[] newInterval){
        if(intervals.length == 0){
            return new int[][]{newInterval};
        }
        List<int[]> list = new ArrayList<>();
        int left = 0,right = intervals.length-1;
        while (left < intervals.length && intervals[left][1] < newInterval[0]){
            list.add(intervals[left++]);
        }
        while (right >= 0 && intervals[right][0] > newInterval[1]){
            list.add(left,intervals[right--]);
        }

        int[] newArr = new int[2];
        newArr[0] = left >= intervals.length ? newInterval[0] : Math.min(intervals[left][0],newInterval[0]);
        newArr[1] = right < 0 ? newInterval[1] : Math.max(intervals[right][1],newInterval[1]);
        list.add(left,newArr);
        int[][] res = new int[list.size()][2];
        for(int i =0; i < list.size();i++){
            res[i] = list.get(i);
        }
        return res;
    }

    //468、提莫攻击的两种解决方式
    public int findPoisonedDuration(int[] timeSeries,int duration){
        if(timeSeries.length == 0 || duration ==0){
            return 0;
        }
        int res = duration;
        for(int i = 1; i < timeSeries.length;i++){
            res += Math.min(timeSeries[i]-timeSeries[i-1],duration);
        }
        return res;
    }

    //467、递归和非递归解路劲总和问题
    public boolean hasPathSum(TreeNode root,int sum){
        if(root == null)
            return false;
        if(root.left == null && root.right == null && sum-root.val == 0){
            return true;
        }
        return hasPathSum(root.left,sum-root.val) || hasPathSum(root.right,sum-root.val);
    }

    //454、字母异位词分组
    public List<List<String>> groupAnagrams(String[] strs){
        if(strs == null || strs.length == 0){
            return new ArrayList<>();
        }
        Map<String,List<String>> map = new HashMap<>();
        for(int i = 0 ; i < strs.length ;i++){
            char[] c = strs[i].toCharArray();
            Arrays.sort(c);
            String key = String.valueOf(c);
            if(!map.containsKey(key)){
                map.put(key,new ArrayList<>());
            }
            map.get(key).add(strs[i]);
        }
        return new ArrayList<>(map.values());
    }

    //452、跳跃游戏
    public boolean canJump(int[] nums){
        int maxStep = 0,length = nums.length;
        for(int i = 0 ; i < length ;i++){
            if(i > maxStep){
                return false;
            }
            maxStep = Math.max(maxStep,i+nums[i]);
            if(maxStep >= length){
                return true;
            }
        }
        return true;
    }

    //443、滑动窗口最大值
    public int[] maxSlidingWindow(int[] nums,int k){
        if(nums == null || nums.length == 0){
            return new int[0];
        }
        int [] res = new int[nums.length-k+1];
        for(int i = 0 ; i < res.length ;i++){
            int max = nums[i];
            for(int j = 1; j < k;j++){
                max = Math.max(max,nums[i+j]);
            }
            res[i] = max;
        }
        return res;
    }

    //463、顺时针打印矩阵
    public int[] spiralOrder(int[][] matrix){
        if(matrix == null || matrix.length == 0)
            return new int[0];
        int m = matrix.length, n = matrix[0].length;
        int[] res = new int[m*n];
        int up = 0,down = m-1,left = 0,right = n-1,index = 0;
        while (true){
            for(int col = left; col <= right; col++){
                res[index++] = matrix[up][col];
            }
            if(++up > down){
                break;
            }

            for(int row = up; row <= down;row++){
                res[index++] = matrix[row][right];
            }
            if(--right < left){
                break;
            }

            for(int col = right; col >= left;col--){
                res[index++] = matrix[down][col];
            }
            if(--down < up){
                break;
            }
            for(int row = down ; row >= up;row--){
                res[index++] = matrix[row][left];
            }
            if(++left > right){
                break;
            }
        }
        return res;
    }

    //428、打印从1到最大的n位数
    public int[] printNumbers(int n){
        int size = (int) (Math.pow(10,n)-1);
        int[] res = new int[size];
        for(int i = 0; i < size;i++){
            res[i] = i+1;
        }
        return res;
    }

    //427、数值的整数次方
    public double myPow(double x,int n){
        if(n == 0)
            return 1;
        if(n < 0)
            return myPow(1/x,-n);
        return (n%2 == 0) ? myPow(x*x,n/2) : x*myPow(x*x,n/2);
    }

    //424、剪绳子
    public int cuttingRope(int n){
        if(n == 2 || n == 3){
            return n-1;
        }
        int res = 1;
        while (n > 4){
            n = n-3;
            res *= 3;
        }
        return n * res;
    }

    //419、旋转数组的最小数字
    public  int minArray(int[] numbers){
        int left = 0,right = numbers.length-1;
        while (left < right){
            int mid = left + (right-left) /2;
            if(numbers[mid] > numbers[right]){
                left = mid+1;
            }else if(numbers[mid] < numbers[right]){
                right = mid;
            }else {
                right--;
            }
        }
        return numbers[left];
    }

    //418、斐波那契数列
    public int fib(int n){
        int constant = 1000000007,first = 0,second = 1;
        while (n-- > 0){
            int temp = first + second;
            first = second % constant;
            second = temp % constant;
        }
        return first;
    }

    //415、最佳观光组合
    public int maxScoreSightseeingPair(int[] A){
        int res = 0,cur = A[0] + 0;
        for(int j = 1; j < A.length;j++){
            res = Math.max(res,cur+A[j]-j);
            cur = Math.max(cur,A[j]+j);
        }
        return res;
    }

    //412、判断子序列
    public boolean isSubsequence(String s,String t){
        if(s.length() == 0){
            return true;
        }
        int indexS = 0,indexT = 0;
        while (indexT < t.length()){
            if(t.charAt(indexT) == s.charAt(indexS)){
                indexS++;
                if(indexS == s.length()){
                    return true;
                }
            }
            indexT++;
        }
        return false;
    }

    public boolean isSubsequence2(String s,String t){
        if(s.length() == 0)
            return true;
        boolean[][] dp = new boolean[s.length()+1][t.length()+1];
        for(int i = 0 ; i < t.length();i++){
            dp[0][i] = true;
        }
        for(int i = 1; i <= s.length();i++){
            for(int j = 1; j <= t.length();j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        return dp[s.length()][t.length()];
    }

    //408、替换空格
    public String replaceSpace(String s){
        return s.replace(" ","%20");
    }

    //406、二维数组中的查找
    public boolean findNumberIn2DArray(int[][] matrix,int target){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int row = matrix.length-1,col = matrix[0].length;
        int column = 0;
        while (row >= 0 && column < col){
            int num = matrix[row][column];
            if(num == target){
                return true;
            }else if(num > target){
                row--;
            }else {
                column++;
            }
        }
        return false;

    }

    //405、换酒问题
    public int numWaterBottles(int numBottles,int numExchange){
        return (numBottles*numExchange-1) / (numExchange-1);
    }

    //404、数组中重复的数字
    public int findRepeatNumber(int[] nums){
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            if(!set.add(num)){
                return num;
            }
        }
        return -1;
    }

    //393、括号生成
    public List<String> generateParenthesis(int n){
        List<String> res= new ArrayList<>();
        dfs(res,n,n,"");
        return res;
    }

    public void dfs(List<String> res,int left,int right,String curStr){
        if(left == 0 && right == 0){
            res.add(curStr);
            return;
        }
        if(left < 0){
            return;
        }
        if(right < left){
            return;
        }
        dfs(res,left-1,right,curStr+"(");
        dfs(res,left,right-1,curStr+")");
    }

    //392、检查数组对是否可以被k整除
    public boolean canArrange(int[] arr,int k){
        int length = arr.length;
        boolean[] visit = new boolean[arr.length];
        for(int i = 0 ; i < length-1;i++){
            if(visit[i]){
                continue;
            }
            for(int j = i+1; j < length;j++){
                if(visit[j])
                    continue;
                if((arr[i]+arr[j] ) % k == 0){
                    visit[i] = visit[j] = true;
                    break;
                }
            }
            if(!visit[i])
                return false;
        }
        return true;
    }

    //390、长度最小的子数组
    public int minSubArrayLen(int s,int[] nums){
        int lo = 0,hi = 0,sum = 0,min = Integer.MAX_VALUE;
        while (hi < nums.length){
            sum += nums[hi++];
            while (sum >= s){
                min = Math.min(min,hi-lo);
                sum -= nums[lo++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    //384、整数反转
    public int reverse(int x){
        long res = 0;
        while (x != 0){
            res = res * 10 + x % 10;
            x /= 10;
        }
        return (int) res == res ? (int) res : 0;
    }

    //382、每日温度的5种解体思路
    public int[] dailyTemperatures(int[] T){
        int length = T.length;
        int [] res = new int[length];
        for(int i = 0 ; i < length; i++){
            for(int j = i+1; j < length;j++){
                if(T[j] > T[i]){
                    res[i] = j-i;
                    break;
                }
            }
        }
        return res;
    }

    //380、缺失的第一个正数
    public int firstMissingPositive(int[] nums){
        for(int i = 1; i <= nums.length;i++){
            boolean has = false;
            for(int j =0; j < nums.length;j++){
                if(nums[j] == i){
                    has = true;
                    break;
                }
            }
            if(!has){
                return i;
            }
        }
        return nums.length+1;
    }

    public int firstMissingPositive2(int[] nums){
        int len = nums.length;
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }
        for(int i = 1; i <= len;i++){
            if(!set.contains(i))
                return i;
        }
        return len+1;
    }

    //379、柱形图中最大的矩形
    public int largestRectangleArea(int[] heights){
        int length = heights.length;
        int area = 0;
        for(int left = 0; left < length; left++){
            int minHeight = Integer.MAX_VALUE;
            for(int right = left; right < length;right++){
                minHeight = Math.min(minHeight,heights[right]);
                area = Math.max(area,(right-left+1) * minHeight);
            }
        }
        return area;
    }

    public int largestRectangleArea2(int[] height){
        if(height == null || height.length == 0){
            return 0;
        }
        int[] leftLess = new int[height.length];
        int[] rightLess = new int[height.length];
        rightLess[height.length-1] = height.length;
        leftLess[0] = -1;
        for(int i = 1; i < height.length;i++){
            int p = i-1;
            while (p >= 0 && height[p] >= height[i]){
                p = leftLess[p];
            }
            leftLess[i] = p;
        }
        for(int i = height.length-2;i >= 0; i--){
            int p = i+1;
            while (p < height.length && height[p] >= height[i]){
                p = rightLess[p];
            }
            rightLess[i] = p;
        }
        int maxArea = 0;
        for(int i = 0 ; i < height.length;i++){
            maxArea = Math.max(maxArea,height[i] * (rightLess[i] - leftLess[i]-1));
        }
        return maxArea;
    }

    //377、调整数组顺序使奇数位于偶数前面
    public int[] exchange(int[] nums){
        if(nums == null || nums.length == 0){
            return nums;
        }
        int left = 0,right = nums.length-1;
        while (left < right){
            while (left < right && (nums[left] & 1) == 1){
                left++;
            }
            while (left < right && (nums[right] & 1) == 0){
                right--;
            }
            if(left < right){
                nums[left] ^= nums[right];
                nums[right] ^= nums[left];
                nums[left] ^= nums[right];
            }
        }
        return nums;
    }

    public int[] exchange2(int[] nums){
        int slow = 0 , fast = 0;
        while (fast < nums.length){
            if((nums[fast] & 1) == 1){
                if(slow != fast){
                    nums[slow] ^= nums[fast];
                    nums[fast] ^= nums[slow];
                    nums[slow] ^= nums[fast];
                }
                slow++;
            }
            fast++;
        }
        return nums;
    }

    //369、整数替换
    public int integerReplacement(int n){
        if(n == Integer.MAX_VALUE){
            return 32;
        }
        if(n <= 3){
            return n-1;
        }
        if(n % 2 == 0){
            return integerReplacement(n /2);
        }else {
            return Math.min(integerReplacement(n-1),integerReplacement(n+1))+1;
        }
    }

    //365、消除游戏
    public int lastRemaining(int n){
        boolean left = true;
        int remaining = n,step = 1,head = 1;
        while (remaining > 1){
            if(left || ((remaining & 1) == 1)){
                head = head + step;
            }
            remaining = remaining >> 1;
            step = step << 1;
            left =!left;
        }
        return head;
    }

    //360、等差数列划分
    public int numberOfArithmeticSlices(int[] A){
        int curr = 0, sum = 0;
        for(int i = 2; i < A.length ;i++){
            if(A[i] - A[i-1] == A[i-1] - A[i-2]){
                curr += 1;
                sum += curr;
            }else {
                curr = 0 ;
            }
        }
        return sum;
    }


    //358、移掉k位数字


    //355、两数相加
    public ListNode addTwoNumbers(ListNode list1,ListNode list2){
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (list1 != null){
            s1.push(list1.val);
            list1 = list1.next;
        }
        while (list2 != null){
            s2.push(list2.val);
            list2 = list2.next;
        }
        int sum = 0;
        ListNode head = new ListNode(0);
        while (!s1.empty() || !s2.empty()){
            if(!s1.empty())
                sum += s1.pop();
            if(!s2.empty()){
                sum += s2.pop();
            }
            head.val = sum % 10;
            ListNode node = new ListNode(sum / 10);
            node.next = head;
            head = node;
            sum /= 10;
        }
        return head.val == 0 ? head.next : head;
    }

    //354、字典序排数




    public static void main(String[] args) {
        System.out.println(StringUtils.isEmpty("1"));
        System.out.println(3&-2);
        System.out.println(4&-2);
    }


}

class ListNode{
    int val;
    ListNode next;
    ListNode(int x){
        val = x;
    }
}
