package org.msharma.algorithm.problems;

import java.util.Arrays;

import static java.lang.Math.max;

/**
 * @author Mohan Sharma Created on 30/11/17.
 */
public class MaximumSumCircularSubArrayProblem
{
	private int getMaximumSumSubArrayUsingKadaneAlgo(int[] array)
	{
		int maxSum = array[0];
		int currentIterationSum = array[0];
		for(int i=1; i<array.length; i++)
		{
			currentIterationSum = max(array[i], currentIterationSum + array[i]);
			if(currentIterationSum > maxSum)
				maxSum = currentIterationSum;
		}
		return maxSum;
	}

	public int[] getMaximumSumCircularSubArrayUsingKadaneAlgo(int[] array)
	{
		int sumOfAllElements = 0;
		for(int i=0; i<array.length; i++)
		{
			sumOfAllElements += array[i];
			array[i] = -array[i];
		}
		int minSumSubArray = getMaximumSumSubArrayUsingKadaneAlgo(array);

		for(int i=0; i<array.length; i++)
		{
			array[i] = -array[i];
		}
		int maxSumCircularSubArray = max(getMaximumSumSubArrayUsingKadaneAlgo(array), sumOfAllElements + minSumSubArray);
		System.out.println("Maximum sum of circular sub array is : " + maxSumCircularSubArray);
		return array;
	}

	public static void main(String[] args)
	{
		int[] array = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		MaximumSumCircularSubArrayProblem maximumSumSubArrayProblem = new MaximumSumCircularSubArrayProblem();
		int[] result = maximumSumSubArrayProblem.getMaximumSumCircularSubArrayUsingKadaneAlgo(array);
		System.out.println(result);
	}
}
