package org.msharma.algorithm.linkedlist;

import java.util.Objects;

/**
 * @author Mohan Sharma Created on 06/10/17.
 */
public class CheckLoopUsingOsterMiller<K>
{
	private SingleLinkedList<K> singleLinkedList;

	public CheckLoopUsingOsterMiller(SingleLinkedList<K> singleLinkedList)
	{
		this.singleLinkedList = singleLinkedList;
	}

	public boolean isLinkedListCircular() throws Exception
	{
		Node<K> head = singleLinkedList.getHead();
		if(Objects.isNull(head))
			throw new Exception("List doesn't contain any element!");
		Node<K> currentNode = head;
		Node<K> pointingNode = head.getNext();
		int since = 0;
		int sinceNode = 2;
		do
		{
			if (pointingNode == currentNode)
				return Boolean.TRUE;
			if (since >= sinceNode)
			{
				currentNode = pointingNode;
				sinceNode = 2 * since;
				since = 0;
			}
			since++;
			pointingNode = pointingNode.getNext();
		} while(pointingNode != null);
		return Boolean.FALSE;
	}

	public static void main(String[] args) throws Exception
	{
		SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>();
		for(int i=1; i<8; i++)
		{
			singleLinkedList.insertFromTail(i);
		}
		singleLinkedList.makeItCyclic(8);
		CheckLoopUsingOsterMiller checkLoopUsingOsterMiller = new CheckLoopUsingOsterMiller(singleLinkedList);
		System.out.println("Is LinkedList Cyclic : " + checkLoopUsingOsterMiller.isLinkedListCircular());
	}
}
