package org.msharma.algorithm.linkedlist;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Objects;

/**
 * @author Mohan Sharma Created on 07/10/17.
 */
public class CyclicLinkedListUsingHashingFinder<k>
{
	private SingleLinkedList<k> singleLinkedList;

	public CyclicLinkedListUsingHashingFinder(SingleLinkedList<k> singleLinkedList)
	{
		this.singleLinkedList = singleLinkedList;
	}

	public boolean isLinkedListCyclicByHashing()

	{
		Node<k> head = singleLinkedList.getHead();
		Node<k> iteratingHead = head;
		Map<Node<k>, k> hashMap = Maps.newIdentityHashMap();
		while(Objects.nonNull(iteratingHead))
		{
			if(hashMap.containsKey(iteratingHead))
			{
				return Boolean.TRUE;
			}
			hashMap.put(iteratingHead, iteratingHead.getData());
			iteratingHead = iteratingHead.getNext();
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
		CyclicLinkedListUsingHashingFinder cyclicLinkedListUsingHashingFinder = new CyclicLinkedListUsingHashingFinder(singleLinkedList);
		Boolean result = cyclicLinkedListUsingHashingFinder.isLinkedListCyclicByHashing();
		System.out.println("Is linked list cyclic? : " + result);
	}
}
