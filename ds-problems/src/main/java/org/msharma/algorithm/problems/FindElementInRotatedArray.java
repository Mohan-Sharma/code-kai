package org.msharma.algorithm.problems;

/**
 * @author Mohan Sharma Created on 02/07/18.
 */
public class FindElementInRotatedArray
{
	private static int binarySearch(int[] array, int start, int end, int targetElement)
	{
		if(start > end)
			return -1;
		int midIndex = (start + end) / 2;
		if(targetElement < array[midIndex])
			return binarySearch(array, start, midIndex - 1, targetElement);
		else if(targetElement > array[midIndex])
			return binarySearch(array, midIndex + 1, end, targetElement);
		else return midIndex;
	}

	public static int getElementFromRotatedArray(int[] array, int start, int end, int targetElement)
	{
		if(start < end)
		{
			int midIndex = (start + end) / 2;
			int midElement = array[midIndex];
			if (midElement > array[start])
			{
				int result = binarySearch(array, start, midIndex, targetElement);
				if (result >= 0)
					return result;
				else
					return getElementFromRotatedArray(array, midIndex + 1, end, targetElement);
			}
			else if (midElement < array[end])
			{
				int result = binarySearch(array, midIndex, end, targetElement);
				if (result >= 0)
					return result;
				return getElementFromRotatedArray(array, start, midIndex - 1, targetElement);
			}
			else return midIndex;
		}
		return -1;
	}

	public static void main(String[] args)
	{
		int[] array = new int[] {23, 27, 29, 31, 37, 43, 49, 56, 64, 78, 91, 99, 1, 4, 11, 14, 15, 17, 19};
		int result = getElementFromRotatedArray(array, 0, array.length - 1, 37);
		System.out.println(result);
	}
}
