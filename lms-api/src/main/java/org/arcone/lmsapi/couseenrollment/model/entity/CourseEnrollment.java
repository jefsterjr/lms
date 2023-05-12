package org.arcone.lmsapi.couseenrollment.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.arcone.lmsapi.course.model.entity.Course;
import org.arcone.lmsapi.course.model.entity.CourseStatus;
import org.arcone.lmsapi.student.model.entities.Student;
import org.arcone.lmsapi.tasklog.model.entity.TaskLog;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "enrolled_course")
public class CourseEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @Setter
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    @Setter
    private Course course;

    @Setter
    @OneToMany(mappedBy = "CourseEnrollment")
    private List<TaskLog> taskLogs;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CourseStatus status;

}
