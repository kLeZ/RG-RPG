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
package it.d4nguard.rgrpg.profile;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.profile.AbilityScore.UnmodifiableAbilityScore;
import it.d4nguard.rgrpg.util.StringCompiler;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;

public abstract class AbilityScores implements Serializable {
	private static final long serialVersionUID = 1363222839096421722L;

	protected final HashMap<String, AbilityScore> scores;

	public AbilityScores() {
		scores = new HashMap<>();
		Enumeration<String> avail = Context.getAvailableAbilityScores();
		while (avail.hasMoreElements()) {
			String as = avail.nextElement();
			if (as.startsWith(getPropertyPrefix())) {
				scores.put(as, newAbilityScore(Context.getAbilityScore(as)));
			}
		}
	}

	public static AbilityScore unmodifiableAbilityScore(AbilityScore as) {
		return new UnmodifiableAbilityScore(as);
	}

	protected abstract AbilityScore newAbilityScore(String abilityScore);

	public abstract String getPropertyPrefix();

	public AbilityScore getAbilityScore(String name) {
		return unmodifiableAbilityScore(scores.get(name));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((scores == null) ? 0 : scores.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AbilityScores))
			return false;
		AbilityScores other = (AbilityScores) obj;
		if (scores == null) {
			return other.scores == null;
		} else
			return scores.equals(other.scores);
	}

	@Override
	public String toString() {
		StringCompiler sc = new StringCompiler();
		sc.appendln("AbilityScores [");
		sc.appendln(scores.values());
		sc.append("]");
		return sc.toString();
	}
}
