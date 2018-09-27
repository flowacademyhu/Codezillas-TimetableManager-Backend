package hu.flowacademy.timetablemanager.service.dto;

public enum UserRole {
    ADMIN ("Administrator"),
    MENTOR ("Mentor"),
    STUDENT ("Student");

    private String name;

    UserRole(String role) {
        this.name = role;
    }


    public String toString() {
        return name;
    }
}
