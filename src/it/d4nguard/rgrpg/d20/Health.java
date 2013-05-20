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
package it.d4nguard.rgrpg.d20;

import java.util.HashSet;
import java.util.Set;

public class Health
{
	private int totalHealthPoints;
	private int currentHealthPoints;
	private DamageReduction damageReduction;
	private Set<Resistance> resistances;

	public Health()
	{
		this.resistances = new HashSet<Resistance>();
	}

	public int getTotalHealthPoints()
	{
		return totalHealthPoints;
	}

	public void setTotalHealthPoints(int totalHealthPoints)
	{
		this.totalHealthPoints = totalHealthPoints;
	}

	public int getCurrentHealthPoints()
	{
		return currentHealthPoints;
	}

	public void setCurrentHealthPoints(int currentHealthPoints)
	{
		this.currentHealthPoints = currentHealthPoints;
	}

	public DamageReduction getDamageReduction()
	{
		return damageReduction;
	}

	public void setDamageReduction(DamageReduction damageReduction)
	{
		this.damageReduction = damageReduction;
	}

	public Set<Resistance> getResistances()
	{
		return resistances;
	}

	public void setResistances(Set<Resistance> resistances)
	{
		this.resistances = resistances;
	}
}
