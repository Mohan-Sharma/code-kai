package top100.liked.medium.binarysearch;

/**
 * @author Mohan Sharma
 */
public class Search2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0)
            return false;
        int start = 0, row = 0, end = matrix[0].length;

        while (start < end && row < matrix.length) {
            int mid = start + (end - start)/2;
            if (matrix[row][mid] == target)
                return true;
            else if (matrix[row][mid] <  target) {
                if (matrix[row][matrix[0].length - 1] <  target) {
                    row++;
                    start = 0;
                    end = matrix[0].length;
                } else {
                    start = mid + 1;
                }
            } else {
                end = mid;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Search2DMatrix().searchMatrix(new int[][]{{1}}, 2));
    }
}
