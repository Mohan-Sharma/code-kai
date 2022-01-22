package org.msharma.algorithm.stack;

import com.google.common.collect.Lists;

/**
 * @author Mohan Sharma Created on 16/09/17.
 */
public class PostfixExpressionEvaluator
{
	GenericStack<Integer> stack;

	public PostfixExpressionEvaluator()
	{
		this.stack = new GenericStack<>();
	}

	public int evaluatePostfixExpression(String postfixExp) throws Exception
	{
		for(char c : postfixExp.toCharArray())
		{
			if(isOperand(c))
			{
				stack.push(Integer.parseInt(String.valueOf(c)));
				continue;
			}
			if(isOperator(c))
			{
				int optTwo = stack.peek();
				stack.pop();
				int optOne = stack.peek();
				stack.pop();
				int result = performOperation(optOne, optTwo, c);
				stack.push(result);
			}
		}
		int finalResult = stack.peek();
		stack.pop();
		return finalResult;
	}

	private int performOperation(int operandOne, int operandTwo, char operator)
	{
		int result = 0;
		switch (operator)
		{
		case '+' : result = operandOne + operandTwo; break;
		case '-' : result = operandOne - operandTwo; break;
		case '/' : result = operandOne / operandTwo; break;
		case '*' : result = operandOne * operandTwo; break;
		case '%' : result = operandOne % operandTwo; break;
		case '^' : result = operandOne ^ operandTwo; break;
		}
		return result;
	}

	private boolean isOperand(Character c)
	{
		return String.valueOf(c).matches("^[a-zA-Z0-9]*$");
	}

	private boolean isOperator(Character c)
	{
		return Lists.newArrayList('+', '-', '%', '/', '*', '^').contains(c);
	}

	public static void main(String[] args) throws Exception
	{
		int result = new PostfixExpressionEvaluator().evaluatePostfixExpression("23+45+*5-");
		System.out.println(result);
	}
}
