package uz.isystem.universitysystem.journal;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JournalDto {

    private Integer journalId;
    @NotBlank(message = "Journal name is mandatory")
    private String journalName;
}
