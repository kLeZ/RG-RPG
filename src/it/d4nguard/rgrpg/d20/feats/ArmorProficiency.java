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

import it.d4nguard.rgrpg.d20.items.Armor;
import it.d4nguard.rgrpg.d20.types.FeatCategoryType;

import java.util.HashSet;
import java.util.Set;

public class ArmorProficiency extends Feat
{
	private final Set<Armor> items;
	private boolean allLight;
	private boolean allMedium;
	private boolean allHeavy;

	public ArmorProficiency()
	{
		super("Armor Proficiency", FeatCategoryType.General);
		this.items = new HashSet<Armor>();
	}

	public boolean isAllLight()
	{
		return allLight;
	}

	public void setAllLight(boolean allLight)
	{
		this.allLight = allLight;
	}

	public boolean isAllMedium()
	{
		return allMedium;
	}

	public void setAllMedium(boolean allMedium)
	{
		this.allMedium = allMedium;
	}

	public boolean isAllHeavy()
	{
		return allHeavy;
	}

	public void setAllHeavy(boolean allHeavy)
	{
		this.allHeavy = allHeavy;
	}

	public Set<Armor> getItems()
	{
		return items;
	}
}
