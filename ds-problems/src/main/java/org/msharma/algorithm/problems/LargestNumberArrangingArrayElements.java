package org.msharma.algorithm.problems;

import java.util.Arrays;

/**
 * This class demonstrates how to find the largest number by arranging the elements of an array
 * @author Mohan Sharma Created on 29/12/17.
 */
public class LargestNumberArrangingArrayElements
{
	/**
	 * This function finds the largest possible number by arranging the array elements.
	 * @param array
	 * @return largest number
	 */
	private Integer[] getTheLargestNumberByArrangingArrayElements(Integer[] array)
	{
		Arrays.sort(array, (Integer first, Integer second) -> {
			String firstCombination = String.format("%s%s", first , second);
			String secondCombination = String.format("%s%s", second , first);
			return Long.parseLong(firstCombination) > Long.parseLong(secondCombination) ? -1 : 1;
		});
		return array;
	}

	public static void main(String[] args)
	{
		Integer[] array = {10, 68, 75, 7, 21, 12};
		LargestNumberArrangingArrayElements largestNumberArrangingArrayElements = new LargestNumberArrangingArrayElements();
		Integer[] result = largestNumberArrangingArrayElements.getTheLargestNumberByArrangingArrayElements(array);
		System.out.println(Arrays.toString(result));
	}
}
