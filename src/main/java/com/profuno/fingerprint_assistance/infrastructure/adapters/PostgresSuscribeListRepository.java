package com.profuno.fingerprint_assistance.infrastructure.adapters;

import com.profuno.fingerprint_assistance.domain.contracts.data.SuscribeListAble;
import com.profuno.fingerprint_assistance.domain.dto.SuscribeListDTO;
import com.profuno.fingerprint_assistance.infrastructure.repositories.SuscribeList;
import com.profuno.fingerprint_assistance.infrastructure.repositories.SuscribeListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Qualifier("PostgresSuscribeListRepository")
@Repository("suscribeListPersistence")
public class PostgresSuscribeListRepository implements SuscribeListAble {
    @Autowired
    private SuscribeListRepository suscribeListRepository;

    @Override
    public SuscribeListDTO save(SuscribeListDTO suscribeListDTO) {
        return suscribeListRepository.save(new SuscribeList(suscribeListDTO)).toSuscribeListDTO();
    }

    @Override
    @Transactional
    public boolean deleteByListId(String listId) {
        return suscribeListRepository.deleteAllByList_ListId(listId) == 1;
    }

    @Override
    public List<SuscribeListDTO> listSuscribeLists(Predicate<SuscribeList> predicate) {
        return suscribeListRepository.findAll().stream().filter(predicate).map(slE -> {return slE.toSuscribeListDTO();}).collect(Collectors.toList());
    }
}
