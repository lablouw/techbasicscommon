package techbasics.common.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessedPerson {

	private String name;
	private boolean over18;
	private int numberOfQualifications;
	private String firstQualification;

}
