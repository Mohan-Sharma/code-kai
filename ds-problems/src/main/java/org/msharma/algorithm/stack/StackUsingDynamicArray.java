package org.msharma.algorithm.stack;

import java.util.Arrays;

/**
 * @author Mohan Sharma Created on 08/09/17.
 */
public class StackUsingDynamicArray
{
	private static final int DEFAULT_CAPACITY = 10;
	private int[] backingArr;
	private int capacity;
	private int top = -1;

	public StackUsingDynamicArray()
	{
		this(DEFAULT_CAPACITY);
	}

	public StackUsingDynamicArray(int capacity)
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

	public void push(int data)
	{
		ensureCapacityInteral();
		backingArr[++top] = data;
	}

	public int pop()
	{
		int oldData = backingArr[top];
		backingArr[top--] = Integer.MIN_VALUE;
		return oldData;
	}

	private void ensureCapacityInteral()
	{
		if(isStackFull())
		{
			int oldCapacity = capacity;
			int newCapacity = oldCapacity + oldCapacity << 1;
			backingArr = Arrays.copyOf(backingArr, newCapacity);
		}
	}
}
