package ca.vanhebron.restapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Hosting {
	private int Id;
	private String name;
	private long website;
}
