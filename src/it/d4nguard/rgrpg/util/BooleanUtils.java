/*
 * Copyright (C) 2021 Alessandro 'kLeZ' Accardo
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

public class BooleanUtils {
	public static boolean xnor(boolean x, boolean y) {
		return !xor(x, y);
	}

	public static boolean xor(boolean x, boolean y) {
		return (x || y) && !(x && y);
	}

	public static <T> boolean all(Collection<T> items, Method method, Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean ret = true;
		for (T t : items) {
			ret &= (Boolean) method.invoke(t, args);
		}
		return ret;
	}

	public static <T> boolean any(Collection<T> items, Method method, Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean ret = false;
		Iterator<T> it = items.iterator();
		while (it.hasNext() && !ret) {
			T t = it.next();
			ret = (Boolean) method.invoke(t, args);
		}
		return ret;
	}
}
