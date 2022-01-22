package org.msharma.algorithm.recursion;

import java.util.Arrays;

/**
 * @author Mohan Sharma Created on 24/03/18.
 */
public class BinaryStringProblem
{
	public void generateBinaryString(int n, String[] array)
	{
		if(n < 1)
		{
			System.out.println(Arrays.toString(array));
			return;
		}
		if(array[n-1] == "?")
		{
			array[n-1] = "1";
			generateBinaryString(n - 1, array);
			array[n-1] = "0";
			generateBinaryString(n - 1, array);
			array[n-1] = "?";
		}
		else
		{
			generateBinaryString(n - 1, array);
		}
	}

	public static void main(String[] args)
	{
		String[] array = new String[]{"1", "?", "?" , "0", "?", "1", "0", "1"};
		BinaryStringProblem binaryStringProblem = new BinaryStringProblem();
		binaryStringProblem.generateBinaryString(array.length, array);
	}
}
