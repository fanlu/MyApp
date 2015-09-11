import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-8-1
 * Time: 下午3:53
 * To change this template use File | Settings | File Templates.
 */
public class CountingSortTest {

    @Test
    public void testCountingSort() {
        Integer[] A = countingSort(new int[]{16, 4, 10, 14, 3, 7, 7, 7, 7, 8, 9, 4, 7, 9, 3, 2, 8, 1});
        System.out.println(Joiner.on(",").skipNulls().join(A));
    }

    private Integer[] countingSort(int[] origin) {
        int max = origin[0];
        for (int i = 1; i < origin.length; i++) {
            if (origin[i] > max) {
                max = origin[i];
            }
        }
        System.out.println(max);
        Integer[] sortArray = new Integer[origin.length];
        int[] countArray = new int[max + 1];
        for (int i = 0; i < origin.length; i++) {
            countArray[origin[i]]++;
        }
        System.out.println(Joiner.on(",").skipNulls().join(Arrays.asList(countArray)));
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }
        System.out.println(Joiner.on(",").skipNulls().join(Arrays.asList(countArray)));
        for (int i = origin.length - 1; i >= 0; i--) {
            int a = origin[i];
            sortArray[countArray[a] - 1] = origin[i];
            countArray[a]--;
        }
        return sortArray;
    }
}
