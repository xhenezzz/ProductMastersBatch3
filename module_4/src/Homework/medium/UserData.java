package Homework.medium;

public class UserData {
    private int id;
    private String name;
    private String email;

    public UserData(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "UserData{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}
