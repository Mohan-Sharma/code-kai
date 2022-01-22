package org.msharma.algorithm.stack;

import com.google.common.collect.Lists;

/**
 * @author Mohan Sharma Created on 11/09/17.
 */
public class ComplexInfixToPostfixEvaluation
{
	private GenericStack<Character> stack;

	public ComplexInfixToPostfixEvaluation()
	{
		this.stack = new GenericStack<>();
	}

	public String getPostfixString(String infix) throws Exception
	{
		infix = infix.replaceAll("\\s+", "");
		StringBuffer buffer = new StringBuffer();
		for(char c  : infix.toCharArray())
		{
			if(isOperand(c))
			{
				buffer.append(c);
				continue;
			}
			if(isOperator(c))
			{
				while(!stack.isStackEmpty() && '(' != stack.peek() && hasHigherPrecedence(stack.peek(), c))
				{
					buffer.append(stack.peek());
					stack.pop();
				}
				stack.push(c);
			}
			if('(' == c)
			{
				stack.push(c);
			}
			if(')' == c)
			{
				while(!stack.isStackEmpty() && '(' != stack.peek())
				{
					buffer.append(stack.peek());
					stack.pop();
				}
				stack.pop();
			}
		}
		while(!stack.isStackEmpty())
		{
			buffer.append(stack.peek());
			stack.pop();
		}
		return buffer.toString();
	}

	private boolean isOperand(Character c)
	{
		return String.valueOf(c).matches("^[a-zA-Z0-9]*$");
	}

	private boolean isOperator(Character c)
	{
		return Lists.newArrayList('+', '-', '%', '/', '*', '^').contains(c);
	}

	private boolean hasHigherPrecedence(Character optOne, Character optTwo)
	{
		int weightOne = getWeightOfOperator(optOne);
		int weightTwo = getWeightOfOperator(optTwo);
		return weightOne >= weightTwo;
	}

	private int getWeightOfOperator(Character opt)
	{
		int weight = -1;
		switch (opt)
		{
		case '^' :
		{
			weight = 4; break;
		}
		case '$' :
		{
			weight = 3; break;
		}
		case '*' : case '/' :
		{
			weight = 4; break;
		}
		case '+' : case '-' :
		{
			weight = 4; break;
		}
		}
		return weight;
	}

	public static void main(String[] args) throws Exception
	{
		String result = new ComplexInfixToPostfixEvaluation().getPostfixString("(A+B)*(C+D)-E");
		System.out.println(result);
	}

}
