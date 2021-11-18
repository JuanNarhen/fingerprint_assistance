package com.profuno.fingerprint_assistance.infrastructure.adapters;

import com.profuno.fingerprint_assistance.domain.contracts.data.SuscribeListAble;
import com.profuno.fingerprint_assistance.domain.dto.SuscribeListDTO;
import com.profuno.fingerprint_assistance.exception.FingerprintApplicationException;
import com.profuno.fingerprint_assistance.infrastructure.repositories.SuscribeList;
import com.profuno.fingerprint_assistance.infrastructure.repositories.SuscribeListRepository;
import com.profuno.fingerprint_assistance.utils.resources.MessageResource;
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

    @Autowired
    private MessageResource messageResource;

    @Override
    public SuscribeListDTO save(SuscribeListDTO suscribeListDTO) throws FingerprintApplicationException {
        if(!suscribeListRepository.existsByList_ListIdAndAndAssistant_FingerprintImage(
                suscribeListDTO.getListId(), suscribeListDTO.getAssistantFingerprint()
        )){
            return suscribeListRepository.save(new SuscribeList(suscribeListDTO)).toSuscribeListDTO();
        }else{
            throw new FingerprintApplicationException(
                    messageResource.getDefaultMessage("error.assistant_subscribed",suscribeListDTO.getListId())
            );
        }
    }

    @Override
    @Transactional
    public boolean deleteByListId(String listId) throws FingerprintApplicationException {
        if(!suscribeListRepository.existsByList_ListId(listId)){
            return false;
        }
        if(listId != null && !listId.isBlank()){
            return suscribeListRepository.deleteAllByList_ListId(listId) == 1;
        }else{
            throw new FingerprintApplicationException(
                    messageResource.getDefaultMessage("error.id_not_found",listId, "SuscribeList")
            );
        }
    }

    @Override
    public List<SuscribeListDTO> listSuscribeLists(Predicate<SuscribeList> predicate) {
        return suscribeListRepository.findAll().stream().filter(predicate).map(slE -> {return slE.toSuscribeListDTO();}).collect(Collectors.toList());
    }
}
