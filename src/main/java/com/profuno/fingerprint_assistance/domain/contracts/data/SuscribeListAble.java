package com.profuno.fingerprint_assistance.domain.contracts.data;

import com.profuno.fingerprint_assistance.domain.dto.SuscribeListDTO;
import com.profuno.fingerprint_assistance.exception.FingerprintApplicationException;
import com.profuno.fingerprint_assistance.infrastructure.repositories.SuscribeList;

import java.util.List;
import java.util.function.Predicate;

public interface SuscribeListAble {
    SuscribeListDTO save(SuscribeListDTO suscribeListDTO) throws FingerprintApplicationException;
    boolean deleteByListId(String listId) throws FingerprintApplicationException;
    List<SuscribeListDTO> listSuscribeLists(Predicate<SuscribeList> predicate);
}
