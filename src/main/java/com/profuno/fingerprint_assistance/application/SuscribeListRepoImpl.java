package com.profuno.fingerprint_assistance.application;

import com.profuno.fingerprint_assistance.domain.contracts.data.SuscribeListAble;
import com.profuno.fingerprint_assistance.domain.dto.SuscribeListDTO;
import com.profuno.fingerprint_assistance.exception.FingerprintApplicationException;
import com.profuno.fingerprint_assistance.infrastructure.repositories.SuscribeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class SuscribeListRepoImpl implements SuscribeListAble {
    @Autowired
    @Qualifier("PostgresSuscribeListRepository")
    private SuscribeListAble suscribeListPersistence;

    @Override
    public SuscribeListDTO save(SuscribeListDTO suscribeListDTO) throws FingerprintApplicationException {
        return suscribeListPersistence.save(suscribeListDTO);
    }

    @Override
    public boolean deleteByListId(String listId) throws FingerprintApplicationException {
        return suscribeListPersistence.deleteByListId(listId);
    }

    @Override
    public List<SuscribeListDTO> listSuscribeLists(Predicate<SuscribeList> predicate) {
        return suscribeListPersistence.listSuscribeLists(predicate);
    }
}
