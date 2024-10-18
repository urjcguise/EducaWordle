package app.TFGWordle.security.entity;

import app.TFGWordle.security.enums.RolName;
import jakarta.persistence.*;

@Entity
@Table(name="role")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private RolName rolName;

    public Rol() {
    }

    public Rol(RolName rolName) {
        this.rolName = rolName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RolName getRolName() {
        return rolName;
    }

    public void setRolName(RolName rolName) {
        this.rolName = rolName;
    }
}
