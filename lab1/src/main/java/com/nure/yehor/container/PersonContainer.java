package com.nure.yehor.container;

import com.nure.yehor.entity.Person;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class PersonContainer {

    private Map<String, Person> persons = new TreeMap<>();

    public PersonContainer() {
    }

    public PersonContainer(Collection<Person> persons) {
        persons.forEach(p -> this.persons.put(p.getName(), p));
    }

    public void add(Person person) {
        persons.put(person.getName(), person);
    }

    public void remove(Person person) {
        persons.remove(person.getName());
    }

    public void print() {
        persons.keySet().forEach(System.out::println);
    }
}
