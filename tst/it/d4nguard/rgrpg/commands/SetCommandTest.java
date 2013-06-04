package it.d4nguard.rgrpg.commands;

import static org.junit.Assert.assertEquals;
import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.managers.CharacterManager;
import it.d4nguard.rgrpg.managers.PlayerManager;

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
	}
}
