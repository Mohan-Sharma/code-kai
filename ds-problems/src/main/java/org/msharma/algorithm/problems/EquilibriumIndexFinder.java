package org.msharma.algorithm.problems;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author Mohan Sharma Created on 25/10/17.
 */
public class EquilibriumIndexFinder
{
	public List getEquilibriumIndicesBruteForce(int[] array)
	{
		List<Integer> indices = Lists.newArrayList();
		int count = 0;
		for(int i=0; i<array.length; i++)
		{
			int sumBeforeCurrentIndex = 0;
			for(int k = i -1; k>=0; k--)
			{
				sumBeforeCurrentIndex += array[k];
			}
			int sumTillEnd = 0;
			for(int j = i+1; j<array.length; j++)
			{
				sumTillEnd += array[j];
			}
			if(sumBeforeCurrentIndex ==  sumTillEnd)
			{
				indices.add(i);
			}
		}
		return indices;
	}

	public List getEquilibriumIndicesEfficiently(int[] array)
	{
		List<Integer> indices = Lists.newArrayList();
		int sumOfElements = 0;
		for(int element : array)
		{
			sumOfElements += element;
		}

		int leftSum = 0;
		for(int i=0; i<array.length; i++)
		{
			int element = array[i];
			sumOfElements = sumOfElements - element;
			if(leftSum == sumOfElements)
				indices.add(i);
			leftSum += element;
		}
		return indices;
	}

	public static void main(String[] args)
	{
		int[] array = { 0, -3, 5, -4, -2, 3, 1, 0 };
		EquilibriumIndexFinder equilibriumIndexFinder = new EquilibriumIndexFinder();
		List result = equilibriumIndexFinder.getEquilibriumIndicesEfficiently(array);
		System.out.println("Equilibrium Index at : " + result.toString());
	}
}
