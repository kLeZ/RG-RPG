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
package it.d4nguard.rgrpg.util.dynacast.adapters;

import it.d4nguard.rgrpg.util.dynacast.Adapter;

import java.lang.reflect.Type;

/**
 * This abstract class implements an {@link AbstractAdapter} providing an empty
 * {@link AbstractAdapter#beforeCreateAdapter(Type)} method for those classes
 * who don't need it.
 * This class has reason to be only because it helps to keep the code simpler.
 * 
 * @author kLeZ-hAcK
 * @param <T>
 *            The type of the implementing {@link Adapter}
 */
public abstract class SimpleAdapter<T> extends AbstractAdapter<T>
{
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void beforeCreateAdapter(Type type)
	{
	}
}
