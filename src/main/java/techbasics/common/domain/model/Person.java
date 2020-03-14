package techbasics.common.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

	private String name;
	private Date dateOfBirth;
	private int age;
	private List<String> qualifications;

}