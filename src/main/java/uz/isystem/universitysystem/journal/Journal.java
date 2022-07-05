package uz.isystem.universitysystem.journal;


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
@Table(name = "journals")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer journalId;

    @Column(name = "journal_name")
    private String journalName;

    @OneToOne(mappedBy = "journal")
    private Group group;

    @Column(name = "is_active")
    public Boolean isActive = false;

    @Column(name = "created_date")
    public LocalDateTime createdDate;

    @Column(name = "updated_date")
    public LocalDateTime updatedDate;

    @Column(name = "deleted_date")
    public LocalDateTime deletedDate;
}
