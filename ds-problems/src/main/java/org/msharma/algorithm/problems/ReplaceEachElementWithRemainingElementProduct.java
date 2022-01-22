package org.msharma.algorithm.problems;

import java.util.Arrays;

/**
 * @author Mohan Sharma Created on 03/11/17.
 */
public class ReplaceEachElementWithRemainingElementProduct
{
	public int[] getTheArrayHavingEachElementAsProductOfRemainingElements(int[] array)
	{
		int[] result = new int[array.length];
		for(int i=0; i<array.length; i++)
		{
			int product = 1;
			int currentElement = array[i];
			for(int j=0; j<array.length; j++)
			{
				if(array[j] != currentElement)
					product *= array[j];
			}
			result[i] = product;
		}
		return result;
	}

	public int[] getTheArrayHavingEachElementAsProductOfRemainingElementsEfficiently(int[] array) //1, 2, 3, 4, 5, 6
	{
		int[] result = new int[array.length];
		int product = 1;
		for(int i=0; i<array.length; i++)
		{
			result[i] = product; // 1 1 2 6 24 120 [p p*a0 p*a0*a1 p*a0*a1*a2 p*a0*a1*a2*a3 p*a0*a1*a2*a3*a4] where p=1
			product *= array[i];
		}
		product = 1;
		for (int j=array.length - 1; j>=0; j--)
		{
			result[j] *=product; //720 120  60  40  30  24   [r0*a1*a2*a3*a4*a5*p  r1*a2*a3*a4*a5*p  r2*a3*a4*a5*p  r3*a4*a5*p  r4*a5*p r5*p] where p=1
			product *=array[j];
		}
		return result;
	}

	public int getTheArrayHavingEachElementAsProductOfRemainingElementsRecursively(int[] array, int fwdProduct, int index) //1, 2, 3, 4, 5
	{
		int revProduct = 1;
		if(index < array.length)
		{
			revProduct = getTheArrayHavingEachElementAsProductOfRemainingElementsRecursively(array, array[index]*fwdProduct, index + 1);
			int currentElement = array[index];
			array[index] = fwdProduct * revProduct;
			revProduct *= currentElement;
		}
		return revProduct;
	}

	public static void main(String[] args)
	{
		int[] array = {1, 2, 3, 4};
		System.out.println("Input : " + Arrays.toString(array));
		ReplaceEachElementWithRemainingElementProduct object = new ReplaceEachElementWithRemainingElementProduct();
		object.getTheArrayHavingEachElementAsProductOfRemainingElementsRecursively(array, 1, 0);
		System.out.println(Arrays.toString(array));
		//System.out.println("Output : " + Arrays.toString(result));
	}
}
