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
package it.d4nguard.rgrpg.util.dynacast;

import it.d4nguard.rgrpg.util.StringUtils;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class DynaManipulator
{
	public static void setValue(String exp, Object root, String value)
					throws DynaManipulatorException
	{
		assert root != null;
		assert value != null;
		try
		{
			Field f = getField(exp, root);
			f.setAccessible(true);
			Adapter<?> a = TypeAdapter.getAdapter(f.getType());
			Object nurt = getValue(StringUtils.getExcludeLast(exp, "\\."), root);
			f.set(nurt, a.adapt(value));
		}
		catch (Throwable e)
		{
			throw new DynaManipulatorException(e);
		}
	}

	public static Object getValue(String exp, Object root)
					throws DynaManipulatorException
	{
		assert root != null;
		Object ret = root;
		try
		{
			LinkedList<Field> path = getExpPath(exp, root);
			for (Field f : path)
			{
				f.setAccessible(true);
				ret = f.get(ret);
			}
		}
		catch (Throwable e)
		{
			throw new DynaManipulatorException(e);
		}
		return ret;
	}

	public static Field getField(String exp, Object root)
					throws NoSuchFieldException
	{
		return getExpPath(exp, root).getLast();
	}

	public static LinkedList<Field> getExpPath(String exp, Object root)
					throws NoSuchFieldException
	{
		// TODO: Implement arrays and collections logic.
		assert root != null;
		StringTokenizer st = new StringTokenizer(exp, ".");
		String fst = st.hasMoreTokens() ? st.nextToken() : "";
		LinkedList<Field> ret = new LinkedList<Field>();
		if (!StringUtils.isNullOrWhitespace(fst))
		{
			ret.add(findField(fst, root.getClass()));
			while (st.hasMoreTokens())
			{
				String next = st.nextToken();
				if (ret.getLast() != null)
				{
					ret.add(findField(next, ret.getLast().getType()));
				}
				else throw new NoSuchFieldException(next);
			}
		}
		return ret;
	}

	public static Field findField(String name, Class<?> root)
	{
		if (root == null) return null;
		Field[] fields = root.getDeclaredFields();
		for (Field f : fields)
			if (f.getName().equals(name)) return f;
		return findField(name, root.getSuperclass());
	}
}
