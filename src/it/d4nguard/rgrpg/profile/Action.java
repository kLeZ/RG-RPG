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
package it.d4nguard.rgrpg.profile;

import it.d4nguard.rgrpg.profile.types.ActionType;
import it.d4nguard.rgrpg.profile.types.AttackType;

/**
 * This interface represents an action that can be performed by an RPG
 * Character.<br/>
 * There are many action types, two of which must be "Talk" and "Attack",
 * because of the mandatory actions that an RPG Character can necessarily
 * perform.
 *
 * @author kLeZ
 */
public interface Action {
	/**
	 * Gets the {@link ActionType} of this Action.
	 *
	 * @return the action type for this Action
	 */
	ActionType getActionType();

	/**
	 * Gets the {@link AttackType} of this action, in case this is an attack
	 * action, it returns 'null' otherwise.
	 *
	 * @return the {@link AttackType} of this attack action, if it is so,
	 * otherwise null.
	 */
	AttackType getAttackType();

	/**
	 * Performs the action of this action object.
	 */
	void perform();
}
