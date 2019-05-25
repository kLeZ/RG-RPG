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
package it.d4nguard.rgrpg.d20.feats;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.d20.Character;
import it.d4nguard.rgrpg.util.dynacast.DynaManipulator;
import it.d4nguard.rgrpg.util.dynacast.DynaManipulatorException;

import java.io.Serializable;
import java.lang.reflect.Method;

public class Prerequisite implements Serializable {
	private static final long serialVersionUID = 4585271111842174784L;
	public static Method MEETS;

	static {
		try {
			MEETS = Prerequisite.class.getMethod("meets", Character.class);
		} catch (NoSuchMethodException | SecurityException e) {
			Context.printThrowable(e);
		}
	}

	private final String description;
	private final String expression;

	public Prerequisite(String description, String expression) {
		this.description = description;
		this.expression = expression;
	}

	public String getDescription() {
		return description;
	}

	public String getExpression() {
		return expression;
	}

	public boolean meets(Character character) throws DynaManipulatorException {
		return ((Boolean) DynaManipulator.getValue(expression, character)).booleanValue();
	}
}
