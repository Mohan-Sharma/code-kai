package org.msharma.algorithm.trees;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;
import lombok.Getter;

/**
 * @author Mohan Sharma Created on 25/05/18.
 */
public class BinarySearchTree<T extends Comparable<T>>
{
	@Getter
	private Node<T> root;
	private static int index;
	private int some;
	private String somestr;

	public Node<T> insert(final T data, Node<T> node)
	{
		if(Objects.isNull(node))
		{
			node =  new Node<>(data, null, null);
			if(Objects.isNull(root))
				this.root = node;
		}
		else
		{
			if(data.compareTo(node.getData()) <= 0)
			{
				node.setLeft(insert(data, node.getLeft()));
			}
			else
			{
				node.setRight(insert(data, node.getRight()));
			}
		}
		return node;
	}

	public boolean searchRecursively(final T data, Node<T> node)
	{
		if(Objects.isNull(node))
			return false;
		if(data.equals(node.getData()))
			return true;
		else
		{
			if(data.compareTo(node.getData()) < 0)
			{
				return searchRecursively(data, node.getLeft());
			}
			else
			{
				return searchRecursively(data, node.getRight());
			}
		}
	}

	public boolean searchIteratively(final T data)
	{
		if(Objects.isNull(root))
		{
			return false;
		}
		Node<T> iteratingNode = root;
		while(Objects.nonNull(iteratingNode))
		{
			if(data.equals(iteratingNode.getData()))
				return true;
			else if(data.compareTo(iteratingNode.getData()) <= 0 )
			{
				iteratingNode = iteratingNode.getLeft();
			}
			else
				iteratingNode = iteratingNode.getRight();
		}
		return false;
	}

	public T findMinData()
	{
		if(Objects.isNull(root))
		{
			throw new IllegalStateException("BST is empty");
		}
		T minElement = root.getData();
		Node<T> iteratingNode = root;
		while(Objects.nonNull(iteratingNode))
		{
			if(minElement.compareTo(iteratingNode.getData()) >= 0)
				minElement = iteratingNode.getData();
			iteratingNode = iteratingNode.getLeft();
		}
		return minElement;
	}

	public T findMaxData()
	{
		if(Objects.isNull(root))
		{
			throw new IllegalStateException("BST is empty");
		}
		T maxElement = root.getData();
		Node<T> iteratingNode = root;
		while(Objects.nonNull(iteratingNode))
		{
			if(maxElement.compareTo(iteratingNode.getData()) <= 0)
				maxElement = iteratingNode.getData();
			iteratingNode = iteratingNode.getRight();
		}
		return maxElement;
	}

	public T findMinDataRecursively(T minElement, Node<T> nodeToCheck)
	{
		if(Objects.isNull(nodeToCheck))
		{
			return minElement;
		}
		if(minElement.compareTo(nodeToCheck.getData()) >= 0)
		{
			minElement =  nodeToCheck.getData();
		}
		return findMinDataRecursively(minElement, nodeToCheck.getLeft());
	}


	public T findMaxDataRecursively(T maxElement, Node<T> nodeToCheck)
	{
		if(Objects.isNull(nodeToCheck))
		{
			return maxElement;
		}
		if(maxElement.compareTo(nodeToCheck.getData()) <= 0)
		{
			maxElement =  nodeToCheck.getData();
		}
		return findMaxDataRecursively(maxElement, nodeToCheck.getRight());
	}

	public int findHeightOfBST(Node<T> node)
	{
		if(Objects.isNull(node))
			return -1;
		int heightOfLeftSubTree = findHeightOfBST(node.getLeft());
		int heightOfRightSubTree = findHeightOfBST(node.getRight());
		return Math.max(heightOfLeftSubTree, heightOfRightSubTree) + 1;
	}

	public void breadthFirstSearchTraversal()
	{
		if(Objects.isNull(root))
		{
			throw new IllegalStateException("BST is empty");
		}
		final Queue<Node<T>> queue = new ArrayDeque<>();
		queue.add(this.root);
		while(!queue.isEmpty())
		{
			Node<T> currentNode = queue.peek();
			System.out.printf("%s ", currentNode.getData());
			if(Objects.nonNull(currentNode.getLeft()))
				queue.add(currentNode.getLeft());
			if(Objects.nonNull(currentNode.getRight()))
				queue.add(currentNode.getRight());
			queue.poll();
		}
	}

	public void preOrderTraversal(Node<T> root)
	{
		if(Objects.isNull(root))
			return;
		System.out.printf("%s ", root.getData());
		preOrderTraversal(root.getLeft());
		preOrderTraversal(root.getRight());
	}

	public void inOrderTraversal(Node<T> root)
	{
		if(Objects.isNull(root))
			return;
		inOrderTraversal(root.getLeft());
		System.out.printf("%s ", root.getData());
		inOrderTraversal(root.getRight());
	}

	public void postOrderTraversal(Node<T> root)
	{
		if(Objects.isNull(root))
			return;
		postOrderTraversal(root.getLeft());
		postOrderTraversal(root.getRight());
		System.out.printf("%s ", root.getData());
	}

	public boolean isBinarySearchTreeBruteForce(Node<T> root)
	{
		if(Objects.isNull(root))
			return true;
		if(isLeftSubtreeIsLess(root.getLeft(), root.getData())
				&& isRightSubtreeGreater(root.getRight(), root.getData())
				&& isBinarySearchTreeBruteForce(root.getLeft())
				&& isBinarySearchTreeBruteForce(root.getRight()))
			return true;
		else
			return false;
	}

	private boolean isRightSubtreeGreater(Node<T> root, T data)
	{
		if(Objects.isNull(root))
			return true;
		if(root.getData().compareTo(data) > 0 && isRightSubtreeGreater(root.getLeft(), data)  && isRightSubtreeGreater(root.getRight(), data))
			return true;
		else
			return false;
	}

	private boolean isLeftSubtreeIsLess(Node<T> root, T data)
	{
		if(Objects.isNull(root))
			return true;
		if(root.getData().compareTo(data) <= 0 && isLeftSubtreeIsLess(root.getLeft(), data)  && isLeftSubtreeIsLess(root.getRight(), data))
			return true;
		else
			return false;
	}

	public boolean isBinarySearchTreeEfficient(Node<T> root, T minData, T maxData)
	{
		if(Objects.isNull(root))
			return true;
		if(root.getData().compareTo(minData) != -1 && root.getData().compareTo(maxData) < 0
				&& isBinarySearchTreeEfficient(root.getLeft(), minData, root.getData())
				&& isBinarySearchTreeEfficient(root.getRight(), root.getData(), maxData))
			return true;
		else
			return false;
	}

	public boolean isBinarySearchTreeUsingInOrderTraversal(Node<T> root)
	{
		final int numberOfNodes = getNumberOfNodesOfTheTree(root);
		if(numberOfNodes > 0)
		{
			final Object[] arrayToPopulate = new Object[numberOfNodes];
			getInOrderTraversalArray(root, arrayToPopulate);
			return checkIfSorted(arrayToPopulate);
		}
		return false;
	}

	private boolean checkIfSorted(Object[] arrayToPopulate)
	{
		for(int i=1; i<arrayToPopulate.length; i++)
		{
			final T first = (T) arrayToPopulate[i-1];
			final T second = (T) arrayToPopulate[i];
			if(first.compareTo(second) == 1)
				return false;
		}
		return true;
	}

	private void getInOrderTraversalArray(Node<T> root, final Object[] arrayToPopulate)
	{
		if(Objects.isNull(root))
			return;
		getInOrderTraversalArray(root.getLeft(), arrayToPopulate);
		arrayToPopulate[index++] = root.getData();
		getInOrderTraversalArray(root.getRight(), arrayToPopulate);
	}

	public int getNumberOfNodesOfTheTree(Node<T> root)
	{
		if(Objects.isNull(root))
			return 0;
		return  1 +  getNumberOfNodesOfTheTree(root.getLeft()) + getNumberOfNodesOfTheTree(root.getRight());
	}

	public Node<T> deleteNode(T data, Node<T> node)
	{
		if(Objects.isNull(node))
			return node;
		else if(node.getData().compareTo(data) > 0)
		{
			node.setLeft(deleteNode(data, node.getLeft()));
		}
		else if(node.getData().compareTo(data) < 0)
		{
			node.setRight(deleteNode(data, node.getRight()));
		}
		else
		{
			if(Objects.isNull(node.getLeft()) && Objects.isNull(node.getRight()))
			{
				return null;
			}
			else if(Objects.isNull(node.getLeft()))
			{
				Node<T> temp = node;
				node = node.getRight();
				temp = null;
			}
			else if(Objects.isNull(node.getRight()))
			{
				Node<T> temp = node;
				node = node.getLeft();
				temp = null;
			}
			else
			{
				Node<T> minRightSubtreeNode = findMinDataRecursively(node.getRight());
				node.setData(minRightSubtreeNode.getData());
				node.setRight(deleteNode(minRightSubtreeNode.getData(), node.getRight()));
			}
		}
		return node;
	}

	private Node<T> findMinDataRecursively(Node<T> node)
	{
		if(Objects.isNull(node))
			return node;
		Node<T> leftNode = node.getLeft();
		if(Objects.isNull(leftNode))
			return node;
		else
			return findMinDataRecursively(leftNode);
	}

	public static void main(String[] args)
	{
		/**
		 *            15
		 *       12        18
		 *
		 *   9		16         22
		 *
		 * 1   10	  17    21      26
		 */
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(15, bst.root);
		bst.insert(12, bst.root);
		bst.insert(18, bst.root);
		bst.insert(9, bst.root);
		bst.insert(10, bst.root);
		bst.insert(16, bst.root);
		bst.insert(17, bst.root);
		bst.insert(1, bst.root);
		bst.insert(22, bst.root);
		bst.insert(26, bst.root);
		bst.insert(21, bst.root);
		//System.out.println("Exists : " + bst.searchIteratively(22));
		//System.out.println("Exists : " + bst.findMinData());
		//System.out.println("Exists : " + bst.findMaxDataRecursively(Integer.MIN_VALUE, bst.root));
		//System.out.println("Height of the binary search tree is : " + bst.findHeightOfBST(bst.root));
		//System.out.println(bst.isBinarySearchTreeEfficient(bst.root, Integer.MIN_VALUE, Integer.MAX_VALUE));
		//System.out.println(bst.isBinarySearchTreeUsingInOrderTraversal(bst.root));
		//bst.deleteNode(18, bst.root);
		System.out.println("Exists : " + bst.findMaxData());
	}

	@Override public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		BinarySearchTree<?> that = (BinarySearchTree<?>) o;

		if (some != that.some)
			return false;
		if (root != null ? !root.equals(that.root) : that.root != null)
			return false;
		return somestr != null ? somestr.equals(that.somestr) : that.somestr == null;
	}

	@Override public int hashCode()
	{
		int result = root != null ? root.hashCode() : 0;
		result = 31 * result + some;
		result = 31 * result + (somestr != null ? somestr.hashCode() : 0);
		return result;
	}
}
