package org.msharma.algorithm.stack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Mohan Sharma Created on 08/09/17.
 */
@Getter
@Setter
@AllArgsConstructor
public class Node
{
	private int data;
	private Node next;

	public Node(int data)
	{
		this.data = data;
	}
}
