package org.msharma.algorithm.problems;

/**
 * @author Mohan Sharma Created on 18/01/18.
 */
public class MaximumSumPathOfTwoArray
{
	public int getMaximumSumPathOfTwoArray(int[] arrayOne, int[] arrayTwo)
	{
		int i = 0;
		int j = 0;
		int maximumSum = 0;
		int lastCommonElementArrayOneAt = -1;
		int lastCommonElementArrayTwoAt = -1;
		while(i < arrayOne.length && j < arrayTwo.length)
		{
			if(arrayOne[i] == arrayTwo[j])
			{
				int currentFirstSum = 0;
				int currentFirstIndex = i - 1;
				int firstIteration = Math.max(lastCommonElementArrayOneAt, 0);
				while (currentFirstIndex >= firstIteration)
				{
					currentFirstSum += arrayOne[currentFirstIndex];
					currentFirstIndex--;
				}
				int currentSecondIndex = j - 1;
				int currentSecondSum = 0;
				int secondIteration = Math.max(lastCommonElementArrayOneAt, 0);
				while (currentSecondIndex >= secondIteration)
				{
					currentSecondSum += arrayTwo[currentSecondIndex];
					currentSecondIndex--;
				}
				maximumSum += (Math.max(currentFirstSum, currentSecondSum) + arrayOne[i]);
				lastCommonElementArrayOneAt = i;
				lastCommonElementArrayTwoAt = j;
				j++;
				i++;
			}
			else
			{
				j++;
				if(j >= arrayTwo.length)
				{
					j = lastCommonElementArrayTwoAt + 1;
					i++;
				}
			}
		}
		int currentFirstSum = 0;
		while(lastCommonElementArrayOneAt + 1  < arrayOne.length)
		{
			currentFirstSum += arrayOne[lastCommonElementArrayOneAt];
			lastCommonElementArrayOneAt++;
		}

		int currentSecondSum = 0;
		while(lastCommonElementArrayTwoAt + 1< arrayTwo.length)
		{
			currentSecondSum += arrayTwo[lastCommonElementArrayOneAt];
			lastCommonElementArrayTwoAt++;
		}

		maximumSum += (Math.max(currentFirstSum, currentSecondSum));
		return maximumSum;
	}

	public static void main(String[] args)
	{
		int[] arrayOne = { 3, 6, 7, 8, 10, 12, 15, 18, 100 };
		int[] arrayTwo = { 1, 2, 3, 5, 7, 9, 10, 11, 15, 16, 18, 25, 50 }; //64
		MaximumSumPathOfTwoArray maximumSumPathOfTwoArray = new MaximumSumPathOfTwoArray();
		int result = maximumSumPathOfTwoArray.getMaximumSumPathOfTwoArray(arrayOne, arrayTwo);
		System.out.println(result);
	}
}
