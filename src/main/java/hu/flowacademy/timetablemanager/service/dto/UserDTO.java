package hu.flowacademy.timetablemanager.service.dto;

public class UserDTO {

    private Long id;

    private String name;

    private String nickname;

    private String email;

    private String password;

    private String role;

    private Long fk_group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getFk_group() {
        return fk_group;
    }

    public void setFk_group(Long fk_group) {
        this.fk_group = fk_group;
    }
}
