package com.profuno.fingerprint_assistance.infrastructure.adapters;

import com.profuno.fingerprint_assistance.domain.contracts.data.ListAble;
import com.profuno.fingerprint_assistance.domain.dto.ListDTO;
import com.profuno.fingerprint_assistance.infrastructure.repositories.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Qualifier("PostgresListRepository")
@Repository("listPersistence")
public class PostgresListRepository implements ListAble {
    @Autowired
    private ListRepository listRepository;

    @Override
    public ListDTO save(ListDTO listDTO) {
        return listRepository.save(new com.profuno.fingerprint_assistance.infrastructure.repositories.List(listDTO)).toListDTO();
    }

    @Override
    @Transactional
    public boolean delete(String listId) {
        return listRepository.deleteByListId(listId) == 1;
    }

    @Override
    public List<ListDTO> listAssistantsLists(Predicate<com.profuno.fingerprint_assistance.infrastructure.repositories.List> predicate) {
        return listRepository.findAll().stream().filter(predicate).map(lE -> {return lE.toListDTO();}).collect(Collectors.toList());
    }
}
