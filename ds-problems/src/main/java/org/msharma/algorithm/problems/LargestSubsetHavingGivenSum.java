package org.msharma.algorithm.problems;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * @author Mohan Sharma Created on 02/11/17.
 */
public class LargestSubsetHavingGivenSum
{
	public void getLargestSubsetArrayHavingGivenSumBruteForce(int[] array, int sumToMatch)
	{
		int maxLength = 0;
		int endIndex = -1;
		for(int i=0; i<array.length; i++)
		{
			int sum = 0;
			for(int j= i; j<array.length; j++)
			{
				sum += array[j];
				if(sum == sumToMatch)
				{
					System.out.println(i + " -- " + j);
					int lengthOfSubset = j - i + 1;
					if(lengthOfSubset > maxLength)
					{
						maxLength = lengthOfSubset;
						endIndex = j;
					}
				}
			}
		}
		System.out.println("SubArray from " + (endIndex - maxLength + 1)+ " to " + endIndex);
	}

	public void getLargestSubsetArrayHavingGivenSumHashing(int[] array, int sumToMatch)
	{
		Map<Integer, Integer> storage = Maps.newHashMap();
		storage.put(0, -1);
		int sum = 0;
		int maxLength = 0;
		int endIndex = -1;
		for (int i=0; i<array.length; i++)
		{
			sum += array[i];
			if(!storage.containsKey(sum))
			{
				storage.put(sum, i);
			}
			if(storage.containsKey(sum - sumToMatch) &&  maxLength < i - storage.get(sum - sumToMatch))
			{
				maxLength = i - storage.get(sum - sumToMatch);
				endIndex = i;
			}
		}
		System.out.println("SubArray from " + (endIndex - maxLength + 1)+ " to " + endIndex);
	}

	public static void main(String[] args)
	{
		int[] array = { 5, 6, -5, 5, 3, 5, 3, -2, -1 };
					//0	5  11  6  11 14 19 22 20  19
					// -3  3  -2  3   6 11 16 12  11
		LargestSubsetHavingGivenSum largestSubsetHavingGivenSum = new LargestSubsetHavingGivenSum();
		largestSubsetHavingGivenSum.getLargestSubsetArrayHavingGivenSumHashing(array, 8);
	}
}
