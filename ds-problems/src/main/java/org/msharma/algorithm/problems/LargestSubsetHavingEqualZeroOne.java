package org.msharma.algorithm.problems;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author Mohan Sharma Created on 03/11/17.
 */
public class LargestSubsetHavingEqualZeroOne
{
	public void printLargestSubsetHavingEqualZeroOneBruteForce(int[] array)
	{
		int endIndex = -1;
		int maxLength = 0;
		for(int i=0; i<array.length; i++)
		{
			int zeroCount = 0;
			int oneCount = 0;
			for(int j=i; j<array.length; j++)
			{
				if(array[j] == 0)
					zeroCount++;
				else
					oneCount++;
				if(zeroCount == oneCount && maxLength < j - i + 1)
				{
					endIndex = j;
					maxLength = j - i + 1;
				}
			}
		}
		System.out.println("SubArray from " + (endIndex - maxLength + 1)+ " to " + endIndex);
	}

	public void printLargestSubsetHavingEqualZeroOneHashing(int[] array)
	{
		int endIndex = -1;
		int sum = 0;
		int maxLength = 0;
		Map<Integer, Integer> storage = Maps.newHashMap();
		for(int i=0; i<array.length; i++)
		{
			sum += (array[i] == 0 ? -1 : 1);
			if(storage.containsKey(sum) && maxLength < i - storage.get(sum))
			{
				maxLength = i - storage.get(sum);
				endIndex = i;
			}
			else
				storage.put(sum, i);
		}
		System.out.println("SubArray from " + (endIndex - maxLength + 1)+ " to " + endIndex);
	}

	public static void main(String[] args)
	{
		int[] array = { 0, 0, 1, 0, 1, 0, 0 };
		LargestSubsetHavingEqualZeroOne largestSubsetHavingEqualZeroOne = new LargestSubsetHavingEqualZeroOne();
		largestSubsetHavingEqualZeroOne.printLargestSubsetHavingEqualZeroOneHashing(array);
	}
}
