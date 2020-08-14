package com.example.lenovo.myapplication.newapp;

import android.os.Build;
import android.util.Log;


import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class method {
    public int BiSearch(int[] array,double a){
        int s=0,e=array.length-1;
        int mid;
        while(s<e){
            mid = s+(e-s)/2;
            if(a<array[mid]){
                e=mid-1;
            }else{s=mid+1;}
        }
        return s;
    }
    public int getNumbeOfA(int[] array,int a){
        double s = a+0.5;
        double e = a-0.5;
        return BiSearch(array,e) - BiSearch(array,s);
    }

    //一个数组中，只有一个数字仅出现一次，其他数字均出现两次，找出这个数字
    public int singleNum(int[] nums){
        int num = 0;
        for(int i = 0;i<nums.length;i++){
            num^=nums[i];

        }
        Math.max
        return num;
    }

    //一个数组中，只有两个数字仅出现一次，其他数字均出现两次，找出这个数字
    public int[] singNum2(int[] nums){
        int[] single = new int[2];
        single[0] = 0;
        single[1] = 0;
        int result1 = singleNum(nums);
        int index = findFirst1(result1);
        for(int i=0;i<nums.length;i++){
            if(isBit1(nums[i],index)){
                single[0]^=nums[i];
            }else {
                single[1]^=nums[i];
            }
        }
        return single;
    }
    private int findFirst1(int num){
        int index = 0;
        while (((num & 1)== 0 )&& index<32){
            num>>=1;
            index++;
        }
        return index;
    }
    private boolean isBit1(int target,int index){
        return ((target>>index)&1)==1;
    }


//    输入一个递增排序的数组和一个数字S，
//    在数组中查找两个数，使得他们的和正好是S，
//    如果有多对数字的和等于S，输出两个数的乘积最小的。
//    输出描述:
//    对应每个测试案例，输出两个数，小的先输出。
    public void findTwoNums(int[] array,int target){
        int sIndex = 0;
        int eIndex = array.length-1;
        while (sIndex<eIndex){
            if(array[sIndex]+array[eIndex]==target){
                Log.d("sIndex",String.valueOf(sIndex));
                Log.d("eIndex",String.valueOf(eIndex));
                break;
            }else if(array[sIndex]+array[eIndex]>target){
                eIndex--;
            }else sIndex++;
        }
    }
//    给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
//    其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
    public int[] ConstuctArray(int[] A){
        int[] B = new int[A.length];
        B[0]=1;
        for(int i=1;i<=A.length-1;i++){
            B[i]=B[i-1]*A[i-1];
        }
        int temp = 1;
        for(int j=A.length-2;j>=0;j--){
            temp = temp*A[j+1];
            B[j]*=temp;
        }
        return B;
    }
    //合法的出战序列
    public void checkIsValid(Queue<Integer> queue){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Stack<Integer> st = new Stack<Integer>();
        int[] arry = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arry[i] = sc.nextInt();
        }
        int A = 1, B = 1;
        int flag = 1;
        while (B <= n) {
            if (arry[B] == A) {
                A++;
                B++;
            } else if (!st.empty() && st.peek() == arry[B]) {
                st.pop();
                B++;
            } else if (A <= n) {
                st.push(A++);
            } else {
                flag = 0;
                break;
            }
        }
        if (flag == 0)
            System.out.println("No");
        else
            System.out.println("Yes");


    }
    //查数组交集
//    public int[] intersection(int[] nums1,int[] nums2){
//        if(nums1.length<1||nums2.length<1){
//            return new int[]{};
//        }else if(nums1.length<1&&nums2.length<1){
//            return nums1;
//        }
//        int sum = nums1.length>nums2.length?nums2.length:nums1.length;
//        int[] newlist = new int[sum];
//        Arrays.sort(nums1);
//        int count = 0;
//        int k=0;
//        if(BiSearch(nums2,nums1[0])){
//            newlist[k++]=nums1[0];
//            count++;
//        }
//        for(int i=1;i<nums1.length;i++){
//            if(nums1[i]!=nums1[i-1]&&BiSearch(nums2,nums1[i])){
//                newlist[k++]=nums1;
//                count++;
//            }
//        }
//        int[] result = new int[count];
//        for(int i = 0; i < result.length; i++){
//            result[i]=newlist[i];
//        }
//        return result;
//    }

/*
给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
注意：答案中不可以包含重复的三元组。
例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */

    /*
     *首先用三个for循环是不行的因为三个for时间复杂对超过了，那么我的思路就是遍历数组时进行两个指针
     *进行遍历，利用hashset不可重复进行判断重复，我们队数组排序使得它可以让进入到set中的数组相同
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums==null){
            return null;
        }
        if(nums.length<3){
            return new ArrayList<>();
        }
        //对数组nums进行排序
        Arrays.sort(nums);
        HashSet<List<Integer>> set = new HashSet<>();
        for(int i=0;i<nums.length;i++){
            int j=i+1;
            int k=nums.length-1;
            while (j<k){
                if(nums[i] + nums[j] + nums[k] == 0){
                    List<Integer> result = new ArrayList<Integer>();
                    result.add(nums[i]);
                    result.add(nums[j]);
                    result.add(nums[k]);
                    set.add(result);
                    while (j < k && nums[j]==nums[j+1]){
                        j++;
                    }
                    while (j < k && nums[k]==nums[k-1]){
                        k--;
                    }
                    j++;
                    k--;
                }else if(nums[i] + nums[j] + nums[k] > 0){
                    k--;
                }else {j++;}
            }
        }

        return new ArrayList<>(set);
    }

    //输入一个整数，输出该数二进制表示中1的个数。
    //其中负数用补码表示。
    public int numbeiOf1(int target){
        int count=0;
        while (target!=0){
            count++;
            target = target&(target-1);
        }
        return count;
    }
    //求1+2+3+...+n，
    //要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）
    public int sum_sulotion(int n){
        int ans = n;
        //ans && (ans+=sum_sulotion(n-1));
        boolean t = (n>0)&& (ans += sum_sulotion(n-1))>0;
        return ans;
    }
    //队列表示栈
    class MyStack{
        Queue<Integer> result;
        MyStack(){}
        void push(int a){
            Queue<Integer> temp = null;
            temp.add(a);
            for(int i =0;i<result.size();i++){
                temp.add(result.poll());
            }
            result = temp;
        }
        int pop(){
            if(!result.isEmpty()){
                return result.poll();
            }else return 0;
        }
        int top(){
            return result.peek();
        }
        boolean empty(){
            return result.isEmpty();
        }
    }

    //栈表示队列
    class MyQueue {
        Stack<Integer> result;
        MyQueue(){}
        void push(int x){
            Stack<Integer> temp =null;
            for(int i=0;i<result.size();i++){
                temp.push(result.pop());
            }
            temp.push(x);
            for(int i=0;i<temp.size();i++){
                result.push(temp.pop());
            }
        }
        int pop(){
            return result.pop();
        }
        int peek(){
            return result.peek();
        }
        boolean empty(){
            return result.empty();
        }
    }
    //含有min函数的栈
    class MinStack{
        Stack<Integer> result;
        Stack<Integer> minOfStack;
        void push(int x){
            result.push(x);
            if(minOfStack.empty()){
                minOfStack.push(x);
            }else {
                if(x>minOfStack.peek()){
                    x = minOfStack.peek();
                }
                minOfStack.push(x);
            }
        }
        int pop(){
            minOfStack.pop();
            return result.pop();
        }
        int getMin(){
            return minOfStack.peek();
        }
    }
//同字符词语分组
    public List<List<String>> groupAnagrams(String[] strs){
        if (strs.length == 0){
            return Collections.emptyList();
        }
        Map<String,List<String>> ans = new HashMap<>();
        for(String str:strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = chars.toString();
            if(!ans.containsKey(key)){
                ans.put(key,new ArrayList<String>());
            }
            ans.get(key).add(str);
        }
        return new ArrayList(ans.values());
    }
    //替换空格
    public String replaceSpace(StringBuffer s){
        int spaceNum = 0;
        int j = s.length()-1;
        for (int i=0;i<s.length();i++){
            if(s.charAt(i) == ' '){
                spaceNum++;
            }
        }
        int sum = s.length()+spaceNum*2;
        s.setLength(sum);
        int i = sum-1;
        while(i >= 0 && j >=0 && i >= j) {
            if(s.charAt(j)==' '){
                s.setCharAt(i--,'0');
                s.setCharAt(i--,'2');
                s.setCharAt(i--,'%');
            }else {
                s.setCharAt(i--,s.charAt(j));
            }
            j--;
        }
        return s.toString();
    }
    ///**
    // * 题目：
    // * 字符串的排列 -- newcoder 剑指Offer 27
    // *
    // * 题目描述：
    // * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
    // * 例如输入字符串abc,则打印出由字符a,b,c 所能排列出来的所有字符串
    // * abc,acb,bac,bca,cab和cba。
    // *
    // * 输入描述:
    // * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
    // */
    public class sulotion{
        public ArrayList<String> Permutation(String str) {
            if(str==null){return null;}
            char[] ins = str.toCharArray();
            ArrayList<String> list = new ArrayList<>();
            DoPermutation(ins,0,list);
            Collections.sort(list);
            return list;
        }
        private void DoPermutation(char[] ins,int i,ArrayList<String> list){
            if (ins == null) return;
            //如果i指向了最后一个字符
            if(i == ins.length-1){
                if(list.contains(String.valueOf(ins))){
                    return;
                }
                list.add(String.valueOf(ins));
            }
            //当i不指向最后一个时，i代表我们做排列操作的字符串的第一个字符
            else {
                for(int j=i;j<ins.length;j++){
                    swap(ins,i,j);//将第一个字符与后面的字符交换
                    DoPermutation(ins,i+1,list);//对后面的字符进行全排列
                    swap(ins,j,i);//再将之前交换的字符交换回来，以便于第一个字符再与其他字符进行交换
                }
            }
        }

    }
    /*交换*/
    private void swap(char[] ins, int i, int j) {
        char tmp = ins[i];
        ins[i] = ins[j];
        ins[j] = tmp;
    }
    //左旋转字符串
    public String LiftRotateString(String s,int n){
        if(s.length()==0){
            return null;
        }
        char[] tep = s.toCharArray();
        for(int i=0,j=n-1;i<j;i++,j--){swap(tep,i,j);}
        for (int i=n,j=s.length()-1;i<j;i++,j--){swap(tep,i,j);}
        for (int i=0,j=s.length()-1;i<j;i++,j--){swap(tep,i,j);}
        return tep.toString();
    }

    //翻转单词的顺序列
    public String reverseWords(String s){
        if(s.length()==0){return null;}
        String[] strings = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=strings.length-1;i>=0;i--){
            if (i==0){
                stringBuilder.append(strings[i]);
            }else {
                stringBuilder.append(strings[i]);
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    // 孩子们的游戏（圆圈中最后剩下的数）
    public int LastRemaining_Solution(int n, int m) {
        if(n<1||m<1) return -1;
        int[] array = new int[n];
        int i = -1,step = 0, count = n;
        while (count>0){
            i++;
            if(i>=n){i=0;}
            step++;
            if(array[i]==-1){continue;}
            if (step==m){
                count--;
                array[i]=-1;
                step=0;
            }
        }
        return i;
    }

    //超级丑数
    public static int nthSuperUglyNumber(int n, int[] primes) {
        //记录丑数的数组
        int nums[] = new int[n];
        nums[0] = 1;
        //每个因子的位置
        int primesIndex[] = new int[primes.length];
        int count = 1;
        while (count < n) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < primes.length; i++) {
                //求乘以每一个因子得到的最小丑数
                int temp = nums[primesIndex[i]] * primes[i];
                min = Math.min(temp, min);
            }
            for (int i = 0; i < primes.length; i++) {
                //更新index，可能更新多个index
                if (min == nums[primesIndex[i]] * primes[i]) {
                    primesIndex[i]++;
                }
            }
            nums[count++] = min;
        }
        return nums[count - 1];
    }
    //有序矩阵中第K小的元素
    //1.找出二维矩阵中最小的数left，最大的数right，那么第k小的数必定在left~right之间
    //2.mid=(left+right) / 2；在二维矩阵中寻找小于等于mid的元素个数count
    //3.若这个count小于k，表明第k小的数在右半部分且不包含mid，即left=mid+1, right=right，又保证了第k小的数在left~right之间
    //4.若这个count大于k，表明第k小的数在左半部分且可能包含mid，即left=left, right=mid，又保证了第k小的数在left~right之间
    //5.因为每次循环中都保证了第k小的数在left~right之间，当left==right时，第k小的数即被找出，等于right
    public int theKOfNum(int[][] matrix, int k){
        int row = matrix.length;
        int col = matrix[0].length;
        int left = matrix[0][0];
        int right = matrix[row-1][col-1];
        while (left<right){
            int mid = left+(right-left)/2;
            int count = findNotBiggerThanMid(matrix,mid,row,col);
            if (count<k){
                left = mid+1;
            }
            else right=mid;
        }
        return right;
    }
    public int findNotBiggerThanMid(int[][] matrix,int mid,int row,int col){
        int i=row-1;
        int j=0;
        int count=0;
        while (i>=0&&j<col){
            if(matrix[i][j]>mid){
                i--;
            }else {
                count+=i+1;
                j++;
            }

        }
        return count;
    }

    //前K个高频元素
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int num:nums){
            if (map.containsKey(num)){
                map.put(num,map.get(num)+1);
            }else {
                map.put(num,1);
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return map.get(integer)-map.get(t1);
            }
        });
        for (Integer key:map.keySet()){
            if (pq.size()<k){
                pq.add(key);
            }else if(map.get(key)>map.get(pq.peek())){
                pq.remove();
                pq.add(key);
            }
        }
    }

//#######
//合并区间
//假设输入[2,4]  [3,5]  [11,15]  [18,20]  [14,18]  [7,8]
//会输出[2,5]  [7,8]  [11,20]
    public int[][] mergeList(int[][] allList){
        if (allList.length<=1){
            return allList;
        }
        Arrays.sort(allList, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0]-b[0];
            }
        });
        ArrayList<int[]> result = new ArrayList<>();
        for (int i=0;i<allList.length-1;i++){
            if (allList[i][1]>=allList[i+1][0]){
                allList[i+1][0]=allList[i][0];
                allList[i+1][1]=Math.max(allList[i+1][1],allList[i][1]);
                //result.add(new int[]{allList[i][0],allList[i+1][1]});
            }else {
                result.add(allList[i]);
            }
        }
        result.add(allList[allList.length-1]);
        int[][] resultList = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++){
            resultList[i][0] = result.get(i)[0];
            resultList[i][1] = result.get(i)[1];
        }
        return resultList;
    }

//########
//生产者消费者问题。生产者生成1~100的随机整数，消费者消费这个整数并打印。
// 生产者有三个，分别以1秒、2~3秒、2~5秒的速度生成。   // 2~3秒的意思是，每次产生一个随机时间，随机时间在2~3秒的区间里
// 消费者有两个，分别以1秒、1~2秒的速度消费。


//########
//输入一串如下字符串"1.21 + 2.1 * 3.1 / 4.1 - 5"，使用代码解析，并计算出结果。
    public double calString(String s){
        String[] strings = s.split(" ");
        ArrayList<String> temp = new ArrayList<>();
        for(int i =0;i<strings.length;i++){
            if(strings[i]=="*"){
                double a = Double.parseDouble(strings[i-1]);
                double b = Double.parseDouble(strings[i+1]);
                String c = String.valueOf(a*b);
                strings[i-1] =c;
                for(int j = i;j<strings.length-2;j++){
                    strings[i] = strings[i+2];
                }
                strings[strings.length-2] = "0";
                strings[strings.length-1] = "0";
            }}
        for(int i =0;i<strings.length;i++){
            if(strings[i]=="/"){
                double a = Double.parseDouble(strings[i-1]);
                double b = Double.parseDouble(strings[i+1]);
                String c = String.valueOf(a/b);
                strings[i-1] =c;
                for(int j = i;j<strings.length-2;j++){
                    strings[i] = strings[i+2];
                }
                strings[strings.length-2] = "0";
                strings[strings.length-1] = "0";
            }
        }
        for(int i =0;i<strings.length;i++) {
            if (strings[i] == "+") {
                double a = Double.parseDouble(strings[i - 1]);
                double b = Double.parseDouble(strings[i + 1]);
                String c = String.valueOf(a + b);
                strings[i - 1] = c;
                for (int j = i; j < strings.length - 2; j++) {
                    strings[i] = strings[i + 2];
                }
                strings[strings.length - 2] = "0";
                strings[strings.length - 1] = "0";
            }
        }

        for(int i =0;i<strings.length;i++) {
            if(strings[i]=="-"){
                double a = Double.parseDouble(strings[i-1]);
                double b = Double.parseDouble(strings[i+1]);
                String c = String.valueOf(a-b);
                strings[i-1] =c;
                for(int j = i;j<strings.length-2;j++){
                    strings[i] = strings[i+2];
                }
                strings[strings.length-2] = "0";
                strings[strings.length-1] = "0";
            }
        }
        return Double.valueOf(strings[0]);
    }


//插入位置
public int findInsert(int[] nums,int target){
        //int index = -1;
        int s = 0;
        int e = nums.length-1;
        while (s<=e){
            int mid = s+(e-s)/2;
            if(target == nums[mid]){
                return mid;
            }else if(target > nums[mid]){
                s = mid+1;
            }else {
                e = mid -1;
            }
        }
        return s;
}

//区间查找
    public int[] searchRange(int[] nums,int target){
        int s=0;
        int e=0;
        int[] index = new int[2];
        index[0]=leftRange(nums,target);
        index[1]=rightRange(nums, target);

    }
    public int leftRange(int[] nums,int target){
        int s=0;
        int e=nums.length-1;
        while (s<=e){
            int mid = s+(e-s)/2;
            if (target == nums[mid]){
                if (mid==0||nums[mid-1]<target){
                    return mid;
                }
                e = mid-1;
            }else if(target>nums[mid]){
                s=mid+1;
            }else {
                e = mid-1;
            }
        }
        return -1;
    }
    public int rightRange(int[] nums,int target){
        int s=0;
        int e=nums.length-1;
        while (s<=e){
            int mid = s+(e-s)/2;
            if (target == nums[mid]){
                if (mid==0||nums[mid+1]>target){
                    return mid;
                }
                s = mid+1;
            }else if(target>nums[mid]){
                s=mid+1;
            }else {
                e = mid-1;
            }
        }
        return -1;
    }

///寻找重复数
    public int findrepi(int[] nums){
        int s=0;
        int e=nums.length;
        while (s<e){
            int mid=s+(e-s)/2;
            int count =0;
            for(int num:nums){
                if(num<=mid){count++;}
            }
            if(mid<=count){
                s=mid+1;
            }else e=mid;
        }
        return e;
    }

//搜索旋转排序数组
    public int searchTarget(int[] nums,int target){
        if (nums==null||nums.length<1){
            return -1;
        }
        int s=0;
        int e=nums.length-1;
        while (s<=e){
            int mid = s+(e-s)/2;
            if (nums[mid] == target) return mid;
            if (nums[mid]>=nums[s]){
                if(target>nums[s]&&target<=nums[mid]){
                    e = mid-1;
                }else s = mid+1;
            }
            if (nums[mid]<=nums[e]){
                if(target<=nums[e]&&target>nums[mid]){
                    s = mid+1;
                }else e = mid-1;
            }
        }
        return -1;
    }

//求子集
    //1.非递归
    public List<String> calculateZiJi(String a){
        List<String> result = new ArrayList<>();
        //求第一个元素的子集
        result.add("");
        result.add(a.charAt(0) + "");
        for (int i =1;i<a.length();i++){
            for (int j =0;j<result.size();j++){
                result.add(result.get(j)+a.charAt(i));
            }
        }
        return result;
    }
    //2。递归
    //使用递归的方式
    private static void calculate(List<String> result, String a, int start, int end) {
        //当只有一个元素时，在结果集合中添加空集和当前元素
        if (end == start) {
            result.add(a.charAt(start) + "");
            result.add("");
            return;
        }
        //求去掉最后一个元素后的子集
        calculate(result, a, start, end - 1);
        //把最后一个元素添加到最后一个元素后的子集，分为两种情况，包含最后一个元素和不包含最后一个元素
        //不包含最后一个元素：这一部分的集合和去掉最后一个元素后的子集相同，无需操作
        //包含最后一个元：将去掉最后一个元素后的子集中添加最后一个元素，然后加入结果的list中
        int size = result.size();
        for (int i = 0; i < size; i++) {
            result.add(result.get(i) + a.charAt(end));
        }
    }
    //火柴棍摆正方形
    class solution{
        public boolean makesquare(int[] nums){
            if(nums==null||nums.length<4){
                return false;
            }
            //提前判断是否有可能是正方形
            int sum_num=0;
            for(int i:nums){
                sum_num+=i;
            }
            if (sum_num==0||sum_num%4!=0){
                return false;
            }
            Arrays.sort(nums);
            return canMake(nums.length-1,nums,new int[4],sum_num/4);
        }
        public boolean canMake(int pos,int[]nums,int[] sum,int average){
            if(pos<0){
                return (sum[0]==average &&sum[1]==average&&sum[2]==average);
            }
            for(int i=0;i<sum.length;i++){
                if(nums[pos]+sum[i]>average){
                    continue;
                }
                sum[i]+=nums[pos];
                if(canMake(pos-1,nums,sum,average)){
                    return true;
                }
                //回溯
                sum[i]-=nums[pos];
            }
            return false;
        }
    }
//分糖果
    public int devSugar(int[] yinzi,int[] child){
        Map<String, Integer> words = new HashMap<>();
        words.

    }


//冒泡排序
    public int[] maopao(int[] nums){

        for (int i=nums.length-1;i>0;i--){
            boolean istrun = false;
            for (int j=0;j<i;j++){
                if (nums[j] > nums[j+1]) {
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1]=temp;
                    istrun=true;
                }
                }
            if (!istrun){
                break;
            }
            }
        return nums;
        }
    }

    //归并排序
class solution{
    public int[] guibing(int[] nums){
        int[] temp = new int[nums.length];
        internalMergeSort(nums,temp,0,nums.length-1);
        return nums;
    }
    private void internalMergeSort(int[] nums,int[] temp,int left,int right){
        if (left<right){
            int mid = (right-left)/2;
            internalMergeSort(nums, temp, left, mid);
            internalMergeSort(nums, temp, mid+1, right);
            mergeSortedArray(nums,temp,left, mid, right);
        }
    }
    private void mergeSortedArray(int[] nums,int[] temp,int left,int mid,int right){
        int i =left;
        int j=mid+1;
        int k=0;
        while (i<=mid&&j<=right){
            temp[k++] = nums[j]<nums[j]?nums[i++]:nums[j++];
        }
        while (i<=mid){
            temp[k++] = nums[i++];
        }
        while (j<=right){
            temp[k++]=nums[j++];
        }
        for (i=0;i<k;i++){
            nums[left+i]=temp[i];
        }
    }
    }
    //快速排序
class soution{
    public void quickSort(int[] nums){
        quickSort(nums,0,nums.length);
    }
    private void quickSort(int[] nums,int left,int right){
        if(left>right){
            return;
        }
        int midnum = divSums(nums,left,right);
        quickSort(nums,left,midnum-1);
        quickSort(nums,midnum+1,right);
    }
    private int divSums(int[] nums,int left,int right){
        int midnum = nums[left];
        while (left<right){
            while (left<right&&nums[right]>=midnum){
                right--;
            }
            nums[left]=nums[right];
            while (left<right&&nums[left]<=midnum){
                left++;
            }
            nums[right]=nums[left];
        }
        nums[left]=midnum;
        return left;
    }
    }
    //计数排序
public void countSort(int[] nums){
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i =0;i<nums.length;i++){
        min = Math.min(min,nums[i]);
        max = Math.max(max,nums[i]);
    }
    int[] temp = new int[max-min + 1];
    for (int i =0;i<temp.length;i++){
        temp[i] = 0;
    }
    for (int i =0;i<nums.length;i++){
        temp[nums[i]-min]++;
    }
    for (int i =0;i<temp.length;i++){
        int index = 0;
        while (temp[i]--!=0){
            nums[index++]=
        }
        temp[i] = 0;
    }
}

public void bucketSort(int[] nums) {
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
        min = Math.min(min, nums[i]);
        max = Math.max(max, nums[i]);
    }
    int bucketNum = (max - min) / nums.length + 1;
    List<ArrayList<Integer>> bucketArr = new ArrayList<>();
    for (int i = 0; i < bucketNum; i++) {
        bucketArr.add(new ArrayList<>());
    }
    for (int i = 0; i < nums.length; i++) {
        int num = (nums[i] - min) / nums.length;
        bucketArr.get(num).add(nums[i]);
    }
    int k = 0;
    for (int i = 0; i < bucketArr.size(); i++) {
        Collections.sort(bucketArr.get(i));
        for (int j = 0; j < bucketArr.get(i).size(); j++) {
            nums[k] = bucketArr.get(i).get(j);
            k++;
        }
    }
}}

class TreeNode{
    public TreeNode left;
    public TreeNode right;
    public int val;
    public TreeNode(int val) { this.val = val; }
}

    //先序遍历
    public void preTraverse(TreeNode root){
    if (root!=null){
        System.out.println(root.val);
        preTraverse(root.left);
        preTraverse(root.right);
    }
    }
//中序遍历
    public void inTraverse(TreeNode root){
        if (root!=null){

            inTraverse(root.left);
            System.out.println(root.val);
            inTraverse(root.right);
        }
    }
    //后序遍历
    public void postTraverse(TreeNode root){
        if (root!=null){
            inTraverse(root.left);
            inTraverse(root.right);
            System.out.println(root.val);
        }
    }

//层次遍历
    public List<List<Integer>> levelOrder(TreeNode root){
    List<List<Integer>> res = new ArrayList<>();
    if (root ==null){
        return res;
    }
    def(root,res,0);
    return res;

    }
public void def(TreeNode root,List<List<Integer>> res,int level){
if (root==null){return;}
if(res.size()==level){
    res.add(new ArrayList<>());
}
res.get(level).add(root.val);
def(root.left,res,level+1);
def(root.right,res,level+1);
}

//左右反转
    public void invert(TreeNode root){
    if (root==null){
        return;
    }
    TreeNode temp = root.left;
    root.left=root.left.right;
    root.right=temp;
    invert(root.left);
    invert(root.right);
    }
//最大值
    public int maxValue(TreeNode root){
        if (root==null){
            return Integer.MIN_VALUE;
        }
        else {
            int Left = maxValue(root.left);
            int Right = maxValue(root.right);
            return Math.max(Math.max(Left, Right), root.val);
        }
    }
//最大深度
    public int depth(TreeNode root){
        if (root==null){
            return 0;
        }else {
            int Left = depth(root.left);
            int Right = depth(root.right);
            return Math.max(Left, Right)+1;
        }
    }
//最小深度


    //有效括号
    //给定一个字符串所表示的括号序列，包含以下字符: '(', ')', '{', '}', '[' and ']'，
    // 判 定是否是有效的括号序列。括号必须依照 "()" 顺序表示，
    // "()[]{}" 是有效的括 号，但 "([)]" 则是无效的括号。
    public boolean isValidParentheses(String s){
    Stack<Character> stack = new Stack<>();
    for (Character c:s.toCharArray()){
        if ("({[".contains(String.valueOf(c))){
            stack.push(c);
        }
        else {
            if (!stack.empty()&&isValid(stack.peek(),c)){
                stack.pop();
            }else {
                return false;
            }
        }
    }
    return stack.empty();
    }
    public boolean isValid(Character c1,Character c2){
        return (c1 == '(' && c2 == ')') || (c1 == '{' && c2 == '}')
                || (c1 == '[' && c2 == ']');
    }

//x的平方根
    public int sqrt(int x){
    if (x<0){
        throw new IllegalArgumentException();
    }
    else if (x<=1){
        return x;
    }
    int start=1;
    int end = x;
    while (start+1<end){
        int mid = start+(end-start)/2;
        if (mid==x/mid){
            return mid;
        }else if(mid<x/mid){
            start =mid;
        }else {
            end=mid;
        }
    }
    if (end>x/end){
        return start;
    }
    return end;
    }




























}
