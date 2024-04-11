package delivery.management.system.model.enums;

public enum PermissionType {

    CUSTOMER_READ("customer::read"),
    CUSTOMER_DELETE("customer::delete"),
    CUSTOMER_PUT("customer::put"),
    CUSTOMER_PATCH("customer::patch");

    private final String name;

    PermissionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
