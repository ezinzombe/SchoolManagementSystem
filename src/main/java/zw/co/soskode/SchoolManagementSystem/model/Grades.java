package zw.co.soskode.SchoolManagementSystem.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@Entity
public class Grades extends BaseEntityId{

    private Student student;
    private Subject subject;
    @ManyToOne
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @ManyToOne
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


}
