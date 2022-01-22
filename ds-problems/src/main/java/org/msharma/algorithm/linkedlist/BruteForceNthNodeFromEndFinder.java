package org.msharma.algorithm.linkedlist;

import java.util.Objects;

/**
 * @author Mohan Sharma Created on 30/09/17.
 */
public class BruteForceNthNodeFromEndFinder<K>
{
	private SingleLinkedList<K> singleLinkedList;

	public BruteForceNthNodeFromEndFinder(SingleLinkedList<K> singleLinkedList)
	{
		this.singleLinkedList = singleLinkedList;
	}

	public Node<K> getNthNodeFromEnd(int n) throws Exception
	{
		int sizeOfLinkedList = singleLinkedList.getSize();
		if(sizeOfLinkedList <= 0 || sizeOfLinkedList - n < 0 || Objects.isNull(singleLinkedList.getHead()))
		{
			throw new Exception("List does not contain that element");
		}
		Node<K> iteratingHead = singleLinkedList.getHead();
		int iterateTill = sizeOfLinkedList - n;
		while(iterateTill > 0)
		{
			iteratingHead = iteratingHead.getNext();
			iterateTill--;
		}
		return iteratingHead;
	}

	public static void main(String[] args) throws Exception
	{
		SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>();
		for(int i=1; i<7; i++)
		{
			singleLinkedList.insertFromTail(i);
		}
		BruteForceNthNodeFromEndFinder bruteForceNthNodeFromEndFinder = new BruteForceNthNodeFromEndFinder(singleLinkedList);
		Node<Integer> nthNode = bruteForceNthNodeFromEndFinder.getNthNodeFromEnd(3);
		System.out.println(nthNode.getData());
	}
}
