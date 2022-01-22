package org.msharma.algorithm.problems;

import java.util.Arrays;

/**
 * @author Mohan Sharma Created on 05/11/17.
 */
public class ConsecutiveElementsInArrayChecker
{
	public boolean checkIfArrayContainsConsecutiveElementSorting(int[] array)
	{
		Arrays.sort(array);
		for(int i=0; i<array.length - 1; i++)
		{
			if(array[i] + 1 != array[i+1])
				return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	// doesn't work for negative integers
	public boolean checkIfArrayContainsConsecutivePositiveElement(int[] array)
	{
		int maxElement = array[0];
		int minElement = array[0];
		for(int i=0; i<array.length; i++)
		{
			if(array[i] > maxElement)
				maxElement = array[i];
			if(array[i] < minElement)
				minElement = array[i];
		}
		if(maxElement - minElement + 1 != array.length)
			return Boolean.FALSE;
		for(int i=0; i<array.length; i++)
		{
			int index = Math.abs(array[i] - minElement);
			if(array[index] < 0)
				return Boolean.FALSE;
			else
				array[index] = -array[index];
		}
		return Boolean.TRUE;
	}

	// doesn't work for negative integers
	public boolean checkIfArrayContainsConsecutiveIntegers(int[] array)
	{
		int maxElement = array[0];
		int minElement = array[0];
		for(int i=0; i<array.length; i++)
		{
			if(array[i] > maxElement)
				maxElement = array[i];
			if(array[i] < minElement)
				minElement = array[i];
		}
		if(maxElement - minElement + 1 != array.length)
			return Boolean.FALSE;
		for(int i=0; i<array.length; i++)
		{
			int index = Math.abs(array[i] - minElement);
			if(array[index] < 0)
				return Boolean.FALSE;
			else
				array[index] = -array[index];
		}
		return Boolean.TRUE;
	}

	public boolean checkIfArrayContainsConsecutivePositiveIntegersUsingExtraSpace(int[] array)
	{
		int maxElement = array[0];
		int minElement = array[0];
		boolean[] visited = new boolean[array.length];
		for(int i=0; i<array.length; i++)
		{
			if(array[i] > maxElement)
				maxElement = array[i];
			if(array[i] < minElement)
				minElement = array[i];
		}
		if(maxElement - minElement + 1 != array.length)
			return Boolean.FALSE;

		for(int i=0; i<array.length; i++)
		{
			int index = array[i] - minElement;
			if(visited[index])
				return Boolean.FALSE;
			else
				visited[index] = true;
		}
		return Boolean.TRUE;
	}

	public static void main(String[] args)
	{
		int[] array = {0, 1, -3, -2, -1};
		ConsecutiveElementsInArrayChecker consecutiveElementsInArrayChecker = new ConsecutiveElementsInArrayChecker();
		boolean result = consecutiveElementsInArrayChecker.checkIfArrayContainsConsecutivePositiveIntegersUsingExtraSpace(array);
		System.out.println("Is given array consecutive? : " + result);
	}
}
