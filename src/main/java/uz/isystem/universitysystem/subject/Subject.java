package uz.isystem.universitysystem.subject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.group.Group;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subjectId;

    @Column(name = "subject_name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    private Group group;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "is_active")
    public Boolean isActive = false;

    @Column(name = "created_date")
    public LocalDateTime createdDate;

    @Column(name = "updated_date")
    public LocalDateTime updatedDate;

    @Column(name = "deleted_date")
    public LocalDateTime deletedDate;
}
