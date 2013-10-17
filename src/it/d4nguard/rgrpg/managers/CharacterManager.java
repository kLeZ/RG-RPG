package it.d4nguard.rgrpg.managers;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.Main;
import it.d4nguard.rgrpg.profile.CharacterInfo;
import it.d4nguard.rgrpg.profile.Character;
import it.d4nguard.rgrpg.util.StringUtils;

import java.util.Iterator;
import java.util.Map.Entry;

public class CharacterManager implements Manager<Character>
{
	@Override
	public Character create(String name, Object... args)
	{
		Context.getCurrentPlayer().getCharacters().put(
						Character.build(name, args), new CharacterInfo());
		return get(name);
	}

	@Override
	public boolean delete(String name)
	{
		return Context.getCurrentPlayer().getCharacters().remove(get(name)) != null;
	}

	@Override
	public Character rename(String name, String newName)
	{
		get(name).getInfo().setName(newName);
		return get(newName);
	}

	@Override
	public Character use(String name)
	{
		Iterator<Entry<Character, CharacterInfo>> it = Context.getCurrentPlayer().getCharacters().entrySet().iterator();
		while (it.hasNext())
		{
			Entry<Character, CharacterInfo> e = it.next();
			e.getValue().setCurrent(e.getKey().getInfo().getName().equals(name));
		}
		return current();
	}

	@Override
	public Character current()
	{
		return Context.getCurrentCharacter();
	}

	@Override
	public Character get(String name)
	{
		Character ret = null;
		Iterator<Character> it = Context.getCurrentPlayer().getCharacters().keySet().iterator();
		while (it.hasNext() && ret == null)
		{
			Character c = it.next();
			if (c.getInfo().getName().equals(name)) ret = c;
		}
		return ret;
	}

	@Override
	public String availables()
	{
		return StringUtils.prettyPrint(Main.class.getPackage().getName(),
						Character.class);
	}
}
