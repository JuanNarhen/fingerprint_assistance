package com.profuno.fingerprint_assistance.infrastructure.adapters;

import com.profuno.fingerprint_assistance.domain.contracts.data.AssistantAble;
import com.profuno.fingerprint_assistance.domain.dto.AssistantDTO;
import com.profuno.fingerprint_assistance.infrastructure.repositories.Assistant;
import com.profuno.fingerprint_assistance.infrastructure.repositories.AssistantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Qualifier("PostgresAssistantRepository")
@Repository("assistantPersistence")
public class PostgresAssistantRepository implements AssistantAble {
    @Autowired
    private AssistantRepository assistantRepository;

    @Override
    public AssistantDTO save(AssistantDTO assistantDTO) {
        return assistantRepository.save(new Assistant(assistantDTO)).toAssistantDTO();
    }

    @Override
    public List<AssistantDTO> listAssistants(Predicate<Assistant> predicate) {
        return assistantRepository.findAll().stream().filter(predicate).map(aE -> {return aE.toAssistantDTO();}).collect(Collectors.toList());
    }
}
