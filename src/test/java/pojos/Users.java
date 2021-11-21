package pojos;

public class Users {

    private String first_name;
    private String last_name;
    private String email;
    private String password;

    public Users(){
    }

    public Users(String firstName, String lastName, String email, String password) {
        this.first_name = firstName;
        this.last_name = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
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

}
