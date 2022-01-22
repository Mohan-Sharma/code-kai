package org.msharma.algorithm.recursion;

/**
 * @author Mohan Sharma Created on 26/08/17.
 */
public class TowerOfHanoi
{
	private void performMoves(int n, char start, char aux, char dest)
	{
		if(n == 1)
		{
			System.out.println("Moving " + start + " --> " + dest);
			return;
		}
		performMoves(n-1, start, dest, aux);
		System.out.println("Moving " +start+ " --> " +dest);
		performMoves(n-1, aux, start, dest);
	}

	public static void main(String[] args)
	{
		TowerOfHanoi towerOfHanoi = new TowerOfHanoi();
		towerOfHanoi.performMoves(3, 'A', 'B', 'C');
	}
}
