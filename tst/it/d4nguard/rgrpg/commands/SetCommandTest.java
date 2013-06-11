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
import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.managers.CharacterManager;
import it.d4nguard.rgrpg.managers.PlayerManager;
import it.d4nguard.rgrpg.profile.GenderType;
import it.d4nguard.rgrpg.profile.RPGCharacter;
import it.d4nguard.rgrpg.util.dynacast.DynaManipulator;
import it.d4nguard.rgrpg.util.dynacast.DynaManipulatorException;

import org.junit.Before;
import org.junit.Test;

public class SetCommandTest
{
	@Before
	public void setUp()
	{
		Context.wipe();
		PlayerManager pm = new PlayerManager();
		CharacterManager cm = new CharacterManager();
		pm.create("kLeZ", new Object[] {});
		pm.use("kLeZ");

		cm.create("Julius", "d20");
		cm.use("Julius");
	}

	@Test
	public final void testExecute()
	{
		CharacterManager cm = new CharacterManager();
		SetCommand set = new SetCommand();
		String cmd = "character Julius \"info.description=This is a simple description for this character\"";
		set.execute(cmd.split("\\s"));
		assertEquals("This is a simple description for this character",
						cm.get("Julius").getInfo().getDescription());
		RPGCharacter c = cm.get("Julius");
		try
		{
			DynaManipulator.setValue("info.gender", c, "GenderType.Male");
		}
		catch (DynaManipulatorException e)
		{
			e.printStackTrace();
		}
		assertEquals(GenderType.Male, c.getInfo().getGender());
		set.execute("character availables Weapon".split("\\s"));
		set.execute("availables Item".split("\\s"));
	}
}
