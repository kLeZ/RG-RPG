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
package it.d4nguard.rgrpg.storyboard;

import it.d4nguard.rgrpg.util.StringUtils;

import java.io.Serial;
import java.io.Serializable;

public class Story implements Serializable {
	@Serial
	private static final long serialVersionUID = 7740473809178561351L;

	private final Chapter first;
	private final String description;

	public Story(String description, Chapter chapter) {
		this.first = chapter;
		this.description = description;
	}

	public Chapter getFirst() {
		return first;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return StringUtils.genericToString(getClass(), this);
	}
}
