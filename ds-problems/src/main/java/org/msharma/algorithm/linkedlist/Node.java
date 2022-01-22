package org.msharma.algorithm.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Mohan Sharma Created on 30/09/17.
 */
@Getter
@Setter
@AllArgsConstructor
public class Node<K>
{
	private K data;
	private Node next;
	private Node previous;

	public Node(K data, Node next)
	{
		this.data = data;
		this.next = next;
	}
}
