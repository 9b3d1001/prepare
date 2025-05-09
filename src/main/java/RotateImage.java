import java.net.StandardSocketOptions;
import java.util.Arrays;

public class RotateImage {

    public static void main(String[] args) {
        RotateImage rotateImage = new RotateImage();
        //rotateImage.rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
        //rotateImage.rotate(new int[][]{{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}});
        rotateImage.rotate(new int[][]{{1,2}, {3,4}});
    }
/*
    when n = 4
    0,1 -> 1, 3| 3, 2|2, 0
    0,2 -> 2, 3 | 3, 1 | 1, 0

    j, n-i-1 | n-i-1, n-j-1 | n-j-1, i


    0,0 -> 0,3 | 3, 3 | 3, 0
           j, n - i - 1 | n -i - 1, n - j - 1| n -j - 1, i

    1,1 -> 1,2 | 2, 2 | 2, 1
           j, n - i - 1 | n - i - 1,n - j - 1 |  n -j - 1, i

 */

    public void rotate(int[][] matrix) {
        //print(matrix);
        System.out.println("ROTATE");
        int temp=0;
        int len = matrix.length;
        for(int i=0; i < len / 2; i++) {
            for(int j=i; j < len - i - 1; j++) {
                    temp = matrix[j][len - i - 1];
                    matrix[j][len - i - 1] = matrix[i][j];
                    matrix[i][j] = temp;
                    temp = matrix[len - i - 1][len - j - 1];
                    matrix[len - i - 1][len - j - 1] = matrix[i][j];
                    matrix[i][j] = temp;
                    temp = matrix[len - j - 1][i];
                    matrix[len - j - 1][i] = matrix[i][j];
                    matrix[i][j] = temp;
            }
        }
        //print(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    public void print(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j=0; j < matrix[i].length; j++) {
                System.out.println(i + " - " + j);
                System.out.println(matrix[i][j]);
            }
        }
    }
}
