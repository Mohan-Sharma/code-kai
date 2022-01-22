package org.msharma.algorithm.linkedlist;

/**
 * @author Mohan Sharma Created on 30/09/17.
 */
public class StackUsingLinkedList<K>
{
	private int top;

	private SingleLinkedList<K> singleLinkedList;

	public StackUsingLinkedList(SingleLinkedList<K> singleLinkedList)
	{
		this.singleLinkedList = singleLinkedList;
	}

	public boolean isStackEmpty()
	{
		return this.singleLinkedList.isLinkedListEmpty();
	}

	public void push(K data)
	{
		singleLinkedList.insertFromHead(data);
	}

	public K pop() throws Exception
	{
		return singleLinkedList.removeFromHead();
	}

	public int size()
	{
		return singleLinkedList.getSize();
	}

	public static void main(String[] args) throws Exception
	{
		SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>();
		StackUsingLinkedList stackUsingLinkedList = new StackUsingLinkedList(singleLinkedList);
		System.out.println("Is stack empty ? : "+stackUsingLinkedList.isStackEmpty());
		stackUsingLinkedList.push(2);
		stackUsingLinkedList.singleLinkedList.iterateList();
		System.out.println("Is stack empty ? : "+stackUsingLinkedList.isStackEmpty());
		System.out.println("size of stack : "+ stackUsingLinkedList.size());
		System.out.println("popped element : " +stackUsingLinkedList.pop());
		System.out.println("Is stack empty ? : "+stackUsingLinkedList.isStackEmpty());
		System.out.println("size of stack : "+ stackUsingLinkedList.size());
	}
}
