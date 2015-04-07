package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  private static int instanceCount = 0;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    ssn = "";
    instanceCount++;
  }

  public int getAge() {
    return age;
  }
  
  public String getName() {
    return name;
  }
  
  public double getSalary() {
    return salary;
  }
  
  public String getSSN() {
    return ssn;
  }

  public void setAge(int a) {
    if (a < 0) {
      throw new IllegalArgumentException("Value can't be negative");
    }
    age = a;
  }

  public void setName(String n) {
    if (n == null) {
      throw new IllegalArgumentException("Value can't be null");
    }
    name = n;
  }

  public void setSalary(double s) {
    salary = s;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }

  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public static int count() {
    return instanceCount;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  public boolean equals(Object obj) {
  if (obj instanceof Person) {
    Person other = (Person)obj;
    return (this.name.equals(other.name) && this.age == other.age); 
  }
    return false;
  }

  public String toString() {
    return ("[Person name:" + this.name + " age:" + this.age + " salary:" + this.salary + "]");
  }

  @Override
  public int compareTo(Person other) {
    return (int)(other.getSalary() - this.salary);
  }

  public static ArrayList<Person> getNewardFamily() {
    ArrayList<Person> list = new ArrayList<Person>();
    list.add(new Person("Ted", 41, 250000));
    list.add(new Person("Charlotte", 43, 150000));
    list.add(new Person("Michael", 22, 10000));
    list.add(new Person("Matthew", 15, 0));

    return list;
  }

  public static class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
      return p1.getAge() - p2.getAge();
    }
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
