package org.msharma.algorithm.linkedlist;

import java.util.Objects;

/**
 * @author Mohan Sharma Created on 09/10/17.
 */
public class FloydsCyclicLinkedListFinder<K>
{
	private SingleLinkedList<K> singleLinkedList;

	public FloydsCyclicLinkedListFinder(SingleLinkedList<K> singleLinkedList)
	{
		this.singleLinkedList = singleLinkedList;
	}

	public boolean isLinkedListCyclic()
	{
		Node<K> iteratingHead = singleLinkedList.getHead();
		Node<K> tortoise = iteratingHead;
		Node<K> hare = iteratingHead;
		while(Objects.nonNull(tortoise) && Objects.nonNull(hare) && Objects.nonNull(hare.getNext()))
		{
			tortoise = tortoise.getNext();
			hare = hare.getNext().getNext();
			if(hare.equals(tortoise))
				return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
