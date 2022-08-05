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

    // here timecomplexity is O(mn) where m is number of rows and n is the number of elements in a row
    public boolean searchMatrixMN(int[][] matrix, int target) {
        if (matrix.length == 0)
            return false;
        for (int[] row : matrix) {
            if (row.length == 0)
                return false;
            if (row[0] <= target && row[row.length-1] >= target)
                return binarySearch(row, target);
        }
        return false;
    }

    private boolean binarySearch(int[] row, int target) {
        int start = 0, end = row.length;
        while (start < end) {
            int mid = start + (end - start)/2;
            if (row[mid] == target)
                return true;
            else if (row[mid] < target)
                start = mid + 1;
            else end = mid;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Search2DMatrix().searchMatrixMN(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 2));
    }
}
