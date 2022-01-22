package org.msharma.algorithm.linkedlist;

import lombok.Getter;

import java.util.Objects;

/**
 * @author Mohan Sharma Created on 30/09/17.
 */
@Getter
public class SingleLinkedList<K>
{
	private Node<K> head;
	private int size;

	public void insertFromTail(K data)
	{
		Node node = new Node(data, null);
		if(this.head == null)
		{
			this.head = node;
		}
		else
		{
			Node iteratingNode = head;
			while (iteratingNode.getNext() != null)
			{
				iteratingNode = iteratingNode.getNext();
			}
			iteratingNode.setNext(node);
		}
		size++;
	}

	public void insertFromHead(K data)
	{
		Node node = new Node(data, null);
		if(this.head == null)
		{
			this.head = node;
		}
		else
		{
			node.setNext(head);
			this.head = node;
		}
		size++;
	}

	public K removeFromHead() throws Exception
	{
		if(isLinkedListEmpty() || Objects.isNull(head))
			throw new Exception("Operation cannot be performed, List is empty!");
		Node<K> currentHead = head;
		K data = currentHead.getData();
		Node<K> nextTohead = currentHead.getNext();
		head = nextTohead;
		currentHead = null;
		size--;
		return data;
	}

	public boolean isLinkedListEmpty()
	{
		return size <= 0;
	}

	public void iterateList() throws Exception
	{
		Node<K> iteratingHead = head;
		if(Objects.isNull(iteratingHead))
			throw new Exception("Operation cannot be performed, List is empty!");
		do
		{
			System.out.println(iteratingHead.getData());
			iteratingHead = iteratingHead.getNext();
		}
		while(iteratingHead != null);
	}

	public void makeItCyclic(K data) throws Exception
	{
		Node<K> node = new Node<>(data, null);
		Node<K> iteratingHead = head;
		if(Objects.isNull(iteratingHead))
			throw new Exception("Operation cannot be performed, List is empty!");
		while(Objects.nonNull(iteratingHead.getNext()))
		{
			iteratingHead = iteratingHead.getNext();
		}
		iteratingHead.setNext(node);
		node.setNext(head);
	}
}
