/*
 * Copyright (C) 2020 Alessandro 'kLeZ' Accardo
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
package it.d4nguard.rgrpg.commands;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.managers.CharacterManager;
import it.d4nguard.rgrpg.managers.PlayerManager;
import it.d4nguard.rgrpg.profile.Character;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class LoadCommandTest {
	private final Set<String> names = new HashSet<>();

	{
		names.add("Julius");
		names.add("Mialee");
		names.add("Viktor");
		names.add("Hansel");
		names.add("Marril");
		names.add("Pipino");
	}

	@Before
	public void setUp() {
		Context.wipe();
		PlayerManager pm = new PlayerManager();
		CharacterManager cm = new CharacterManager();
		pm.use(pm.create("kLeZ")
				.getName());
		for (String name : names)
			cm.create(name, "d20");
		Context.save("~/test-session.dat");
		Context.wipe();
	}

	@Test
	public final void testExecute() {
		new LoadCommand().execute("~/test-session.dat");
		assertEquals("kLeZ", Context.getCurrentPlayer()
				.getName());
		for (final Character c : Context.getCurrentPlayer()
				.getCharacters()
				.keySet()) {
			assertTrue(names.contains(c.getInfo()
					.getName()));
		}
	}

	@After
	public final void tearDown() {
		String path = "~/test-session.dat";
		File f = new File(path.replace("~", System.getProperty("user.home")));
		//noinspection ResultOfMethodCallIgnored
		f.delete();
	}
}
