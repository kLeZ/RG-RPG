package it.d4nguard.rgrpg.managers;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.Main;
import it.d4nguard.rgrpg.profile.CharacterInfo;
import it.d4nguard.rgrpg.profile.RPGCharacter;
import it.d4nguard.rgrpg.util.StringUtils;

import java.util.Iterator;
import java.util.Map.Entry;

public class CharacterManager implements Manager<RPGCharacter>
{
	@Override
	public RPGCharacter create(String name, Object... args)
	{
		Context.getCurrentPlayer().getCharacters().put(
						RPGCharacter.build(name, args), new CharacterInfo());
		return get(name);
	}

	@Override
	public boolean delete(String name)
	{
		return Context.getCurrentPlayer().getCharacters().remove(get(name)) != null;
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
		Iterator<Entry<RPGCharacter, CharacterInfo>> it = Context.getCurrentPlayer().getCharacters().entrySet().iterator();
		while (it.hasNext())
		{
			Entry<RPGCharacter, CharacterInfo> e = it.next();
			e.getValue().setCurrent(e.getKey().getInfo().getName().equals(name));
		}
		return current();
	}

	@Override
	public RPGCharacter current()
	{
		return Context.getCurrentCharacter();
	}

	@Override
	public RPGCharacter get(String name)
	{
		RPGCharacter ret = null;
		Iterator<RPGCharacter> it = Context.getCurrentPlayer().getCharacters().keySet().iterator();
		while (it.hasNext() && ret == null)
		{
			RPGCharacter c = it.next();
			if (c.getInfo().getName().equals(name)) ret = c;
		}
		return ret;
	}

	@Override
	public String availables()
	{
		return StringUtils.prettyPrint(Main.class.getPackage().getName(),
						RPGCharacter.class);
	}
}
