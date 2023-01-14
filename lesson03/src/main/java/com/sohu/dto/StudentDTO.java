package com.sohu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
public class StudentDTO {
    @NotNull(message = "cannot be null")
    @Min(value = 0, message = "cannot be < 0")
    @Max(value = 100, message = "cannot be > 100")
    @JsonProperty(value = "studentId")
    private Integer id;

    @NotNull(message = "cannot be null")
    @NotBlank(message = "cannot be empty String")
    @Length(min = 2, max = 8, message = "the length of String should be around 2~8")
    @JsonProperty(value = "sName")
    private String studentName;

    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty List")
    @Size(min = 1, max = 3, message = "the size of List should be around 1~3")
    @JsonProperty(value = "grades")
    private List<Integer> grades;

    @NotNull(message = "cannot be null")
    @Size(min = 3, max = 5, message = "the size of Array should be around 3~5")
    @NotEmpty(message = "cannot be empty Array")
    @JsonProperty(value = "subjects")
    private String subjects[];
}
