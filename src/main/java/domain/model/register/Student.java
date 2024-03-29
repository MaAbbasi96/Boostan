package domain.model.register;

import domain.model.common.Name;
import domain.model.common.PersonalInfo;
import domain.model.common.Term;
import domain.model.course.Course;
import domain.model.offer.CourseOffering;
import domain.model.offer.exception.ClassCapacityFullException;
import domain.model.register.exception.NotDeleteStudentCourseException;
import domain.model.register.exception.courseTakingException.CourseTakingException;
import shared.Entity;

import java.util.ArrayList;
import java.util.Date;

public class Student implements Entity<Student> {
    private ArrayList<AcademicRecord> finishedAcademicRecords;
    private AcademicRecord currentAcademicRecord;
    private Term firstTerm;
    private PersonalInfo personalInfo;

    public Student(Name name, String nationalCode, String id, Date birthDate, Term firstTerm) {
        this.personalInfo = new PersonalInfo(name, nationalCode, id, birthDate);
        this.finishedAcademicRecords = new ArrayList<>();
        this.firstTerm = firstTerm;
    }

    public ArrayList<Course> getPassedCourses(){
        ArrayList<Course> passedCourses = new ArrayList<>();
        for(AcademicRecord academicRecord : this.finishedAcademicRecords)
            passedCourses.addAll(academicRecord.getPassedAndTakenCourses());
        return passedCourses;
    }

    public ArrayList<Course> getCurrentCourses(){
        return new ArrayList<>(currentAcademicRecord.getPassedAndTakenCourses());
    }

    public void receiveCourse(CourseOffering courseOffering) {
        this.currentAcademicRecord.receiveCourse(courseOffering);
    }

    public void deleteCourse(CourseOffering courseOffering) throws NotDeleteStudentCourseException {
        this.currentAcademicRecord.deleteCourse(courseOffering);
    }

    public void validateConditions(CourseOffering courseOffering) throws CourseTakingException,
            ClassCapacityFullException {
        this.currentAcademicRecord.validateConditions(courseOffering, this.calculateLastTermGpa());
    }

    private float calculateLastTermGpa(){
        AcademicRecord lastTermAcademicRecord =
                this.finishedAcademicRecords.get(this.finishedAcademicRecords.size()-1);
        return lastTermAcademicRecord.getGpa();
    }

    public String getId(){
        return this.personalInfo.getId();
    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof Student))
            return false;
        return this.personalInfo.sameIdentityAs((((Student) other).personalInfo));
    }

    @Override
    public boolean sameIdentityAs(Student other) {
        return this.personalInfo.sameIdentityAs(other.personalInfo);
    }
}
