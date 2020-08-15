/*
 * Copyright (C) 2020 Alessandro 'kLeZ' Accardo
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
package it.d4nguard.rgrpg.util.dynacast;

import it.d4nguard.rgrpg.util.DefaultMemberAccess;
import ognl.Ognl;

import java.util.Map;

/**
 * Main class to access the {get/set}Value methods. The entry point of the
 * component.
 *
 * @author kLeZ-hAcK
 */
public class DynaManipulator {
	private static final AdapterTypeConverter converter = new AdapterTypeConverter();
	private static final DefaultMemberAccess ACCESS = new DefaultMemberAccess(true);

	/**
	 * Evaluates the given OGNL expression to insert a value into the object
	 * graph rooted at the given root object.
	 *
	 * @param exp
	 * 		the OGNL expression to be parsed
	 * @param root
	 * 		the root object for the OGNL expression
	 * @param value
	 * 		the value to insert into the object graph
	 *
	 * @throws DynaManipulatorException
	 * 		if something fails below.
	 */
	public static void setValue(String exp, Object root, String value) throws DynaManipulatorException {
		assert root != null;
		assert value != null;
		try {
			Map<?, ?> context = Ognl.createDefaultContext(root, ACCESS, null, converter);
			Ognl.setValue(exp, context, root, value);
		} catch (Exception e) {
			throw new DynaManipulatorException(e);
		}
	}

	/**
	 * Evaluates the given OGNL expression tree to extract a value from the
	 * given root object.
	 *
	 * @param exp
	 * 		the OGNL expression to be parsed
	 * @param root
	 * 		the root object for the OGNL expression
	 *
	 * @return the value produced by the expression
	 *
	 * @throws DynaManipulatorException
	 * 		wrapping the real exception if something goes wrong
	 * @see Ognl#parseExpression(String)
	 */
	public static Object getValue(String exp, Object root) throws DynaManipulatorException {
		assert root != null;
		Object ret;
		try {
			Object expression = Ognl.parseExpression(exp);
			Map<?, ?> context = Ognl.createDefaultContext(root, ACCESS, null, converter);
			ret = Ognl.getValue(expression, context, root);
		} catch (Exception e) {
			throw new DynaManipulatorException(e);
		}
		return ret;
	}
}
