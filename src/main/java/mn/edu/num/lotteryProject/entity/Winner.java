package mn.edu.num.lotteryProject.entity;

import javax.persistence.*;

@Entity
@Table(name = "WINNER")
public class Winner {
    @Id
    @SequenceGenerator(name = "winner_sequence", sequenceName = "winner_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "winner_sequence")
    @Column(name = "WINNER_ID", updatable = false)
    private Long id;
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String userName;

    @Column(name = "FIRSTNAME", nullable = false)
    private String firstName;

    @Column(name = "LASTNAME", nullable = false)
    private String lastName;
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @Column(name = "PHONENUMBER", nullable = false)
    private String phoneNumber;

    @ManyToOne
    private Lottery lottery;

    public Winner() {
    }

    public Winner(String userName, String firstName, String lastName, String email, String phoneNumber, String lotteryId) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
