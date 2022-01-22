package org.msharma.algorithm.recursion;

/**
 * @author Mohan Sharma Created on 27/08/17.
 */
public class Factorial
{
	public static void main(String[] args)
	{
		int num = 5;
		Factorial factorial = new Factorial();
		int result  = factorial.getFactorial(num);
		System.out.println("Factorial of " + num + " is : " + result);
	}

	private int getFactorial(int num)
	{
		if(num == 1)
			return 1;
		return num * getFactorial(num - 1);
	}
}
