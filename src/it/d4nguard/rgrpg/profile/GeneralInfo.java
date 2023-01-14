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
package it.d4nguard.rgrpg.profile;

import it.d4nguard.rgrpg.profile.types.GenderType;
import org.jscience.physics.amount.Amount;
import org.jscience.physics.amount.AmountFormat;

import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

public class GeneralInfo implements Serializable {
	@Serial
	private static final long serialVersionUID = -6846828906540838870L;

	private String name;
	private String description;
	private String skinColor;
	private String hairColor;
	private String eyesColor;
	private Amount<Length> height;
	private Amount<Mass> weight;
	private ZonedDateTime dateOfBirth;
	private GenderType gender;
	private String philosophyDeityReligion;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSkinColor() {
		return skinColor;
	}

	public void setSkinColor(String skinColor) {
		this.skinColor = skinColor;
	}

	public String getHairColor() {
		return hairColor;
	}

	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

	public String getEyesColor() {
		return eyesColor;
	}

	public void setEyesColor(String eyesColor) {
		this.eyesColor = eyesColor;
	}

	public Amount<Length> getHeight() {
		return height;
	}

	public void setHeight(Amount<Length> height) {
		this.height = height;
	}

	public Amount<Mass> getWeight() {
		return weight;
	}

	public void setWeight(Amount<Mass> weight) {
		this.weight = weight;
	}

	public ZonedDateTime getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(ZonedDateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public String getPhilosophyDeityReligion() {
		return philosophyDeityReligion;
	}

	public void setPhilosophyDeityReligion(String philosophyDeityReligion) {
		this.philosophyDeityReligion = philosophyDeityReligion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((eyesColor == null) ? 0 : eyesColor.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((hairColor == null) ? 0 : hairColor.hashCode());
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((philosophyDeityReligion == null) ? 0 : philosophyDeityReligion.hashCode());
		result = prime * result + ((skinColor == null) ? 0 : skinColor.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof GeneralInfo))
			return false;
		GeneralInfo other = (GeneralInfo) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (eyesColor == null) {
			if (other.eyesColor != null)
				return false;
		} else if (!eyesColor.equals(other.eyesColor))
			return false;
		if (gender != other.gender)
			return false;
		if (hairColor == null) {
			if (other.hairColor != null)
				return false;
		} else if (!hairColor.equals(other.hairColor))
			return false;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (philosophyDeityReligion == null) {
			if (other.philosophyDeityReligion != null)
				return false;
		} else if (!philosophyDeityReligion.equals(other.philosophyDeityReligion))
			return false;
		if (skinColor == null) {
			if (other.skinColor != null)
				return false;
		} else if (!skinColor.equals(other.skinColor))
			return false;
		if (weight == null) {
			return other.weight == null;
		} else
			return weight.equals(other.weight);
	}

	@Override
	public String toString() {
		AmountFormat af = AmountFormat.getInstance();
		StringBuilder builder = new StringBuilder();
		builder.append("GeneralInfo [ name = ");
		builder.append(name);
		builder.append(", description = ");
		builder.append(description);
		builder.append(", skinColor = ");
		builder.append(skinColor);
		builder.append(", hairColor = ");
		builder.append(hairColor);
		builder.append(", eyesColor = ");
		builder.append(eyesColor);
		if (height != null) {
			builder.append(", height = ");
			builder.append(af.format(height));
		}
		if (weight != null) {
			builder.append(", weight = ");
			builder.append(af.format(weight));
		}
		builder.append(", dateOfBirth = ");
		builder.append(dateOfBirth);
		builder.append(", gender = ");
		builder.append(gender);
		builder.append(", philosophyDeityReligion = ");
		builder.append(philosophyDeityReligion);
		builder.append(" ]");
		return builder.toString();
	}
}
