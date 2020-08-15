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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class SaveCommandTest {
	@Before
	public final void setUp() throws Exception {
		Context.wipe();
		PlayerManager pm = new PlayerManager();
		CharacterManager cm = new CharacterManager();
		pm.use(pm.create("kLeZ")
				.getName());
		String[] names = new String[] {
				"Julius", "Mialee", "Viktor", "Hansel", "Marril", "Pipino"
		};
		for (String name : names)
			cm.create(name, "d20");
	}

	@Test
	public final void testExecute() {
		String file = "~/test-session.dat";
		new SaveCommand().execute(file);
		File f = new File(file.replace("~", System.getProperty("user.home")));
		assertTrue(f.exists());
		assertTrue(f.length() > 0);
	}

	@After
	public final void tearDown() {
		String file = "~/test-session.dat";
		File f = new File(file.replace("~", System.getProperty("user.home")));
		f.delete();
	}
}
