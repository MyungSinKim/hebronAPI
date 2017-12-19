package ca.vanhebron.restapi.models;

import static org.h2.util.StringUtils.isNullOrEmpty;

public enum Status {

	CANADIAN, KOREAN, PR, WORKING_VISA, STUDENT_VISA, VISITOR;

	public static Status from(String value) {
		if (!isNullOrEmpty(value)) {
			return Status.valueOf(value.toUpperCase());
		}
		return null;
	}
}
