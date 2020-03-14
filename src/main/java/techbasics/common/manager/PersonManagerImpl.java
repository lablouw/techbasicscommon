package techbasics.common.manager;

import org.springframework.util.CollectionUtils;
import techbasics.common.domain.model.Person;
import techbasics.common.domain.model.ProcessedPerson;

import java.text.SimpleDateFormat;
import java.util.Date;

// Manager layer should contain all business logic, including calls to other managers as well as DAOs.
// No api classes should be used in this layer..

public class PersonManagerImpl implements PersonManager {

	@Override
	public ProcessedPerson processPerson(Person p) {
		ProcessedPerson pp = new ProcessedPerson();
		pp.setName(p.getName());
		pp.setFirstQualification(CollectionUtils.isEmpty(p.getQualifications()) ? null : p.getQualifications().get(0));
		pp.setNumberOfQualifications(CollectionUtils.isEmpty(p.getQualifications()) ? 0 : p.getQualifications().size());

		if (p.getDateOfBirth() != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
			pp.setOver18((Integer.valueOf(df.format(new Date())) - Integer.valueOf(df.format(p.getDateOfBirth()))) >= 18);
		} else {
			pp.setOver18(p.getAge() >= 18);
		}

		return pp;
	}
}
