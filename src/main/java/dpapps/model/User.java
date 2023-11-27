package dpapps.model;

import dpapps.constants.DateConstants;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements DatabaseEntryMarker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "verified")
    private Boolean verified;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = DateConstants.DATE_FORMAT)
    private LocalDate creationDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    public User() {
    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.verified = false;
        this.creationDate = LocalDate.now();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, email, verified, creationDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        User otherUser = (User) obj;

        return Objects.equals(id, otherUser.id) &&
                Objects.equals(login, otherUser.login) &&
                Objects.equals(email, otherUser.email) &&
                Objects.equals(verified, otherUser.verified) &&
                Objects.equals(creationDate, otherUser.creationDate);
    }
}
