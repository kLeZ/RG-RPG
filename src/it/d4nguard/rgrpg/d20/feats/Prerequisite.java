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
package it.d4nguard.rgrpg.d20.feats;

import it.d4nguard.rgrpg.d20.D20Character;

import java.lang.reflect.Method;

import ognl.Ognl;
import ognl.OgnlException;

public class Prerequisite
{
	private final String description;
	private final String expression;

	public Prerequisite(String description, String expression)
	{
		this.description = description;
		this.expression = expression;
	}

	public String getDescription()
	{
		return description;
	}

	public String getExpression()
	{
		return expression;
	}

	public boolean meets(D20Character character) throws OgnlException
	{
		return ((Boolean) Ognl.getValue(expression, character)).booleanValue();
	}

	public static Method MEETS;
	static
	{
		try
		{
			MEETS = Prerequisite.class.getMethod("meets", D20Character.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
	}
}
