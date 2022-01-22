package org.msharma.algorithm.problems;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Mohan Sharma Created on 11/10/17.
 */
public class SubsetArraySumZeroFinder
{
	private int[] array;

	public SubsetArraySumZeroFinder(int[] array)
	{
		this.array = array;
	}

	private static void insert(Map<Integer, List> hashMap, Integer key, Integer value)
	{
		if (hashMap.containsKey(key))
			hashMap.get(key).add(value);
		else
		{
			List<Integer> list = Lists.newArrayList();
			list.add(value);

			hashMap.put(key, list);
		}
	}

	public static void printallSubarrays(int arr[])
	{
		// 3, 4, -7,  3,  1,  3,   1,  -4,  -2,  -2
		                   // 5
		int n = arr.length;
		Map<Integer, List> hashMap = Maps.newHashMap();
		hashMap.put(0, Lists.newArrayList(-1));

		int sum = 0;

		for (int i = 0; i < n; i++)
		{
			sum += arr[i];
			if (hashMap.containsKey(sum))
			{
				List<Integer> list = hashMap.get(sum);

				for (Integer value: list) {
					System.out.println("Subarray [" + (value + 1) + ".." +
							i + "]");
				}
			}

			if (hashMap.containsKey(sum))
				hashMap.get(sum).add(i);
			else
			{
				List<Integer> list = Lists.newArrayList();
				list.add(i);

				hashMap.put(sum, list);
			}
			//insert(hashMap, sum, i);
		}
	}

	public void printSubsetArrayWhoseElementsAddUptoZero()
	{
		Map<Integer, Integer> storage = Maps.newHashMap();
		int sum = 0;
		storage.put(sum, -1);
		for(int i=0; i<array.length; i++)
		{
			sum = sum + array[i];
			Integer oldIndex = storage.get(sum);
			if(Objects.isNull(oldIndex))
			{
				storage.put(sum, i);
			}
			else
			{
				System.out.println("from : " + (oldIndex + 1) + " to :" + (i));
			}
		}
	}

	public void printAllSubsetArrayWhoseElementsAddUptoZero()
	{
		Map<Integer, ArrayList<Integer>> storage = Maps.newHashMap();
		storage.put(0, Lists.newArrayList(-1));
		int sum = 0;
		for(int i=0; i<array.length; i++)
		{
			sum += array[i];
			if(storage.containsKey(sum))
			{
				List<Integer> indexList = storage.get(sum);
				for (Integer value: indexList) {
					System.out.println("Subset : " + Arrays.toString(Arrays.copyOfRange(array, value + 1, i + 1)));
				}
				storage.get(sum).add(i);
			}
			else
				storage.put(sum, Lists.newArrayList(i));
		}
	}

	public void printAllSubsetWhoseElementSumToZero()
	{
		Map<Integer, List<Integer>> storage = Maps.newHashMap();
		int sum = 0;
		storage.put(sum, Lists.newArrayList(-1));
		for(int i=0; i<array.length; i++)
		{
			sum += array[i];
			if(storage.containsKey(sum))
			{
				List<Integer> indexList = storage.get(sum);
				for (Integer value: indexList) {
					System.out.println("Subset : " + Arrays.toString(Arrays.copyOfRange(array, value + 1, i + 1)));
				}
				storage.get(sum).add(i);
			}
			else
			{
				storage.put(sum, Lists.newArrayList(i));
			}
		}
	}


	public void printLargestSubsetWhoseElementSumToZero()
	{
		Map<Integer, Integer> storage = Maps.newHashMap();
		int sum = 0;
		storage.put(sum, -1);
		int maxLength = 0;
		int endIndex = -1;
		for(int i=0; i<array.length; i++)
		{
			sum += array[i];
			if(storage.containsKey(sum) && maxLength < i - storage.get(sum))
			{
				maxLength = i - storage.get(sum);
				endIndex = i;
			}
			else
			{
				storage.put(sum, i);
			}
		}
		System.out.println("SubArray from " + (endIndex - maxLength + 1)+ " to " + endIndex);
	}

	public Set<Set<Integer>> subset(List<Integer> list)
	{
		Set<Set<Integer>> result = Sets.newHashSet();
		int numberOfSets = 1 << list.size();
		for(int i=0; i< numberOfSets; i++)
		{
			Set<Integer> set = Sets.newHashSet();
			int bitPosition = 1;
			for (int j = 0; j < list.size(); j++)
			{
				if ((bitPosition & i) != 0)
				{
					set.add(list.get(j));
				}
				bitPosition = bitPosition << 1;
			}
			result.add(set);
		}
		return result;
	}

	public static void main(String[] args)
	{
		int[] array = new int[]{ 3, 4, -7, 3, 1, 3, 1, -4, -2, -2};
		SubsetArraySumZeroFinder subsetArraySumZeroFinder = new SubsetArraySumZeroFinder(array);
		subsetArraySumZeroFinder.printAllSubsetWhoseElementSumToZero();
		//printallSubarrays(new int[]{ 3, 4, -7,  3,  1,  3,   1,  -4,  -2,  -2});
		//						   0   3  7	  0   3   4   7    8    4    2    0

	}
}
