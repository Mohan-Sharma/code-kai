package org.msharma.algorithm.recursion;


/**
 * @author Mohan Sharma Created on 27/08/17.
 */
public class Fibonacci
{
	public static void main(String[] args)
	{
		int num = 5;
		Fibonacci fibonacci = new Fibonacci();
		for(int i = 0; i<num ; i++)
		{
			System.out.print(fibonacci.getFibonacci(i) + " ");
		}
	}

	private int getFibonacci(int num)
	{
		if (num <= 1)
			return num;
		else
			return getFibonacci(num-1) + getFibonacci(num-2);
	}
}
