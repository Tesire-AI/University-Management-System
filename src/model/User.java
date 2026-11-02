package model;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;

    public User(String id, String firstName, String lastName, String email) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID cannot be empty");
        }
        this.id = id;
        setfirstName( firstName);
        setlastName( lastName);
        setemail(email);
    }
    public String getid(){

        return id;
    }

    public String getfirstName() {

        return firstName;
    }

    public void setfirstName(String firstName) {
        if(firstName == null || firstName.isBlank()){
            throw new IllegalArgumentException("Firstname cannot be empty");
        }
        this.firstName = firstName.trim();
    }

    public String getlastName() {
        return lastName;
    }

    public void setlastName(String lastName) {
        if(lastName == null || lastName.isBlank()){
            throw new IllegalArgumentException("Lastname cannot be empty");
        }
        this.lastName = lastName.trim();
    }

    public String getemail() {

        return email;
    }

    public void setemail(String email) {
        if(email== null || !email.contains("@")){
            throw new IllegalArgumentException("Email should not be nulland must contain '@'");
        }
        this.email = email.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "ID='" + id + '\'' +
                ", FirstName='" + firstName + '\'' +
                ", LastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
