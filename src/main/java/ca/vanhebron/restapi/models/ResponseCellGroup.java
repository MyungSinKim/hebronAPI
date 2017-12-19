package ca.vanhebron.restapi.models;

import ca.vanhebron.restapi.entities.HebronPerson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCellGroup {
	private Long id;
	private String name;
	private String korean;
	private HebronPerson groupLeader;
	private String memo;
}
