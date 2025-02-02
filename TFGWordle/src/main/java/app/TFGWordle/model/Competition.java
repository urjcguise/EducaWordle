package app.TFGWordle.model;

import app.TFGWordle.security.entity.User;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User professor;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Participation> participations = new HashSet<>();

    @OneToMany(mappedBy = "competition")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Contest> contests = new ArrayList<>();

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

    public Set<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(Set<Participation> participations) {
        this.participations = participations;
    }

    public List<Contest> getContests() {
        return contests;
    }

    public void setContests(List<Contest> contests) {
        this.contests = contests;
    }
}
