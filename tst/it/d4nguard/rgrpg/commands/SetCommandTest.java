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
package it.d4nguard.rgrpg.commands;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.managers.CharacterManager;
import it.d4nguard.rgrpg.managers.PlayerManager;
import it.d4nguard.rgrpg.profile.Character;
import it.d4nguard.rgrpg.profile.types.GenderType;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.jscience.physics.amount.Amount;
import org.junit.Before;
import org.junit.Test;

import javax.measure.unit.SI;

import static org.junit.Assert.assertEquals;

public class SetCommandTest {
	private SetCommand set;
	private String cmd;
	private Character c;

	@Before
	public void setUp() {
		Context.wipe();
		PlayerManager pm = new PlayerManager();
		CharacterManager cm = new CharacterManager();
		set = new SetCommand();
		pm.create("kLeZ");
		pm.use("kLeZ");

		cm.create("Julius", "d20");
		c = cm.use("Julius");
	}

	@Test
	public final void testExecute() {
		cmd = "character Julius \"info.description=This is a simple description for this character\"";
		set.execute(cmd.split("\\s"));
		assertEquals("This is a simple description for this character", c.getInfo().getDescription());

		cmd = "character Julius \"info.gender=Male\"";
		set.execute(cmd.split("\\s"));
		assertEquals(GenderType.Male, c.getInfo().getGender());

		cmd = "character Julius \"info.height=185 cm\"";
		set.execute(cmd.split("\\s"));
		assertEquals(Amount.valueOf(185, SI.CENTIMETER), c.getInfo().getHeight());

		cmd = "character Julius \"info.weight=65 kg\"";
		set.execute(cmd.split("\\s"));
		assertEquals(Amount.valueOf(65, SI.KILOGRAM), c.getInfo().getWeight());

		cmd = "character Julius \"info.gender=Male\"";
		set.execute(cmd.split("\\s"));
		assertEquals(GenderType.Male, c.getInfo().getGender());

		cmd = "character Julius \"info.dateOfBirth=07/04/1987[dd/MM/yyyy]\"";
		set.execute(cmd.split("\\s"));
		assertEquals(DateTime.parse("07/04/1987", DateTimeFormat.forPattern("dd/MM/yyyy")), c.getInfo().getDateOfBirth());

		// 16 14 12 10 10 8
		cmd = "character Julius \"abilityScores.strength=8\"";
		set.execute(cmd.split("\\s"));
		System.out.println(((it.d4nguard.rgrpg.d20.Character) c).getAbilityScores());
		System.out.println(((it.d4nguard.rgrpg.d20.Character) c).getAbilityScores().getStrength());
		assertEquals(8, ((it.d4nguard.rgrpg.d20.Character) c).getAbilityScores().getStrength().getValue());
		assertEquals(-1, ((it.d4nguard.rgrpg.d20.Character) c).getAbilityScores().getStrength().getModifier());
	}
}
