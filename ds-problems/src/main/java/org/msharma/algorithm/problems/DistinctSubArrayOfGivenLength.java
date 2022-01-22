package org.msharma.algorithm.problems;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Mohan Sharma Created on 01/12/17.
 */
public class DistinctSubArrayOfGivenLength
{
	public void printDistinctSubArrayOfGivenLengthBruteForce(int[] array, int length)
	{
		for (int i = 0; i < array.length; i++)
		{
			int[] result = new int[length];
			int count = 0;
			result[count++] = array[i];
			int inc = i + 1;
			for (int j = i + 1; j < array.length; )
			{
				if (count < length)
				{
					result[count++] = array[j];
					j++;
				}
				else
				{
					System.out.println(Arrays.toString(result));
					count = 0;
					result = new int[length];
					result[count++] = array[i];
					inc++;
					j = inc;
				}
			}
		}
	}

	public void printAllSubsetsOfAnArray(int[] array)
	{
		int[] subsets = new int[array.length];
		printSubSets(array, subsets, 0);
	}

	private void printSubSets(int[] array, int[] subsets, int start)
	{
		if(start == array.length)
		{
			System.out.print("{");
			for(int i=0; i<subsets.length; i++)
			{
				if(subsets[i] >= 0)
					System.out.print(subsets[i] + ",");
			}
			System.out.print("}");
			System.out.println();
			return;
		}
		subsets[start] = Integer.MIN_VALUE;
		printSubSets(array, subsets, start + 1);
		subsets[start] = array[start];
		printSubSets(array, subsets, start + 1);

	}

	private void printAllSizeKSubset(int[] array, int[] data, int startIndex, int currentIndex, int maxLength)
	{
		if(currentIndex == maxLength)
		{
			System.out.println(Arrays.toString(data));
			return;
		}
		if(startIndex >= array.length)
			return;

		data[currentIndex] = array[startIndex];
		printAllSizeKSubset(array, data, startIndex + 1, currentIndex + 1, maxLength);
		printAllSizeKSubset(array, data, startIndex + 1, currentIndex , maxLength);

	}

	private void printAllDistinctSizeKSubset(int[] array, int[] subset, int startIndex, int endIndex, int currentIndex, int maxLength)
	{
		if(currentIndex == maxLength)
		{
			System.out.println(Arrays.toString(subset));
			return;
		}
		for(int i=startIndex; i<endIndex && (endIndex - i) >= (maxLength - currentIndex); i++)
		{
			subset[currentIndex] = array[i];
			printAllDistinctSizeKSubset(array, subset, i + 1,  endIndex, currentIndex + 1, maxLength);
			// for handling duplicates assume the array is sorted or sort it before passing to the function and
			/*while(array[i - 1] == array[i])
				i++;*/
		}
	}

	public static void main(String[] args)
	{
		int[] array = {1, 2, 3};
		int[] data = new int[2];
		DistinctSubArrayOfGivenLength distinctSubArrayOfGivenLength = new DistinctSubArrayOfGivenLength();
		//distinctSubArrayOfGivenLength.printAllDistinctSizeKSubset(array,  data, 0, array.length,  0, 2);
		distinctSubArrayOfGivenLength.printAllSubsetsOfAnArray(array);
	}
}
