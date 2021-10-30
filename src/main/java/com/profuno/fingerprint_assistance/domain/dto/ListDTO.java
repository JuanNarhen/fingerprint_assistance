package com.profuno.fingerprint_assistance.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListDTO {
    @Size(max = 32)
    private String listId;
    @NotNull
    private LocalDate createdDate;
    @NotNull
    private LocalDate lastUpdateDate;
    @NotNull
    @NotBlank
    private String name;
    @PositiveOrZero
    private Integer managerId;
}
