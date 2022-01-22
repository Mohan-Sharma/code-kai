package org.msharma.algorithm.practice;

import java.util.Objects;

/**
 * @author Mohan Sharma Created on 31/03/18.
 */
public class SinglyLinkedList<T>
{
	private SinglyNode<T> head;
	private int size;

	public void insertFromHead(T data)
	{
		SinglyNode node = new SinglyNode(data);
		if(Objects.nonNull(head))
		{
			node.setNext(head);
			head = node;
		}
		else
		{
			head = node;
		}
		size++;
	}

	public T removeFromHead()
	{
		if(isLinkedListEmpty() || Objects.isNull(head))
		{
			throw new IllegalStateException("Operation cannot be performed, List is empty!");
		}
		SinglyNode<T> head = this.head;
		T data = head.getData();
		SinglyNode nextNode = head.getNext();
		this.head = nextNode;
		head = null;
		size--;
		return data;
	}

	public void insertFromTail(T data)
	{
		SinglyNode node = new SinglyNode(data);
		if(Objects.isNull(head))
		{
			this.head = node;
		}
		else
		{
			SinglyNode<T> iteratingNode = this.head;
			while(Objects.nonNull(iteratingNode.getNext()))
			{
				iteratingNode = iteratingNode.getNext();
			}
			iteratingNode.setNext(node);
		}
		size++;
	}

	public T removeFromTail()
	{
		if(Objects.isNull(head) || isLinkedListEmpty())
		{
			throw new IllegalStateException("Operation cannot be performed, List is empty!");
		}
		SinglyNode<T> iteratingNode = this.head;
		SinglyNode<T> lastNode = null;
		while(Objects.nonNull(iteratingNode.getNext()) && Objects.nonNull(iteratingNode.getNext().getNext()))
		{
			iteratingNode = iteratingNode.getNext();
			lastNode = iteratingNode.getNext();

		}
		T data = lastNode.getData();
		iteratingNode.setNext(null);
		lastNode = null;
		return data;
	}
	public boolean isLinkedListEmpty()
	{
		return size <= 0;
	}

	public void traverseList()
	{
		if(Objects.isNull(head) || isLinkedListEmpty())
		{
			throw new IllegalStateException("Operation cannot be performed, List is empty!");
		}
		SinglyNode iteratingNode = this.head;
		while(Objects.nonNull(iteratingNode))
		{
			System.out.println(iteratingNode.getData());
			iteratingNode = iteratingNode.getNext();
		}
	}

	public static void main(String[] args)
	{
		SinglyLinkedList<Integer> singleLinkedList = new SinglyLinkedList<>();
		for(int i=1; i<7; i++)
		{
			singleLinkedList.insertFromHead(i);
		}
		singleLinkedList.traverseList();
		singleLinkedList.removeFromTail();
		System.out.println("After deletion");
		singleLinkedList.traverseList();
	}
}
