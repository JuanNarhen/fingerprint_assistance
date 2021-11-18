package com.profuno.fingerprint_assistance.domain.dto.request;

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
public class RequestListAndAssistanceDTO {
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
