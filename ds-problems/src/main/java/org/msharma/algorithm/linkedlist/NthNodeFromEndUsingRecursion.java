package org.msharma.algorithm.linkedlist;

import java.util.Objects;

/**
 * @author Mohan Sharma Created on 06/10/17.
 */
public class NthNodeFromEndUsingRecursion<K>
{
	SingleLinkedList<K> singleLinkedList;
	private int counter;
	private Node<K> nthNode;

	public NthNodeFromEndUsingRecursion(SingleLinkedList singleLinkedList)
	{
		this.singleLinkedList = singleLinkedList;
	}

	public Node<K> getNthNodeFromEndUsingRecursion(int n, Node<K> iteratingHead) throws Exception
	{
		if(Objects.isNull(iteratingHead))
			return iteratingHead;
		Node<K> result = getNthNodeFromEndUsingRecursion(n, iteratingHead.getNext());
		counter++;
		if(counter == n)
		{
			return iteratingHead;
		}
		return result;
	}

	public static void main(String[] args) throws Exception
	{
		SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>();
		for(int i=1; i<7; i++)
		{
			singleLinkedList.insertFromTail(i);
		}
		NthNodeFromEndUsingRecursion nthNodeFromEndUsingRecursion = new NthNodeFromEndUsingRecursion(singleLinkedList);
		Node<Integer> node = nthNodeFromEndUsingRecursion.getNthNodeFromEndUsingRecursion(2, singleLinkedList.getHead());
		System.out.println(node.getData());
	}
}
