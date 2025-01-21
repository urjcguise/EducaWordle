package app.TFGWordle.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date startDate;
    private Date endDate;
    private int numTries;
    private Boolean useDictionary;
    private Boolean useExternalFile;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Competition competition;

    @ManyToMany
    @JoinTable(
            name = "contest_wordle",
            joinColumns = @JoinColumn(name = "contest_id"),
            inverseJoinColumns = @JoinColumn(name = "wordle_id")
    )
    private List<Wordle> wordles = new ArrayList<>();


    public Contest() {}

    public Contest(String name, Competition competition, Date startDate, Date endDate, int numTries, Boolean useDictionary, Boolean useExternalFile) {
        this.name = name;
        this.competition = competition;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numTries = numTries;
        this.useDictionary = useDictionary;
        this.useExternalFile = useExternalFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contest contest = (Contest) o;
        return numTries == contest.numTries && Objects.equals(id, contest.id) && Objects.equals(name, contest.name) && Objects.equals(startDate, contest.startDate) && Objects.equals(endDate, contest.endDate) && Objects.equals(useDictionary, contest.useDictionary) && Objects.equals(useExternalFile, contest.useExternalFile) && Objects.equals(competition, contest.competition) && Objects.equals(wordles, contest.wordles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate, endDate, numTries, useDictionary, useExternalFile, competition, wordles);
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

    public int getNumTries() {
        return numTries;
    }

    public void setNumTries(int numTries) {
        this.numTries = numTries;
    }

    public Boolean getUseDictionary() {
        return useDictionary;
    }

    public void setUseDictionary(Boolean useDictionary) {
        this.useDictionary = useDictionary;
    }

    public Boolean getUseExternalFile() {
        return useExternalFile;
    }

    public void setUseExternalFile(Boolean useExternalFile) {
        this.useExternalFile = useExternalFile;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public List<Wordle> getWordles() {
        return wordles;
    }

    public void setWordles(List<Wordle> wordles) {
        this.wordles = wordles;
    }

    public void updateWordles(List<Wordle> wordles) {
        this.wordles.clear();
        this.wordles.addAll(wordles);
    }
}
