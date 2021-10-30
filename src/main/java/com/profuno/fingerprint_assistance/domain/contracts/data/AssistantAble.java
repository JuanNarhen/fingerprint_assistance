package com.profuno.fingerprint_assistance.domain.contracts.data;

import com.profuno.fingerprint_assistance.domain.dto.AssistantDTO;
import com.profuno.fingerprint_assistance.infrastructure.repositories.Assistant;

import java.util.List;
import java.util.function.Predicate;

public interface AssistantAble {
    AssistantDTO save(AssistantDTO assistantDTO);
    List<AssistantDTO> listAssistants(Predicate<Assistant> predicate);
}
