package zw.co.soskode.SchoolManagementSystem.model;

import javax.persistence.Entity;

/**
 * Created by zinzombe on Oct
 */
@Entity(name = "address")
public class Address extends BaseEntityId {

    private String city;

    private String suburb;

    private String physicalAddress;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }
}
