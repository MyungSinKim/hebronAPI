package ca.vanhebron.restapi.models;

import ca.vanhebron.restapi.entities.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by rocky.lee on 2017-12-05.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCellGroup {
	private Integer id;
	private String name;
	private String korean;
	private Person groupLeader;
	private String memo;
}
