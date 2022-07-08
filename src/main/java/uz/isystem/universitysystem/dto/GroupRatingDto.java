package uz.isystem.universitysystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupRatingDto {
    private Double averageRating;
    private List<StudentRatingsDto> studentRatingsDtoList;
}
