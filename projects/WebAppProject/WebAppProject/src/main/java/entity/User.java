package entity;

import java.util.List;

public class User implements EntityInterface {
    private int id;
    private String email;
    private String password;
    private String gender;  // "м", "ж"
    private String country;
    private List<Post> posts;

    public User() {}

    public User(String email, String password, String gender, String country) {
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.country = country;
    }

    public User(int id, String email, String password, String gender, String country) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
