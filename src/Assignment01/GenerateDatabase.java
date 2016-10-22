package Assignment01;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import dao.PeopleStore;
import model.HealthProfile;
import model.Person;
import utils.RandomData;

/*
 *  1.
 *  Using the Health Profile Reader/Writer examples we have used in the lab,
 *  replace the array list or hash map we used as database with a xml file as follows
 *  
 *  2.
 *  Extend the example above to include at least 20 people
 *  (maybe your friends with fake names, **extra points if you find a bigger datasource**) 
 */

public class GenerateDatabase {

	public static void main(String[] args) throws Exception {
		System.out.println("********************************Generate " + args[0] + " person for the people.xml database file******************************");
		PeopleStore people = new PeopleStore();
		RandomData rd = new RandomData();
		for (int i = 1; i <= Integer.parseInt(args[0]); i ++) {
			HealthProfile hp = new HealthProfile(rd.getRandomWeight(), rd.getRandomHeight());
			Person person = new Person(new Long(i), rd.getRandomFirstName(), rd.getRandomLastName(), rd.getRandomBirthdate(), hp);
			people.getData().add(person);
		}

		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		m.marshal(people, new File("people.xml")); // marshalling into a file
		m.marshal(people, System.out); // marshalling into the system default output

	}

}
