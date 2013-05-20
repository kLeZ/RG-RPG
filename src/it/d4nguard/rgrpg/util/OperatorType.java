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
package it.d4nguard.rgrpg.util;

public enum OperatorType
{
	Addition,
	Subtraction,
	Multiplication,
	Division;

	public static char[] getOperators()
	{
		return new char[] { '+', '-', '*', '/' };
	}

	public static OperatorType parseOperator(char op)
	{
		OperatorType type = null;
		switch (op)
		{
			case '+':
				type = Addition;
				break;
			case '-':
				type = Subtraction;
				break;
			case '*':
				type = Multiplication;
				break;
			case '/':
				type = Division;
				break;
		}
		return type;
	}

	public int doOperation(int op1, int op2)
	{
		int ret = 0;
		switch (this)
		{
			case Addition:
				ret = add(op1, op2);
				break;
			case Division:
				ret = divide(op1, op2);
				break;
			case Multiplication:
				ret = multiply(op1, op2);
				break;
			case Subtraction:
				ret = subtract(op1, op2);
				break;
		}
		return ret;
	}

	private int add(int op1, int op2)
	{
		check(op1, op2);
		return op1 + op2;
	}

	private int subtract(int op1, int op2)
	{
		check(op1, op2);
		return op1 - op2;
	}

	private int divide(int op1, int op2)
	{
		check(op1, op2);
		return op1 / op2;
	}

	private int multiply(int op1, int op2)
	{
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
	 * @param op2
	 */
	private void check(int op1, int op2)
	{
		if (op1 < 0)
		{
			op1 = 0;
		}
		if (op2 < 0)
		{
			op2 = 0;
		}
		if ((this == Division) && (op2 <= 0))
		{
			Utils.swap(op1, op2);
		}
		if ((this == Subtraction) && ((op1 - op2) < 0))
		{
			Utils.swap(op1, op2);
		}
	}

	public static int indexOfOperator(String s)
	{
		int ret = -1;
		char[] chars = s.toCharArray();
		for (char c : chars)
		{
			if (OperatorType.parseOperator(c) != null)
			{
				ret = s.indexOf(c);
				break;
			}
		}
		return ret;
	}

	public static boolean containsOperator(String s)
	{
		return indexOfOperator(s) > -1;
	}

	public static boolean isOperator(String s)
	{
		return (s.length() == 1) && (indexOfOperator(s) > -1);
	}

	public static boolean isOperator(char c)
	{
		return isOperator(String.valueOf(c));
	}

	public static OperatorType getOperator(String s)
	{
		return parseOperator(s.charAt(indexOfOperator(s)));
	}

	/**
	 * This method escapes the operator in a string following Regex list of
	 * escape chars.
	 * Useful for use in Regex expressions.
	 * 
	 * @return an escaped string representation of the operator.
	 */
	public String toEscapedString()
	{
		String ret = toString();
		switch (this)
		{
			case Addition:
				ret = "\\".concat(ret);
				break;
			case Division:
				break;
			case Multiplication:
				ret = "\\".concat(ret);
				break;
			case Subtraction:
				break;
			default:
				break;
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString()
	{
		String ret = "";
		switch (this)
		{
			case Addition:
				ret = "+";
				break;
			case Division:
				ret = "/";
				break;
			case Multiplication:
				ret = "*";
				break;
			case Subtraction:
				ret = "-";
				break;
		}
		return ret.toString();
	}
}
