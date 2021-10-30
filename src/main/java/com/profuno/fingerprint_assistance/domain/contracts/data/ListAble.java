package com.profuno.fingerprint_assistance.domain.contracts.data;

import com.profuno.fingerprint_assistance.domain.dto.ListDTO;

import java.util.List;
import java.util.function.Predicate;

public interface ListAble {
    ListDTO save(ListDTO listDTO);
    boolean delete(String listDTO);
    List<ListDTO> listAssistantsLists(Predicate<com.profuno.fingerprint_assistance.infrastructure.repositories.List> predicate);
}
