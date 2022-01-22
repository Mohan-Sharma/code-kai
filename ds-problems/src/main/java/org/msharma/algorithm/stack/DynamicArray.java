package org.msharma.algorithm.stack;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author Mohan Sharma Created on 02/09/17.
 */
public class DynamicArray
{
	private static final int DEFAULT_CAPACITY = 10;
	private int[] elementData;
	private int[] DEFAULT_EMPTY_ELEMENT_DATA = {};
	private int size;

	public DynamicArray()
	{
		this.elementData = DEFAULT_EMPTY_ELEMENT_DATA;
	}

	public DynamicArray(int capacity)
	{
		if(capacity > 0)
			this.elementData = new int[capacity];
		else if(capacity == 0)
			this.elementData = DEFAULT_EMPTY_ELEMENT_DATA;
		else
			throw new IllegalArgumentException("Illegal Capacity: "+capacity);
	}

	public boolean add(int data)
	{
		ensureCapacityInternal(size + 1);
		elementData[size++] = data;
		return true;
	}

	private void ensureCapacityInternal(int minCapacity)
	{
		if(elementData == DEFAULT_EMPTY_ELEMENT_DATA)
		{
			minCapacity = Math.min(DEFAULT_CAPACITY, minCapacity);
		}
		ensureExplicitCapacity(minCapacity);
	}

	private void ensureExplicitCapacity(int minCapacity)
	{
		if(minCapacity - elementData.length > 0)
			grow(minCapacity);
	}

	private void grow(int minCapacity)
	{
		int oldCapacity = elementData.length;
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		if(newCapacity - minCapacity < 0)
			newCapacity = minCapacity;
		if (newCapacity - Integer.MAX_VALUE > 0)
			newCapacity = Integer.MAX_VALUE;
		elementData = Arrays.copyOf(elementData, newCapacity);
	}
}
