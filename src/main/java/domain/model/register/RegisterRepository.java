package domain.model.register;

import domain.model.common.Term;
import domain.model.course.Course;
import domain.model.common.DataNotExistInDatabase;

import java.util.ArrayList;

public class RegisterRepository {
    private static RegisterRepository ourInstance = new RegisterRepository();
    public RegisterRepository getInstance(){
        return ourInstance;
    }

    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Registration> registrations = new ArrayList<>();
    private ArrayList<ReceivedCourse> receivedCourses = new ArrayList<>();

    public Student findStudent(String id) throws DataNotExistInDatabase {
        for (Student student: this.students)
            if (student.getId().equals(id))
                return student;
        throw new DataNotExistInDatabase();
    }

    public Registration findRegistration(Term term) throws DataNotExistInDatabase {
        for (Registration registration: this.registrations)
            if (registration.getTerm().sameValueAs(term))
                return registration;
        throw new DataNotExistInDatabase();
    }

    public ReceivedCourse findReceivedCourse(Course course) throws DataNotExistInDatabase {
        for (ReceivedCourse receivedCourse: this.receivedCourses)
            if (receivedCourse.getCourse().sameValueAs(course))
                return receivedCourse;
        throw new DataNotExistInDatabase();
    }

    public void insertReceivedCourse(ReceivedCourse receivedCourse){
        this.receivedCourses.add(receivedCourse);
    }

}
