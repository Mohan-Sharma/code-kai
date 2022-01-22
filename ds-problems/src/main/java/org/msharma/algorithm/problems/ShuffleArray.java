package org.msharma.algorithm.problems;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Mohan Sharma Created on 24/10/17.
 */
public class ShuffleArray
{
	public int[] shuffleArrayFromTail(int[] array)
	{
		for(int i=array.length-1; i>=0; i--)
		{
			Random random = new Random();
			int j = random.nextInt(i + 1);
			int temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
		return array;
	}

	public int[] shuffleArrayFromHead(int[] array)
	{
		for(int i=0; i<array.length; i++)
		{
			Random random = new Random();
			int j = random.nextInt(array.length - i);
			int temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
		return array;
	}

	public static void main(String[] args)
	{
		int[] array = {1, 2, 3, 4};
		ShuffleArray shuffleArray = new ShuffleArray();
		int[] result = shuffleArray.shuffleArrayFromTail(array);
		System.out.println(Arrays.toString(result));
	}
}
