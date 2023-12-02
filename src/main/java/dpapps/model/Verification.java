package dpapps.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "verification")
public class Verification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true)
    private String code;

    @OneToOne
    private User user;

    public Verification() {}

     public Verification(User user, String code) {
        this.user = user;
        this.code = code;
    }
}
