package com.profuno.fingerprint_assistance.application;

import com.profuno.fingerprint_assistance.domain.contracts.data.AssistantAble;
import com.profuno.fingerprint_assistance.domain.contracts.data.ListAble;
import com.profuno.fingerprint_assistance.domain.dto.ListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class ListRepoImpl implements ListAble {
    @Autowired
    @Qualifier("PostgresListRepository")
    private ListAble listPersistence;

    @Override
    public ListDTO save(ListDTO listDTO) {
        return listPersistence.save(listDTO);
    }

    @Override
    public boolean delete(String listId) {
        return listPersistence.delete(listId);
    }

    @Override
    public List<ListDTO> listAssistantsLists(Predicate<com.profuno.fingerprint_assistance.infrastructure.repositories.List> predicate) {
        return listPersistence.listAssistantsLists(predicate);
    }
}
