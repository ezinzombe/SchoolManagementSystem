package zw.co.soskode.SchoolManagementSystem.model;

/**
 * Created by tdhla on 2/5/2017.
 */
public enum FormType {

    FORM_ONE("FORM_ONE"),
    FORM_TWO("FORM_TWO"),
    FORM_THREE("FORM_THREE"),
    FORM_FOUR("FORM_FOUR"),
    LOWER_SIX("LOWER_SIX"),
    UPPER_SIX("UPPER_SIX");

    private final String name;

    FormType(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }


}
