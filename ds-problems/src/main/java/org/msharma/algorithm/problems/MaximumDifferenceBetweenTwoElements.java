package org.msharma.algorithm.problems;

/**
 * @author Mohan Sharma Created on 21/11/17.
 */
public class MaximumDifferenceBetweenTwoElements
{
	public void printMaximumDifferenceBetweenElements(int[] array)
	{
		int maxElement=Integer.MIN_VALUE, minElement=Integer.MAX_VALUE;
		int diff = Integer.MIN_VALUE;
		int n = array.length;
		int max_so_far = array[n-1];

		for (int i = n - 2; i >= 0; i--)
		{
			if (array[i] > max_so_far)
				max_so_far = array[i];
			else
			{
				diff = Math.max(diff, max_so_far - array[i]);
				maxElement = max_so_far;
				minElement = array[i];
			}
		}

		System.out.println("Maximum difference between  " + maxElement + " - " + minElement + " = " + diff);
	}

	public static void main(String[] args)
	{
		int[] array = { 2, 7, 9, 5, 1, 3, 5 };
		MaximumDifferenceBetweenTwoElements maximumDifferenceBetweenTwoElements = new MaximumDifferenceBetweenTwoElements();
		maximumDifferenceBetweenTwoElements.printMaximumDifferenceBetweenElements(array);

	}
}
