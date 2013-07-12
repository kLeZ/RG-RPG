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

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.util.Map;

import org.apache.commons.ognl.TypeConverter;

/**
 * An implementation of the {@link TypeConverter} class that implements dynacast
 * logic.
 * 
 * @author kLeZ-hAcK
 */
public class AdapterTypeConverter implements TypeConverter
{
	/**
	 * {@inheritDoc}
	 */
	public <T> T convertValue(Map<String, Object> context, Object target,
					Member member, String propertyName, Object value,
					Class<T> toType)
	{
		if (member instanceof Field)
		{
			Adapter<T> a = TypeAdapter.getAdapter(toType);
			return a.adapt(String.valueOf(value));
		}
		return null;
	}
}
