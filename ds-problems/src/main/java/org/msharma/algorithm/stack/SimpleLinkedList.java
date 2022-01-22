package org.msharma.algorithm.stack;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mohan Sharma Created on 08/09/17.
 */
@Getter
@Setter
public class SimpleLinkedList
{
	private Node head;
	private Node tail;

	public void addFromLast(int data)
	{
		Node node = new Node(data, null);
		if(head == null)
		{
			head = node;
		}
		else
		{
			Node iteratingNode = head;
			while(iteratingNode.getNext() != null)
			{
				iteratingNode = iteratingNode.getNext();
			}
			iteratingNode.setNext(node);
		}
	}

	public void addFromHead(int data)
	{
		Node node = new Node(data, null);
		if(head == null)
		{
			head = node;
		}
		else
		{
			node.setNext(head);
			head = node;
		}
	}

	public void iterate()
	{
		Node iteratingNode = head;
		int count = 0;
		do
		{
			System.out.println("Element at position - "+ ++count + " : " + iteratingNode.getData());
			iteratingNode = iteratingNode.getNext();
		}
		while(iteratingNode != null);
	}

	public void reverseTheList(Node head)
	{
		Node current = head;
		Node next = null;
		Node previous = null;
		while(current != null && current.getNext() != null)
		{
			next = current.getNext();
			current.setNext(previous);
			previous = current;
			current = next;
		}
	}

	public int size()
	{
		Node iteratingNode = head;
		int size = 0;
		do
		{
			++size;
		}
		while(iteratingNode != null);
		return size;
	}
}
