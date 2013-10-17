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

import it.d4nguard.rgrpg.d20.Character;
import it.d4nguard.rgrpg.d20.types.FeatCategoryType;
import it.d4nguard.rgrpg.util.BooleanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public abstract class Feat implements Serializable
{
	private static final long serialVersionUID = 2194072657322601662L;

	private final String name;
	private final FeatCategoryType featCategory;
	private final List<Prerequisite> prerequisites;

	public Feat(String name, FeatCategoryType featCategory)
	{
		this(name, featCategory, new ArrayList<Prerequisite>());
	}

	public Feat(String name, FeatCategoryType featCategory,
					List<Prerequisite> prerequisites)
	{
		this.name = name;
		this.featCategory = featCategory;
		this.prerequisites = prerequisites;
	}

	public boolean meets(Character character) throws IllegalAccessException,
					IllegalArgumentException, InvocationTargetException
	{
		return BooleanUtils.all(prerequisites, Prerequisite.MEETS, character);
	}

	public String getName()
	{
		return name;
	}

	public FeatCategoryType getFeatCategory()
	{
		return featCategory;
	}

	public List<Prerequisite> getPrerequisites()
	{
		return prerequisites;
	}
}
