package org.msharma.algorithm.linkedlist;

import java.util.Objects;

/**
 * @author Mohan Sharma Created on 02/10/17.
 */
public class NthNodeFromEndSingleScan<K>
{
	private SingleLinkedList<K> singleLinkedList;

	public NthNodeFromEndSingleScan(SingleLinkedList<K> singleLinkedList)
	{
		this.singleLinkedList = singleLinkedList;
	}

	public Node<K> getNthNodeFromEndInSingleScan(int n) throws Exception
	{
		Node<K> tempNode = singleLinkedList.getHead();
		if(Objects.isNull(tempNode) || singleLinkedList.isLinkedListEmpty())
		{
			throw new Exception("List does not contain element");
		}
		Node<K> currentPointer = singleLinkedList.getHead();
		while (n > 0)
		{
			if(Objects.isNull(tempNode))
				throw new Exception("List does not contain element");
			tempNode = tempNode.getNext();
			n--;
		}
		while(Objects.nonNull(tempNode))
		{
			tempNode = tempNode.getNext();
			if(Objects.isNull(currentPointer))
				currentPointer = singleLinkedList.getHead();
			else
				currentPointer =
						currentPointer.getNext();
		}
		return currentPointer;
	}

	public Node<K> getNthffNodeFromEndInSingleLoop(int nthPosition)
	{
		Node<K> startNode = singleLinkedList.getHead();
		Node<K> ptr1 = startNode;
		Node<K> ptr2 = startNode;

		while(Objects.nonNull(ptr2))
		{
			nthPosition--;
			if(nthPosition<0)
			{
				ptr1 = ptr1.getNext();
			}
			ptr2 = ptr2.getNext();
		}

		if(nthPosition<=0){
			return ptr1;
		}
		return null;
	}

	public static void main(String[] args) throws Exception
	{
		SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>();
		for(int i=1; i<7; i++)
		{
			singleLinkedList.insertFromTail(i);
		}
		NthNodeFromEndSingleScan nthNodeFromEndSingleScan = new NthNodeFromEndSingleScan(singleLinkedList);
		Node<Integer> nthNode = nthNodeFromEndSingleScan.getNthffNodeFromEndInSingleLoop(3);
		System.out.println(nthNode.getData());
	}
}
