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
package it.d4nguard.rgrpg.d20;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.d20.AbilityScore.UnmodifiableAbilityScore;

import java.util.HashMap;
import java.util.Map;

public class AbilityScores
{
	public static final String STRENGTH = "strength";
	public final String StrengthD = Context.getAbilityScore(STRENGTH);
	public static final String DEXTERITY = "dexterity";
	public final String DexterityD = Context.getAbilityScore(DEXTERITY);
	public static final String CONSTITUTION = "constitution";
	public final String ConstitutionD = Context.getAbilityScore(CONSTITUTION);
	public static final String INTELLIGENCE = "intelligence";
	public final String IntelligenceD = Context.getAbilityScore(INTELLIGENCE);
	public static final String WISDOM = "wisdom";
	public final String WisdomD = Context.getAbilityScore(WISDOM);
	public static final String CHARISMA = "charisma";
	public final String CharismaD = Context.getAbilityScore(CHARISMA);

	private final Map<String, AbilityScore> scores;

	public AbilityScores()
	{
		this.scores = new HashMap<String, AbilityScore>();
		scores.put(STRENGTH, new AbilityScore(StrengthD));
		scores.put(DEXTERITY, new AbilityScore(DexterityD));
		scores.put(CONSTITUTION, new AbilityScore(ConstitutionD));
		scores.put(INTELLIGENCE, new AbilityScore(IntelligenceD));
		scores.put(WISDOM, new AbilityScore(WisdomD));
		scores.put(CHARISMA, new AbilityScore(CharismaD));
	}

	public AbilityScore getStrength()
	{
		return getAbilityScore(STRENGTH);
	}

	public AbilityScore getDexterity()
	{
		return getAbilityScore(DEXTERITY);
	}

	public AbilityScore getConstitution()
	{
		return getAbilityScore(CONSTITUTION);
	}

	public AbilityScore getIntelligence()
	{
		return getAbilityScore(INTELLIGENCE);
	}

	public AbilityScore getWisdom()
	{
		return getAbilityScore(WISDOM);
	}

	public AbilityScore getCharisma()
	{
		return getAbilityScore(CHARISMA);
	}

	public void setStrength(int strength)
	{
		scores.get(STRENGTH).setValue(strength);
	}

	public void setDexterity(int dexterity)
	{
		scores.get(DEXTERITY).setValue(dexterity);
	}

	public void setConstitution(int constitution)
	{
		scores.get(CONSTITUTION).setValue(constitution);
	}

	public void setIntelligence(int intelligence)
	{
		scores.get(INTELLIGENCE).setValue(intelligence);
	}

	public void setWisdom(int wisdom)
	{
		scores.get(WISDOM).setValue(wisdom);
	}

	public void setCharisma(int charisma)
	{
		scores.get(CHARISMA).setValue(charisma);
	}

	public AbilityScore getAbilityScore(String name)
	{
		return AbilityScores.unmodifiableAbilityScore(scores.get(name));
	}

	public static AbilityScore unmodifiableAbilityScore(AbilityScore as)
	{
		return new UnmodifiableAbilityScore(as);
	}
}
