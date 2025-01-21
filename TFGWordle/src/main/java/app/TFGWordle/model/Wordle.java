package app.TFGWordle.model;

import app.TFGWordle.security.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="wordle")
public class Wordle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String word;

    @ManyToMany(mappedBy = "wordles")
    @JsonIgnore
    private List<Contest> contests;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Folder folder;

    public Wordle(String word, List<Contest> contests, User user) {
        this.word = word;
        this.contests = contests;
        this.user = user;
    }

    public Wordle() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wordle wordle = (Wordle) o;
        return Objects.equals(id, wordle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setWord(String word) {
        this.word = word;
    }

    public Long getId() {
        return id;
    }
    public String getWord() {
        return word;
    }

    public List<Contest> getContests() {
        return contests;
    }
    public void setContests(List<Contest> contests) {
        this.contests = contests;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }
}
