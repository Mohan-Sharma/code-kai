package org.msharma.algorithm.problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

import com.google.common.collect.Maps;

/**
 * @author Mohan Sharma Created on 30/06/18.
 */
public class RemoveSubStringFromGivenString
{
	public static void main(String[] args)
	{
		Set<String> set = new HashSet<>();
		permutate("", "abc", set);
		System.out.println(set);
	}

	private String removeAllOccurencesOfGivenSubstrings(String string, String[] subStrings)
	{
		for(int i=0; i<subStrings.length; )
		{
			final String subString = subStrings[i];
			Pattern pattern = Pattern.compile(subString,Pattern.CASE_INSENSITIVE + Pattern.LITERAL);
			if(pattern.matcher(string).find())
			{
				string = string.replaceAll(subString, "");
				i = 0;
			}
			else
				i++;
		}
		return string;
	}

	static long findSimilar(String a, String b) {
		Map<Character, Integer> firstCount = new HashMap<>();
		for(char element : a.toCharArray())
		{
			firstCount.compute(element, (k, v) -> Objects.isNull(v) ? 1 : v + 1);
		}
		Map<Character, Integer> secondCount = Maps.newHashMap();
		for(char element : b.toCharArray())
		{
			secondCount.compute(element, (k, v) -> Objects.isNull(v) ? 1 : v + 1);
		}
		boolean similar = true;
		for(char element : a.toCharArray())
		{
			if(!firstCount.get(element).equals(secondCount.get(element)))
			{
				similar = false;
				break;
			}
		}
		Set<String> set = new HashSet<>();
		findCombinationsAndStore(a.toCharArray(), 0, set);
		final long numberOfCombinationsOfA = getValidNumberOfCombinations(set);
		//final int numberOfCombinationsOfA = set.size();
		set = new HashSet<>();
		findCombinationsAndStore(b.toCharArray(), 0, set);
		final long numberOfCombinationsOfB = getValidNumberOfCombinations(set);
		return similar ? numberOfCombinationsOfA : numberOfCombinationsOfB;
	}

	private static long getValidNumberOfCombinations(Set<String> set)
	{
		return set
					.stream()
					.filter(element -> {
						if(element.charAt(0) == '0')
							return false;
						else return true;
					})
					.count();
	}

	static void findCombinationsAndStore(char[] a, int k, Set<String> set)
	{
		if (k == a.length)
		{
			set.add(String.valueOf(a));
		}
		else
		{
			for (int i = k; i < a.length; i++)
			{
				int temp = a[k];
				a[k] = a[i];
				a[i] = (char)temp;

				findCombinationsAndStore(a, k + 1, set);

				temp = a[k];
				a[k] = a[i];
				a[i] = (char)temp;
			}
		}
	}

	static void permutate(String prefix, String remaining, Set<String> set)
	{
		if(remaining.length() == 0)
		{
			set.add(prefix);
			return;
		}
		for(int i=0; i<remaining.length(); i++)
		{
			permutate(prefix+remaining.charAt(i), remaining.substring(0, i) + remaining.substring(i + 1, remaining.length()), set);
		}
	}
}


