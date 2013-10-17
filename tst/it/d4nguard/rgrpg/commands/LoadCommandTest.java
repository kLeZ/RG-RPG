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
package it.d4nguard.rgrpg.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.managers.CharacterManager;
import it.d4nguard.rgrpg.managers.PlayerManager;
import it.d4nguard.rgrpg.profile.Character;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoadCommandTest
{
	Set<String> names = new HashSet<String>();
	{
		names.add("Julius");
		names.add("Mialee");
		names.add("Viktor");
		names.add("Hansel");
		names.add("Marril");
		names.add("Pipino");
	}

	@Before
	public void setUp() throws Exception
	{
		Context.wipe();
		PlayerManager pm = new PlayerManager();
		CharacterManager cm = new CharacterManager();
		pm.use(pm.create("kLeZ", new Object[] {}).getName());
		for (String name : names)
			cm.create(name, "d20");
		Context.save("~/test-session.dat");
		Context.wipe();
	}

	@Test
	public final void testExecute()
	{
		new LoadCommand().execute("~/test-session.dat");
		assertEquals("kLeZ", Context.getCurrentPlayer().getName());
		Iterator<Character> it = Context.getCurrentPlayer().getCharacters().keySet().iterator();
		while (it.hasNext())
		{
			Character c = it.next();
			assertTrue(names.contains(c.getInfo().getName()));
		}
	}

	@After
	public final void tearDown()
	{
		String path = "~/test-session.dat";
		File f = new File(path.replace("~", System.getProperty("user.home")));
		f.delete();
	}
}
