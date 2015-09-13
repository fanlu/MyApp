import org.junit.Test;

/**
 * Created by Lonica on 15/9/13.
 */
public class FillMatrix {

    public void fillMatrix(int i){
        int[][] matrix = new int[i][i];
        int max = i * i;
        int row = 0, col = 0;
        int direction = 0;
        for (int j = 1; j <= max; j++) {
            matrix[row][col] = j;
            switch (direction) {
                case 0:
                    if (col + 1 >= i || matrix[row][col + 1] > 0) {
                        direction += 1;
                        direction %= 4;
                        row += 1;
                    } else {
                        col = col + 1;
                    }
                    break;
                case 1:
                    if (row + 1 >= i || matrix[row + 1][col] > 0) {
                        direction += 1;
                        direction %= 4;
                        col -= 1;
                    } else {
                        row = row + 1;
                    }
                    break;
                case 2:
                    if (col - 1 < 0 || matrix[row][col - 1] > 0) {
                        direction += 1;
                        direction %= 4;
                        row = row - 1;
                    } else {
                        col = col - 1;
                    }
                    break;
                case 3:
                    if (row - 1 < 0 || matrix[row - 1][col] > 0) {
                        direction += 1;
                        direction %= 4;
                        col += 1;
                    } else {
                        row = row - 1;
                    }
                    break;
                default:
                    System.out.println("ERROR");
                    System.exit(0);
            }
        }
        for (int j = 0; j < i; j++) {
            for (int k = 0; k < i; k++) {
                if (matrix[j][k] < 10)
                    System.out.print("  " + matrix[j][k]);
                else
                    System.out.print(" " + matrix[j][k]);
            }
            System.out.println("");
        }
    }

    @Test
    public void test(){
        fillMatrix(4);
        fillMatrix(5);
    }
}
