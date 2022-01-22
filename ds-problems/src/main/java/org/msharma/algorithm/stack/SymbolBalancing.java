package org.msharma.algorithm.stack;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Mohan Sharma Created on 09/09/17.
 */
public class SymbolBalancing
{
	GenericStack<Character> genericStack = new GenericStack<>();

	public boolean isBalancedSymbols(String input) throws Exception
	{
		if(StringUtils.isEmpty(input))
			throw new Exception("Do not enter empty string");
		for (char c : input.toCharArray())
		{
			switch (c)
			{
			case ')':
			{
				if (!genericStack.isStackEmpty() && '(' == genericStack.peek())
					genericStack.pop();
				else
					return false;
				break;
			}
			case '}':
			{
				if (!genericStack.isStackEmpty() && '{' == genericStack.peek())
					genericStack.pop();
				else
					return false;
				break;
			}
			case ']':
			{
				if (!genericStack.isStackEmpty() && '[' == genericStack.peek())
					genericStack.pop();
				else
					return false;
				break;
			}
			default: {
				genericStack.push(c);
			}
			}
		}
		return genericStack.isStackEmpty() ? Boolean.TRUE : Boolean.FALSE;
	}
}
