package dpapps.model;

import dpapps.constants.DateConstants;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

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
    private LocalDate creation_date;

    public User() {
    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.verified = false;
        this.creation_date = LocalDate.now();
    }
}
