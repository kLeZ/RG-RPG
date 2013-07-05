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

import it.d4nguard.rgrpg.util.CollectionsUtils;
import it.d4nguard.rgrpg.util.StringUtils;
import it.d4nguard.rgrpg.util.dynacast.factories.AdapterFactory;
import it.d4nguard.rgrpg.util.dynacast.factories.AdapterFactoryMap;
import it.d4nguard.rgrpg.util.dynacast.factories.StrategyFactory;

import java.lang.reflect.Type;
import java.util.Collection;

public class TypeAdapter
{
	/**
	 * This static method gets a type adapter from its factory class. <br>
	 * Call example:<br>
	 * <code>TypeAdapter.getAdapter(Integer.class).adapt("5");</code>
	 * 
	 * @param type
	 *            is the type you want to adapt to.
	 * @return An adapter class that can adapt a string to the recalled object.
	 */
	@SuppressWarnings(
	{ "rawtypes", "unchecked" })
	public static <T> Adapter<T> getAdapter(Type type)
	{
		assert type != null;
		Strategy strategy = StrategyFactory.getStrategy(type);
		Type applied = strategy.apply(type);
		AdapterFactory factory = AdapterFactoryMap.current().get(applied);
		return factory == null ? null : factory.create(type);
	}

	public static String toString(Object value)
	{
		String ret = "";
		if (value.getClass().isArray())
		{
			Object[] arr = CollectionsUtils.getArray(value);
			ret = StringUtils.join(Adapter.ARRAY_JOINER, arr);
			ret = String.format("[%s]", ret);
		}
		else if (Collection.class.isAssignableFrom(value.getClass()))
		{
			Collection<?> coll = (Collection<?>) value;
			ret = StringUtils.join(Adapter.ARRAY_JOINER, coll);
			ret = String.format("[%s]", ret);
		}
		else ret = String.valueOf(value);
		return ret;
	}
}
