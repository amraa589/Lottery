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

    @Column(name = "FIRSTNAME", nullable = false)
    private String firstName;

    @Column(name = "LASTNAME", nullable = false)
    private String lastName;
    @Column(name = "REGISTRATION_NUMBER", nullable = false)
    private String registrationNumber;
    @Column(name = "PHONENUMBER", nullable = false)
    private String phoneNumber;

    @ManyToOne
    private Lottery lottery;

    public Winner() {
    }

    public Lottery getLottery() {
        return lottery;
    }

    public void setLottery(Lottery lottery) {
        this.lottery = lottery;
    }

    public Winner(String firstName, String lastName, String registrationNumber, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationNumber = registrationNumber;
        this.phoneNumber = phoneNumber;
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

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String email) {
        this.registrationNumber = email;
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
