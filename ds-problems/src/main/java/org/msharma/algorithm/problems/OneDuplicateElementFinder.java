package org.msharma.algorithm.problems;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

import static java.lang.Math.abs;

/**
 * @author Mohan Sharma Created on 12/10/17.
 */
public class OneDuplicateElementFinder
{
	public int findTheDuplicateElement(int[] array)
	{
		Arrays.sort(array);
		for(int i=0; i<array.length-1;i++)
		{
			if(array[i] == array[i+1])
				return array[i];
		}
		return -1;
	}

	public int findTheDuplicateElementUsingHashing(int[] array)
	{
		Map<Integer, Integer> map = Maps.newHashMap();
		for(int i=0;i <array.length; i++)
		{
			if(map.containsKey(array[i]))
				return array[i];
			else map.put(array[i], i);
		}
		return -1;
	}

	public int findTheDuplicateElementEfficiently(int[] array)
	{
		for(int i=0; i<array.length; i++)
		{
			int absValue = abs(array[i]);
			int indexElement = array[absValue -1];
			if(indexElement > 0)
				array[absValue - 1] = -indexElement;
			else
				return - indexElement;
		}
		return -1;
	}

	public int findDuplicateElementFromArrayContainingNMinusOneElement(int[] array)
	{
		for(int i=0; i<array.length; i++)
		{
			int index = abs(array[i]);
			if(array[index] < 0)
				return abs(array[i]);
			else
				array[index] = -array[index];
		}
		return -1;
	}

	public int findOneDuplicateElementFromArrayContainingNMinusOneElement(int[] array)
	{
		int sum = 0;
		int sumOfSquare = 0;
		for(int i=0; i<array.length; i++)
		{
			sum += array[i];
			sumOfSquare += (array[i] * array[i]);
		}

		int len = array.length;
		int a = (len * (len - 1))/2 - sum;
		int b = (((len * (len - 1) * ((2*len) - 1)) / 6) - sumOfSquare) / a;
		return (b - a) / 2;
	}

	public void duplicate(int[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			int index = array[i] % array.length;
			array[index] += array.length;
		}
		for (int i = 0; i < array.length; i++)
		{
			if ((array[i]/array.length) > 1)
				System.out.println(i);
		}
	}

	public static void main(String[] args)
	{
		int[] array = { 1, 2, 3, 4, 4, 4};
		OneDuplicateElementFinder oneDuplicateElementFinder = new OneDuplicateElementFinder();
		oneDuplicateElementFinder.duplicate(array);
		//System.out.println(result);
	}
}
