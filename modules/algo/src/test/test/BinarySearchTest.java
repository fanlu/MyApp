import org.junit.Test;

/**
 * Created by wangxin on 2015/9/10.
 */
public class BinarySearchTest {

    @Test
    public void test(){
//        int[] a = {4, 5, 6, 7, 8, 9, 1, 2};
//        int[] a = {6, 7, 1, 2, 3, 4, 5};
        int[] a = {7, 8, 9, 10, 11, 12, 13, 14, 1, 2, 3, 4, 5, 6};
        int i = binarySearch(a, 0, a.length - 1);
        System.out.println(i);
        int j = binarySearch0(a, 0, a.length - 1);
        System.out.println(j);
    }

    public int binarySearch(int[] a, int min, int max){
        int i = (min + max) / 2;
//        System.out.println(i);
        if(i == min || i == max){
            System.out.println(i);
            return i;
        }
        if(a[i] > a[i+1]){
            System.out.println("222222:" + i);
            return i;
        }
        if(a[i] < a[i-1]){
//            System.out.println(a[i]);
//            System.out.println(a[i -1]);
            System.out.println("333333:" + (i-1));
            return i - 1;
        }
        if(a[i] > a[max]){
            System.out.println("444444:" + (i +1) + "ddd" + max);
            return binarySearch(a, i + 1, max);
        }
        if(a[i] < a[max]){
            System.out.println("55555:" + 0 + "ddd" + (i - 1));
            return binarySearch(a, min, i - 1);
        }
        return -1;
    }

    public int binarySearch0(int[] a, int min, int max) {

        while (min <= max) {
            int i = (min + max) >>> 1;
            if(a[i] > a[i+1]){
                System.out.println("222222:" + i);
                return i;
            }
            if(a[i] < a[i-1]){
                System.out.println("333333:" + (i-1));
                return i - 1;
            }
            if(a[i] > a[max]){
                System.out.println("444444:" + (i +1) + "ddd" + max);
                min = i + 1;
            }
            if(a[i] < a[max]){
                System.out.println("55555:" + 0 + "ddd" + (i - 1));
                max = i -1;
            }
        }
        return -(min + 1);  // key not found.
    }
}
