package org.msharma.algorithm.stack;

import com.google.common.collect.Lists;

/**
 * @author Mohan Sharma Created on 09/09/17.
 */
public class SimpleInfixToPostfixEvaluation
{
	GenericStack<Character> stack;

	public SimpleInfixToPostfixEvaluation()
	{
		stack = new GenericStack<>();
	}


	public String getPostfixFromInfixExpression(String infix) throws Exception
	{
		StringBuffer b = new StringBuffer();
		infix = infix.replaceAll("\\s+", "");
		for (char c : infix.toCharArray())
		{
			if (isOperand(c))
			{
				b.append(c);
				continue;
			}
			else
			{
				while (!stack.isStackEmpty() && hasHigherPrecedence(c, stack.peek()))
				{
					b.append(stack.peek());
					stack.peek();
				}
				stack.push(c);
			}
		}
		while(!stack.isStackEmpty())
		{
			b.append(stack.peek());
			stack.pop();
		}
		return b.toString();
	}

	private boolean hasHigherPrecedence(char toBeCompared, char compareWith)
	{
		int firstOptWeight = getOperatorWeight(toBeCompared);
		int secondOptWeight = getOperatorWeight(compareWith);
		return secondOptWeight >= firstOptWeight;
	}

	private int getOperatorWeight(char op)
	{
		int weight = -1;
		switch (op)
		{
		case '+': case '-':
			weight = 1;
			break;
		case '*': case '/':
			weight = 2;
			break;
		case '$':
			weight = 3;
			break;
		}
		return weight;
	}

	public boolean isOperator(char character)
	{
		return Lists.newArrayList('+', '-', '*', '/', "%", "^").contains(character);
	}

	public boolean isOperand(char character)
	{
		String pattern= "^[a-zA-Z0-9]*$";
		return String.valueOf(character).matches(pattern);
	}

	public static void main(String[] args) throws Exception
	{
		SimpleInfixToPostfixEvaluation simpleInfixToPostfixEvaluation = new SimpleInfixToPostfixEvaluation();
		String result = simpleInfixToPostfixEvaluation.getPostfixFromInfixExpression("a+b*c");
		System.out.println(result);
	}
}
