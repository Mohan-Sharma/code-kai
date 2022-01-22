package org.msharma.algorithm.problems;

/**
 * @author Mohan Sharma Created on 04/07/18.
 */
public class HourglassProblem2dArray
{
	public static int getMaximumHourGlassSum(int[][] array)
	{
		int maxSum = Integer.MIN_VALUE;
		for(int i=0; i<array.length - 2; i++)
		{
			for(int j=0; j<array.length - 2; j++)
			{
				int currentSum = array[i][j] + array[i][j + 1] + array[i][j + 2] + array[i + 1][j + 1] + array[i + 2][j] + array[i + 2][j + 1] + array[i + 2][j + 2];
				if(currentSum > maxSum)
					maxSum = currentSum;
			}
		}
		return maxSum;
	}

	public static void main(String[] args)
	{
		int[][] array = {{1,1 ,1 ,0 ,0 ,0},{0,1 ,0 ,0 ,0 ,0},{1,1 ,1 ,0 ,0 ,0},{0,9 ,2 ,-4 ,-4 ,0},{0,0 ,0 ,-2 ,0 ,0},{0,0 -1 ,-2 ,-4 ,0}};
		int result = getMaximumHourGlassSum(array);
		System.out.println(result);
	}
}
