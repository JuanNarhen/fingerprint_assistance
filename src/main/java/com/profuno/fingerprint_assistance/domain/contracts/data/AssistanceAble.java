package com.profuno.fingerprint_assistance.domain.contracts.data;

import com.profuno.fingerprint_assistance.domain.dto.AssistanceDTO;
import com.profuno.fingerprint_assistance.infrastructure.repositories.Assistance;

import java.util.List;
import java.util.function.Predicate;

public interface AssistanceAble {
    AssistanceDTO save(AssistanceDTO assistanceDTO);
    boolean deleteByListId(String listId);
    List<AssistanceDTO> listAssistances(Predicate<Assistance> predicate);
}
