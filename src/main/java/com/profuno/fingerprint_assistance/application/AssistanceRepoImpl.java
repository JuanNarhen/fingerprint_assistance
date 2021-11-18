package com.profuno.fingerprint_assistance.application;

import com.profuno.fingerprint_assistance.domain.contracts.data.AssistanceAble;
import com.profuno.fingerprint_assistance.domain.dto.AssistanceDTO;
import com.profuno.fingerprint_assistance.exception.FingerprintApplicationException;
import com.profuno.fingerprint_assistance.infrastructure.repositories.Assistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class AssistanceRepoImpl implements AssistanceAble{
    @Autowired
    @Qualifier("PostgresAssistanceRepository")
    private AssistanceAble assistancePersistence;

    @Override
    public AssistanceDTO save(AssistanceDTO assistanceDTO) {
        return assistancePersistence.save(assistanceDTO);
    }

    @Override
    public boolean deleteByListId(String listId) throws FingerprintApplicationException {
        return assistancePersistence.deleteByListId(listId);
    }

    @Override
    public List<AssistanceDTO> listAssistances(Predicate<Assistance> predicate) {
        return assistancePersistence.listAssistances(predicate);
    }
}
