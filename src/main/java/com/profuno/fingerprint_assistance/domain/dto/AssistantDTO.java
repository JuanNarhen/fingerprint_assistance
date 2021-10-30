package com.profuno.fingerprint_assistance.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssistantDTO {
    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    private String email;
    @NotNull
    @NotBlank
    private String name;
    private LocalDate lastUpdateDate;
    @NotNull
    @NotEmpty
    private byte[] fingerprintImage;

    public void setFingerprintImage(String data){
        this.fingerprintImage = data.getBytes();
    }

    public void setFingerprintImage(byte[] data){
        this.fingerprintImage = data;
    }
}
