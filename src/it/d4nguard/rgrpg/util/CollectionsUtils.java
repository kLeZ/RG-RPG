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

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CollectionsUtils {
	public static final Class<?>[] ARRAY_PRIMITIVE_TYPES = { int[].class, float[].class, double[].class, boolean[].class, byte[].class, short[].class, long[].class, char[].class };

	public static Object[] getArray(Object val) {
		Class<?> valKlass = val.getClass();
		Object[] outputArray = null;
		for (Class<?> arrKlass : ARRAY_PRIMITIVE_TYPES) {
			if (valKlass.isAssignableFrom(arrKlass)) {
				int arrlength = Array.getLength(val);
				outputArray = new Object[arrlength];
				for (int i = 0; i < arrlength; ++i) {
					outputArray[i] = Array.get(val, i);
				}
				break;
			}
		}
		if (outputArray == null) // not primitive type array
			outputArray = (Object[]) val;
		return outputArray;
	}

	public static <T> T getByIndex(Iterable<T> iterable, int index) {
		T el = null;
		Iterator<T> it = iterable.iterator();
		for (int i = 0; it.hasNext(); i++) {
			T cur = it.next();
			if (i == index) {
				el = cur;
				break;
			}
		}
		return el;
	}

	public static <K, V> K getByValue(Map<K, V> map, V value) {
		Set<Entry<K, V>> entrySet = map.entrySet();
		K el = null;
		Iterator<Entry<K, V>> it = entrySet.iterator();
		while (it.hasNext()) {
			Entry<K, V> cur = it.next();
			if (cur.getValue().equals(value)) {
				el = cur.getKey();
				break;
			}
		}
		return el;
	}

	public static <T> void cleanNulls(Collection<T> c) {
		Iterator<T> it = c.iterator();
		try {
			while (it.hasNext())
				if (it.next() == null)
					c.remove(null);
		} catch (NullPointerException e) {
			// Does not permit nulls, it should be ok
		}
	}

	public static <T> T getFirstNonNull(Collection<T> c) {
		T ret = null;
		Iterator<T> it = c.iterator();
		while (it.hasNext() && ret == null) {
			T t = it.next();
			if (t != null)
				ret = t;
		}
		return ret;
	}
}
