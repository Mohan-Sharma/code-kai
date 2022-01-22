package org.msharma.algorithm.stack;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class GenericStack<T>
{
	private static final int DEFAULT_CAPACITY = 10;
	private Object[] DEFAULT_EMPTY_ELEMENT_DATA = {};
	private T[] backingArr;
	private int capacity;
	private int top = -1;

	public GenericStack()
	{
		this.backingArr = (T[]) DEFAULT_EMPTY_ELEMENT_DATA;
	}

	public GenericStack(int capacity)
	{
		if(capacity > 0)
			this.capacity = capacity;
		if(capacity == 0)
			this.backingArr = (T[]) DEFAULT_EMPTY_ELEMENT_DATA;
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

	public void push(T data)
	{
		ensureCapacityInternal(top + 1);
		backingArr[++top] = data;
	}

	public T pop()
	{
		T oldData = (T) backingArr[top];
		backingArr[top--] = null;
		return oldData;
	}

	public void ensureCapacityInternal(int top)
	{
		if(this.backingArr == DEFAULT_EMPTY_ELEMENT_DATA)
		{
			top = Math.max(DEFAULT_CAPACITY, top);
		}
		if(top > backingArr.length)
		{
			int oldCapacity = top;
			int newCapacity = oldCapacity + oldCapacity << 1;
			backingArr = Arrays.copyOf(backingArr, newCapacity);
		}
	}

	public T peek() throws Exception
	{
		if (isStackEmpty())
			throw new Exception("Stack is already empty");
		return (T) backingArr[top];
	}
}