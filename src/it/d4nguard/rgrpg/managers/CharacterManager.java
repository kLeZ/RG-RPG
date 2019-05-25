/*
 * Copyright (C) 2019 Alessandro 'kLeZ' Accardo
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

package it.d4nguard.rgrpg.managers;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.Main;
import it.d4nguard.rgrpg.profile.Character;
import it.d4nguard.rgrpg.profile.CharacterInfo;
import it.d4nguard.rgrpg.util.StringUtils;

import java.util.Iterator;
import java.util.Map.Entry;

public class CharacterManager implements Manager<Character> {
	@Override
	public Character create(String name, Object... args) {
		Context.getCurrentPlayer().getCharacters().put(Character.build(name, args), new CharacterInfo());
		return get(name);
	}

	@Override
	public boolean delete(String name) {
		return Context.getCurrentPlayer().getCharacters().remove(get(name)) != null;
	}

	@Override
	public Character rename(String name, String newName) {
		get(name).getInfo().setName(newName);
		return get(newName);
	}

	@Override
	public Character use(String name) {
		Iterator<Entry<Character, CharacterInfo>> it = Context.getCurrentPlayer().getCharacters().entrySet().iterator();
		while (it.hasNext()) {
			Entry<Character, CharacterInfo> e = it.next();
			e.getValue().setCurrent(e.getKey().getInfo().getName().equals(name));
		}
		return current();
	}

	@Override
	public Character current() {
		return Context.getCurrentCharacter();
	}

	@Override
	public Character get(String name) {
		Character ret = null;
		Iterator<Character> it = Context.getCurrentPlayer().getCharacters().keySet().iterator();
		while (it.hasNext() && ret == null) {
			Character c = it.next();
			if (c.getInfo().getName().equals(name))
				ret = c;
		}
		return ret;
	}

	@Override
	public String availables() {
		return StringUtils.prettyPrint(Main.class.getPackage().getName(), Character.class);
	}
}
