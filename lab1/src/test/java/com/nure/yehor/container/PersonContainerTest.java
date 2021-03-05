package com.nure.yehor.container;

import com.nure.yehor.entity.Student;
import com.nure.yehor.entity.Teacher;
import org.junit.Test;

public class PersonContainerTest {

    @Test
    public void demo() {
        PersonContainer container = new PersonContainer();
        container.add(new Teacher("Abu"));
        container.add(new Student("Nike"));
        container.add(new Student("Best"));
        container.add(new Teacher("Anna"));
        container.print();
    }
}