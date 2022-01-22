package org.msharma.algorithm.linkedlist;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Objects;

/**
 * @author Mohan Sharma Created on 01/10/17.
 */
public class NthNodeFromEndUsingHashing<K>
{
	private SingleLinkedList<K> singleLinkedList;

	public NthNodeFromEndUsingHashing(SingleLinkedList<K> singleLinkedList)
	{
		this.singleLinkedList = singleLinkedList;
	}

	public Node<K> getNthNodeFromLastUsingHashing(int n) throws Exception
	{
		Node<K> iteratingHead = singleLinkedList.getHead();
		if(Objects.isNull(iteratingHead) || singleLinkedList.isLinkedListEmpty())
		{
			throw new Exception("List does not contain element");
		}
		Map<Integer, Node<K>> hashMap = Maps.newHashMap();
		int count = 0;
		while(Objects.nonNull(iteratingHead))
		{
			hashMap.put(++count, iteratingHead);
			iteratingHead = iteratingHead.getNext();
		}
		int nodeFromEnd = count - n + 1;
		if(nodeFromEnd < 0)
		{
			throw new Exception("List does not contain element");
		}
		return hashMap.get(nodeFromEnd);
	}

	public static void main(String[] args) throws Exception
	{
		SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>();
		for(int i=1; i<8; i++)
		{
			singleLinkedList.insertFromTail(i);
		}
		NthNodeFromEndUsingHashing nthNodeFromEndUsingHashing = new NthNodeFromEndUsingHashing(singleLinkedList);
		Node<Integer> nthNode = nthNodeFromEndUsingHashing.getNthNodeFromLastUsingHashing(3);
		System.out.println(nthNode.getData());
	}
}
