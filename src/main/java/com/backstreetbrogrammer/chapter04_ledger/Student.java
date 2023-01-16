package com.backstreetbrogrammer.chapter04_ledger;

public final class Student {
    private final String studentId;
    private final String firstName;
    private final String lastName;
    private final int age;

    private double totalCash = 200D;

    public Student(final String studentId, final String firstName, final String lastName, final int age) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public double getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(final double totalCash) {
        this.totalCash = totalCash;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", totalCash=" + totalCash +
                '}';
    }
}
