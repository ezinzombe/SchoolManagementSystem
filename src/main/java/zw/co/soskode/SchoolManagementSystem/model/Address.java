package zw.co.soskode.SchoolManagementSystem.model;

import javax.persistence.*;
import javax.xml.stream.util.StreamReaderDelegate;

@Entity
@Table(name = "address")
public class Address extends BaseEntityId {

    private Student student;
    private String addressLine1;
    private String addressLine2;
    @Enumerated
    private AddressType addressType;
    private String contact;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @ManyToOne
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }



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

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    @Transient
    public String getDetail() {
        return getAddressLine1() + " " + getAddressLine2();
    }
}
