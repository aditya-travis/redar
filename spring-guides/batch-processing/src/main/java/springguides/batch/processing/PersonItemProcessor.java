package springguides.batch.processing;

import org.springframework.batch.item.ItemProcessor;
import springguides.batch.model.Person;

/**
 * Created by meng on 1/25/15.
 */
public class PersonItemProcessor implements ItemProcessor<Person, Person>{
    @Override
    public Person process(Person person) throws Exception {
        final Person transformedPerson = new Person(person.getFirstName().toUpperCase(), person.getLastName().toUpperCase());
        System.out.println("transformed <" + person + "> to <" + transformedPerson + ">");
        return transformedPerson;
    }
}
