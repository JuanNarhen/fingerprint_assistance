package com.profuno.fingerprint_assistance.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuscribeListDTO {
    @Size(max = 32)
    private String suscribeListId;
    @NotNull
    @NotEmpty
    private byte[] assistantFingerprint;
    @NotNull
    @NotBlank
    private String listId;

    public void setAssistantFingerprint(String assistantFingerprint){
        this.assistantFingerprint = assistantFingerprint.getBytes();
    }

    public void setAssistantFingerprint(byte[] assistantFingerprint){
        this.assistantFingerprint = assistantFingerprint;
    }
}
