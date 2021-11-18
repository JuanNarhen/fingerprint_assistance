package com.profuno.fingerprint_assistance.utils.services;

import com.profuno.fingerprint_assistance.domain.dto.response.ErrorDTO;
import com.profuno.fingerprint_assistance.utils.resources.MessageResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ListValidationService {
    @Autowired
    private MessageResource messageResource;

    public <T> List<ErrorDTO> validateList(List<T> referenceList){
        var errors = new LinkedList<ErrorDTO>();

        if(this.<T>isNullOrEmpty(referenceList)){
            errors.add(new ErrorDTO(HttpStatus.NOT_FOUND.value(), messageResource.getDefaultMessage("response.not_found")));
        }

        if(errors.isEmpty()){
            return null;
        }

        return errors;
    }

    private <T> boolean isNullOrEmpty(List<T> referenceList){
        return referenceList == null || referenceList.isEmpty();
    }
}
