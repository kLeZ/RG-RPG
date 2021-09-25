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

public class Triplet<L, C, R> {
	private L left;
	private C center;
	private R right;

	public Triplet() {
	}

	public Triplet(L left, C center, R right) {
		this.left = left;
		this.center = center;
		this.right = right;
	}

	public L getLeft() {
		return left;
	}

	public void setLeft(L left) {
		this.left = left;
	}

	public boolean hasLeft() {
		return getLeft() != null;
	}

	public C getCenter() {
		return center;
	}

	public void setCenter(C center) {
		this.center = center;
	}

	public boolean hasCenter() {
		return getCenter() != null;
	}

	public R getRight() {
		return right;
	}

	public void setRight(R right) {
		this.right = right;
	}

	public boolean hasRight() {
		return getRight() != null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((center == null) ? 0 : center.hashCode());
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Triplet))
			return false;
		Triplet<?, ?, ?> other = (Triplet<?, ?, ?>) obj;
		if (center == null) {
			if (other.center != null)
				return false;
		} else if (!center.equals(other.center))
			return false;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			return other.right == null;
		} else
			return right.equals(other.right);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Triplet [left=");
		builder.append(left);
		builder.append(", center=");
		builder.append(center);
		builder.append(", right=");
		builder.append(right);
		builder.append("]");
		return builder.toString();
	}
}
