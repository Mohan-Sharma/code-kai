package org.msharma.algorithm.problems;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author Mohan Sharma Created on 26/10/17.
 */
public class MajorityElementFinder
{
	public int getTheElementWhichExistsMaximumNumberOfTimesBruteForce(int[] array)
	{
		for(int i=0; i<array.length; i++)
		{
			int count = 0;
			for(int j=i; j<array.length; j++)
			{
				if(array[i] == array[j])
				{
					count++;
					if(count > array.length/2)
						return array[i];
				}
			}
		}
		return -1;
	}

	public int getTheElementWhichExistsMaximumNumberOfTimesBySorting(int[] array)
	{
		Arrays.sort(array);
		// count = 1 as element compared with itself,
		// e.g {2, 2, 2} as arr[i] compared to arr[i+1], index 0 and 1 when compared and found equal should give count 2
		int count = 1;
		for(int i=0; i<array.length-1; i++)
		{
			if(array[i] == array[i+1])
			{
				count++;
				if(count > array.length/2)
					return array[i];
			}
			else
			{
				count = 1;
			}
		}
		return -1;
	}

	public int getTheElementWhichExistsMaximumNumberOfTimesByHashing(int[] array)
	{
		Map<Integer, Integer> storage = Maps.newHashMap();
		for(int element : array)
		{
			if(storage.containsKey(element))
				storage.put(element, storage.get(element) + 1);
			else
				storage.put(element, 1);
		}
		for(Map.Entry<Integer, Integer> entry : storage.entrySet())
		{
			if(entry.getValue() > array.length/2)
				return entry.getKey();

		}
		return -1;
	}

	public int getTheElementWhichExistsMaximumNumberOfTimesByHashingJava8(int[] array)
	{
		final Predicate<Map.Entry<Integer, Integer>> filteringPredicate = entry -> entry.getValue() > (array.length / 2);
		Map<Integer, Integer> storage = Maps.newHashMap();
		for(int element : array)
		{
			storage.compute(element, (k, v) -> Objects.isNull(v) ? 1 : v + 1);
		}
		return storage.entrySet().stream().filter(filteringPredicate).findFirst().orElseThrow(IllegalArgumentException::new).getKey();
	}

	public int getTheElementWhichExistsMaximumNumberOfTimesByBoyerMoore(int[] array)
	{
		int maxIndex = 0, count = 1;
		for(int i=1; i<array.length; i++)
		{
			if(array[maxIndex] == array[i])
				count++;
			else
				count--;
			if(count == 0)
			{
				maxIndex = i;
				count = 1;
			}
		}
		int maxIndexElement = array[maxIndex];
		count = 0;
		for(int element : array)
		{
			if(element == maxIndexElement)
				count++;
			if(count >  array.length/2)
				return element;
		}
		return -1;
	}

	public static void main(String[] args)
	{
		int[] array = {0,2,2,3,3,3,3};
		MajorityElementFinder elementFinder = new MajorityElementFinder();
		int result = elementFinder.getTheElementWhichExistsMaximumNumberOfTimesByHashingJava8(array);
		System.out.println("Majority Element is : " + result);
	}
}
