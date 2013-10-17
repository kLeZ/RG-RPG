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
package it.d4nguard.rgrpg.profile;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.util.StringUtils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class Character implements Serializable
{
	private static final long serialVersionUID = 5045353718327076827L;

	protected final Player owner;
	protected final GeneralInfo info;
	protected AbilityScores scores;
	protected final HashSet<Action> permittedActions;
	protected final Set<Skill> skills;

	public Character(Player owner, GeneralInfo info)
	{
		this.owner = owner;
		this.info = info;
		this.permittedActions = new HashSet<>();
		this.skills = new HashSet<>();
	}

	public Player getOwner()
	{
		return owner;
	}

	public GeneralInfo getInfo()
	{
		return info;
	}

	public HashSet<Action> getPermittedActions()
	{
		return permittedActions;
	}

	public Set<Skill> getSkills()
	{
		return skills;
	}

	public AbilityScores getScores()
	{
		return scores;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((permittedActions == null) ? 0 : permittedActions.hashCode());
		result = prime * result + ((scores == null) ? 0 : scores.hashCode());
		result = prime * result + ((skills == null) ? 0 : skills.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Character)) return false;
		Character other = (Character) obj;
		if (info == null)
		{
			if (other.info != null) return false;
		}
		else if (!info.equals(other.info)) return false;
		if (owner == null)
		{
			if (other.owner != null) return false;
		}
		else if (!owner.equals(other.owner)) return false;
		if (permittedActions == null)
		{
			if (other.permittedActions != null) return false;
		}
		else if (!permittedActions.equals(other.permittedActions)) return false;
		if (scores == null)
		{
			if (other.scores != null) return false;
		}
		else if (!scores.equals(other.scores)) return false;
		if (skills == null)
		{
			if (other.skills != null) return false;
		}
		else if (!skills.equals(other.skills)) return false;
		return true;
	}

	@Override
	public String toString()
	{
		return StringUtils.genericToString(getClass(), this,
						"serialVersionUID", "owner");
	}

	public static Character build(String name, Object... args)
	{
		Character ret = null;
		Player current = Context.getCurrentPlayer();
		GeneralInfo gi = new GeneralInfo();
		gi.setName(name);
		String charType = String.valueOf(args[0]);

		switch (charType)
		{
			case it.d4nguard.rgrpg.d20.Character.TYPE:
			{
				ret = new it.d4nguard.rgrpg.d20.Character(current, gi);
				ret.scores = new it.d4nguard.rgrpg.d20.AbilityScores();
				break;
			}
			default:
				ret = new Character(current, gi)
				{
					private static final long serialVersionUID = 3268711504482838654L;
				};
				ret.scores = new AbilityScores()
				{
					private static final long serialVersionUID = 2694913871398901741L;

					@Override
					protected AbilityScore newAbilityScore(String abilityScore)
					{
						return new AbilityScore(abilityScore, 1)
						{
							private static final long serialVersionUID = 2327752836269369873L;

							@Override
							public int calculateModifier(int value)
							{
								return 0;
							}
						};
					}

					@Override
					public String getPropertyPrefix()
					{
						return "";
					}
				};
				break;
		}
		return ret;
	}
}
