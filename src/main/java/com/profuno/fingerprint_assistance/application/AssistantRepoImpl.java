package com.profuno.fingerprint_assistance.application;

import com.profuno.fingerprint_assistance.domain.contracts.data.AssistantAble;
import com.profuno.fingerprint_assistance.domain.dto.AssistantDTO;
import com.profuno.fingerprint_assistance.infrastructure.repositories.Assistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class AssistantRepoImpl implements AssistantAble {
    @Autowired
    @Qualifier("PostgresAssistantRepository")
    private AssistantAble assistantPersistence;

    @Override
    public AssistantDTO save(AssistantDTO assistantDTO) {
        return assistantPersistence.save(assistantDTO);
    }

    @Override
    public List<AssistantDTO> listAssistants(Predicate<Assistant> predicate) {
        return assistantPersistence.listAssistants(predicate);
    }
}
