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
import static org.junit.Assert.fail;
import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.managers.CharacterManager;
import it.d4nguard.rgrpg.managers.PlayerManager;
import it.d4nguard.rgrpg.profile.GenderType;
import it.d4nguard.rgrpg.profile.RPGCharacter;

import javax.measure.unit.SI;

import ognl.Ognl;
import ognl.OgnlException;

import org.joda.time.DateMidnight;
import org.joda.time.format.DateTimeFormat;
import org.jscience.physics.amount.Amount;
import org.junit.Before;
import org.junit.Test;

public class SetCommandTest
{
	private SetCommand set;
	private String cmd;
	private RPGCharacter c;

	@Before
	public void setUp()
	{
		Context.wipe();
		PlayerManager pm = new PlayerManager();
		CharacterManager cm = new CharacterManager();
		set = new SetCommand();
		pm.create("kLeZ", new Object[] {});
		pm.use("kLeZ");

		cm.create("Julius", "d20");
		c = cm.use("Julius");
	}

	@Test
	public final void testExecute()
	{
		cmd = "character Julius \"info.description=This is a simple description for this character\"";
		set.execute(cmd.split("\\s"));
		assertEquals("This is a simple description for this character",
						c.getInfo().getDescription());

		cmd = "character Julius \"info.gender=Male\"";
		set.execute(cmd.split("\\s"));
		assertEquals(GenderType.Male, c.getInfo().getGender());

		cmd = "character Julius \"info.height=185 cm\"";
		set.execute(cmd.split("\\s"));
		assertEquals(Amount.valueOf(185, SI.CENTIMETER),
						c.getInfo().getHeight());

		cmd = "character Julius \"info.weight=65 kg\"";
		set.execute(cmd.split("\\s"));
		assertEquals(Amount.valueOf(65, SI.KILOGRAM), c.getInfo().getWeight());

		cmd = "character Julius \"info.gender=Male\"";
		set.execute(cmd.split("\\s"));
		assertEquals(GenderType.Male, c.getInfo().getGender());

		cmd = "character Julius \"info.dateOfBirth=07/04/1987[dd/MM/yyyy]\"";
		set.execute(cmd.split("\\s"));
		assertEquals(DateMidnight.parse("07/04/1987",
						DateTimeFormat.forPattern("dd/MM/yyyy")),
						c.getInfo().getDateOfBirth());

		// TODO: OGNL Tests, I'll try again
		try
		{
			Ognl.setValue("hpModifiers", c, null);
		}
		catch (OgnlException e)
		{
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}

	}
}
