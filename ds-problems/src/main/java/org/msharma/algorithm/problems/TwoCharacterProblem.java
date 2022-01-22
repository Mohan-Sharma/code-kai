package org.msharma.algorithm.problems;

/**
 * @author Mohan Sharma Created on 19/07/17.
 */
public class TwoCharacterProblem
{
	public static int getLengthOfLongestNoRepeatingCharacter(String str)
	{
		int length = 0;
		int sameCharCount = 1;
		for(int i = 0; i < str.length()-1; i++)
		{
			if(str.charAt(i) != str.charAt(i+1))
			{
				if(sameCharCount > 1)
				{
					sameCharCount = 1;
				}
				if(sameCharCount == 1)
					length++;
			}
			else
			{
				sameCharCount++;
			}
		}
		if(sameCharCount > 1)
		{
			length++;
			sameCharCount = 1;
		}
		return length;
	}

	public static void main(String[] args)
	{
		System.out.println(getLengthOfLongestNoRepeatingCharacter("ababbababbaaa"));
	}
}
