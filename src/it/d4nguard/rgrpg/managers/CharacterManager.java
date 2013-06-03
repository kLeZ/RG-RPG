package it.d4nguard.rgrpg.managers;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.profile.CharacterInfo;
import it.d4nguard.rgrpg.profile.Player;
import it.d4nguard.rgrpg.profile.RPGCharacter;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class CharacterManager implements Manager<RPGCharacter>
{
	private final Player player = Context.getCurrentPlayer();
	private final Map<RPGCharacter, CharacterInfo> characters = player.getCharacters();

	@Override
	public RPGCharacter create(String name, Object... args)
	{
		characters.put(RPGCharacter.build(name, args), new CharacterInfo());
		return get(name);
	}

	@Override
	public boolean delete(String name)
	{
		return characters.remove(get(name)) != null;
	}

	@Override
	public RPGCharacter rename(String name, String newName)
	{
		get(name).getInfo().setName(newName);
		return get(newName);
	}

	@Override
	public RPGCharacter use(String name)
	{
		RPGCharacter ret = null;
		Iterator<Entry<RPGCharacter, CharacterInfo>> it = characters.entrySet().iterator();
		while (it.hasNext())
		{
			Entry<RPGCharacter, CharacterInfo> e = it.next();
			e.getValue().setCurrent(e.getKey().getInfo().getName().equals(name));
		}
		return ret;
	}

	@Override
	public RPGCharacter current()
	{
		RPGCharacter ret = null;
		Iterator<Entry<RPGCharacter, CharacterInfo>> it = characters.entrySet().iterator();
		while (it.hasNext() && ret == null)
		{
			Entry<RPGCharacter, CharacterInfo> e = it.next();
			if (e.getValue().isCurrent()) ret = e.getKey();
		}
		return ret;
	}

	@Override
	public RPGCharacter get(String name)
	{
		RPGCharacter ret = null;
		Iterator<RPGCharacter> it = characters.keySet().iterator();
		while (it.hasNext() && ret == null)
		{
			RPGCharacter c = it.next();
			if (c.getInfo().getName().equals(name)) ret = c;
		}
		return ret;
	}
}
