package mn.edu.num.lotteryProject.entity;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Date;

@Entity
@Table(name = "LOTTERY")
public class Lottery {

    @Column(name = "NAME", nullable = false)
    public String name;

    @Column(name = "DESCRIPTION", nullable = false)
    public String description;

    @Lob
    @Column(name = "BANNER")
    public byte[] banner;

    @Column(name = "STARTDATE", nullable = false)
    public Date startDate;

    @Column(name = "ENDDATE", nullable = false)
    public Date endDate;

    @Column(name = "RUNNINGDATE", nullable = false)
    public Date runningDate;

    @Column(name = "NUMBEROFWINNERS")
    public Integer numberOfWinners;

    @Id
    @SequenceGenerator(name = "lottery_sequence", sequenceName = "lottery_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lottery_sequence")
    @Column(name = "ID", updatable = false)
    private long id;


    public Lottery() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getBanner() {
        return banner;
    }

    public void setBanner(byte[] banner) {
        this.banner = banner;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getRunningDate() {
        return runningDate;
    }

    public void setRunningDate(Date runningDate) {
        this.runningDate = runningDate;
    }

    public Integer getNumberOfWinners() {
        return numberOfWinners;
    }

    public void setNumberOfWinners(Integer numberOfWinners) {
        this.numberOfWinners = numberOfWinners;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
