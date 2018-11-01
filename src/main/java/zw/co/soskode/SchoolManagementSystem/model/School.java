package zw.co.soskode.SchoolManagementSystem.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by zinzombe on Oct
 */
@Entity(name = "school")
public class School extends BaseEntityId {

    private String name;
    private String centerNumber;
    private String addressLine1;
    private String addressLine2;
    private Province province;

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @Enumerated(EnumType.STRING)
    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

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
