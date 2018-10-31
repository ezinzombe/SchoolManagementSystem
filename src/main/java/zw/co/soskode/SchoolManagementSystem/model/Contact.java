package zw.co.soskode.SchoolManagementSystem.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * Created by zinzombe on Oct
 */
@Entity
@Table(name = "contact")
public class Contact extends BaseEntityId {

    private Student studentDetails;
    @NotEmpty
    private String contact1;
    private String contact2;

    @ManyToOne
    public Student getStudentDetails() {
        return studentDetails;
    }

    public void setStudentDetails(Student studentDetails) {
        this.studentDetails = studentDetails;
    }

    public String getContact1() {
        return contact1;
    }

    public void setContact1(String contact1) {
        this.contact1 = contact1;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }
}
