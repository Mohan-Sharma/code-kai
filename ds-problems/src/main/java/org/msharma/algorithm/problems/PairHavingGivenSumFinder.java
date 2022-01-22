package org.msharma.algorithm.problems;

import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Mohan Sharma Created on 10/10/17.
 */
public class PairHavingGivenSumFinder
{
	private int[] array;

	public PairHavingGivenSumFinder(int[] array)
	{
		this.array = array;
	}

	public void printIndicesWhoseElementSumEqualsResultBruteForce(int sum)
	{
		for(int i=0; i<array.length;i++)
		{
			for(int j=i+1; j<array.length;j++)
			{
				if(array[i] + array[j] == sum)
					System.out.println("sum of element at index : " + i  + " and index : " + j + " is : " + sum);
			}
		}
	}

	public void printIndicesWhoseElementSumEqualsResultUsingSorting(int sum)
	{
		Arrays.sort(array);
		int low = 0;
		int high = array.length - 1;
		while(low < high)
		{
			if(array[low] + array[high] == sum)
				System.out.println("sum of element at index : " + low  + " and index : " + high + " is : " + sum);
			if(array[low] + array[high] < sum) low++;
			else high--;
		}
	}

	public void printIndicesWhoseElementSumEqualsResultUsingHashing(int sum)
	{
		Map<Integer, Integer> map = Maps.newHashMap();

		for(int i=0; i<array.length; i++)
		{
			if(map.containsKey(sum - array[i]))
				System.out.println("sum of element at index : " + map.get(sum - array[i])  + " and index : " + i + " is : " + sum);
			map.put(array[i], i);
		}
	}

	public static void main(String[] args)
	{
		int[] array = {2, 5, 6, 8, 9, 4};
		int sum = 10;
		PairHavingGivenSumFinder finder = new PairHavingGivenSumFinder(array);
		finder.printIndicesWhoseElementSumEqualsResultUsingHashing(sum);
	}
}
