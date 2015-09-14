import org.junit.Test;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Lonica
 * Date: 14-4-29
 * Time: 上午11:45
 * To change this template use File | Settings | File Templates.
 */
public class HeapSortUtil {


    /**
     * 用堆排序方法  找出前N个最大的数
     *
     * @return 包含topN个最大数的数组
     * @originalArray 原始数据数组
     * @topN 需要取得的N个最大数
     */
    public int[] getTopArray(int[] originalArray, int topN) {
        int len = originalArray.length;
        if (len <= topN) {
            return originalArray;
        }
        int[] array = new int[topN];
        initHeap(originalArray);
        int temp;
        for (int i = 0; i < array.length; i++) {
            array[i] = originalArray[0];
            temp = originalArray[originalArray.length - i - 1];
            originalArray[originalArray.length - i - 1] = originalArray[0];
            originalArray[0] = temp;
            buildHeap(0, originalArray.length - i - 1, originalArray);
        }


        return array;
    }

    /**
     * 创建初始无序堆
     */
    private void initHeap(int[] orignalArr) {
        for (int i = orignalArr.length - 1; i >= 0; i--) {
            buildHeap(i, orignalArr.length, orignalArr);
        }
    }

    /**
     * 调整堆
     *
     * @param location     起始位置
     * @param unSortLength 无序堆的长度
     */
    private void buildHeap(int location, int unSortLength, int[] arr) {
        int temp;
        int tempLoc;
        //判断该父节点是否有左右孩子
        if ((tempLoc = (location + 1) * 2) < unSortLength) {
            if (arr[tempLoc] > arr[tempLoc - 1]) {//如果右节点大于左节点
                if (arr[tempLoc] > arr[location]) {//如果右节点大于父节点  就双方交换值
                    temp = arr[location];
                    arr[location] = arr[tempLoc];
                    arr[tempLoc] = temp;
                    buildHeap(tempLoc, unSortLength, arr);//递归
                }
            } else {//如果左节点大于右节点
                if (arr[tempLoc - 1] > arr[location]) {//如果左节点大于父节点
                    temp = arr[location];
                    arr[location] = arr[tempLoc - 1];
                    arr[tempLoc - 1] = temp;
                    buildHeap(tempLoc - 1, unSortLength, arr);//递归
                }
            }
        } else if ((tempLoc = ((location + 1) * 2 - 1)) < unSortLength) {//如果该父节点有左节点
            if (arr[tempLoc] > arr[location]) {//如果右节点大于父节点
                temp = arr[location];
                arr[location] = arr[tempLoc];
                arr[tempLoc] = temp;
                buildHeap(tempLoc, unSortLength, arr);//递归
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[100000];
        Random ran = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ran.nextInt(100000);
        }
        HeapSortUtil h = new HeapSortUtil();
        long start = System.currentTimeMillis();
        int topArr[] = h.getTopArray(arr, 20);
        //打印出排序后的数组
        for (int i = 0; i < topArr.length; i++) {
            System.out.println(topArr[i]);
        }


        long end = System.currentTimeMillis() - start;
        System.out.println("Total time：" + end + "ms");
    }

    public void maxHeapify(int[] a, int i, int length) {
        System.out.println("从" + i + "开始保持堆");
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int largest = i;
        if (l <= length - 1 && a[l] > a[i]) {
            largest = l;
        } else {
            largest = i;
        }
        if (r <= length - 1 && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != i) {
            int temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;
            maxHeapify(a, largest, length);
        }
    }

    public void buildHeap(int[] a){
        for(int i = a.length / 2 - 1; i>= 0; i--){
            maxHeapify(a, i, a.length);
        }
    }

    public void sortHeap(int[] a){
        for(int i = a.length - 1; i>=1; i--){
            int temp = a[i];
            a[i] = a[0];
            a[0] = temp;

            maxHeapify(a, 0, i);
        }
    }

    @Test
    public void test(){
        int[] a = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        buildHeap(a);
        sortHeap(a);
        for(int i=0;i<a.length; i++){
            System.out.println(a[i] + " ");
        }
    }
}