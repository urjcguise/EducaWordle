package app.TFGWordle.model;

import jakarta.persistence.*;

@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String username;
    private String email;
    private String password;
    private String rol;

    public User(Long id, String username, String email, String password, String rol) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
