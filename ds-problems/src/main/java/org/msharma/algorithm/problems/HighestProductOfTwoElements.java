package org.msharma.algorithm.problems;

import java.util.Arrays;

/**
 * @author Mohan Sharma Created on 03/11/17.
 */
public class HighestProductOfTwoElements
{
	public void printElementsHavingHighestProductBruteForce(int[] array)
	{
		int highestProduct = 0;
		int numberOne= 0, numberTwo = 0;
		for(int i=0; i<array.length; i++)
		{
			for(int j = i+1; j<array.length; j++)
			{
				int currentProduct = array[i] * array[j];
				if(highestProduct < currentProduct)
				{
					highestProduct = currentProduct;
					numberOne = array[i];
					numberTwo = array[j];
				}
			}
		}
		System.out.println("Highest Product of " + numberOne + " * " +numberTwo + " is : "+highestProduct);
	}

	public void printElementsHavingHighestProductSorting(int[] array)
	{
		Arrays.sort(array);
		int minHighestElement = array[0];
		int secondMinHighestElement = array[1];
		int highestElement = array[array.length - 1];
		int secondHighestElement = array[array.length - 2];
		int productOfMinHighestElements = minHighestElement*secondMinHighestElement;
		int productOfHighestElements = highestElement*secondHighestElement;
		if(productOfMinHighestElements > productOfHighestElements)
			System.out.println("Highest Product of " + minHighestElement + " * " +secondHighestElement + " is : "+ productOfMinHighestElements);
		else
			System.out.println("Highest Product of " + highestElement + " * " + secondHighestElement + " is : "+ productOfHighestElements);
	}

	public void printElementsHavingHighestProductEfficiently(int[] array)
	{
		int minHighestElement = array[0];
		int secondMinHighestElement = Integer.MAX_VALUE;
		int highestElement = array[0];
		int secondHighestElement = Integer.MIN_VALUE;
		for(int i=0; i<array.length; i++)
		{
			int currentElement = array[i];
			if(currentElement > highestElement)
			{
				secondHighestElement = highestElement;
				highestElement = currentElement;
			}
			if(currentElement < minHighestElement)
			{
				secondMinHighestElement = minHighestElement;
				minHighestElement = currentElement;
			}
		}
		int productOfMinHighestElements = minHighestElement*secondMinHighestElement;
		int productOfHighestElements = highestElement*secondHighestElement;
		if(productOfMinHighestElements > productOfHighestElements)
			System.out.println("Highest Product of " + minHighestElement + " * " +secondHighestElement + " is : "+ productOfMinHighestElements);
		else
			System.out.println("Highest Product of " + highestElement + " * " + secondHighestElement + " is : "+ productOfHighestElements);
	}

	public static void main(String[] args)
	{
		int[] array = { -10, -3, 5, 6, -2 };
		HighestProductOfTwoElements highestProductOfTwoElements = new HighestProductOfTwoElements();
		highestProductOfTwoElements.printElementsHavingHighestProductEfficiently(array);
	}
}
