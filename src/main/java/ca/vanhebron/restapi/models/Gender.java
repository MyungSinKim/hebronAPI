package ca.vanhebron.restapi.models;

import static com.mysql.jdbc.StringUtils.isNullOrEmpty;

/**
 * Created by rocky.lee on 2017-11-29.
 */
public enum Gender {
	MALE, FEMALE;

	public static Gender from(String value) {
		if (!isNullOrEmpty(value)) {
			return Gender.valueOf(value.toUpperCase());
		}
		return null;
	}
}
