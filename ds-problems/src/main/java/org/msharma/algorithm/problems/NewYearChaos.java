package org.msharma.algorithm.problems;

/**
 * @author Mohan Sharma Created on 10/07/18.
 */
public class NewYearChaos
{
	static void  minimumBribes(final int[] array)
	{
		int firstElement = 1;
		int index = 0;
		int bribeCount = 0;
		boolean shouldPrintBribeCount = true;
		while(index < array.length && firstElement <= array.length)
		{
			int resultElement = array[index];int currentBrideCount = resultElement - firstElement;
			if(currentBrideCount > 2)
			{
				System.out.println("Too chaotic");
				shouldPrintBribeCount = false;
				break;
			}
			//bribeCount += currentBrideCount;
			for (int j = Math.max(0, array[index]-2); j < index; j++)
			{
				if (array[j] > array[index])
				{
					bribeCount++;
				}
			}
			firstElement++;
			index++;
		}
		if(shouldPrintBribeCount)
			System.out.println(bribeCount);
	}

	public static void main(String[] args)
	{
		int[] array = new int[] {1, 2, 5, 3, 7, 8, 6, 4};
		/*int bribeCount = 0;
		boolean shouldPrintBribeCount = true;
		for(int i = 0; i < array.length; i++)
		{
			if(array[i]-(i+1) > 2)
			{
				shouldPrintBribeCount = false;
				break;
			}
			for (int j = Math.max(0, i-2); j < i; j++)
			{
				if (array[j] > array[i])
				{
					bribeCount++;
				}
			}
		}
		if(shouldPrintBribeCount)
		{
			System.out.println(bribeCount);
		}
		else
		{
			System.out.println("too chaotic");
		}*/
		minimumBribes(array);
	}
}
