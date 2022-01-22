package com.code.kai.recursion;

import java.util.Arrays;
import lombok.AllArgsConstructor;

/**
 * @author Mohan Sharma Created on 27/08/17.
 */
@AllArgsConstructor
public class KAryStringOfLength
{
	private int[] bitArray;

	public void k_AryString(int numberOfBits, int k)
	{
		if(numberOfBits < 1) {
			System.out.println(Arrays.toString(bitArray));
			return;
		}
		else
		{
			for(int i=0; i<k; i++)
			{
				bitArray[numberOfBits - 1] = i;
				k_AryString(numberOfBits - 1, k);
			}

		}
	}

	public static void main(String[] args)
	{
		int digitUse = 4;
		int bits = 2;
		KAryStringOfLength kAryStringOfLength = new KAryStringOfLength(new int[bits]);
		kAryStringOfLength.k_AryString(bits, digitUse);
	}
}
