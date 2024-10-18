package app.TFGWordle.model;

import jakarta.persistence.*;

@Entity
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    public Contest() {}

    public Contest(String name, Competition competition) {
        this.name = name;
        this.competition = competition;
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

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}
