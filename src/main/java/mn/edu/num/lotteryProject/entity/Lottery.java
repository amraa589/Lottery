package mn.edu.num.lotteryProject.entity;

import java.sql.Blob;
import java.sql.Date;

public class Lottery {
    public String name;
    public String description;
    public Blob banner;
    public Date startDate;
    public Date endDate;
    public Date runningDate;
    public Integer numberOfWinners;


    public Lottery() {

    }

    public Lottery(String name,
                   String description,
                   Blob banner,
                   Date startDate,
                   Date endDate,
                   Date runningDate,
                   Integer numberOfWinners) {
        this.name = name;
        this.description = description;
        this.banner = banner;
        this.startDate = startDate;
        this.endDate = endDate;
        this.runningDate = runningDate;
        this.numberOfWinners = numberOfWinners;
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

    public Blob getBanner() {
        return banner;
    }

    public void setBanner(Blob banner) {
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

}
