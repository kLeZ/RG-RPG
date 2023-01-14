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

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.util.StringUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class Character implements Serializable {
	@Serial
	private static final long serialVersionUID = 5045353718327076827L;

	protected final Player owner;
	protected final GeneralInfo info;
	protected final HashSet<Action> permittedActions;
	protected final Set<Skill> skills;
	protected AbilityScores scores;

	public Character(Player owner, GeneralInfo info) {
		this.owner = owner;
		this.info = info;
		this.permittedActions = new HashSet<>();
		this.skills = new HashSet<>();
	}

	public static Character build(String name, Object... args) {
		Player current = Context.getCurrentPlayer();
		GeneralInfo gi = new GeneralInfo();
		gi.setName(name);
		String charType = String.valueOf(args[0]);

		Character ret;
		if (it.d4nguard.rgrpg.d20.Character.TYPE.equals(charType)) {
			ret = new it.d4nguard.rgrpg.d20.Character(current, gi);
			ret.scores = new it.d4nguard.rgrpg.d20.AbilityScores();
		} else {
			ret = new Character(current, gi) {
				@Serial
				private static final long serialVersionUID = 3268711504482838654L;
			};
			ret.scores = new AbilityScores() {
				@Serial
				private static final long serialVersionUID = 2694913871398901741L;

				@Override
				protected AbilityScore newAbilityScore(String abilityScore) {
					return new AbilityScore(abilityScore, 1) {
						@Serial
						private static final long serialVersionUID = 2327752836269369873L;

						@Override
						public int calculateModifier(int value) {
							return 0;
						}
					};
				}

				@Override
				public String getPropertyPrefix() {
					return "";
				}
			};
		}
		return ret;
	}

	public Player getOwner() {
		return owner;
	}

	public GeneralInfo getInfo() {
		return info;
	}

	public HashSet<Action> getPermittedActions() {
		return permittedActions;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public AbilityScores getScores() {
		return scores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + permittedActions.hashCode();
		result = prime * result + ((scores == null) ? 0 : scores.hashCode());
		result = prime * result + skills.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Character))
			return false;
		Character other = (Character) obj;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (!permittedActions.equals(other.permittedActions))
			return false;
		if (scores == null) {
			if (other.scores != null)
				return false;
		} else if (!scores.equals(other.scores))
			return false;
		return skills.equals(other.skills);
	}

	@Override
	public String toString() {
		return StringUtils.genericToString(getClass(), this, "serialVersionUID", "owner");
	}
}
