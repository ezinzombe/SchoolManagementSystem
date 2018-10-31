package zw.co.soskode.SchoolManagementSystem.model;

/**
 * Created by tdhla on 2/5/2017.
 */
public enum AddressType {

    RESIDENTIAL("Residential"),
    POSTAL("Postal");

    private final String name;

    AddressType(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }


}
