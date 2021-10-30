package com.profuno.fingerprint_assistance.domain.contracts.data;

import com.profuno.fingerprint_assistance.domain.dto.SuscribeListDTO;
import com.profuno.fingerprint_assistance.infrastructure.repositories.SuscribeList;

import java.util.List;
import java.util.function.Predicate;

public interface SuscribeListAble {
    SuscribeListDTO save(SuscribeListDTO suscribeListDTO);
    boolean deleteByListId(String listId);
    List<SuscribeListDTO> listSuscribeLists(Predicate<SuscribeList> predicate);
}
