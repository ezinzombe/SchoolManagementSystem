package zw.co.soskode.SchoolManagementSystem.model;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Classes extends BaseEntityId {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
