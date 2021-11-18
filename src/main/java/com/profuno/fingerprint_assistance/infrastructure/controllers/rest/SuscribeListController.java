package com.profuno.fingerprint_assistance.infrastructure.controllers.rest;

import com.profuno.fingerprint_assistance.application.SuscribeListRepoImpl;
import com.profuno.fingerprint_assistance.domain.dto.SuscribeListDTO;
import com.profuno.fingerprint_assistance.domain.dto.response.ResponseDTO;
import com.profuno.fingerprint_assistance.exception.FingerprintApplicationException;
import com.profuno.fingerprint_assistance.utils.resources.MessageResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="suscribe-list")
@Validated
@CrossOrigin("http://localhost:4200")
public class SuscribeListController {
    //region services
    @Autowired
    private MessageResource messageResource;
    //endregion

    //region repositories
    @Autowired
    private SuscribeListRepoImpl suscribeListRepoImpl;
    //endregion

    @PostMapping(path = "/save")
    public @ResponseBody
    ResponseEntity<ResponseDTO> save(@RequestBody @Valid SuscribeListDTO suscribeListDTO) throws FingerprintApplicationException {
        return new ResponseEntity<ResponseDTO>(
                new ResponseDTO(
                        messageResource.getDefaultMessage("response.succes"),
                        suscribeListRepoImpl.save(suscribeListDTO),
                        null
                ), HttpStatus.OK);
    }
}
