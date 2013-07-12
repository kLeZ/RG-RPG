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

import java.util.Map;

import org.apache.commons.ognl.Ognl;

/**
 * Main class to access the {get/set}Value methods. The entry point of the
 * component.
 * 
 * @author kLeZ-hAcK
 */
public class DynaManipulator
{
	/**
	 * Evaluates the given OGNL expression to insert a value into the object
	 * graph rooted at the given root object.
	 * 
	 * @param exp
	 *            the OGNL expression to be parsed
	 * @param root
	 *            the root object for the OGNL expression
	 * @param value
	 *            the value to insert into the object graph
	 * @throws DynaManipulatorException
	 *             if something fails below.
	 */
	public static void setValue(String exp, Object root, String value)
					throws DynaManipulatorException
	{
		assert root != null;
		assert value != null;
		try
		{
			Map<String, Object> context = Ognl.createDefaultContext(root, null,
							new AdapterTypeConverter());
			Ognl.setValue(exp, context, root, value);
		}
		catch (Exception e)
		{
			throw new DynaManipulatorException(e);
		}
	}

	/**
	 * Evaluates the given OGNL expression tree to extract a value from the
	 * given root object.
	 * 
	 * @see Ognl#parseExpression(String)
	 * @param exp
	 *            the OGNL expression to be parsed
	 * @param root
	 *            the root object for the OGNL expression
	 * @return
	 * @throws DynaManipulatorException
	 */
	public static Object getValue(String exp, Object root)
					throws DynaManipulatorException
	{
		assert root != null;
		Object ret = root;
		try
		{
			Object expression = Ognl.parseExpression(exp);
			Map<String, Object> context = Ognl.createDefaultContext(root, null,
							new AdapterTypeConverter());
			ret = Ognl.getValue(expression, context, root);
		}
		catch (Exception e)
		{
			throw new DynaManipulatorException(e);
		}
		return ret;
	}
}
