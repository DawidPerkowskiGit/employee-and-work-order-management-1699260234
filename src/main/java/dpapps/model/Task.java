package dpapps.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task implements DatabaseEntryMarker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private CodingLanguage codingLanguage;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id", unique = true)
    private Long taskId;

    public Task() {
    }
}
