package org.msharma.algorithm.linkedlist;

import java.util.Objects;

/**
 * @author Mohan Sharma Created on 07/10/17.
 */
public class BruteForceCyclicLinkedListFinder<K>
{
	private SingleLinkedList<K> singleLinkedList;

	public BruteForceCyclicLinkedListFinder(SingleLinkedList<K> singleLinkedList)
	{
		this.singleLinkedList = singleLinkedList;
	}

	public boolean isLinkedListCyclicByBruteForce()
	{
		Node<K> head = singleLinkedList.getHead();
		Node<K> iteratingNode = head;
		while(Objects.nonNull(iteratingNode))
		{
			iteratingNode = iteratingNode.getNext();
			if(head ==  iteratingNode)
			{
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	public static void main(String[] args) throws Exception
	{
		SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>();
		for(int i=1; i<7; i++)
		{
			singleLinkedList.insertFromTail(i);
		}
		singleLinkedList.makeItCyclic(7);
		BruteForceCyclicLinkedListFinder bruteForceCyclicLinkedListFinder = new BruteForceCyclicLinkedListFinder(singleLinkedList);
		Boolean result = bruteForceCyclicLinkedListFinder.isLinkedListCyclicByBruteForce();
		System.out.println("Is linked list cyclic? : " +result);
	}
}
