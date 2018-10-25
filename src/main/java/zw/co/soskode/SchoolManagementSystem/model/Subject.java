package zw.co.soskode.SchoolManagementSystem.model;

import javax.persistence.Entity;
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
