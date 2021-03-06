/*
 * Copyright (C) 2020 Alessandro 'kLeZ' Accardo
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

import it.d4nguard.rgrpg.util.dynacast.DynaManipulator;
import it.d4nguard.rgrpg.util.dynacast.DynaManipulatorException;
import org.apache.commons.lang3.text.StrMatcher;
import org.apache.commons.lang3.text.StrTokenizer;

import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class StringCompiler implements CharSequence, Appendable, Serializable {
	//-----------------------------------------------------------------------

	/**
	 * An empty immutable {@code char} array.
	 */
	public static final char[] EMPTY_CHAR_ARRAY = new char[0];

	//-----------------------------------------------------------------------
	/**
	 * NUL character, formally \0, used only to organize the code
	 */
	public static final char NUL = '\0';

	//-----------------------------------------------------------------------
	/**
	 * The extra capacity for new builders.
	 */
	static final int CAPACITY = 32;
	private static final char[] LINE_SEPARATOR = System.getProperty("line.separator")
			.toCharArray();
	/**
	 * Required for serialization support.
	 *
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = 7628716375283629643L;
	private final char fill;
	private final int length;
	/** Internal data storage. */
	private char[] buffer;
	/** The new line. */
	private String newLine;
	/** The null text. */
	private String nullText;
	/** Current size of the buffer. */
	private int size;

	/**
	 * Constructor that creates an empty builder initial capacity 32 characters.
	 */
	public StringCompiler() {
		this(CAPACITY, 0, NUL);
	}

	public StringCompiler(int length, char fill) {
		this(CAPACITY, length, fill);
	}

	/**
	 * Constructor that creates an empty builder the specified initial capacity.
	 *
	 * @param initialCapacity
	 * 		the initial capacity, zero or less will be converted to 32
	 */
	public StringCompiler(int initialCapacity, int length, char fill) {
		super();
		if (initialCapacity <= 0) {
			initialCapacity = CAPACITY;
		}
		buffer = new char[initialCapacity];
		this.length = length;
		this.fill = fill;
	}

	/**
	 * Constructor that creates a builder from the string, allocating
	 * 32 extra characters for growth.
	 *
	 * @param str
	 * 		the string to copy, null treated as blank string
	 */
	public StringCompiler(String str) {
		this(str, 0, NUL);
	}

	public StringCompiler(String str, int length, char fill) {
		super();
		if (str == null) {
			buffer = new char[CAPACITY];
		} else {
			buffer = new char[str.length() + CAPACITY];
			append(str);
		}
		this.length = length;
		this.fill = fill;
	}

	public static String toString(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	private void preliminars() {
		if (canFill())
			fill();
	}

	/**
	 * Appends a boolean value to the string builder.
	 *
	 * @param value
	 * 		the value to append
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler append(boolean value) {
		preliminars();
		if (value) {
			ensureCapacity(size + 4);
			buffer[size++] = 't';
			buffer[size++] = 'r';
			buffer[size++] = 'u';
			buffer[size++] = 'e';
		} else {
			ensureCapacity(size + 5);
			buffer[size++] = 'f';
			buffer[size++] = 'a';
			buffer[size++] = 'l';
			buffer[size++] = 's';
			buffer[size++] = 'e';
		}
		return this;
	}

	/**
	 * Appends a char value to the string builder.
	 *
	 * @param ch
	 * 		the value to append
	 *
	 * @return this, to enable chaining
	 *
	 * @since 3.0
	 */
	public StringCompiler append(char ch) {
		preliminars();
		int len = length();
		ensureCapacity(len + 1);
		buffer[size++] = ch;
		return this;
	}

	/**
	 * Appends a char array to the string builder.
	 * Appending null will call {@link #appendNull()}.
	 *
	 * @param chars
	 * 		the char array to append
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler append(char[] chars) {
		preliminars();
		if (chars == null) {
			return appendNull();
		}
		internalAppend(chars);
		return this;
	}

	private void internalAppend(char[] chars) {
		int strLen = chars.length;
		if (strLen > 0) {
			int len = length();
			ensureCapacity(len + strLen);
			System.arraycopy(chars, 0, buffer, len, strLen);
			size += strLen;
		}
	}

	/**
	 * Appends a char array to the string builder.
	 * Appending null will call {@link #appendNull()}.
	 *
	 * @param chars
	 * 		the char array to append
	 * @param startIndex
	 * 		the start index, inclusive, must be valid
	 * @param length
	 * 		the length to append, must be valid
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler append(char[] chars, int startIndex, int length) {
		preliminars();
		if (chars == null) {
			return appendNull();
		}
		if (startIndex < 0 || startIndex > chars.length) {
			throw new StringIndexOutOfBoundsException("Invalid startIndex: " + length);
		}
		if (length < 0 || (startIndex + length) > chars.length) {
			throw new StringIndexOutOfBoundsException("Invalid length: " + length);
		}
		if (length > 0) {
			int len = length();
			ensureCapacity(len + length);
			System.arraycopy(chars, startIndex, buffer, len, length);
			size += length;
		}
		return this;
	}

	/**
	 * Appends a CharSequence to this string builder.
	 * Appending null will call {@link #appendNull()}.
	 *
	 * @param seq
	 * 		the CharSequence to append
	 *
	 * @return this, to enable chaining
	 *
	 * @since 3.0
	 */
	public StringCompiler append(CharSequence seq) {
		preliminars();
		if (seq == null) {
			return appendNull();
		}
		return append(seq.toString());
	}

	/**
	 * Appends part of a CharSequence to this string builder.
	 * Appending null will call {@link #appendNull()}.
	 *
	 * @param seq
	 * 		the CharSequence to append
	 * @param startIndex
	 * 		the start index, inclusive, must be valid
	 * @param length
	 * 		the length to append, must be valid
	 *
	 * @return this, to enable chaining
	 *
	 * @since 3.0
	 */
	public StringCompiler append(CharSequence seq, int startIndex, int length) {
		preliminars();
		if (seq == null) {
			return appendNull();
		}
		return append(seq.toString(), startIndex, length);
	}

	/**
	 * Appends a double value to the string builder using
	 * <code>String.valueOf</code>.
	 *
	 * @param value
	 * 		the value to append
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler append(double value) {
		preliminars();
		return append(String.valueOf(value));
	}

	/**
	 * Appends a float value to the string builder using
	 * <code>String.valueOf</code>.
	 *
	 * @param value
	 * 		the value to append
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler append(float value) {
		preliminars();
		return append(String.valueOf(value));
	}

	/**
	 * Appends an int value to the string builder using
	 * <code>String.valueOf</code>.
	 *
	 * @param value
	 * 		the value to append
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler append(int value) {
		preliminars();
		return append(String.valueOf(value));
	}

	/**
	 * Appends a long value to the string builder using
	 * <code>String.valueOf</code>.
	 *
	 * @param value
	 * 		the value to append
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler append(long value) {
		preliminars();
		return append(String.valueOf(value));
	}

	/**
	 * Appends an object to this string builder.
	 * Appending null will call {@link #appendNull()}.
	 *
	 * @param obj
	 * 		the object to append
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler append(Object obj) {
		preliminars();
		if (obj == null) {
			return appendNull();
		}
		return append(obj.toString());
	}

	/**
	 * Appends a string to this string builder.
	 * Appending null will call {@link #appendNull()}.
	 *
	 * @param str
	 * 		the string to append
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler append(String str) {
		preliminars();
		if (str == null) {
			return appendNull();
		}
		int strLen = str.length();
		if (strLen > 0) {
			int len = length();
			ensureCapacity(len + strLen);
			str.getChars(0, strLen, buffer, len);
			size += strLen;
		}
		return this;
	}

	/**
	 * Appends part of a string to this string builder.
	 * Appending null will call {@link #appendNull()}.
	 *
	 * @param str
	 * 		the string to append
	 * @param startIndex
	 * 		the start index, inclusive, must be valid
	 * @param length
	 * 		the length to append, must be valid
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler append(String str, int startIndex, int length) {
		preliminars();
		if (str == null) {
			return appendNull();
		}
		if (startIndex < 0 || startIndex > str.length()) {
			throw new StringIndexOutOfBoundsException("startIndex must be valid");
		}
		if (length < 0 || (startIndex + length) > str.length()) {
			throw new StringIndexOutOfBoundsException("length must be valid");
		}
		if (length > 0) {
			int len = length();
			ensureCapacity(len + length);
			str.getChars(startIndex, startIndex + length, buffer, len);
			size += length;
		}
		return this;
	}

	public StringCompiler append(String fmt, Object... args) {
		return append(String.format(fmt, args));
	}

	/**
	 * Appends a string buffer to this string builder.
	 * Appending null will call {@link #appendNull()}.
	 *
	 * @param str
	 * 		the string buffer to append
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler append(StringBuffer str) {
		return append(str.toString());
	}

	/**
	 * Appends part of a string buffer to this string builder.
	 * Appending null will call {@link #appendNull()}.
	 *
	 * @param str
	 * 		the string to append
	 * @param startIndex
	 * 		the start index, inclusive, must be valid
	 * @param length
	 * 		the length to append, must be valid
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler append(StringBuffer str, int startIndex, int length) {
		return append(str.toString(), startIndex, length);
	}

	/**
	 * Appends another string builder to this string builder.
	 * Appending null will call {@link #appendNull()}.
	 *
	 * @param str
	 * 		the string builder to append
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler append(StringCompiler str) {
		preliminars();
		if (str == null) {
			return appendNull();
		}
		int strLen = str.length();
		if (strLen > 0) {
			int len = length();
			ensureCapacity(len + strLen);
			System.arraycopy(str.buffer, 0, buffer, len, strLen);
			size += strLen;
		}
		return this;
	}

	/**
	 * Appends part of a string builder to this string builder.
	 * Appending null will call {@link #appendNull()}.
	 *
	 * @param str
	 * 		the string to append
	 * @param startIndex
	 * 		the start index, inclusive, must be valid
	 * @param length
	 * 		the length to append, must be valid
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler append(StringCompiler str, int startIndex, int length) {
		return append(str.toString(), startIndex, length);
	}

	public StringCompiler append(Class<?> clazz, Object value, String fieldName) {
		preliminars();
		Field field = GenericsUtils.safeGetDeclaredField(clazz, fieldName);
		if (field != null) {
			append(field.getName()).append(" = ");
			if (field.getType()
					.isArray() || Iterable.class.isAssignableFrom(clazz)) {
				fill(field.getName()
						.length() + 1, ' ');
			}
			try {
				append(DynaManipulator.getValue(fieldName, value));
			} catch (DynaManipulatorException e) {
				append("null");
			}
		} else
			append(fieldName).append(" = null");
		return this;
	}

	/**
	 * Appends each item in a iterable to the builder without any separators.
	 * Appending a null iterable will have no effect.
	 * Each object is appended using {@link #append(Object)}.
	 *
	 * @param iterable
	 * 		the iterable to append
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendAll(Iterable<?> iterable) {
		if (iterable != null) {
			Iterator<?> it = iterable.iterator();
			while (it.hasNext()) {
				append(it.next());
			}
		}
		return this;
	}

	/**
	 * Appends each item in an iterator to the builder without any separators.
	 * Appending a null iterator will have no effect.
	 * Each object is appended using {@link #append(Object)}.
	 *
	 * @param it
	 * 		the iterator to append
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendAll(Iterator<?> it) {
		if (it != null) {
			while (it.hasNext()) {
				append(it.next());
			}
		}
		return this;
	}

	/**
	 * Appends each item in an array to the builder without any separators.
	 * Appending a null array will have no effect.
	 * Each object is appended using {@link #append(Object)}.
	 *
	 * @param array
	 * 		the array to append
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendAll(Object[] array) {
		if (array != null && array.length > 0) {
			for (Object element : array) {
				append(element);
			}
		}
		return this;
	}

	/**
	 * Appends an object to the builder padding on the left to a fixed width.
	 * The <code>String.valueOf</code> of the <code>int</code> value is used.
	 * If the formatted value is larger than the length, the left hand side is
	 * lost.
	 *
	 * @param value
	 * 		the value to append
	 * @param width
	 * 		the fixed field width, zero or negative has no effect
	 * @param padChar
	 * 		the pad character to use
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler appendFixedWidthPadLeft(int value, int width, char padChar) {
		return appendFixedWidthPadLeft(String.valueOf(value), width, padChar);
	}

	/**
	 * Appends an object to the builder padding on the left to a fixed width.
	 * The <code>toString</code> of the object is used.
	 * If the object is larger than the length, the left hand side is lost.
	 * If the object is null, the null text value is used.
	 *
	 * @param obj
	 * 		the object to append, null uses null text
	 * @param width
	 * 		the fixed field width, zero or negative has no effect
	 * @param padChar
	 * 		the pad character to use
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler appendFixedWidthPadLeft(Object obj, int width, char padChar) {
		if (width > 0) {
			ensureCapacity(size + width);
			String str = (obj == null ? getNullText() : obj.toString());
			if (str == null) {
				str = "";
			}
			int strLen = str.length();
			if (strLen >= width) {
				str.getChars(strLen - width, strLen, buffer, size);
			} else {
				int padLen = width - strLen;
				for (int i = 0; i < padLen; i++) {
					buffer[size + i] = padChar;
				}
				str.getChars(0, strLen, buffer, size + padLen);
			}
			size += width;
		}
		return this;
	}

	//-----------------------------------------------------------------------

	/**
	 * Appends an object to the builder padding on the right to a fixed length.
	 * The <code>String.valueOf</code> of the <code>int</code> value is used.
	 * If the object is larger than the length, the right hand side is lost.
	 *
	 * @param value
	 * 		the value to append
	 * @param width
	 * 		the fixed field width, zero or negative has no effect
	 * @param padChar
	 * 		the pad character to use
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler appendFixedWidthPadRight(int value, int width, char padChar) {
		return appendFixedWidthPadRight(String.valueOf(value), width, padChar);
	}

	/**
	 * Appends an object to the builder padding on the right to a fixed length.
	 * The <code>toString</code> of the object is used.
	 * If the object is larger than the length, the right hand side is lost.
	 * If the object is null, null text value is used.
	 *
	 * @param obj
	 * 		the object to append, null uses null text
	 * @param width
	 * 		the fixed field width, zero or negative has no effect
	 * @param padChar
	 * 		the pad character to use
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler appendFixedWidthPadRight(Object obj, int width, char padChar) {
		if (width > 0) {
			ensureCapacity(size + width);
			String str = (obj == null ? getNullText() : obj.toString());
			if (str == null) {
				str = "";
			}
			int strLen = str.length();
			if (strLen >= width) {
				str.getChars(0, width, buffer, size);
			} else {
				int padLen = width - strLen;
				str.getChars(0, strLen, buffer, size);
				for (int i = 0; i < padLen; i++) {
					buffer[size + strLen + i] = padChar;
				}
			}
			size += width;
		}
		return this;
	}

	//-----------------------------------------------------------------------

	/**
	 * Appends a boolean value followed by a new line to the string builder.
	 *
	 * @param value
	 * 		the value to append
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendln(boolean value) {
		return append(value).appendNewLine();
	}

	/**
	 * Appends a char value followed by a new line to the string builder.
	 *
	 * @param ch
	 * 		the value to append
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendln(char ch) {
		return append(ch).appendNewLine();
	}

	/**
	 * Appends a char array followed by a new line to the string builder.
	 * Appending null will call {@link #appendNull()}.
	 *
	 * @param chars
	 * 		the char array to append
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendln(char[] chars) {
		return append(chars).appendNewLine();
	}

	/**
	 * Appends a char array followed by a new line to the string builder.
	 * Appending null will call {@link #appendNull()}.
	 *
	 * @param chars
	 * 		the char array to append
	 * @param startIndex
	 * 		the start index, inclusive, must be valid
	 * @param length
	 * 		the length to append, must be valid
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendln(char[] chars, int startIndex, int length) {
		return append(chars, startIndex, length).appendNewLine();
	}

	/**
	 * Appends a double value followed by a new line to the string builder using
	 * <code>String.valueOf</code>.
	 *
	 * @param value
	 * 		the value to append
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendln(double value) {
		return append(value).appendNewLine();
	}

	/**
	 * Appends a float value followed by a new line to the string builder using
	 * <code>String.valueOf</code>.
	 *
	 * @param value
	 * 		the value to append
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendln(float value) {
		return append(value).appendNewLine();
	}

	/**
	 * Appends an int value followed by a new line to the string builder using
	 * <code>String.valueOf</code>.
	 *
	 * @param value
	 * 		the value to append
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendln(int value) {
		return append(value).appendNewLine();
	}

	/**
	 * Appends a long value followed by a new line to the string builder using
	 * <code>String.valueOf</code>.
	 *
	 * @param value
	 * 		the value to append
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendln(long value) {
		return append(value).appendNewLine();
	}

	/**
	 * Appends an object followed by a new line to this string builder.
	 * Appending null will call {@link #appendNull()}.
	 *
	 * @param obj
	 * 		the object to append
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendln(Object obj) {
		return append(obj).appendNewLine();
	}

	/**
	 * Appends a string followed by a new line to this string builder.
	 * Appending null will call {@link #appendNull()}.
	 *
	 * @param str
	 * 		the string to append
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendln(String str) {
		return append(str).appendNewLine();
	}

	/**
	 * Appends part of a string followed by a new line to this string builder.
	 * Appending null will call {@link #appendNull()}.
	 *
	 * @param str
	 * 		the string to append
	 * @param startIndex
	 * 		the start index, inclusive, must be valid
	 * @param length
	 * 		the length to append, must be valid
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendln(String str, int startIndex, int length) {
		return append(str, startIndex, length).appendNewLine();
	}

	//-----------------------------------------------------------------------

	public StringCompiler appendln(String fmt, Object... args) {
		return appendln(String.format(fmt, args));
	}

	/**
	 * Appends a string buffer followed by a new line to this string builder.
	 * Appending null will call {@link #appendNull()}.
	 *
	 * @param str
	 * 		the string buffer to append
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendln(StringBuffer str) {
		return append(str).appendNewLine();
	}

	/**
	 * Appends part of a string buffer followed by a new line to this string
	 * builder.
	 * Appending null will call {@link #appendNull()}.
	 *
	 * @param str
	 * 		the string to append
	 * @param startIndex
	 * 		the start index, inclusive, must be valid
	 * @param length
	 * 		the length to append, must be valid
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendln(StringBuffer str, int startIndex, int length) {
		return append(str, startIndex, length).appendNewLine();
	}

	/**
	 * Appends another string builder followed by a new line to this string
	 * builder.
	 * Appending null will call {@link #appendNull()}.
	 *
	 * @param str
	 * 		the string builder to append
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendln(StringCompiler str) {
		return append(str).appendNewLine();
	}

	/**
	 * Appends part of a string builder followed by a new line to this string
	 * builder.
	 * Appending null will call {@link #appendNull()}.
	 *
	 * @param str
	 * 		the string to append
	 * @param startIndex
	 * 		the start index, inclusive, must be valid
	 * @param length
	 * 		the length to append, must be valid
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendln(StringCompiler str, int startIndex, int length) {
		return append(str, startIndex, length).appendNewLine();
	}

	public StringCompiler appendln(Class<?> clazz, Object value, String field) {
		return append(clazz, value, field).appendNewLine();
	}

	/**
	 * Appends the new line string to this string builder.
	 * <p>
	 * The new line string can be altered using {@link #setNewLineText(String)}.
	 * This might be used to force the output to always use Unix line endings
	 * even when on Windows.
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler appendNewLine() {
		if (newLine == null) {
			append(LINE_SEPARATOR);
			return this;
		}
		return append(newLine);
	}

	/**
	 * Appends the text representing <code>null</code> to this string builder.
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler appendNull() {
		if (nullText == null) {
			return this;
		}
		return append(nullText);
	}

	/**
	 * Appends the pad character to the builder the specified number of times.
	 *
	 * @param length
	 * 		the length to append, negative means no append
	 * @param padChar
	 * 		the character to append
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler appendPadding(int length, char padChar) {
		if (length >= 0) {
			ensureCapacity(size + length);
			for (int i = 0; i < length; i++) {
				buffer[size++] = padChar;
			}
		}
		return this;
	}

	//-----------------------------------------------------------------------

	/**
	 * Appends a separator if the builder is currently non-empty.
	 * The separator is appended using {@link #append(char)}.
	 * <p>
	 * This method is useful for adding a separator each time around the loop
	 * except the first.
	 *
	 * <pre>
	 * for (Iterator it = list.iterator(); it.hasNext();)
	 * {
	 * 	appendSeparator(',');
	 * 	append(it.next());
	 * }
	 * </pre>
	 * <p>
	 * Note that for this simple example, you should use
	 * {@link #appendWithSeparators(Iterable, String)}.
	 *
	 * @param separator
	 * 		the separator to use
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendSeparator(char separator) {
		if (size() > 0) {
			append(separator);
		}
		return this;
	}

	/**
	 * Append one of both separators to the builder
	 * If the builder is currently empty it will append the
	 * defaultIfEmpty-separator
	 * Otherwise it will append the standard-separator
	 * The separator is appended using {@link #append(char)}.
	 *
	 * @param standard
	 * 		the separator if builder is not empty
	 * @param defaultIfEmpty
	 * 		the separator if builder is empty
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.5
	 */
	public StringCompiler appendSeparator(char standard, char defaultIfEmpty) {
		if (size() > 0) {
			append(standard);
		} else {
			append(defaultIfEmpty);
		}
		return this;
	}

	//-----------------------------------------------------------------------

	/**
	 * Appends a separator to the builder if the loop index is greater than
	 * zero.
	 * The separator is appended using {@link #append(char)}.
	 * <p>
	 * This method is useful for adding a separator each time around the loop
	 * except the first.
	 *
	 * <pre>
	 * for (int i = 0; i &lt; list.size(); i++)
	 * {
	 * 	appendSeparator(&quot;,&quot;, i);
	 * 	append(list.get(i));
	 * }
	 * </pre>
	 * <p>
	 * Note that for this simple example, you should use
	 * {@link #appendWithSeparators(Iterable, String)}.
	 *
	 * @param separator
	 * 		the separator to use
	 * @param loopIndex
	 * 		the loop index
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendSeparator(char separator, int loopIndex) {
		if (loopIndex > 0) {
			append(separator);
		}
		return this;
	}

	/**
	 * Appends a separator if the builder is currently non-empty.
	 * Appending a null separator will have no effect.
	 * The separator is appended using {@link #append(String)}.
	 * <p>
	 * This method is useful for adding a separator each time around the loop
	 * except the first.
	 *
	 * <pre>
	 * for (Iterator it = list.iterator(); it.hasNext();)
	 * {
	 * 	appendSeparator(&quot;,&quot;);
	 * 	append(it.next());
	 * }
	 * </pre>
	 * <p>
	 * Note that for this simple example, you should use
	 * {@link #appendWithSeparators(Iterable, String)}.
	 *
	 * @param separator
	 * 		the separator to use, null means no separator
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendSeparator(String separator) {
		return appendSeparator(separator, null);
	}

	/**
	 * Appends a separator to the builder if the loop index is greater than
	 * zero.
	 * Appending a null separator will have no effect.
	 * The separator is appended using {@link #append(String)}.
	 * <p>
	 * This method is useful for adding a separator each time around the loop
	 * except the first.
	 *
	 * <pre>
	 * for (int i = 0; i &lt; list.size(); i++)
	 * {
	 * 	appendSeparator(&quot;,&quot;, i);
	 * 	append(list.get(i));
	 * }
	 * </pre>
	 * <p>
	 * Note that for this simple example, you should use
	 * {@link #appendWithSeparators(Iterable, String)}.
	 *
	 * @param separator
	 * 		the separator to use, null means no separator
	 * @param loopIndex
	 * 		the loop index
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.3
	 */
	public StringCompiler appendSeparator(String separator, int loopIndex) {
		if (separator != null && loopIndex > 0) {
			append(separator);
		}
		return this;
	}

	/**
	 * Appends one of both separators to the StringCompiler.
	 * If the builder is currently empty it will append the
	 * defaultIfEmpty-separator
	 * Otherwise it will append the standard-separator
	 * Appending a null separator will have no effect.
	 * The separator is appended using {@link #append(String)}.
	 * <p>
	 * This method is for example useful for constructing queries
	 *
	 * <pre>
	 * StringCompiler whereClause = new StringCompiler();
	 * if(searchCommand.getPriority() != null) {
	 *  whereClause.appendSeparator(" and", " where");
	 *  whereClause.append(" priority = ?")
	 * }
	 * if(searchCommand.getComponent() != null) {
	 *  whereClause.appendSeparator(" and", " where");
	 *  whereClause.append(" component = ?")
	 * }
	 * selectClause.append(whereClause)
	 * </pre>
	 *
	 * @param standard
	 * 		the separator if builder is not empty, null means no separator
	 * @param defaultIfEmpty
	 * 		the separator if builder is empty, null means no separator
	 *
	 * @return this, to enable chaining
	 *
	 * @since 2.5
	 */
	public StringCompiler appendSeparator(String standard, String defaultIfEmpty) {
		String str = isEmpty() ? defaultIfEmpty : standard;
		if (str != null) {
			append(str);
		}
		return this;
	}

	//-----------------------------------------------------------------------

	/**
	 * Appends a iterable placing separators between each value, but
	 * not before the first or after the last.
	 * Appending a null iterable will have no effect.
	 * Each object is appended using {@link #append(Object)}.
	 *
	 * @param iterable
	 * 		the iterable to append
	 * @param separator
	 * 		the separator to use, null means no separator
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler appendWithSeparators(Iterable<?> iterable, String separator) {
		if (iterable != null) {
			separator = toString(separator);
			Iterator<?> it = iterable.iterator();
			while (it.hasNext()) {
				append(it.next());
				if (it.hasNext()) {
					append(separator);
				}
			}
		}
		return this;
	}

	/**
	 * Appends an iterator placing separators between each value, but
	 * not before the first or after the last.
	 * Appending a null iterator will have no effect.
	 * Each object is appended using {@link #append(Object)}.
	 *
	 * @param it
	 * 		the iterator to append
	 * @param separator
	 * 		the separator to use, null means no separator
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler appendWithSeparators(Iterator<?> it, String separator) {
		if (it != null) {
			separator = toString(separator);
			while (it.hasNext()) {
				append(it.next());
				if (it.hasNext()) {
					append(separator);
				}
			}
		}
		return this;
	}

	/**
	 * Appends an array placing separators between each value, but
	 * not before the first or after the last.
	 * Appending a null array will have no effect.
	 * Each object is appended using {@link #append(Object)}.
	 *
	 * @param array
	 * 		the array to append
	 * @param separator
	 * 		the separator to use, null means no separator
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler appendWithSeparators(Object[] array, String separator) {
		if (array != null && array.length > 0) {
			separator = toString(separator);
			append(array[0]);
			for (int i = 1; i < array.length; i++) {
				append(separator);
				append(array[i]);
			}
		}
		return this;
	}

	/**
	 * Gets the contents of this builder as a Reader.
	 * <p>
	 * This method allows the contents of the builder to be read using any
	 * standard method that expects a Reader.
	 * <p>
	 * To use, simply create a <code>StringCompiler</code>, populate it with
	 * data, call <code>asReader</code>, and then read away.
	 * <p>
	 * The internal character array is shared between the builder and the
	 * reader. This allows you to append to the builder after creating the
	 * reader, and the changes will be picked up. Note however, that no
	 * synchronization occurs, so you must perform all operations with the
	 * builder and the reader in one thread.
	 * <p>
	 * The returned reader supports marking, and ignores the flush method.
	 *
	 * @return a reader that reads from this builder
	 */
	public Reader asReader() {
		return new StringCompilerReader();
	}

	/**
	 * Creates a tokenizer that can tokenize the contents of this builder.
	 * <p>
	 * This method allows the contents of this builder to be tokenized. The
	 * tokenizer will be setup by default to tokenize on space, tab, newline and
	 * formfeed (as per StringTokenizer). These values can be changed on the
	 * tokenizer class, before retrieving the tokens.
	 * <p>
	 * The returned tokenizer is linked to this builder. You may intermix calls
	 * to the buider and tokenizer within certain limits, however there is no
	 * synchronization. Once the tokenizer has been used once, it must be
	 * {@link StrTokenizer#reset() reset} to pickup the latest changes in the
	 * builder. For example:
	 *
	 * <pre>
	 * StringCompiler b = new StringCompiler();
	 * b.append(&quot;a b &quot;);
	 * StrTokenizer t = b.asTokenizer();
	 * String[] tokens1 = t.getTokenArray(); // returns a,b
	 * b.append(&quot;c d &quot;);
	 * String[] tokens2 = t.getTokenArray(); // returns a,b (c and d ignored)
	 * t.reset(); // reset causes builder changes to be picked up
	 * String[] tokens3 = t.getTokenArray(); // returns a,b,c,d
	 * </pre>
	 * <p>
	 * In addition to simply intermixing appends and tokenization, you can also
	 * call the set methods on the tokenizer to alter how it tokenizes. Just
	 * remember to call reset when you want to pickup builder changes.
	 * <p>
	 * Calling {@link StrTokenizer#reset(String)} or
	 * {@link StrTokenizer#reset(char[])} with a non-null value will break the
	 * link with the builder.
	 *
	 * @return a tokenizer that is linked to this builder
	 */
	public StrTokenizer asTokenizer() {
		return new StringCompilerTokenizer();
	}

	//-----------------------------------------------------------------------

	/**
	 * Gets this builder as a Writer that can be written to.
	 * <p>
	 * This method allows you to populate the contents of the builder using any
	 * standard method that takes a Writer.
	 * <p>
	 * To use, simply create a <code>StringCompiler</code>, call
	 * <code>asWriter</code>, and populate away. The data is available at any
	 * time using the methods of the <code>StringCompiler</code>.
	 * <p>
	 * The internal character array is shared between the builder and the
	 * writer. This allows you to intermix calls that append to the builder and
	 * write using the writer and the changes will be occur correctly. Note
	 * however, that no synchronization occurs, so you must perform all
	 * operations with the builder and the writer in one thread.
	 * <p>
	 * The returned writer ignores the close and flush methods.
	 *
	 * @return a writer that populates this builder
	 */
	public Writer asWriter() {
		return new StringCompilerWriter();
	}

	//-----------------------------------------------------------------------

	/**
	 * Gets the current size of the internal character array buffer.
	 *
	 * @return the capacity
	 */
	public int capacity() {
		return buffer.length;
	}

	//-----------------------------------------------------------------------

	/**
	 * Gets the character at the specified index.
	 *
	 * @param index
	 * 		the index to retrieve, must be valid
	 *
	 * @return the character at the index
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if the index is invalid
	 * @see #setCharAt(int, char)
	 * @see #deleteCharAt(int)
	 */
	public char charAt(int index) {
		if (index < 0 || index >= length()) {
			throw new StringIndexOutOfBoundsException(index);
		}
		return buffer[index];
	}

	//-----------------------------------------------------------------------

	/**
	 * Clears the string builder (convenience Collections API style method).
	 * <p>
	 * This method does not reduce the size of the internal character buffer. To
	 * do that, call <code>clear()</code> followed by
	 * {@link #minimizeCapacity()}.
	 * <p>
	 * This method is the same as {@link #setLength(int)} called with zero and
	 * is provided to match the API of Collections.
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler clear() {
		size = 0;
		return this;
	}

	//-----------------------------------------------------------------------

	/**
	 * Checks if the string builder contains the specified char.
	 *
	 * @param ch
	 * 		the character to find
	 *
	 * @return true if the builder contains the character
	 */
	public boolean contains(char ch) {
		char[] thisBuf = buffer;
		for (int i = 0; i < this.size; i++) {
			if (thisBuf[i] == ch) {
				return true;
			}
		}
		return false;
	}

	//-----------------------------------------------------------------------

	/**
	 * Checks if the string builder contains the specified string.
	 *
	 * @param str
	 * 		the string to find
	 *
	 * @return true if the builder contains the string
	 */
	public boolean contains(String str) {
		return indexOf(str, 0) >= 0;
	}

	/**
	 * Checks if the string builder contains a string matched using the
	 * specified matcher.
	 * <p>
	 * Matchers can be used to perform advanced searching behaviour. For example
	 * you could write a matcher to search for the character 'a' followed by a
	 * number.
	 *
	 * @param matcher
	 * 		the matcher to use, null returns -1
	 *
	 * @return true if the matcher finds a match in the builder
	 */
	public boolean contains(StrMatcher matcher) {
		return indexOf(matcher, 0) >= 0;
	}

	//-----------------------------------------------------------------------

	/**
	 * Deletes the characters between the two specified indices.
	 *
	 * @param startIndex
	 * 		the start index, inclusive, must be valid
	 * @param endIndex
	 * 		the end index, exclusive, must be valid except
	 * 		that if too large it is treated as end of string
	 *
	 * @return this, to enable chaining
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if the index is invalid
	 */
	public StringCompiler delete(int startIndex, int endIndex) {
		endIndex = validateRange(startIndex, endIndex);
		int len = endIndex - startIndex;
		if (len > 0) {
			deleteImpl(startIndex, endIndex, len);
		}
		return this;
	}

	/**
	 * Deletes the character wherever it occurs in the builder.
	 *
	 * @param ch
	 * 		the character to delete
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler deleteAll(char ch) {
		for (int i = 0; i < size; i++) {
			if (buffer[i] == ch) {
				int start = i;
				while (++i < size) {
					if (buffer[i] != ch) {
						break;
					}
				}
				int len = i - start;
				deleteImpl(start, i, len);
				i -= len;
			}
		}
		return this;
	}

	/**
	 * Deletes the string wherever it occurs in the builder.
	 *
	 * @param str
	 * 		the string to delete, null causes no action
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler deleteAll(String str) {
		int len = (str == null ? 0 : str.length());
		if (len > 0) {
			int index = indexOf(str, 0);
			while (index >= 0) {
				deleteImpl(index, index + len, len);
				index = indexOf(str, index);
			}
		}
		return this;
	}

	/**
	 * Deletes all parts of the builder that the matcher matches.
	 * <p>
	 * Matchers can be used to perform advanced deletion behaviour. For example
	 * you could write a matcher to delete all occurances where the character
	 * 'a' is followed by a number.
	 *
	 * @param matcher
	 * 		the matcher to use to find the deletion, null causes no action
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler deleteAll(StrMatcher matcher) {
		return replace(matcher, null, 0, size, -1);
	}

	//-----------------------------------------------------------------------

	/**
	 * Deletes the character at the specified index.
	 *
	 * @param index
	 * 		the index to delete
	 *
	 * @return this, to enable chaining
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if the index is invalid
	 * @see #charAt(int)
	 * @see #setCharAt(int, char)
	 */
	public StringCompiler deleteCharAt(int index) {
		if (index < 0 || index >= size) {
			throw new StringIndexOutOfBoundsException(index);
		}
		deleteImpl(index, index + 1, 1);
		return this;
	}

	//-----------------------------------------------------------------------

	/**
	 * Deletes the character wherever it occurs in the builder.
	 *
	 * @param ch
	 * 		the character to delete
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler deleteFirst(char ch) {
		for (int i = 0; i < size; i++) {
			if (buffer[i] == ch) {
				deleteImpl(i, i + 1, 1);
				break;
			}
		}
		return this;
	}

	//-----------------------------------------------------------------------

	/**
	 * Deletes the string wherever it occurs in the builder.
	 *
	 * @param str
	 * 		the string to delete, null causes no action
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler deleteFirst(String str) {
		int len = (str == null ? 0 : str.length());
		if (len > 0) {
			int index = indexOf(str, 0);
			if (index >= 0) {
				deleteImpl(index, index + len, len);
			}
		}
		return this;
	}

	/**
	 * Deletes the first match within the builder using the specified matcher.
	 * <p>
	 * Matchers can be used to perform advanced deletion behaviour. For example
	 * you could write a matcher to delete where the character 'a' is followed
	 * by a number.
	 *
	 * @param matcher
	 * 		the matcher to use to find the deletion, null causes no action
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler deleteFirst(StrMatcher matcher) {
		return replace(matcher, null, 0, size, 1);
	}

	/**
	 * Checks whether this builder ends with the specified string.
	 * <p>
	 * Note that this method handles null input quietly, unlike String.
	 *
	 * @param str
	 * 		the string to search for, null returns false
	 *
	 * @return true if the builder ends with the string
	 */
	public boolean endsWith(String str) {
		if (str == null) {
			return false;
		}
		int len = str.length();
		if (len == 0) {
			return true;
		}
		if (len > size) {
			return false;
		}
		int pos = size - len;
		for (int i = 0; i < len; i++, pos++) {
			if (buffer[pos] != str.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks the capacity and ensures that it is at least the size specified.
	 *
	 * @param capacity
	 * 		the capacity to ensure
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler ensureCapacity(int capacity) {
		if (capacity > buffer.length) {
			char[] old = buffer;
			buffer = new char[capacity * 2];
			System.arraycopy(old, 0, buffer, 0, size);
		}
		return this;
	}

	/**
	 * Checks the contents of this builder against another to see if they
	 * contain the same character content.
	 *
	 * @param obj
	 * 		the object to check, null returns false
	 *
	 * @return true if the builders contain the same characters in the same
	 * order
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof StringCompiler) {
			return equals((StringCompiler) obj);
		}
		return false;
	}

	/**
	 * Checks the contents of this builder against another to see if they
	 * contain the same character content.
	 *
	 * @param other
	 * 		the object to check, null returns false
	 *
	 * @return true if the builders contain the same characters in the same
	 * order
	 */
	public boolean equals(StringCompiler other) {
		if (this == other) {
			return true;
		}
		if (this.size != other.size) {
			return false;
		}
		char[] thisBuf = this.buffer;
		char[] otherBuf = other.buffer;
		for (int i = size - 1; i >= 0; i--) {
			if (thisBuf[i] != otherBuf[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks the contents of this builder against another to see if they
	 * contain the same character content ignoring case.
	 *
	 * @param other
	 * 		the object to check, null returns false
	 *
	 * @return true if the builders contain the same characters in the same
	 * order
	 */
	public boolean equalsIgnoreCase(StringCompiler other) {
		if (this == other) {
			return true;
		}
		if (this.size != other.size) {
			return false;
		}
		char[] thisBuf = this.buffer;
		char[] otherBuf = other.buffer;
		for (int i = size - 1; i >= 0; i--) {
			char c1 = thisBuf[i];
			char c2 = otherBuf[i];
			if (c1 != c2 && Character.toUpperCase(c1) != Character.toUpperCase(c2)) {
				return false;
			}
		}
		return true;
	}

	public StringCompiler fill() {
		return fill(length, fill);
	}

	public StringCompiler fill(int length, char fill) {
		char[] chars = new char[length];
		Arrays.fill(chars, fill);
		internalAppend(chars);
		return this;
	}

	//-----------------------------------------------------------------------

	/**
	 * Copies the character array into the specified array.
	 *
	 * @param destination
	 * 		the destination array, null will cause an array to be created
	 *
	 * @return the input array, unless that was null or too small
	 */
	public char[] getChars(char[] destination) {
		int len = length();
		if (destination == null || destination.length < len) {
			destination = new char[len];
		}
		System.arraycopy(buffer, 0, destination, 0, len);
		return destination;
	}

	/**
	 * Copies the character array into the specified array.
	 *
	 * @param startIndex
	 * 		first index to copy, inclusive, must be valid
	 * @param endIndex
	 * 		last index, exclusive, must be valid
	 * @param destination
	 * 		the destination array, must not be null or too small
	 * @param destinationIndex
	 * 		the index to start copying in destination
	 *
	 * @throws NullPointerException
	 * 		if the array is null
	 * @throws IndexOutOfBoundsException
	 * 		if any index is invalid
	 */
	public void getChars(int startIndex, int endIndex, char[] destination, int destinationIndex) {
		if (startIndex < 0) {
			throw new StringIndexOutOfBoundsException(startIndex);
		}
		if (endIndex < 0 || endIndex > length()) {
			throw new StringIndexOutOfBoundsException(endIndex);
		}
		if (startIndex > endIndex) {
			throw new StringIndexOutOfBoundsException("end < start");
		}
		System.arraycopy(buffer, startIndex, destination, destinationIndex, endIndex - startIndex);
	}

	/**
	 * Gets the text to be appended when a new line is added.
	 *
	 * @return the new line text, null means use system default
	 */
	public String getNewLineText() {
		return newLine;
	}

	/**
	 * Sets the text to be appended when a new line is added.
	 *
	 * @param newLine
	 * 		the new line text, null means use system default
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler setNewLineText(String newLine) {
		this.newLine = newLine;
		return this;
	}

	/**
	 * Gets the text to be appended when null is added.
	 *
	 * @return the null text, null means no append
	 */
	public String getNullText() {
		return nullText;
	}

	//-----------------------------------------------------------------------

	/**
	 * Sets the text to be appended when null is added.
	 *
	 * @param nullText
	 * 		the null text, null means no append
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler setNullText(String nullText) {
		if (nullText != null && nullText.length() == 0) {
			nullText = null;
		}
		this.nullText = nullText;
		return this;
	}

	//-----------------------------------------------------------------------

	/**
	 * Gets a suitable hash code for this builder.
	 *
	 * @return a hash code
	 */
	@Override
	public int hashCode() {
		char[] buf = buffer;
		int hash = 0;
		for (int i = size - 1; i >= 0; i--) {
			hash = 31 * hash + buf[i];
		}
		return hash;
	}

	/**
	 * Searches the string builder to find the first reference to the specified
	 * char.
	 *
	 * @param ch
	 * 		the character to find
	 *
	 * @return the first index of the character, or -1 if not found
	 */
	public int indexOf(char ch) {
		return indexOf(ch, 0);
	}

	//-----------------------------------------------------------------------

	/**
	 * Searches the string builder to find the first reference to the specified
	 * char.
	 *
	 * @param ch
	 * 		the character to find
	 * @param startIndex
	 * 		the index to start at, invalid index rounded to edge
	 *
	 * @return the first index of the character, or -1 if not found
	 */
	public int indexOf(char ch, int startIndex) {
		startIndex = (startIndex < 0 ? 0 : startIndex);
		if (startIndex >= size) {
			return -1;
		}
		char[] thisBuf = buffer;
		for (int i = startIndex; i < size; i++) {
			if (thisBuf[i] == ch) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Searches the string builder to find the first reference to the specified
	 * string.
	 * <p>
	 * Note that a null input string will return -1, whereas the JDK throws an
	 * exception.
	 *
	 * @param str
	 * 		the string to find, null returns -1
	 *
	 * @return the first index of the string, or -1 if not found
	 */
	public int indexOf(String str) {
		return indexOf(str, 0);
	}

	/**
	 * Searches the string builder to find the first reference to the specified
	 * string starting searching from the given index.
	 * <p>
	 * Note that a null input string will return -1, whereas the JDK throws an
	 * exception.
	 *
	 * @param str
	 * 		the string to find, null returns -1
	 * @param startIndex
	 * 		the index to start at, invalid index rounded to edge
	 *
	 * @return the first index of the string, or -1 if not found
	 */
	public int indexOf(String str, int startIndex) {
		startIndex = (startIndex < 0 ? 0 : startIndex);
		if (str == null || startIndex >= size) {
			return -1;
		}
		int strLen = str.length();
		if (strLen == 1) {
			return indexOf(str.charAt(0), startIndex);
		}
		if (strLen == 0) {
			return startIndex;
		}
		if (strLen > size) {
			return -1;
		}
		char[] thisBuf = buffer;
		int len = size - strLen + 1;
		outer:
		for (int i = startIndex; i < len; i++) {
			for (int j = 0; j < strLen; j++) {
				if (str.charAt(j) != thisBuf[i + j]) {
					continue outer;
				}
			}
			return i;
		}
		return -1;
	}

	/**
	 * Searches the string builder using the matcher to find the first match.
	 * <p>
	 * Matchers can be used to perform advanced searching behaviour. For example
	 * you could write a matcher to find the character 'a' followed by a number.
	 *
	 * @param matcher
	 * 		the matcher to use, null returns -1
	 *
	 * @return the first index matched, or -1 if not found
	 */
	public int indexOf(StrMatcher matcher) {
		return indexOf(matcher, 0);
	}

	/**
	 * Searches the string builder using the matcher to find the first
	 * match searching from the given index.
	 * <p>
	 * Matchers can be used to perform advanced searching behaviour. For example
	 * you could write a matcher to find the character 'a' followed by a number.
	 *
	 * @param matcher
	 * 		the matcher to use, null returns -1
	 * @param startIndex
	 * 		the index to start at, invalid index rounded to edge
	 *
	 * @return the first index matched, or -1 if not found
	 */
	public int indexOf(StrMatcher matcher, int startIndex) {
		startIndex = (startIndex < 0 ? 0 : startIndex);
		if (matcher == null || startIndex >= size) {
			return -1;
		}
		int len = size;
		char[] buf = buffer;
		for (int i = startIndex; i < len; i++) {
			if (matcher.isMatch(buf, i, startIndex, len) > 0) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Inserts the value into this builder.
	 *
	 * @param index
	 * 		the index to add at, must be valid
	 * @param value
	 * 		the value to insert
	 *
	 * @return this, to enable chaining
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if the index is invalid
	 */
	public StringCompiler insert(int index, boolean value) {
		validateIndex(index);
		if (value) {
			ensureCapacity(size + 4);
			System.arraycopy(buffer, index, buffer, index + 4, size - index);
			buffer[index++] = 't';
			buffer[index++] = 'r';
			buffer[index++] = 'u';
			buffer[index] = 'e';
			size += 4;
		} else {
			ensureCapacity(size + 5);
			System.arraycopy(buffer, index, buffer, index + 5, size - index);
			buffer[index++] = 'f';
			buffer[index++] = 'a';
			buffer[index++] = 'l';
			buffer[index++] = 's';
			buffer[index] = 'e';
			size += 5;
		}
		return this;
	}

	/**
	 * Inserts the character array into this builder.
	 * Inserting null will use the stored null text value.
	 *
	 * @param index
	 * 		the index to add at, must be valid
	 * @param chars
	 * 		the char array to insert
	 *
	 * @return this, to enable chaining
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if the index is invalid
	 */
	public StringCompiler insert(int index, char[] chars) {
		validateIndex(index);
		if (chars == null) {
			return insert(index, nullText);
		}
		int len = chars.length;
		if (len > 0) {
			ensureCapacity(size + len);
			System.arraycopy(buffer, index, buffer, index + len, size - index);
			System.arraycopy(chars, 0, buffer, index, len);
			size += len;
		}
		return this;
	}

	/**
	 * Inserts the value into this builder.
	 *
	 * @param index
	 * 		the index to add at, must be valid
	 * @param value
	 * 		the value to insert
	 *
	 * @return this, to enable chaining
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if the index is invalid
	 */
	public StringCompiler insert(int index, char value) {
		validateIndex(index);
		ensureCapacity(size + 1);
		System.arraycopy(buffer, index, buffer, index + 1, size - index);
		buffer[index] = value;
		size++;
		return this;
	}

	/**
	 * Inserts part of the character array into this builder.
	 * Inserting null will use the stored null text value.
	 *
	 * @param index
	 * 		the index to add at, must be valid
	 * @param chars
	 * 		the char array to insert
	 * @param offset
	 * 		the offset into the character array to start at, must be valid
	 * @param length
	 * 		the length of the character array part to copy, must be
	 * 		positive
	 *
	 * @return this, to enable chaining
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if any index is invalid
	 */
	public StringCompiler insert(int index, char[] chars, int offset, int length) {
		validateIndex(index);
		if (chars == null) {
			return insert(index, nullText);
		}
		if (offset < 0 || offset > chars.length) {
			throw new StringIndexOutOfBoundsException("Invalid offset: " + offset);
		}
		if (length < 0 || offset + length > chars.length) {
			throw new StringIndexOutOfBoundsException("Invalid length: " + length);
		}
		if (length > 0) {
			ensureCapacity(size + length);
			System.arraycopy(buffer, index, buffer, index + length, size - index);
			System.arraycopy(chars, offset, buffer, index, length);
			size += length;
		}
		return this;
	}

	/**
	 * Inserts the value into this builder.
	 *
	 * @param index
	 * 		the index to add at, must be valid
	 * @param value
	 * 		the value to insert
	 *
	 * @return this, to enable chaining
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if the index is invalid
	 */
	public StringCompiler insert(int index, double value) {
		return insert(index, String.valueOf(value));
	}

	/**
	 * Inserts the value into this builder.
	 *
	 * @param index
	 * 		the index to add at, must be valid
	 * @param value
	 * 		the value to insert
	 *
	 * @return this, to enable chaining
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if the index is invalid
	 */
	public StringCompiler insert(int index, float value) {
		return insert(index, String.valueOf(value));
	}

	/**
	 * Inserts the value into this builder.
	 *
	 * @param index
	 * 		the index to add at, must be valid
	 * @param value
	 * 		the value to insert
	 *
	 * @return this, to enable chaining
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if the index is invalid
	 */
	public StringCompiler insert(int index, int value) {
		return insert(index, String.valueOf(value));
	}

	/**
	 * Inserts the value into this builder.
	 *
	 * @param index
	 * 		the index to add at, must be valid
	 * @param value
	 * 		the value to insert
	 *
	 * @return this, to enable chaining
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if the index is invalid
	 */
	public StringCompiler insert(int index, long value) {
		return insert(index, String.valueOf(value));
	}

	/**
	 * Inserts the string representation of an object into this builder.
	 * Inserting null will use the stored null text value.
	 *
	 * @param index
	 * 		the index to add at, must be valid
	 * @param obj
	 * 		the object to insert
	 *
	 * @return this, to enable chaining
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if the index is invalid
	 */
	public StringCompiler insert(int index, Object obj) {
		if (obj == null) {
			return insert(index, nullText);
		}
		return insert(index, obj.toString());
	}

	//-----------------------------------------------------------------------

	/**
	 * Inserts the string into this builder.
	 * Inserting null will use the stored null text value.
	 *
	 * @param index
	 * 		the index to add at, must be valid
	 * @param str
	 * 		the string to insert
	 *
	 * @return this, to enable chaining
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if the index is invalid
	 */
	public StringCompiler insert(int index, String str) {
		validateIndex(index);
		if (str == null) {
			str = nullText;
		}
		int strLen = (str == null ? 0 : str.length());
		if (strLen > 0) {
			int newSize = size + strLen;
			ensureCapacity(newSize);
			System.arraycopy(buffer, index, buffer, index + strLen, size - index);
			size = newSize;
			str.getChars(0, strLen, buffer, index); // str cannot be null here
		}
		return this;
	}

	/**
	 * Checks is the string builder is empty (convenience Collections API style
	 * method).
	 * <p>
	 * This method is the same as checking {@link #length()} and is provided to
	 * match the API of Collections.
	 *
	 * @return <code>true</code> if the size is <code>0</code>.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Searches the string builder to find the last reference to the specified
	 * char.
	 *
	 * @param ch
	 * 		the character to find
	 *
	 * @return the last index of the character, or -1 if not found
	 */
	public int lastIndexOf(char ch) {
		return lastIndexOf(ch, size - 1);
	}

	//-----------------------------------------------------------------------

	/**
	 * Searches the string builder to find the last reference to the specified
	 * char.
	 *
	 * @param ch
	 * 		the character to find
	 * @param startIndex
	 * 		the index to start at, invalid index rounded to edge
	 *
	 * @return the last index of the character, or -1 if not found
	 */
	public int lastIndexOf(char ch, int startIndex) {
		startIndex = (startIndex >= size ? size - 1 : startIndex);
		if (startIndex < 0) {
			return -1;
		}
		for (int i = startIndex; i >= 0; i--) {
			if (buffer[i] == ch) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Searches the string builder to find the last reference to the specified
	 * string.
	 * <p>
	 * Note that a null input string will return -1, whereas the JDK throws an
	 * exception.
	 *
	 * @param str
	 * 		the string to find, null returns -1
	 *
	 * @return the last index of the string, or -1 if not found
	 */
	public int lastIndexOf(String str) {
		return lastIndexOf(str, size - 1);
	}

	/**
	 * Searches the string builder to find the last reference to the specified
	 * string starting searching from the given index.
	 * <p>
	 * Note that a null input string will return -1, whereas the JDK throws an
	 * exception.
	 *
	 * @param str
	 * 		the string to find, null returns -1
	 * @param startIndex
	 * 		the index to start at, invalid index rounded to edge
	 *
	 * @return the last index of the string, or -1 if not found
	 */
	public int lastIndexOf(String str, int startIndex) {
		startIndex = (startIndex >= size ? size - 1 : startIndex);
		if (str == null || startIndex < 0) {
			return -1;
		}
		int strLen = str.length();
		if (strLen > 0 && strLen <= size) {
			if (strLen == 1) {
				return lastIndexOf(str.charAt(0), startIndex);
			}

			outer:
			for (int i = startIndex - strLen + 1; i >= 0; i--) {
				for (int j = 0; j < strLen; j++) {
					if (str.charAt(j) != buffer[i + j]) {
						continue outer;
					}
				}
				return i;
			}
		} else if (strLen == 0) {
			return startIndex;
		}
		return -1;
	}

	/**
	 * Searches the string builder using the matcher to find the last match.
	 * <p>
	 * Matchers can be used to perform advanced searching behaviour. For example
	 * you could write a matcher to find the character 'a' followed by a number.
	 *
	 * @param matcher
	 * 		the matcher to use, null returns -1
	 *
	 * @return the last index matched, or -1 if not found
	 */
	public int lastIndexOf(StrMatcher matcher) {
		return lastIndexOf(matcher, size);
	}

	/**
	 * Searches the string builder using the matcher to find the last
	 * match searching from the given index.
	 * <p>
	 * Matchers can be used to perform advanced searching behaviour. For example
	 * you could write a matcher to find the character 'a' followed by a number.
	 *
	 * @param matcher
	 * 		the matcher to use, null returns -1
	 * @param startIndex
	 * 		the index to start at, invalid index rounded to edge
	 *
	 * @return the last index matched, or -1 if not found
	 */
	public int lastIndexOf(StrMatcher matcher, int startIndex) {
		startIndex = (startIndex >= size ? size - 1 : startIndex);
		if (matcher == null || startIndex < 0) {
			return -1;
		}
		char[] buf = buffer;
		int endIndex = startIndex + 1;
		for (int i = startIndex; i >= 0; i--) {
			if (matcher.isMatch(buf, i, 0, endIndex) > 0) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Extracts the leftmost characters from the string builder without
	 * throwing an exception.
	 * <p>
	 * This method extracts the left <code>length</code> characters from the
	 * builder. If this many characters are not available, the whole builder is
	 * returned. Thus the returned string may be shorter than the length
	 * requested.
	 *
	 * @param length
	 * 		the number of characters to extract, negative returns empty
	 * 		string
	 *
	 * @return the new string
	 */
	public String leftString(int length) {
		if (length <= 0) {
			return "";
		} else if (length >= size) {
			return new String(buffer, 0, size);
		} else {
			return new String(buffer, 0, length);
		}
	}

	/**
	 * Gets the length of the string builder.
	 *
	 * @return the length
	 */
	public int length() {
		return size;
	}

	//-----------------------------------------------------------------------

	/**
	 * Extracts some characters from the middle of the string builder without
	 * throwing an exception.
	 * <p>
	 * This method extracts <code>length</code> characters from the builder at
	 * the specified index. If the index is negative it is treated as zero. If
	 * the index is greater than the builder size, it is treated as the builder
	 * size. If the length is negative, the empty string is returned. If
	 * insufficient characters are available in the builder, as much as possible
	 * is returned. Thus the returned string may be shorter than the length
	 * requested.
	 *
	 * @param index
	 * 		the index to start at, negative means zero
	 * @param length
	 * 		the number of characters to extract, negative returns empty
	 * 		string
	 *
	 * @return the new string
	 */
	public String midString(int index, int length) {
		if (index < 0) {
			index = 0;
		}
		if (length <= 0 || index >= size) {
			return "";
		}
		if (size <= index + length) {
			return new String(buffer, index, size - index);
		} else {
			return new String(buffer, index, length);
		}
	}

	/**
	 * Minimizes the capacity to the actual length of the string.
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler minimizeCapacity() {
		if (buffer.length > length()) {
			char[] old = buffer;
			buffer = new char[length()];
			System.arraycopy(old, 0, buffer, 0, size);
		}
		return this;
	}

	/**
	 * Replaces a portion of the string builder with another string.
	 * The length of the inserted string does not have to match the removed
	 * length.
	 *
	 * @param startIndex
	 * 		the start index, inclusive, must be valid
	 * @param endIndex
	 * 		the end index, exclusive, must be valid except
	 * 		that if too large it is treated as end of string
	 * @param replaceStr
	 * 		the string to replace with, null means delete range
	 *
	 * @return this, to enable chaining
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if the index is invalid
	 */
	public StringCompiler replace(int startIndex, int endIndex, String replaceStr) {
		endIndex = validateRange(startIndex, endIndex);
		int insertLen = (replaceStr == null ? 0 : replaceStr.length());
		replaceImpl(startIndex, endIndex, endIndex - startIndex, replaceStr, insertLen);
		return this;
	}

	/**
	 * Advanced search and replaces within the builder using a matcher.
	 * <p>
	 * Matchers can be used to perform advanced behaviour. For example you could
	 * write a matcher to delete all occurances where the character 'a' is
	 * followed by a number.
	 *
	 * @param matcher
	 * 		the matcher to use to find the deletion, null causes no action
	 * @param replaceStr
	 * 		the string to replace the match with, null is a delete
	 * @param startIndex
	 * 		the start index, inclusive, must be valid
	 * @param endIndex
	 * 		the end index, exclusive, must be valid except
	 * 		that if too large it is treated as end of string
	 * @param replaceCount
	 * 		the number of times to replace, -1 for replace all
	 *
	 * @return this, to enable chaining
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if start index is invalid
	 */
	public StringCompiler replace(StrMatcher matcher, String replaceStr, int startIndex, int endIndex, int replaceCount) {
		endIndex = validateRange(startIndex, endIndex);
		return replaceImpl(matcher, replaceStr, startIndex, endIndex, replaceCount);
	}

	// -----------------------------------------------------------------------

	/**
	 * Replaces the search character with the replace character
	 * throughout the builder.
	 *
	 * @param search
	 * 		the search character
	 * @param replace
	 * 		the replace character
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler replaceAll(char search, char replace) {
		if (search != replace) {
			for (int i = 0; i < size; i++) {
				if (buffer[i] == search) {
					buffer[i] = replace;
				}
			}
		}
		return this;
	}

	//-----------------------------------------------------------------------

	/**
	 * Replaces the search string with the replace string throughout the
	 * builder.
	 *
	 * @param searchStr
	 * 		the search string, null causes no action to occur
	 * @param replaceStr
	 * 		the replace string, null is equivalent to an empty string
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler replaceAll(String searchStr, String replaceStr) {
		int searchLen = (searchStr == null ? 0 : searchStr.length());
		if (searchLen > 0) {
			int replaceLen = (replaceStr == null ? 0 : replaceStr.length());
			int index = indexOf(searchStr, 0);
			while (index >= 0) {
				replaceImpl(index, index + searchLen, searchLen, replaceStr, replaceLen);
				index = indexOf(searchStr, index + replaceLen);
			}
		}
		return this;
	}

	//-----------------------------------------------------------------------

	/**
	 * Replaces all matches within the builder with the replace string.
	 * <p>
	 * Matchers can be used to perform advanced replace behaviour. For example
	 * you could write a matcher to replace all occurances where the character
	 * 'a' is followed by a number.
	 *
	 * @param matcher
	 * 		the matcher to use to find the deletion, null causes no action
	 * @param replaceStr
	 * 		the replace string, null is equivalent to an empty string
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler replaceAll(StrMatcher matcher, String replaceStr) {
		return replace(matcher, replaceStr, 0, size, -1);
	}

	//-----------------------------------------------------------------------

	/**
	 * Replaces the first instance of the search character with the
	 * replace character in the builder.
	 *
	 * @param search
	 * 		the search character
	 * @param replace
	 * 		the replace character
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler replaceFirst(char search, char replace) {
		if (search != replace) {
			for (int i = 0; i < size; i++) {
				if (buffer[i] == search) {
					buffer[i] = replace;
					break;
				}
			}
		}
		return this;
	}

	/**
	 * Replaces the first instance of the search string with the replace string.
	 *
	 * @param searchStr
	 * 		the search string, null causes no action to occur
	 * @param replaceStr
	 * 		the replace string, null is equivalent to an empty string
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler replaceFirst(String searchStr, String replaceStr) {
		int searchLen = (searchStr == null ? 0 : searchStr.length());
		if (searchLen > 0) {
			int index = indexOf(searchStr, 0);
			if (index >= 0) {
				int replaceLen = (replaceStr == null ? 0 : replaceStr.length());
				replaceImpl(index, index + searchLen, searchLen, replaceStr, replaceLen);
			}
		}
		return this;
	}

	/**
	 * Replaces the first match within the builder with the replace string.
	 * <p>
	 * Matchers can be used to perform advanced replace behaviour. For example
	 * you could write a matcher to replace where the character 'a' is followed
	 * by a number.
	 *
	 * @param matcher
	 * 		the matcher to use to find the deletion, null causes no action
	 * @param replaceStr
	 * 		the replace string, null is equivalent to an empty string
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler replaceFirst(StrMatcher matcher, String replaceStr) {
		return replace(matcher, replaceStr, 0, size, 1);
	}

	/**
	 * Reverses the string builder placing each character in the opposite index.
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler reverse() {
		if (size == 0) {
			return this;
		}

		int half = size / 2;
		char[] buf = buffer;
		for (int leftIdx = 0, rightIdx = size - 1; leftIdx < half; leftIdx++, rightIdx--) {
			char swap = buf[leftIdx];
			buf[leftIdx] = buf[rightIdx];
			buf[rightIdx] = swap;
		}
		return this;
	}

	//-----------------------------------------------------------------------

	/**
	 * Extracts the rightmost characters from the string builder without
	 * throwing an exception.
	 * <p>
	 * This method extracts the right <code>length</code> characters from the
	 * builder. If this many characters are not available, the whole builder is
	 * returned. Thus the returned string may be shorter than the length
	 * requested.
	 *
	 * @param length
	 * 		the number of characters to extract, negative returns empty
	 * 		string
	 *
	 * @return the new string
	 */
	public String rightString(int length) {
		if (length <= 0) {
			return "";
		} else if (length >= size) {
			return new String(buffer, 0, size);
		} else {
			return new String(buffer, size - length, length);
		}
	}

	/**
	 * Sets the character at the specified index.
	 *
	 * @param index
	 * 		the index to set
	 * @param ch
	 * 		the new character
	 *
	 * @return this, to enable chaining
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if the index is invalid
	 * @see #charAt(int)
	 * @see #deleteCharAt(int)
	 */
	public StringCompiler setCharAt(int index, char ch) {
		if (index < 0 || index >= length()) {
			throw new StringIndexOutOfBoundsException(index);
		}
		buffer[index] = ch;
		return this;
	}

	/**
	 * Updates the length of the builder by either dropping the last characters
	 * or adding filler of Unicode zero.
	 *
	 * @param length
	 * 		the length to set to, must be zero or positive
	 *
	 * @return this, to enable chaining
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if the length is negative
	 */
	public StringCompiler setLength(int length) {
		if (length < 0) {
			throw new StringIndexOutOfBoundsException(length);
		}
		if (length < size) {
			size = length;
		} else if (length > size) {
			ensureCapacity(length);
			int oldEnd = size;
			size = length;
			for (int i = oldEnd; i < length; i++) {
				buffer[i] = '\0';
			}
		}
		return this;
	}

	/**
	 * Gets the length of the string builder.
	 * <p>
	 * This method is the same as {@link #length()} and is provided to match the
	 * API of Collections.
	 *
	 * @return the length
	 */
	public int size() {
		return size;
	}

	/**
	 * Checks whether this builder starts with the specified string.
	 * <p>
	 * Note that this method handles null input quietly, unlike String.
	 *
	 * @param str
	 * 		the string to search for, null returns false
	 *
	 * @return true if the builder starts with the string
	 */
	public boolean startsWith(String str) {
		if (str == null) {
			return false;
		}
		int len = str.length();
		if (len == 0) {
			return true;
		}
		if (len > size) {
			return false;
		}
		for (int i = 0; i < len; i++) {
			if (buffer[i] != str.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @since 3.0
	 */
	public CharSequence subSequence(int startIndex, int endIndex) {
		if (startIndex < 0) {
			throw new StringIndexOutOfBoundsException(startIndex);
		}
		if (endIndex > size) {
			throw new StringIndexOutOfBoundsException(endIndex);
		}
		if (startIndex > endIndex) {
			throw new StringIndexOutOfBoundsException(endIndex - startIndex);
		}
		return substring(startIndex, endIndex);
	}

	//-----------------------------------------------------------------------

	/**
	 * Extracts a portion of this string builder as a string.
	 *
	 * @param start
	 * 		the start index, inclusive, must be valid
	 *
	 * @return the new string
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if the index is invalid
	 */
	public String substring(int start) {
		return substring(start, size);
	}

	//-----------------------------------------------------------------------

	/**
	 * Extracts a portion of this string builder as a string.
	 * <p>
	 * Note: This method treats an endIndex greater than the length of the
	 * builder as equal to the length of the builder, and continues without
	 * error, unlike StringBuffer or String.
	 *
	 * @param startIndex
	 * 		the start index, inclusive, must be valid
	 * @param endIndex
	 * 		the end index, exclusive, must be valid except
	 * 		that if too large it is treated as end of string
	 *
	 * @return the new string
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if the index is invalid
	 */
	public String substring(int startIndex, int endIndex) {
		endIndex = validateRange(startIndex, endIndex);
		return new String(buffer, startIndex, endIndex - startIndex);
	}

	//-----------------------------------------------------------------------

	/**
	 * Copies the builder's character array into a new character array.
	 *
	 * @return a new array that represents the contents of the builder
	 */
	public char[] toCharArray() {
		if (size == 0) {
			return EMPTY_CHAR_ARRAY;
		}
		char[] chars = new char[size];
		System.arraycopy(buffer, 0, chars, 0, size);
		return chars;
	}

	/**
	 * Copies part of the builder's character array into a new character array.
	 *
	 * @param startIndex
	 * 		the start index, inclusive, must be valid
	 * @param endIndex
	 * 		the end index, exclusive, must be valid except that
	 * 		if too large it is treated as end of string
	 *
	 * @return a new array that holds part of the contents of the builder
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if startIndex is invalid,
	 * 		or if endIndex is invalid (but endIndex greater than size is
	 * 		valid)
	 */
	public char[] toCharArray(int startIndex, int endIndex) {
		endIndex = validateRange(startIndex, endIndex);
		int len = endIndex - startIndex;
		if (len == 0) {
			return EMPTY_CHAR_ARRAY;
		}
		char[] chars = new char[len];
		System.arraycopy(buffer, startIndex, chars, 0, len);
		return chars;
	}

	/**
	 * Gets a String version of the string builder, creating a new instance
	 * each time the method is called.
	 * <p>
	 * Note that unlike StringBuffer, the string version returned is independent
	 * of the string builder.
	 *
	 * @return the builder as a String
	 */
	@Override
	public String toString() {
		return new String(buffer, 0, size);
	}

	//-----------------------------------------------------------------------

	/**
	 * Gets a StringBuffer version of the string builder, creating a
	 * new instance each time the method is called.
	 *
	 * @return the builder as a StringBuffer
	 */
	public StringBuffer toStringBuffer() {
		return new StringBuffer(size).append(buffer, 0, size);
	}

	/**
	 * Trims the builder by removing characters less than or equal to a space
	 * from the beginning and end.
	 *
	 * @return this, to enable chaining
	 */
	public StringCompiler trim() {
		if (size == 0) {
			return this;
		}
		int len = size;
		char[] buf = buffer;
		int pos = 0;
		while (pos < len && buf[pos] <= ' ') {
			pos++;
		}
		while (pos < len && buf[len - 1] <= ' ') {
			len--;
		}
		if (len < size) {
			delete(len, size);
		}
		if (pos > 0) {
			delete(0, pos);
		}
		return this;
	}

	//-----------------------------------------------------------------------

	public boolean canFill() {
		return length > 0 && fill != NUL;
	}

	/**
	 * Internal method to delete a range without validation.
	 *
	 * @param startIndex
	 * 		the start index, must be valid
	 * @param endIndex
	 * 		the end index (exclusive), must be valid
	 * @param len
	 * 		the length, must be valid
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if any index is invalid
	 */
	private void deleteImpl(int startIndex, int endIndex, int len) {
		System.arraycopy(buffer, endIndex, buffer, startIndex, size - endIndex);
		size -= len;
	}

	//-----------------------------------------------------------------------

	/**
	 * Internal method to delete a range without validation.
	 *
	 * @param startIndex
	 * 		the start index, must be valid
	 * @param endIndex
	 * 		the end index (exclusive), must be valid
	 * @param removeLen
	 * 		the length to remove (endIndex - startIndex), must be valid
	 * @param insertStr
	 * 		the string to replace with, null means delete range
	 * @param insertLen
	 * 		the length of the insert string, must be valid
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if any index is invalid
	 */
	private void replaceImpl(int startIndex, int endIndex, int removeLen, String insertStr, int insertLen) {
		int newSize = size - removeLen + insertLen;
		if (insertLen != removeLen) {
			ensureCapacity(newSize);
			System.arraycopy(buffer, endIndex, buffer, startIndex + insertLen, size - endIndex);
			size = newSize;
		}
		if (insertLen > 0) {
			insertStr.getChars(0, insertLen, buffer, startIndex);
		}
	}

	/**
	 * Replaces within the builder using a matcher.
	 * <p>
	 * Matchers can be used to perform advanced behaviour. For example you could
	 * write a matcher to delete all occurances where the character 'a' is
	 * followed by a number.
	 *
	 * @param matcher
	 * 		the matcher to use to find the deletion, null causes no action
	 * @param replaceStr
	 * 		the string to replace the match with, null is a delete
	 * @param from
	 * 		the start index, must be valid
	 * @param to
	 * 		the end index (exclusive), must be valid
	 * @param replaceCount
	 * 		the number of times to replace, -1 for replace all
	 *
	 * @return this, to enable chaining
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if any index is invalid
	 */
	private StringCompiler replaceImpl(StrMatcher matcher, String replaceStr, int from, int to, int replaceCount) {
		if (matcher == null || size == 0) {
			return this;
		}
		int replaceLen = (replaceStr == null ? 0 : replaceStr.length());
		char[] buf = buffer;
		for (int i = from; i < to && replaceCount != 0; i++) {
			int removeLen = matcher.isMatch(buf, i, from, to);
			if (removeLen > 0) {
				replaceImpl(i, i + removeLen, removeLen, replaceStr, replaceLen);
				to = to - removeLen + replaceLen;
				i = i + replaceLen - 1;
				if (replaceCount > 0) {
					replaceCount--;
				}
			}
		}
		return this;
	}

	//-----------------------------------------------------------------------

	/**
	 * Validates parameters defining a single index in the builder.
	 *
	 * @param index
	 * 		the index, must be valid
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if the index is invalid
	 */
	protected void validateIndex(int index) {
		if (index < 0 || index > size) {
			throw new StringIndexOutOfBoundsException(index);
		}
	}

	//-----------------------------------------------------------------------

	/**
	 * Validates parameters defining a range of the builder.
	 *
	 * @param startIndex
	 * 		the start index, inclusive, must be valid
	 * @param endIndex
	 * 		the end index, exclusive, must be valid except
	 * 		that if too large it is treated as end of string
	 *
	 * @return the new string
	 *
	 * @throws IndexOutOfBoundsException
	 * 		if the index is invalid
	 */
	protected int validateRange(int startIndex, int endIndex) {
		if (startIndex < 0) {
			throw new StringIndexOutOfBoundsException(startIndex);
		}
		if (endIndex > size) {
			endIndex = size;
		}
		if (startIndex > endIndex) {
			throw new StringIndexOutOfBoundsException("end < start");
		}
		return endIndex;
	}

	/**
	 * Inner class to allow StringCompiler to operate as a writer.
	 */
	class StringCompilerReader extends Reader {
		/** The last mark position. */
		private int mark;
		/** The current stream position. */
		private int pos;

		/**
		 * Default constructor.
		 */
		StringCompilerReader() {
			super();
		}

		/** {@inheritDoc} */
		@Override
		public void close() {
			// do nothing
		}

		/** {@inheritDoc} */
		@Override
		public void mark(int readAheadLimit) {
			mark = pos;
		}

		/** {@inheritDoc} */
		@Override
		public boolean markSupported() {
			return true;
		}

		/** {@inheritDoc} */
		@Override
		public int read() {
			if (!ready()) {
				return -1;
			}
			return StringCompiler.this.charAt(pos++);
		}

		/** {@inheritDoc} */
		@Override
		public int read(char[] b, int off, int len) {
			if (off < 0 || len < 0 || off > b.length || (off + len) > b.length || (off + len) < 0) {
				throw new IndexOutOfBoundsException();
			}
			if (len == 0) {
				return 0;
			}
			if (pos >= StringCompiler.this.size()) {
				return -1;
			}
			if (pos + len > size()) {
				len = StringCompiler.this.size() - pos;
			}
			StringCompiler.this.getChars(pos, pos + len, b, off);
			pos += len;
			return len;
		}

		/** {@inheritDoc} */
		@Override
		public boolean ready() {
			return pos < StringCompiler.this.size();
		}

		/** {@inheritDoc} */
		@Override
		public void reset() {
			pos = mark;
		}

		/** {@inheritDoc} */
		@Override
		public long skip(long n) {
			if (pos + n > StringCompiler.this.size()) {
				n = StringCompiler.this.size() - pos;
			}
			if (n < 0) {
				return 0;
			}
			pos += n;
			return n;
		}
	}

	/**
	 * Inner class to allow StringCompiler to operate as a tokenizer.
	 */
	class StringCompilerTokenizer extends StrTokenizer {
		/**
		 * Default constructor.
		 */
		StringCompilerTokenizer() {
			super();
		}

		/** {@inheritDoc} */
		@Override
		public String getContent() {
			String str = super.getContent();
			if (str == null) {
				return StringCompiler.this.toString();
			} else {
				return str;
			}
		}

		/** {@inheritDoc} */
		@Override
		protected List<String> tokenize(char[] chars, int offset, int count) {
			if (chars == null) {
				return super.tokenize(StringCompiler.this.buffer, 0, StringCompiler.this.size());
			} else {
				return super.tokenize(chars, offset, count);
			}
		}
	}

	//-----------------------------------------------------------------------

	/**
	 * Inner class to allow StringCompiler to operate as a writer.
	 */
	class StringCompilerWriter extends Writer {
		/**
		 * Default constructor.
		 */
		StringCompilerWriter() {
			super();
		}

		/** {@inheritDoc} */
		@Override
		public void close() {
			// do nothing
		}

		/** {@inheritDoc} */
		@Override
		public void flush() {
			// do nothing
		}

		/** {@inheritDoc} */
		@Override
		public void write(char[] cbuf) {
			StringCompiler.this.append(cbuf);
		}

		/** {@inheritDoc} */
		@Override
		public void write(char[] cbuf, int off, int len) {
			StringCompiler.this.append(cbuf, off, len);
		}

		/** {@inheritDoc} */
		@Override
		public void write(int c) {
			StringCompiler.this.append((char) c);
		}

		/** {@inheritDoc} */
		@Override
		public void write(String str) {
			StringCompiler.this.append(str);
		}

		/** {@inheritDoc} */
		@Override
		public void write(String str, int off, int len) {
			StringCompiler.this.append(str, off, len);
		}
	}
}
