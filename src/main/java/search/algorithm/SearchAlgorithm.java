package search.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: liming
 * @Date: 2020/5/29 10:53
 * @Description:
 */

public class SearchAlgorithm {

    public static void main(String[] args) {

        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 11, 12, 13, 13, 13};
//        int[] arr = new int[5];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (i +  1) * 5;
//        }

//        List<Integer> integers = interpolationSearchAlgorithm(arr, 16, 0, arr.length - 1);
//        List<Integer> integers = fibonacciSearchAlgorithm(arr, 13);
//        int i = binarySearchAlgorithm1(arr, 24, 0, arr.length - 1);
        int i = binarySearchAlgorithmNonRecursion(arr, 12);
        System.out.println(Arrays.toString(arr));
        System.out.println(i);

    }

    /**
     * 二分查找
     * @param arr 数据
     * @param target 目标值
     * @param left 左边索引
     * @param right 右边索引
     * @return 目标索引
     */
    private static List<Integer> binarySearchAlgorithm(int[] arr, int target, int left, int right) {


        if (left > right) {
            return new ArrayList<>();
        }

        int middle = (right + left) / 2;

        if (target > arr[middle]) {
            return binarySearchAlgorithm(arr, target, middle + 1, right);
        } else if (target < arr[middle]) {
            return binarySearchAlgorithm(arr, target, left, middle - 1);
        } else {

            return getIntegers(arr, target, middle);
        }
    }

    /**
     * 二分查找 (非递归)
     * @param arr 数据
     * @param target 目标值
     * @return 目标索引
     */
    private static int binarySearchAlgorithmNonRecursion(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int middle = (left + right) / 2;

            if (target > arr[middle]) {
                left = middle + 1;
            } else if (target < arr[middle]) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    private static int binarySearchAlgorithm1(int[] arr, int target, int left, int right) {


        if (left > right) {
            return right;
        }

        int middle = (right + left) / 2;

        if (target > arr[middle]) {
            return binarySearchAlgorithm1(arr, target, middle + 1, right);
        } else if (target < arr[middle]) {
            return binarySearchAlgorithm1(arr, target, left, middle - 1);
        } else {

            return middle;
        }
    }

    /**
     * 插值查找
     * @param arr 数据
     * @param target 目标值
     * @param left 左边索引
     * @param right 右边索引
     * @return 目标索引
     */
    private static List<Integer> interpolationSearchAlgorithm(int[] arr, int target, int left, int right) {

        if (left > right || target < arr[0] || target > arr[arr.length - 1]) {
            return new ArrayList<>();
        }

        int flag = left + (target - arr[left]) * (right - left) / (arr[right] - arr[left]);

        if (target < arr[flag]) {
            return interpolationSearchAlgorithm(arr, target, left, flag - 1);
        } else if (target > arr[flag]) {
            return interpolationSearchAlgorithm(arr, target, flag + 1, right);
        } else {
            return getIntegers(arr, target, flag);
        }
    }

    /**
     * 斐波那契查找
     * 数组长度过小时, 会出BUG的
     * @param arr
     * @param target
     * @return
     */
    private static List<Integer> fibonacciSearchAlgorithm(int[] arr, int target) {

        int low = 0;
        int high = arr.length - 1;
        int key = 0;
        int middle;

        int[] fibonacciArr = getFibonacciArr(20);

        while (high > fibonacciArr[key]) {
            key++;
        }

        int[] temp = Arrays.copyOf(arr, fibonacciArr[key]);

        for (int i = high + 1; i < fibonacciArr[key]; i++) {
            temp[i] = arr[high];
        }

        while (low <= high) {
            middle = low + fibonacciArr[key - 1] - 1;
            if (target > temp[middle]) {
                low = middle + 1;
                key -= 2;
            } else if (target < temp[middle]) {
                high = middle - 1;
                key -= 1;
            } else {
                if (middle <= high) {
                    return getIntegers(arr, target, middle);
                } else {
                    return getIntegers(arr, target, high);
                }
            }
        }
        return new ArrayList<>();
    }

    /**
     * 斐波那契查找
     * @param arr
     * @param target
     * @return
     */
    private static int fibonacciSearchAlgorithm1(int[] arr, int target) {

        int low = 0;
        int high = arr.length - 1;
        int key = 0;
        int middle;

        int[] fibonacciArr = getFibonacciArr(10);

        while (high > fibonacciArr[key] - 1) {
            key++;
        }

        int[] temp = Arrays.copyOf(arr, fibonacciArr[key]);

        for (int i = high + 1; i < fibonacciArr[key]; i++) {
            temp[i] = arr[high];
        }

        while (low <= high) {
            if (key - 1 < 0) {
                return high;
            }
            middle = low + fibonacciArr[key - 1] - 1;
            if (target > temp[middle]) {
                low = middle + 1;
                key -= 2;
            } else if (target < temp[middle]) {
                high = middle - 1;
                key -= 1;
            } else {
                if (middle <= high) {
                    return middle;
                } else {
                    return high;
                }
            }
        }
        return high;
    }

    private static int[] getFibonacciArr(int size) {
        int[] arr = new int[size];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < size; i++) {
            arr[i] = arr[i -1] + arr[i - 2];
        }
        return arr;
    }

    private static List<Integer> getIntegers(int[] arr, int target, int flag) {
        List<Integer> list = new ArrayList<>(arr.length);
        list.add(flag);
        int temp = flag;
        while (++temp < arr.length && arr[temp] == target) {
            list.add(temp);
        }
        temp = flag;
        while (--temp >= 0 && arr[temp] == target) {
            list.add(temp);
        }
        return list;
    }

    /**
     * 斐波那契查找
     * @param arr 待查找的数组
     * @param findVal 要查找的数
     * @return 找到返回下标,没找到返回-1
     */
    public static int fibSearch(int[] arr,int findVal){
        //变量的定义
        //左边索引
        int left = 0;
        //右边索引
        int right =arr.length -1;
        //斐波拉契分割数值的下标
        int k = 0;
        //存放找到的mid值
        int mid = 0;
        //获取斐波拉契数列
        int[] f = getFibonacciArr(10);
        //循环处理来查找斐波拉契分割数值的下标所对应的值
        while (right > f[k] -1){
            k++;
        }
        //可能存在f[k]的值大于了arr的长度,我们需要扩容,并指向数组temp[]
        //不足的部分使用0来补齐
        int[] temp = Arrays.copyOf(arr, f[k]);
        //我们使用arr数组的最后的数来填充temp数组
        //如:temp={1,8,10,89,1000,1234,0,0,0}====>{1,8,10,89,1000,1234,1234,1234,1234}
        //right +1 = 1234
        for (int i = right +1; i <temp.length ; i++) {
            temp[i] = arr[right];
        }
        //来找我们的findVal,循环处理
        while (left <= right){
            mid = left + f[k -1] -1;
            //我们应该在数组的左边继续找
            if (findVal < temp[mid]){
                right = mid -1;
                //说明:
                //1.我们数组的全部元数 = left左边 +right元素
                //2.因为我们的黄金分割点是 f[k] = f[k -1] + f[k -2]
                //3. 可以发现的,当前左边还有f[k -1]个元素,因此可以继续拆分f[k -1] = f[k -2] + f[k -3]等
                // 也就是说在f[k -1]的左边继续查找,即 k-- 即 mid = f[k - 1 -1] -1
                k --;
            }
            //我们应该在数组的右边继续找
            else if (findVal > temp[mid]){
                left = mid +1;
                //说明:
                //1.我们数组的全部元数 = left左边 +right元素
                //2.因为我们的黄金分割点是 f[k] = f[k -1] + f[k -2]
                //3.可以发现的,当前右边还有f[k -2]个元素,因此可以继续拆分f[k -1] = f[k -3] + f[k -4]等
                //即在f[k -2]的右边继续查找 k -=2个元素,即 mid = f[k -1 -2] -1
                k -= 2;
            }else { //表示找到了
                //比较下,我们返回的最小的那个
                if (mid <= right){
                    return mid;
                }
                return right;
            }
        }
        return -1;
    }

}
