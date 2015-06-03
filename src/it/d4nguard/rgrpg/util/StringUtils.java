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

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.reflections.Reflections;

public class StringUtils
{
	public static final String STD_LIB_PKG_RGX = "(java|javax|com\\.sun|com\\.oracle|sun|sunw|org\\.w3c|org\\.xml|org\\.omg|org\\.dom4j)\\..*";
	private static final String[] SA = new String[] {};

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
		else args = SA;
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

	public static <T> String prettyPrint(String prefix, Class<T> clazz)
	{
		StringCompiler sc = new StringCompiler();
		Reflections reflections = new Reflections(prefix);
		Set<Class<? extends T>> classes = reflections.getSubTypesOf(clazz);
		for (Class<? extends T> c : classes)
		{
			sc.appendln(prettyPrint(c));
		}
		return sc.toString();
	}

	public static <T> String prettyPrint(Class<T> clazz)
	{
		return prettyPrint(clazz, 0, StringCompiler.NUL);
	}

	public static <T> String prettyPrint(Class<T> clazz, int length, char fill)
	{
		return prettyPrint(clazz, length, fill, false);
	}

	public static <T> String prettyPrint(Class<T> clazz, int length, char fill,
					boolean stdLib)
	{
		if (clazz == null) return "";
		StringCompiler sc = new StringCompiler(length, fill);
		boolean matches = BooleanUtils.xor(stdLib, clazz.isPrimitive()) || clazz.getPackage().getName().matches(
						STD_LIB_PKG_RGX);

		if (BooleanUtils.xnor(stdLib, matches))
		{
			sc.appendln(clazz.getSimpleName());
			if (sc.canFill()) sc.fill();
			sc.fill(clazz.getSimpleName().length(), '=').appendNewLine();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields)
			{
				if (!Modifier.isStatic(field.getModifiers()))
				{
					sc.appendln(printField(field));
				}
			}
		}
		return sc.toString();
	}

	public static String printField(Field field)
	{
		StringCompiler sc = new StringCompiler();
		Type t = field.getGenericType();
		LinkedHashMap<Class<?>, String> toPrint = new LinkedHashMap<Class<?>, String>();
		if (t instanceof Class)
		{
			// It's a normal class, I will print it as usual
			toPrint.put(field.getType(), field.getType().getSimpleName());
		}
		else if (t instanceof ParameterizedType)
		{
			ParameterizedType pt = (ParameterizedType) t;
			Class<?> raw = (Class<?>) pt.getRawType();
			toPrint.put(raw, raw.getSimpleName());
			for (Type argt : pt.getActualTypeArguments())
			{
				Class<?> cls = null;
				if (argt instanceof GenericArrayType)
				{
					cls = (Class<?>) argt;
				}
				else if (argt instanceof ParameterizedType)
				{
					cls = (Class<?>) argt;
				}
				else if (argt instanceof TypeVariable)
				{
					cls = ((TypeVariable<?>) argt).getGenericDeclaration().getClass();
				}
				else if (argt instanceof WildcardType)
				{
					cls = (Class<?>) argt;
				}
				else cls = (Class<?>) argt;
				toPrint.put(cls, cls.getSimpleName());
			}
		}
		List<String> values = Arrays.asList(toPrint.values().toArray(SA));
		if (toPrint.size() == 1)
		{
			sc.append(values.get(0));
		}
		else if (toPrint.size() > 1)
		{
			String gen = join(", ", values.subList(1, values.size()));
			sc.append("%s<%s>", values.get(0), gen);
		}
		sc.append(" %s", field.getName());
		// FIX: We're not ready to do a full print of an Object structure
		// We're getting StackOverflow!!!
		//		for (Class<?> c : toPrint.keySet())
		//		{
		//			sc.appendln(prettyPrint(c, sc.length(), ' '));
		//		}
		return sc.toString();
	}

	public static String getExcludeFirst(String in, String regex)
	{
		LinkedList<String> ll = new LinkedList<String>(
						Arrays.asList(in.split(regex)));
		if (!ll.isEmpty()) ll.removeFirst();
		StringBuilder sb = new StringBuilder();
		for (String s : ll)
			sb.append(s);
		return sb.toString();
	}

	public static String getExcludeLast(String in, String regex)
	{
		LinkedList<String> ll = new LinkedList<String>(
						Arrays.asList(in.split(regex)));
		if (!ll.isEmpty()) ll.removeLast();
		StringBuilder sb = new StringBuilder();
		for (String s : ll)
			sb.append(s);
		return sb.toString();
	}

	public static Triplet<String, String, String> getBetween(String s,
					char before, char after)
	{
		int start = s.indexOf(before), end = s.lastIndexOf(after);
		return getBetween(s, start, end);
	}

	public static Triplet<String, String, String> getBetween(String s,
					String before, String after)
	{
		int start = s.indexOf(before), end = s.lastIndexOf(after);
		return getBetween(s, start, end);
	}

	public static Triplet<String, String, String> getBetween(String s,
					int start, int end) throws IndexOutOfBoundsException
	{
		Triplet<String, String, String> ret = new Triplet<>();
		start = start < 0 ? 0 : start;
		end = end < 0 ? 0 : end;
		if (end > s.length() || start > end) throw new IndexOutOfBoundsException();

		ret.setLeft(s.substring(0, start <= 0 ? 0 : start));
		ret.setCenter(s.substring(start == end ? start : start + 1,
						end <= 0 ? 0 : end));
		ret.setRight(s.substring(end == s.length() ? end : end + 1));

		return ret;
	}

	public static boolean multiMatchAll(String against, String... patterns)
	{
		boolean ret = true;
		List<String> regexes = Arrays.asList(patterns);
		for (String regex : regexes)
			ret &= against.matches(regex);
		return ret;
	}

	public static boolean multiMatchAny(String against, String... patterns)
	{
		boolean ret = false;
		List<String> regexes = Arrays.asList(patterns);
		for (String regex : regexes)
			ret |= against.matches(regex);
		return ret;
	}

	public static String genericToString(Class<?> c, Object o)
	{
		return genericToString(c, o, "serialVersionUID");
	}

	public static String genericToString(Class<?> c, Object o,
					String... excluded)
	{
		return genericToString(c, o, 0, StringCompiler.NUL, true, excluded);
	}

	public static String genericToString(Class<?> c, Object o, int length,
					char filler, boolean recurseSuper, String... excluded)
	{
		if (c.equals(Object.class)) return "";
		StringCompiler sc = new StringCompiler(length, filler);
		sc.append(c.getSimpleName()).appendln("[");
		if (recurseSuper)
		{
			String s = genericToString(c.getSuperclass(), o, length, filler,
							recurseSuper, excluded);
			if (!isNullOrWhitespace(s)) sc.appendln(s);
		}
		for (Field f : c.getDeclaredFields())
			if (!multiMatchAny(f.getName(), excluded))
			{
				sc.fill().appendln(c, o, f.getName());
			}
		sc.append("]");
		return sc.toString();
	}
}
