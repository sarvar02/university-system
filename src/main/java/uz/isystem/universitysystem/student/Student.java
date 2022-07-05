package uz.isystem.universitysystem.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.group.Group;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    private Group groups;

    @Column(name = "group_id")
    private Integer groupId;
}
