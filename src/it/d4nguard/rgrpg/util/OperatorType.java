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

public enum OperatorType {
	Addition,
	Subtraction,
	Multiplication,
	Division;

	public static char[] getOperators() {
		return new char[] {
				'+', '-', '*', '/'
		};
	}

	public static OperatorType parseOperator(char op) {
		return switch (op) {
			case '+' -> Addition;
			case '-' -> Subtraction;
			case '*' -> Multiplication;
			case '/' -> Division;
			default -> null;
		};
	}

	public static int indexOfOperator(String s) {
		int ret = -1;
		char[] chars = s.toCharArray();
		for (char c : chars) {
			if (OperatorType.parseOperator(c) != null) {
				ret = s.indexOf(c);
				break;
			}
		}
		return ret;
	}

	public static boolean containsOperator(String s) {
		return indexOfOperator(s) > -1;
	}

	public static boolean isOperator(String s) {
		return (s.length() == 1) && (indexOfOperator(s) > -1);
	}

	public static boolean isOperator(char c) {
		return isOperator(String.valueOf(c));
	}

	public static OperatorType getOperator(String s) {
		return parseOperator(s.charAt(indexOfOperator(s)));
	}

	public int doOperation(int op1, int op2) {
		return switch (this) {
			case Addition -> add(op1, op2);
			case Division -> divide(op1, op2);
			case Multiplication -> multiply(op1, op2);
			case Subtraction -> subtract(op1, op2);
		};
	}

	private int add(int op1, int op2) {
		check(op1, op2);
		return op1 + op2;
	}

	private int subtract(int op1, int op2) {
		check(op1, op2);
		return op1 - op2;
	}

	private int divide(int op1, int op2) {
		check(op1, op2);
		return op1 / op2;
	}

	private int multiply(int op1, int op2) {
		check(op1, op2);
		return op1 * op2;
	}

	/**
	 * This method checks the operands following the rules below.
	 * RULES:
	 * - A number cannot be less than 0, if it is, it will become 0
	 * - A couple of numbers should be reversed if the operation is division and
	 * the divider is equal or less than 0.
	 * - The result of the operation cannot be negative, so if subtraction need
	 * to reverse operands order
	 *
	 * @param op1
	 * 		first operand
	 * @param op2
	 * 		second operand
	 */
	private void check(int op1, int op2) {
		if (op1 < 0) {
			op1 = 0;
		}
		if (op2 < 0) {
			op2 = 0;
		}
		if ((this == Division) && (op2 <= 0)) {
			Utils.swap(op1, op2);
		}
		if ((this == Subtraction) && ((op1 - op2) < 0)) {
			Utils.swap(op1, op2);
		}
	}

	/**
	 * This method escapes the operator in a string following Regex list of
	 * escape chars.
	 * Useful for use in Regex expressions.
	 *
	 * @return an escaped string representation of the operator.
	 */
	public String toEscapedString() {
		String ret = toString();
		switch (this) {
			case Addition, Multiplication -> ret = "\\".concat(ret);
			default -> {
			}
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return switch (this) {
			case Addition -> "+";
			case Division -> "/";
			case Multiplication -> "*";
			case Subtraction -> "-";
		};
	}
}
