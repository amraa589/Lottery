package mn.edu.num.lotteryProject.entity;

import org.hibernate.annotations.Generated;

import javax.persistence.*;

@Entity
@Table(name="USER")
public class User {

    @Id
    @GeneratedValue
    @Column(name="ID")
    private Long id;
    @Column(name="USERNAME")
    public String userName;
    @Column(name="FIRSTNAME")
    public String firstName;
    @Column(name="LASTNAME")
    public String lastName;
    @Column(name="PASSWORD")
    public String password;
    @Column(name="SALT")
    public String salt;
    @Column(name="HASH")
    public String hash;
    @Column(name="EMAIL")
    public String email;

    public User() {

    }

    public User(String userName,
                String firstName,
                String lastName,
                String password,
                String salt,
                String hash,
                String email) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.salt = salt;
        this.hash = hash;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}



