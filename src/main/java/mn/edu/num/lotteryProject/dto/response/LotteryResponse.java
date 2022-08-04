package mn.edu.num.lotteryProject.dto.response;

import java.sql.Blob;
import java.util.Date;

public class LotteryResponse {
    private String name, description;
    private Date startDate, endDate, runningDate;
    private byte[] banner;
    private Integer numberOfWinners;
    private Long id;


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

    public byte[] getBanner() {
        return banner;
    }

    public void setBanner(byte[] banner) {
        this.banner = banner;
    }

    public Integer getNumberOfWinners() {
        return numberOfWinners;
    }

    public void setNumberOfWinners(Integer numberOfWinners) {
        this.numberOfWinners = numberOfWinners;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
