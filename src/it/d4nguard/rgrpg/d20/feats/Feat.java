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
package it.d4nguard.rgrpg.d20.feats;

import it.d4nguard.rgrpg.d20.Character;
import it.d4nguard.rgrpg.d20.types.FeatCategoryType;
import it.d4nguard.rgrpg.util.BooleanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public abstract class Feat implements Serializable {
	private static final long serialVersionUID = 2194072657322601662L;

	private final String name;
	private final FeatCategoryType featCategory;
	private final List<Prerequisite> prerequisites;

	public Feat(String name, FeatCategoryType featCategory) {
		this(name, featCategory, new ArrayList<>());
	}

	public Feat(String name, FeatCategoryType featCategory, List<Prerequisite> prerequisites) {
		this.name = name;
		this.featCategory = featCategory;
		this.prerequisites = prerequisites;
	}

	public boolean meets(Character character) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return BooleanUtils.all(prerequisites, Prerequisite.MEETS, character);
	}

	public String getName() {
		return name;
	}

	public FeatCategoryType getFeatCategory() {
		return featCategory;
	}

	public List<Prerequisite> getPrerequisites() {
		return prerequisites;
	}
}
