package com.profuno.fingerprint_assistance.infrastructure.adapters;

import com.profuno.fingerprint_assistance.domain.contracts.data.AssistanceAble;
import com.profuno.fingerprint_assistance.domain.dto.AssistanceDTO;
import com.profuno.fingerprint_assistance.infrastructure.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Qualifier("PostgresAssistanceRepository")
@Repository("assistancePersistence")
public class PostgresAssistanceRepository implements AssistanceAble {

    @Autowired
    private AssistanceRepository assistanceRepository;

    @Override
    public AssistanceDTO save(AssistanceDTO assistanceDTO) {
        return assistanceRepository.save(new Assistance(assistanceDTO)).toAssistanceDTO();
    }

    @Override
    @Transactional
    public boolean deleteByListId(String listId){
        return assistanceRepository.deleteAllByList_ListId(listId) == 1;
    }

    @Override
    public List<AssistanceDTO>
    listAssistances(Predicate<Assistance> predicate) {
        return assistanceRepository.findAll().stream().filter(predicate).map(aE -> {return aE.toAssistanceDTO();}).collect(Collectors.toList());
    }
}
