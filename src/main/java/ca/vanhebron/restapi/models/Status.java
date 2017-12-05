package ca.vanhebron.restapi.models;

import static com.mysql.jdbc.StringUtils.isNullOrEmpty;

/**
 * Created by rocky.lee on 2017-12-01.
 */
public enum Status {

	CANADIAN, KOREAN, PR, WORKING_VISA, STUDENT_VISA, VISITOR;

	public static Status from(String value) {
		if (!isNullOrEmpty(value)) {
			return Status.valueOf(value.toUpperCase());
		}
		return null;
	}
}
