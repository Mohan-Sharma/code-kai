package org.msharma.algorithm.stack;

/**
 * @author Mohan Sharma Created on 08/09/17.
 */
public class StackUsingLinkedLIst
{
	private SimpleLinkedList simpleLinkedList;

	public StackUsingLinkedLIst()
	{
		simpleLinkedList = new SimpleLinkedList();
	}

	public int size()
	{
		return simpleLinkedList.size();
	}

	public void push(int data)
	{
		simpleLinkedList.addFromHead(data);
	}

	public void pop() throws Exception
	{
		Node s = simpleLinkedList.getHead();
		Node secondNode = s.getNext();
		if(secondNode != null)
		{
			simpleLinkedList.setHead(secondNode);
			s = null;
		}
	}
}
