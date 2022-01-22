package org.msharma.algorithm.practice;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Mohan Sharma Created on 25/02/18.
 */
public class DynamicArray<T>
{
	private static Object[] EMPTY_ELEMENT_DATA= {};
	private static Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};
	private Object[] elementData;
	private int size;
	private static final int DEFAULT_SIZE = 10;

	public DynamicArray()
	{
		this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
	}

	public DynamicArray(int capacity)
	{
		if(capacity > 0)
		{
			elementData = new Object[capacity];
		}
		else if(capacity == 0)
		{
			elementData = EMPTY_ELEMENT_DATA;
		}
		else
		{
			throw new IllegalArgumentException("size should be greater than zero");
		}
	}

	public boolean add(T element)
	{
		ensureCapacityInternal(size + 1);
		elementData[size++] = element;
		return Boolean.TRUE;
	}

	private void ensureCapacityInternal(int minCapacity)
	{
		if(elementData == DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA)
		{
			minCapacity = Math.max(DEFAULT_SIZE, minCapacity);
		}
		ensureExplicitCapacity(minCapacity);
	}

	private void ensureExplicitCapacity(int minCapacity)
	{
		if(minCapacity - elementData.length > 0)
		{
			int oldCapacity = elementData.length;
			int newCapacity = oldCapacity + (oldCapacity >> 1);
			if(newCapacity < minCapacity)
				newCapacity = minCapacity;
			if(newCapacity > Integer.MAX_VALUE)
				newCapacity = Integer.MAX_VALUE;
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}

	public void add(T element, int index)
	{
		validateIndex(index);
		ensureCapacityInternal(size + 1);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = element;
		size++;
	}

	private void validateIndex(int index)
	{
		if(index > size || index < 0)
		{
			throw new IndexOutOfBoundsException("invalid index");
		}
	}

	public boolean remove(T element)
	{
		if(Objects.isNull(element))
		{
			for(int i=0; i<size; i++)
			{
				if(elementData[i] == null)
				{
					fastRemove(i);
					return true;
				}
			}
		}
		else
		{
			for(int i=0; i<size; i++)
			{
				if(element.equals(elementData[i]))
				{
					fastRemove(i);
					return true;
				}
			}
		}
		return false;
	}

	private void fastRemove(int index)
	{
		int toMove = size - index -1;
		if(toMove > 0)
		{
			System.arraycopy(elementData, index + 1, elementData, index, toMove);
		}
		elementData[--size] = null;
	}

	public T remove(int index)
	{
		validateIndexRemove(index);
		T oldValue = (T) elementData[index];
		int toMove = size - index - 1;
		if(toMove > 0)
		{
			System.arraycopy(elementData, index + 1, elementData, index, toMove);
		}
		elementData[--size] = null;
		return oldValue;
	}

	private void validateIndexRemove(int index)
	{
		if(index >= size || index < 0)
		{
			throw new IndexOutOfBoundsException("invalid index");
		}
	}

	public boolean contains(T element)
	{
		return indexOf(element) >= 0;
	}

	public int indexOf(T element)
	{
		if(Objects.isNull(element))
		{
			for(int i=0; i<size; i++)
			{
				if(elementData[i] == null)
				{
					return i;
				}
			}
		}
		else
		{
			for(int i=0; i<size; i++)
			{
				if(element.equals(elementData[i]))
				{
					return i;
				}
			}
		}
		return  -1;
	}
}
