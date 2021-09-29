/*
 * Copyright (C) 2021 Alessandro 'kLeZ' Accardo
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
package it.d4nguard.rgrpg.util;

import it.d4nguard.rgrpg.Context;

import java.util.*;

public class Dice {
	public static final char OPENED_DICE = '(';
	public static final char CLOSED_DICE = ')';
	public static final char DICE_TOKEN = 'D';
	public static final String DICE_TOKEN_S = String.valueOf(DICE_TOKEN);

	private static final MersenneTwister random = new MersenneTwister(299792458);

	private final int nThrows;
	private final int nFaces;
	private final OperatorType modifierOperator;
	private final int modifier;

	public Dice(int nThrows, int nFaces) {
		this.nThrows = nThrows;
		this.nFaces = nFaces;
		modifierOperator = null;
		modifier = Integer.MIN_VALUE;
	}

	public Dice(int nThrows, int nFaces, OperatorType modifierOperator, int modifier) {
		this.nThrows = nThrows;
		this.nFaces = nFaces;
		this.modifierOperator = modifierOperator;
		this.modifier = modifier;
	}

	public static boolean isDice(String dt) {
		return parse(dt) != null;
	}

	public static LinkedHashMap<Dice, OperatorType> parseMany(String dts) {
		LinkedHashMap<Dice, OperatorType> ret = new LinkedHashMap<>();
		ArrayList<String> parts = Utils.splitEncolosed(dts, OPENED_DICE, CLOSED_DICE);
		Dice dt = null;
		OperatorType ot = null;

		for (int i = 0; (i + 1) <= parts.size(); i += 2) {
			String part1 = parts.get(i);
			String part2 = (i + 1) < parts.size() ? parts.get(i + 1) : "";

			if (isDice(part1)) {
				if (dt != null) {
					ret.put(dt, OperatorType.Addition);
				}
				dt = Dice.parse(part1);
			}

			if (isDice(part2)) {
				ret.put(dt, OperatorType.Addition);
				dt = Dice.parse(part2);
			} else {
				if (OperatorType.isOperator(part2)) {
					ot = OperatorType.getOperator(part2);
				}
				ret.put(dt, ot);
				dt = null;
			}
			ot = null;
		}
		return ret;
	}

	public static Dice parse(String dt) {
		Dice ret = null;
		try {
			dt = dt.toUpperCase();
			String[] diceparts = dt.split(DICE_TOKEN_S);
			if (diceparts.length == 2) {
				int nThrows = Integer.parseInt(diceparts[0]), nFaces, modifier = 0;
				String nFacesBld = "";
				OperatorType opType = null;
				char[] faceParts = diceparts[1].toCharArray();
				for (char c : faceParts) {
					String current = String.valueOf(c);
					if (Utils.isInteger(current)) {
						nFacesBld = nFacesBld.concat(current);
					} else if ((opType = OperatorType.parseOperator(c)) != null) {
						break;
					} else {
						break;
					}
				}
				nFaces = Integer.parseInt(nFacesBld);

				if (opType != null) {
					String[] bonusParts = diceparts[1].split(opType.toEscapedString());
					if ((bonusParts.length == 2) && Utils.isInteger(bonusParts[1])) {
						modifier = Integer.parseInt(bonusParts[1]);
					}
					ret = new Dice(nThrows, nFaces, opType, modifier);
				} else {
					ret = new Dice(nThrows, nFaces);
				}
			}
		} catch (NumberFormatException e) {
			Context.printThrowable(e);
		}
		return ret;
	}

	public static Integer rollSum(String diceExpression) {
		return rollSum(parseMany(diceExpression));
	}

	public static String rollShowResults(String diceExpression) {
		LinkedHashMap<Dice, OperatorType> dice = parseMany(diceExpression);
		StringCompiler sb = new StringCompiler();
		Iterator<Map.Entry<Dice, OperatorType>> it = dice.entrySet()
				.iterator();
		OperatorType op = null;
		Integer res = 0, roll = 0;
		sb.append("{");
		while (it.hasNext()) {
			Map.Entry<Dice, OperatorType> current = it.next();
			if (op != null) {
				roll = current.getKey()
						.roll();
				res = op.doOperation(res, roll);
			} else {
				// First roll
				res = current.getKey()
						.roll();
			}
			op = current.getValue();
			sb.append("[")
					.append(roll)
					.append(":")
					.append(res)
					.append("]");
			if (op != null) {
				sb.append(op.toString());
			}
		}
		sb.append("} = ")
				.append(res);
		return sb.toString();
	}

	public static Integer rollSum(LinkedHashMap<Dice, OperatorType> dice) {
		Integer ret = 0;
		Iterator<Map.Entry<Dice, OperatorType>> it = dice.entrySet()
				.iterator();
		OperatorType op = null;
		while (it.hasNext()) {
			Map.Entry<Dice, OperatorType> current = it.next();
			if (op != null) {
				ret = op.doOperation(ret, current.getKey()
						.roll());
			} else {
				// First roll
				ret = current.getKey()
						.roll();
			}
			op = current.getValue();
		}
		return ret;
	}

	public static ArrayList<Integer> roll(String diceExpression) {
		return roll(new LinkedHashSet<>(parseMany(diceExpression).keySet()));
	}

	public static ArrayList<Integer> roll(LinkedHashSet<Dice> dice) {
		ArrayList<Integer> ret = new ArrayList<>();
		for (final Dice die : dice) {
			ret.add(die.roll());
		}
		return ret;
	}

	public static ArrayList<Integer> roll(Dice... dice) {
		ArrayList<Integer> ret = new ArrayList<>();
		for (Dice die : dice) {
			ret.add(die.roll());
		}
		return ret;
	}

	/**
	 * @return the nThrows
	 */
	public int getnThrows() {
		return nThrows;
	}

	/**
	 * @return the nFaces
	 */
	public int getnFaces() {
		return nFaces;
	}

	/**
	 * @return the modifierOperator
	 */
	public OperatorType getModifierOperator() {
		return modifierOperator;
	}

	/**
	 * @return the modifier
	 */
	public int getModifier() {
		return modifier;
	}

	/**
	 * @return true if the modifier operator exists and the modifier is not {@link Integer#MIN_VALUE}, false otherwise
	 */
	private boolean hasModifier() {
		return (modifierOperator != null) && (modifier != Integer.MIN_VALUE);
	}

	public Integer roll() {
		int ret = 0;
		for (int i = 0; i < getnThrows(); i++) {
			ret += random.next(1, getnFaces());
		}
		if (hasModifier()) {
			ret = getModifierOperator().doOperation(ret, getModifier());
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		boolean ret = false;
		if (o instanceof Dice) {
			ret = getnThrows() == ((Dice) o).getnThrows();
			ret &= getnFaces() == ((Dice) o).getnFaces();
			ret &= getModifierOperator() == ((Dice) o).getModifierOperator();
			ret &= getModifier() == ((Dice) o).getModifier();
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringCompiler sb = new StringCompiler();
		sb.append(nThrows)
				.append(DICE_TOKEN)
				.append(nFaces);
		if (modifierOperator != null) {
			sb.append(modifierOperator)
					.append(modifier);
		}
		return sb.toString();
	}

	public String toEnclosedString() {
		return String.format("%s%s%s", OPENED_DICE, this, CLOSED_DICE);
	}
}
