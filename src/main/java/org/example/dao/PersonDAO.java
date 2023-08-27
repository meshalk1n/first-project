package org.example.dao;
import org.example.models.Person;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;
    {
        people = new ArrayList<>();
    }
    public List<Person> getPeople(){
        return people;
    }
    public Person getPersonById(int id){
        for(Person person: people){
            if(person.getId() == id){
                return person;
            }
        }
        return null;
    }
    public void updatedPerson(int id, Person updatedPersonData){
        Person personToBeUpdated = getPersonById(id);
        personToBeUpdated.setName(updatedPersonData.getName());
        personToBeUpdated.setAge(updatedPersonData.getAge());
    }
    public void deletePerson(int id){
        for(int i = 0; i < people.size(); i++) {
            Person person = people.get(i);
            if(person.getId() == id){
                people.remove(i);
            }
        }
    }
    public void addPerson(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
}
