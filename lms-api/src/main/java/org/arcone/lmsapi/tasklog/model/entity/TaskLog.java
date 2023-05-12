package org.arcone.lmsapi.tasklog.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.arcone.lmsapi.couseenrollment.model.entity.CourseEnrollment;
import org.arcone.lmsapi.taskcategory.model.entity.TaskCategory;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "task_log")
public class TaskLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "enrolled_course_id", nullable = false)
    private CourseEnrollment CourseEnrollment;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private TaskCategory category;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "time_spent", nullable = false)
    private Double timeSpent;
}
