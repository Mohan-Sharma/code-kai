package org.msharma.algorithm.practice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mohan Sharma Created on 31/03/18.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SinglyNode<T>
{
	private T data;
	private SinglyNode next;

	public SinglyNode(T data)
	{
		this.data = data;
	}
}
