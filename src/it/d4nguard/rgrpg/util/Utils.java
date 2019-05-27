/*
 * Copyright (C) 2019 Alessandro 'kLeZ' Accardo
 *
 * This file is part of RG-RPG.
 *
 * RG-RPG is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * RG-RPG is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with RG-RPG.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package it.d4nguard.rgrpg.util;

import it.d4nguard.rgrpg.util.dynacast.Adapter;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.StringTokenizer;

public class Utils {
	public static String ARRAY_JOINER = "|";

	public static void swap(int op1, int op2) {
		int tmp = op2;
		op2 = op1;
		op1 = tmp;
	}

	public static boolean isInteger(String s) {
		boolean ret = true;
		try {
			Integer.valueOf(s);
		} catch (NumberFormatException e) {
			ret = false;
		}
		return ret;
	}

	public static ArrayList<String> splitEncolosed(String s, char open_tag, char close_tag) {
		ArrayList<String> ret = new ArrayList<>();
		char[] chars = s.toCharArray();
		StringBuilder contentsBuilder = new StringBuilder();
		for (char c : chars) {
			if (c == open_tag) {
				if (!contentsBuilder.toString().isEmpty()) {
					ret.add(contentsBuilder.toString());
				}
				contentsBuilder = new StringBuilder();
			} else if (c == close_tag) {
				ret.add(contentsBuilder.toString());
				contentsBuilder = new StringBuilder();
			} else {
				contentsBuilder.append(c);
			}
		}
		String contents = contentsBuilder.toString();
		if (!contents.isEmpty()) {
			ret.add(contents);
		}
		return ret;
	}

	public static String[] replace(String target, String replacement, String... args) {
		ArrayList<String> ret = new ArrayList<>();
		for (String s : args)
			ret.add(s.replace(target, replacement));
		return ret.toArray(new String[] { });
	}

	public static <E, R> Collection<R> doAll(Collection<? extends E> c, Delegate<E, R> d) {
		Collection<R> ret = new ArrayList<>();
		for (E e : c)
			ret.add(d.execute(e));
		return ret;
	}

	public static <T> Set<Class<? extends T>> getSubTypesOf(Class<T> type) {
		return getSubTypesOf(type, false);
	}

	public static <T> Set<Class<? extends T>> getSubTypesOf(Class<T> type, boolean excludeObjectClass) {
		String pkg = type.getPackage().getName().split("\\.")[0];
		Reflections r = new Reflections(pkg, new SubTypesScanner(excludeObjectClass));
		return r.getSubTypesOf(type);
	}

	public static <T> Object convertToArray(String value, Adapter<T> adp, Class<?> type) {
		String str = StringUtils.getBetween(value, '[', ']').getCenter().trim();
		StringTokenizer st = new StringTokenizer(str, ARRAY_JOINER);
		Object ret = Array.newInstance(type, st.countTokens());
		for (int i = 0; st.hasMoreTokens(); i++)
			Array.set(ret, i, adp.adapt(st.nextToken().trim()));
		return ret;
	}
}
