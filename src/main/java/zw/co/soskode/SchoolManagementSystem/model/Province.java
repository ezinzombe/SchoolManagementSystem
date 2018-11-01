package zw.co.soskode.SchoolManagementSystem.model;

/**
 * Created by tdhla on 2/5/2017.
 */
public enum Province {

    HARARE("Harare"),
    BULAWAYO("Bulawayo"),
    MASHONALAND_EAST("Mashonaland_East"),
    MASHONALAND_CENTRAL("Mashonaland Central"),
    MASHONALAND_WEST("Mashonaland West"),
    MASVINGO("Masvingo");

    private final String name;

    Province(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }


}
