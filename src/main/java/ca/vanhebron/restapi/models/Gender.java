package ca.vanhebron.restapi.models;

import static org.h2.util.StringUtils.isNullOrEmpty;

public enum Gender {
	MALE, FEMALE;

	public static Gender from(String value) {
		if (!isNullOrEmpty(value)) {
			return Gender.valueOf(value.toUpperCase());
		}
		return null;
	}
}
