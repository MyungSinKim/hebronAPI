package ca.vanhebron.restapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by rocky.lee on 2017-12-12.
 */
@Data
@AllArgsConstructor
public class Hosting {
	private int Id;
	private String name;
	private long website;
}
