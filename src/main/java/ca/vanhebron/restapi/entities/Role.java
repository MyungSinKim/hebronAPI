package ca.vanhebron.restapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by rocky.lee on 2017-12-18.
 */

@Entity
@Table(name = "role")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

}
