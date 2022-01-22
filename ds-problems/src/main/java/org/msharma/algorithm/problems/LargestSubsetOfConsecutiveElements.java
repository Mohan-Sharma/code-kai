package org.msharma.algorithm.problems;

import java.util.Arrays;

/**
 * @author Mohan Sharma Created on 13/10/17.
 */
public class LargestSubsetOfConsecutiveElements
{
	public int[] getLargestSubsetOfConsecutiveElements(int[] array)
	{
		int start = 0, end = 0, len=1;
		for(int i=0; i<array.length; i++)
		{
			int minValue = array[i];
			int maxValue = array[i];
			for(int j=i+1; j<array.length; j++)
			{
				minValue = Math.min(minValue, array[j]);
				maxValue = Math.max(maxValue, array[j]);
				if(areConsecutiveElements(array, i, j, minValue, maxValue))
				{
					if(len < (maxValue - minValue + 1))
					{
						len = (maxValue - minValue) + 1;
						start = i;
						end = j;
					}
				}
			}
		}
		return Arrays.copyOfRange(array, start, end+1);
	}

	private boolean areConsecutiveElements(int[] array, int i, int j, int minValue, int maxValue)
	{
		boolean[] visited = new boolean[j - i + 1];
		if(maxValue - minValue != j -i) return false;
		for(int k=i; k<=j; k++)
		{
			if(visited[array[k] -minValue]) return false;
			else visited[array[k] - minValue] = true;
		}
		return true;
	}

	public static void main(String[] args)
	{
		int[] array = { 2, 0, 2, 1, 4, 3, 1, 0 };
		LargestSubsetOfConsecutiveElements largestSubsetOfConsecutiveElements = new LargestSubsetOfConsecutiveElements();
		int[] result = largestSubsetOfConsecutiveElements.getLargestSubsetOfConsecutiveElements(array);
		System.out.println(Arrays.toString(result));
	}
}
