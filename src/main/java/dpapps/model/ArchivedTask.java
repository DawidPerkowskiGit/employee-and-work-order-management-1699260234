package dpapps.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "archived_tasks")
public class ArchivedTask {

    @Id
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
    private String taskId;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @OneToOne
    private TaskReview review;


    public ArchivedTask() {}

    public ArchivedTask(Task task) {
        this.id = task.getId();
        this.taskId= task.getTaskId();
        this.isCompleted = task.isCompleted();
        this.project = task.getProject();
        this.codingLanguage = task.getCodingLanguage();
        this.user = task.getUser();
        this.description = task.getDescription();
        this.name = task.getName();
    }


}
