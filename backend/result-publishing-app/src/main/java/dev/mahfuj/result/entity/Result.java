package dev.mahfuj.result.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "results",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "rollNumber"),
                @UniqueConstraint(columnNames = {"student_id", "examination"})
        })
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    @Column(nullable = false, unique = true)
    private Long rollNumber;

    private Integer year;

    @Column(length = 50)
    private String groupName;

    @Column(length = 50)
    private String examination;

    @ManyToOne()
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @OneToMany(mappedBy = "result", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subject> subjects;
}
