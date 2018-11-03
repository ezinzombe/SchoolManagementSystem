package zw.co.soskode.SchoolManagementSystem.model;

/**
 * Created by tdhla on 2/5/2017.
 */
public enum Gender {

    MALE("Male"),
    FEMALE("Female");

    private final String name;

    Gender(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }


}
