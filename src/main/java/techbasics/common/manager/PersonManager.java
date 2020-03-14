package techbasics.common.manager;

import techbasics.common.domain.model.Person;
import techbasics.common.domain.model.ProcessedPerson;

public interface PersonManager {

	ProcessedPerson processPerson(Person p);

}
