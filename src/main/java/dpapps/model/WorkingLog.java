package dpapps.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "work_log")
public class WorkingLog implements SeparateDateTimeModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "clocked_id")
    private boolean clockedIn = false;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "start_time", columnDefinition = "TIME")
    private LocalTime startTime;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "end_time", columnDefinition = "TIME")
    private LocalTime endTime;

    public WorkingLog() {}
}
