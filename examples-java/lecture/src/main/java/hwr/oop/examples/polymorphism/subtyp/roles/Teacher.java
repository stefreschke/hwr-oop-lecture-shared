package hwr.oop.examples.polymorphism.subtyp.roles;

import java.util.Arrays;
import java.util.Collection;

public class Teacher {

  private final Collection<Student> students;

  public Teacher(Student... students) {
    this.students = Arrays.asList(students);
  }

  public void teach() {
    students.forEach(s -> s.study("Content"));
  }
}
