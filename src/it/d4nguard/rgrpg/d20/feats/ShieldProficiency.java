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

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.d20.items.Shield;
import it.d4nguard.rgrpg.d20.types.FeatCategoryType;

import java.util.HashSet;
import java.util.Set;

public class ShieldProficiency extends Feat
{
	private static final long serialVersionUID = 4088624038996266287L;

	private final Set<Shield> items;
	private boolean all;
	private boolean tower;

	public ShieldProficiency()
	{
		super(Context.getFeat("shield.proficiency.title"),
						FeatCategoryType.General);
		this.items = new HashSet<Shield>();
	}

	public boolean isAll()
	{
		return all;
	}

	public void setAll(boolean all)
	{
		this.all = all;
	}

	public boolean isTower()
	{
		return tower;
	}

	public void setTower(boolean tower)
	{
		this.tower = tower;
	}

	public Set<Shield> getItems()
	{
		return items;
	}
}
