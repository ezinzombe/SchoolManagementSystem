package zw.co.soskode.SchoolManagementSystem.model;

import javax.persistence.Entity;

/**
 * Created by zinzombe on Oct
 */
@Entity(name = "school")
public class School extends BaseEntityId {

    private String name;
    private String centerNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCenterNumber() {
        return centerNumber;
    }

    public void setCenterNumber(String centerNumber) {
        this.centerNumber = centerNumber;
    }
}
