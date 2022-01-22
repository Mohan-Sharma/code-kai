package org.msharma.algorithm.problems;

import java.util.Arrays;

/**
 * @author Mohan Sharma Created on 24/10/17.
 */
public class SortAlternateElements
{
	public int[] sortAlternateHighAndLowElement(int[] array)
	{
		for(int i=0; i<array.length - 1; i=i+2)
		{
			if(array[i] > array[i+1])
			{
				int temp = array[i];
				array[i] = array [i+1];
				array[i+1] = temp;
			}
		}
		return array;
	}


	public static void main(String[] args)
	{
		SortAlternateElements sortAlternateElements = new SortAlternateElements();
		int[] array = { 9, 6, 8, 3, 4, 5};
		int[] result = sortAlternateElements.sortAlternateHighAndLowElement(array);
		System.out.println(Arrays.toString(result));
	}
}
