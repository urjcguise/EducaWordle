package app.TFGWordle.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date startDate;
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    public Contest() {}

    public Contest(String name, Competition competition, Date startDate, Date endDate) {
        this.name = name;
        this.competition = competition;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getContestName() {
        return name;
    }

    public void setContestName(String name) {
        this.name = name;
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

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}
