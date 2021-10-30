package com.profuno.fingerprint_assistance.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssistanceDTO {
    @Size(max = 32)
    private String assistanceId;
    @NotNull
    private LocalDateTime presenceDate;
    @NotNull
    @NotBlank
    @Size(max = 32)
    private String listId;
    @NotNull
    @NotEmpty
    private byte[] assistantFingerprint;

    public void setAssistantFingerprint(String data){
        this.assistantFingerprint = data.getBytes();
    }

    public void setAssistantFingerprint(byte[] data){
        this.assistantFingerprint = data;
    }
}
