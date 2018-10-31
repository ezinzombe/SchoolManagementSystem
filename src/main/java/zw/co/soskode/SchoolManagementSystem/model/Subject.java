package zw.co.soskode.SchoolManagementSystem.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

/**
 * Created by zinzombe on Oct
 */
@Entity(name = "subject")
public class Subject extends BaseEntityId {

    @NotEmpty
    private String name;
    @NotEmpty
    private String subjectCode;
    private TeacherDetails teacherDetails;
    private StudentDetails studentDetails;

    @ManyToOne(cascade = {CascadeType.MERGE})
    public StudentDetails getStudentDetails() {
        return studentDetails;
    }

    public void setStudentDetails(StudentDetails studentDetails) {
        this.studentDetails = studentDetails;
    }

    @ManyToOne(cascade = {CascadeType.MERGE})
    public TeacherDetails getTeacherDetails() {
        return teacherDetails;
    }

    public void setTeacherDetails(TeacherDetails teacherDetails) {
        this.teacherDetails = teacherDetails;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
