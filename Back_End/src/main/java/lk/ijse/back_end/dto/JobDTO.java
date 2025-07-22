package lk.ijse.back_end.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
    private Integer id;
    @NotBlank(message = "Job title is mandatory")
    private String jobTitle;
    @NotBlank(message = "Company name is mandatory")
//    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Company name must contain only letters and spaces")
    private String company;
    private String location;
    @NotNull(message = "Job type is mandatory")
    private String type;
    @Size(min = 10, message = "Job description must be at least 10 characters long")
    private String description;
    private String status;
}