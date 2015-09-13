import org.junit.Test;

/**
 * Created by Lonica on 15/9/13.
 */
public class SortTest {

    @Test
    public void test() {
        int[] a = {4, 7, 9, 3, 6, 5};
//        bubbleSort(a);
        quickSort(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public void quickSort(int[] a, int l, int h) {
        int key = a[l];
        int min = l;
        int max = h;

        while (min < max) {
            while (min < max && a[max] >= key)
                max--;
            if (min < max) {
                a[min] = a[max];
                a[max] = key;
                min++;
            }
            while (min < max && a[min] <= key)
                min++;
            if (min < max) {
                a[max] = a[min];
                a[min] = key;
                max--;
            }
        }
        if (min > l)
            quickSort(a, l, min - 1);
        if (max < h)
            quickSort(a, max + 1, h);
    }

    public int[] bubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = a.length - 1; j > i; j--) {
                if (a[j - 1] > a[j]) {
                    swap(a, j - 1, j);
                }
            }
        }
        return a;
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
