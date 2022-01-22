package org.msharma.algorithm.trees;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Mohan Sharma Created on 25/05/18.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = "data")
public class Node<T>
{
	private T data;
	private Node<T> left;
	private Node<T> right;
}
