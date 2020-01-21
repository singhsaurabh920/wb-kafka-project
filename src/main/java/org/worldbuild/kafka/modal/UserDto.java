package org.worldbuild.kafka.modal;

public class UserDto {

    private String username;
    private String  email;
    private long salary;

    public UserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                '}';
    }
}
