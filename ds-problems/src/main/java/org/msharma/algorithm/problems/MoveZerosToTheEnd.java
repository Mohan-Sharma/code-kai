package org.msharma.algorithm.problems;

import java.util.Arrays;

/**
 * @author Mohan Sharma Created on 26/10/17.
 */
public class MoveZerosToTheEnd
{
	public int[] moveAllZerosToTheEndOneScanMethodOne(int[] array)
	{
		int current = 0, start = 0, end = array.length - 1;
		while(start <= end)
		{
			if(array[start] > 0)
			{
				swap(array, current, start); //1,0,1,1,0
				current++; start++;
			}
			else
				start++;
		}
		return array;
	}

	public int[] moveAllZerosToTheEndOneScanMethodTwo(int[] array)
	{
		int j = 0;
		for(int i=0; i<array.length; i++)
		{
			if(array[i] != 0)
			{
				swap(array, j, i);
				j++;
			}
		}
		return array;
	}

	public int[] moveAllZerosToTheEndOneScanMethodThree(int[] array)
	{
		int k = 0;
		for(int i=0; i<array.length; i++)
		{
			if(array[i] != 0)
				array[k++] = array[i];
		}
		for(int i=k; i<array.length; i++)
		{
			array[i] = 0;
		}
		return array;
	}

	private void swap(int[] array, int swapItIndex, int swapWithIndex)
	{
		int temp = array[swapItIndex];
		array[swapItIndex] = array[swapWithIndex];
		array[swapWithIndex] = temp;
	}

	public static void main(String[] args)
	{
		int[] array = { 1,1,1,1,0,1};
		MoveZerosToTheEnd moveZerosToTheEnd = new MoveZerosToTheEnd();
		int[] result = moveZerosToTheEnd.moveAllZerosToTheEndOneScanMethodOne(array);
		System.out.println(Arrays.toString(result));
	}
}
