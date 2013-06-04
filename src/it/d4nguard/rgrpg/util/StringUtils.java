// RG-RPG is a Java-based text, roleplaying-gal game, in which you
// have to carry many girls. The RG-RPG acronym is a recursive one and
// it means "RG-RPG is a Gal Role playing game Pointing on Girls."
// Copyright (C) 2013 by Alessandro Accardo <julius8774@gmail.com>
// 
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 2 of the License, or (at
// your option) any later version.
// 
// This program is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
// 
package it.d4nguard.rgrpg.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

public class StringUtils
{
	public static String decapitalize(String s)
	{
		StringCompiler sb = new StringCompiler();
		Scanner scn = new Scanner(s);
		scn.useDelimiter("\\s");
		while (scn.hasNext())
		{
			String curr = scn.next();
			sb.append(curr.substring(0, 1).toLowerCase().concat(
							curr.substring(1)));
		}
		scn.close();
		return sb.toString();
	}

	public static String capitalize(String s)
	{
		StringCompiler sb = new StringCompiler();
		Scanner scn = new Scanner(s);
		scn.useDelimiter("\\s");
		while (scn.hasNext())
		{
			String curr = scn.next();
			sb.append(curr.substring(0, 1).toUpperCase().concat(
							curr.substring(1)));
		}
		scn.close();
		return sb.toString();
	}

	public static CommandLine getArgs(String... toJoin)
	{
		return getArgs(join(" ", toJoin));
	}

	public static CommandLine getArgs(String cmdLn)
	{
		CommandLine ret = new CommandLine();
		String[] args = cmdLn.split("\\s");
		// System.out.println(String.format("Command: %s%nArgs: %s%n#Args: %d", cmdLn, Arrays.toString(args), args.length));
		cmdLn = args[0];
		if (args.length > 1) args = Arrays.<String> copyOfRange(args, 1,
						args.length);
		else args = new String[] {};
		ret.setProc(cmdLn);
		ret.setArgs(args);
		return ret;
	}

	public static String join(String sep, String... args)
	{
		StringCompiler sb = new StringCompiler();
		for (int i = 0; i < args.length; i++)
		{
			sb.append(args[i]);
			if (i + 1 < args.length) sb.append(sep);
		}
		return sb.toString();
	}

	public static final char[] DIGIT_SYMBOLS = { '+', '-', '.', ',' };
	static
	{
		Arrays.sort(DIGIT_SYMBOLS);
	}

	public static boolean isNullOrWhitespace(final String s)
	{
		return s == null || s.isEmpty() || clean(s).isEmpty();
	}

	public static String clean(final String s)
	{
		return BlankRemover.itrim(BlankRemover.lrtrim(s)).toString();
	}

	public static String cleanDateRange(String s, final int take)
	{
		if (s != null)
		{
			s = s.contains("/") ? s.split("/").length > 0 ? s.split("/")[take] : s.substring(
							0, s.indexOf('/')) : s;
			s = s.contains("-") ? s.split("-").length > 0 ? s.split("-")[take] : s.substring(
							0, s.indexOf('-')) : s;
		}
		else s = "";
		return s;
	}

	public static String filterDigits(final String s)
	{
		return filterDigits(s, true);
	}

	public static String filterDigits(final String s,
					final boolean defaultToZero)
	{
		String ret = "";
		for (final char c : s.toCharArray())
			if (Character.isDigit(c) || Arrays.binarySearch(DIGIT_SYMBOLS, c) >= 0) ret = ret.concat(String.valueOf(c));
		if (defaultToZero && ret.isEmpty()) ret = "0";
		return ret;
	}

	public static <K, V> String
					join(final String separator, final Map<K, V> map)
	{
		final StringBuilder sb = new StringBuilder();
		int i = 0;
		for (final Map.Entry<K, V> entry : map.entrySet())
		{
			sb.append("{ ");
			sb.append(String.valueOf(entry.getKey()));
			sb.append(", ");
			sb.append(String.valueOf(entry.getValue()));
			sb.append(" }");
			if (++i < map.size()) sb.append(separator);
		}
		return sb.toString();
	}

	public static <T> String join(final String separator,
					final Collection<T> coll)
	{
		final StringBuilder sb = new StringBuilder();
		int i = 0;
		for (final T t : coll)
		{
			sb.append(String.valueOf(t));
			if (++i < coll.size()) sb.append(separator);
		}
		return sb.toString();
	}

	@SafeVarargs
	public static <T> String join(final String separator, final T... coll)
	{
		final StringBuilder sb = new StringBuilder();
		int i = 0;
		for (final T t : coll)
		{
			sb.append(String.valueOf(t));
			if (++i < coll.length) sb.append(separator);
		}
		return sb.toString();
	}

	/**
	 * Check that the given CharSequence is neither <code>null</code> nor of
	 * length 0.
	 * Note: Will return <code>true</code> for a CharSequence that purely
	 * consists of whitespace.
	 * <p>
	 * 
	 * <pre>
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true
	 * StringUtils.hasLength("Hello") = true
	 * </pre>
	 * 
	 * @param str
	 *            the CharSequence to check (may be <code>null</code>)
	 * @return <code>true</code> if the CharSequence is not null and has length
	 * @see #hasText(String)
	 */
	public static boolean hasLength(final CharSequence str)
	{
		return str != null && str.length() > 0;
	}

	/**
	 * Check that the given String is neither <code>null</code> nor of length 0.
	 * Note: Will return <code>true</code> for a String that purely consists of
	 * whitespace.
	 * 
	 * @param str
	 *            the String to check (may be <code>null</code>)
	 * @return <code>true</code> if the String is not null and has length
	 * @see #hasLength(CharSequence)
	 */
	public static boolean hasLength(final String str)
	{
		return hasLength((CharSequence) str);
	}

	/**
	 * Check whether the given CharSequence has actual text.
	 * More specifically, returns <code>true</code> if the string not
	 * <code>null</code>,
	 * its length is greater than 0, and it contains at least one non-whitespace
	 * character.
	 * <p>
	 * 
	 * <pre>
	 * StringUtils.hasText(null) = false
	 * StringUtils.hasText("") = false
	 * StringUtils.hasText(" ") = false
	 * StringUtils.hasText("12345") = true
	 * StringUtils.hasText(" 12345 ") = true
	 * </pre>
	 * 
	 * @param str
	 *            the CharSequence to check (may be <code>null</code>)
	 * @return <code>true</code> if the CharSequence is not <code>null</code>,
	 *         its length is greater than 0, and it does not contain whitespace
	 *         only
	 * @see java.lang.Character#isWhitespace
	 */
	public static boolean hasText(final CharSequence str)
	{
		if (!hasLength(str)) return false;
		final int strLen = str.length();
		for (int i = 0; i < strLen; i++)
			if (!Character.isWhitespace(str.charAt(i))) return true;
		return false;
	}

	/**
	 * Check whether the given String has actual text.
	 * More specifically, returns <code>true</code> if the string not
	 * <code>null</code>,
	 * its length is greater than 0, and it contains at least one non-whitespace
	 * character.
	 * 
	 * @param str
	 *            the String to check (may be <code>null</code>)
	 * @return <code>true</code> if the String is not <code>null</code>, its
	 *         length is
	 *         greater than 0, and it does not contain whitespace only
	 * @see #hasText(CharSequence)
	 */
	public static boolean hasText(final String str)
	{
		return hasText((CharSequence) str);
	}
}
