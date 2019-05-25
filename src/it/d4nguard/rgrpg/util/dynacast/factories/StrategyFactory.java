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
package it.d4nguard.rgrpg.util.dynacast.factories;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.util.Utils;
import it.d4nguard.rgrpg.util.dynacast.Strategy;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Set;

/**
 * This is the factory class for {@link Strategy} objects. It retrieves the
 * right {@link Strategy} for the given type.
 *
 * @author kLeZ-hAcK
 */
public class StrategyFactory {
	/**
	 * This method gets the correct {@link Strategy} by searching the given type
	 * in a {@link Set} of {@link Strategy} sub types returned by
	 * {@link Reflections}.
	 *
	 * @param type
	 * 		The type of which a {@link Strategy} must be returned.
	 *
	 * @return A {@link Strategy} that can apply to the given type or a dummy
	 * Strategy hard coded as:<br>
	 * <code>new Strategy() // The dummy one<br>
	 * {<br>
	 * &nbsp;&nbsp;{@literal @Override}<br>
	 * &nbsp;&nbsp;public boolean isMine(Type type) { return false; }<br>
	 * &nbsp;&nbsp;{@literal @Override}<br>
	 * &nbsp;&nbsp;public Type apply(Type type) { return type; }<br>
	 * &nbsp;&nbsp;{@literal @Override}<br>
	 * &nbsp;&nbsp;public Class<?> getMine(Type type) { return Object.class; }<br>
	 * };</code>
	 */
	public static Strategy getStrategy(final Type type) {
		Strategy ret = null;
		Set<Class<? extends Strategy>> subTypes = Utils.getSubTypesOf(Strategy.class);
		Iterator<Class<? extends Strategy>> it = subTypes.iterator();
		while (it.hasNext() && ret == null) {
			Class<? extends Strategy> e = it.next();
			if (!Modifier.isAbstract(e.getModifiers())) {
				try {
					Strategy s = e.newInstance();
					if (s.isMine(type))
						ret = s;
				} catch (InstantiationException | IllegalAccessException ex) {
					Context.printThrowable(ex);
				}
			}
		}

		if (ret != null) {
			return ret;
		} else
			return new Strategy() // The dummy one
			{
				/**
				 * {@inheritDoc}
				 */
				@Override
				public boolean isMine(Type type) {
					return false;
				}

				/**
				 * {@inheritDoc}
				 */
				@Override
				public Type apply(Type type) {
					return type;
				}

				/**
				 * {@inheritDoc}
				 */
				@Override
				public Class<?> getMine(Type type) {
					return Object.class;
				}
			};
	}
}
