package mn.edu.num.lotteryProject.dto;

import java.sql.Blob;
import java.sql.Date;

public class LotteryRequest {
    private String name, description;
    private Integer numberOfWinners;
    private Date runningDate, endDate, startDate;
    private byte[] banner;

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

    public Integer getNumberOfWinners() {
        return numberOfWinners;
    }

    public void setNumberOfWinners(Integer numberOfWinners) {
        this.numberOfWinners = numberOfWinners;
    }

    public Date getRunningDate() {
        return runningDate;
    }

    public void setRunningDate(Date runningDate) {
        this.runningDate = runningDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public byte[] getBanner() {
        return banner;
    }

    public void setBanner(byte[] banner) {
        this.banner = banner;
    }
}
