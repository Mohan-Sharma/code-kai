package org.msharma.algorithm.stack;

import lombok.Getter;

/**
 * @author Mohan Sharma Created on 06/09/17.
 */
@Getter
public class StackUsingConstantArray
{
	private static int DEFAULT_CAPACITY = 10;
	private int top = -1;
	private int[] backingArr;
	private int capacity;

	public StackUsingConstantArray()
	{
		this(DEFAULT_CAPACITY);
	}

	public StackUsingConstantArray(int capacity)
	{
		this.capacity = capacity;
		backingArr = new int[capacity];
	}

	public boolean isStackEmpty()
	{
		return top < 0;
	}

	public boolean isStackFull()
	{
		return size() == capacity;
	}

	public int size()
	{
		return top + 1;
	}

	public void push(int data) throws Exception
	{
		if(isStackFull())
			throw new Exception("Stack is full");
		backingArr[++top] = data;
	}

	public int pop() throws Exception
	{
		if(isStackEmpty())
			throw new Exception("Stack is already empty");
		int top = top();
		backingArr[top--] = Integer.MIN_VALUE;
		return top;
	}

	public int top() throws Exception
	{
		if(isStackEmpty())
			throw new Exception("Stack is already empty");
		return backingArr[top];
	}
}
