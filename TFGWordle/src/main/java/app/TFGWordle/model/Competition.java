package app.TFGWordle.model;

import app.TFGWordle.security.entity.User;
import jakarta.persistence.*;

@Entity
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User professor;

    public Competition() {
    }

    public Competition(String competitionName, User professor) {
        this.name = competitionName;
        this.professor = professor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCompetitionName() {
        return name;
    }

    public void setCompetitionName(String competitionName) {
        this.name = competitionName;
    }

    public User getProfessor() {
        return professor;
    }

    public void setProfessor(User professor) {
        this.professor = professor;
    }
}
