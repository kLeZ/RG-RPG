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
package it.d4nguard.rgrpg.d20;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.util.StringUtils;

import java.io.Serial;
import java.io.Serializable;

public class Language implements Serializable {
	public static final Language COMMON = new Language(Context.getLanguage("common.lang"), Context.getLanguage("common.dialect"));
	@Serial
	private static final long serialVersionUID = -3912431789132870338L;
	private String name;
	private String alphabet;

	public Language(String name, String alphabet) {
		this.name = name;
		this.alphabet = alphabet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(String alphabet) {
		this.alphabet = alphabet;
	}

	@Override
	public String toString() {
		return StringUtils.genericToString(getClass(), this);
	}
}
