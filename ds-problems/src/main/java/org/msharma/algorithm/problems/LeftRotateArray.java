package org.msharma.algorithm.problems;

import java.util.Arrays;

/**
 * @author Mohan Sharma Created on 04/07/18.
 */
public class LeftRotateArray
{
	public int[] rotLeft(int[] a, int d)
	{
		reverse(a, 0, d - 1);
		reverse(a, d, a.length - 1);
		reverse(a, 0, a.length - 1);
		return a;
	}

	public void reverse(int[] array, int startIndex, int endIndex)
	{
		if(startIndex > endIndex)
			return;
		int temp = array[startIndex];
		array[startIndex] = array[endIndex];
		array[endIndex] = temp;
		reverse(array, startIndex + 1, endIndex - 1);
	}

	public static void main(String[] args)
	{
		int[] array = new int[] {1, 2, 3, 4, 5};
		LeftRotateArray leftRotateArray = new LeftRotateArray();
		array = leftRotateArray.rotLeft(array, 2);
		System.out.println(Arrays.toString(array));
	}
}
