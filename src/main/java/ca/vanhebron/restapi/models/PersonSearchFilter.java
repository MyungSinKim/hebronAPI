package ca.vanhebron.restapi.models;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by rocky.lee on 2017-12-13.
 */
@Builder
@Getter
public class PersonSearchFilter {
	private String name;
	private Long roleId;
	private Long serviceId;
	private Long cellgroupId;
	private Gender gender;
}
