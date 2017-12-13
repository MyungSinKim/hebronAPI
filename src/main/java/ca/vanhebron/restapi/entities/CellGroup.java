package ca.vanhebron.restapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by rocky.lee on 2017-12-01.
 */
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="cellgroup")
public class CellGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;
	private String korean;

	@JsonIgnore
	@OneToOne(targetEntity = Person.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "leader_id")
	private Person groupLeader;

	private String memo;

	@Override
	public String toString() {
		return "CellGroup[id=" + id + ", name=" + name
				+ "]";
	}
}
