package dpapps.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {
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
    private String taskId;

    @Column(name = "is_completed")
    private boolean isCompleted = false;

    public Task() {
        taskId = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                Objects.equals(project, task.project) &&
                Objects.equals(codingLanguage, task.codingLanguage) &&
                Objects.equals(user, task.user) &&
                Objects.equals(taskId, task.taskId) &&
                Objects.equals(isCompleted, task.isCompleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, project, codingLanguage, user, taskId, isCompleted);
    }
}
