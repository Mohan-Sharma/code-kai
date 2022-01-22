package org.msharma.algorithm.problems;

import java.util.Arrays;

/**
 * @author Mohan Sharma Created on 21/11/17.
 */
public class LargestBitonicSubArray
{
	public int[] getLargestBitonicSubarray(int[] array)
	{
		int[] inc = new int[array.length];
		int[] dec = new int[array.length];

		inc[0] = 1;
		for(int i = 1; i<array.length; i++)
		{
			inc[i] = 1;
			if(array[i] > array[i - 1])
				inc[i] = inc[i - 1] +  1;

		}

		dec[array.length - 1] = 1;
		for(int i=array.length-2; i>=0; i--)
		{
			dec[i] = 1;
			if(array[i] > array[i + 1])
				dec[i] = dec[i + 1] + 1;
		}
		int maxLength = 0,  beg = 0, end = 0;
		for(int i=0; i<array.length; i++)
		{
			if(maxLength < inc[i] + dec[i] - 1)
			{
				maxLength = inc[i] + dec[i] - 1;
				beg = i - inc[i] + 1;
				end = i + dec[i] - 1;
			}
		}
		return Arrays.copyOfRange(array, beg, end + 1);
	}

	public static void main(String[] args)
	{
		int[] array = { 3, 5, 8, 4, 5, 9, 10, 8, 5, 3, 4 };
		LargestBitonicSubArray largestBitonicSubArray = new LargestBitonicSubArray();
		int[] result = largestBitonicSubArray.getLargestBitonicSubarray(array);
		System.out.println(Arrays.toString(result));
	}
}
