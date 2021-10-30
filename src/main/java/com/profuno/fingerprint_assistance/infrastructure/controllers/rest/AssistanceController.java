package com.profuno.fingerprint_assistance.infrastructure.controllers.rest;

import com.profuno.fingerprint_assistance.application.AssistanceRepoImpl;
import com.profuno.fingerprint_assistance.domain.dto.AssistanceDTO;
import com.profuno.fingerprint_assistance.domain.dto.response.ErrorDTO;
import com.profuno.fingerprint_assistance.domain.dto.response.ResponseDTO;
import com.profuno.fingerprint_assistance.utils.resources.LanguageResource;
import com.profuno.fingerprint_assistance.utils.services.ListValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path="assistance")
@Validated
@CrossOrigin("http://localhost:4200")
public class AssistanceController {

    //region services
    @Autowired
    private LanguageResource languageResource;
    @Autowired
    private ListValidationService listValidationService;
    //endregion

    //region repositories
    @Autowired
    private AssistanceRepoImpl assistanceRepoImpl;
    //endregion

    @PostMapping(path = "/save")
    public @ResponseBody
    ResponseEntity<ResponseDTO> save(@RequestBody @Valid AssistanceDTO assistanceDTO) {
        return new ResponseEntity<ResponseDTO>(
                new ResponseDTO(
                        languageResource.getDefaultMessage("response.succes"),
                        assistanceRepoImpl.save(assistanceDTO),
                        null
                ), HttpStatus.OK);
    }

    @PostMapping(path = "/list-by-list")
    public @ResponseBody
    ResponseEntity<ResponseDTO> listByList(@RequestBody @Valid String listId) {
        List<AssistanceDTO> listAssistances = assistanceRepoImpl.listAssistances(
                (assistance) -> assistance.getList().getListId().equals(listId)
        );

        List<ErrorDTO> errorsList = listValidationService.<AssistanceDTO>validateList(listAssistances);

        var response = new ResponseEntity<ResponseDTO>(
                new ResponseDTO(
                        languageResource.getDefaultMessage("response.succes"),
                        listAssistances,
                        errorsList
                ), HttpStatus.OK);

        return response;
    }

    @PostMapping("/list-by-list-and-assistant")
    public @ResponseBody
    ResponseEntity<ResponseDTO> listByListAndAssistant(@RequestBody @Valid AssistanceDTO assistanceDTO) {
        List<AssistanceDTO> listAssistances = assistanceRepoImpl.listAssistances(
                (assistance) -> assistance.getList().getListId().equals(assistanceDTO.getListId()) && Arrays.equals(assistance.getAssistant().getFingerprintImage(),assistanceDTO.getAssistantFingerprint())
        );

        List<ErrorDTO> errorsList = listValidationService.<AssistanceDTO>validateList(listAssistances);

        var response = new ResponseEntity<ResponseDTO>(
                new ResponseDTO(
                        languageResource.getDefaultMessage("response.succes"),
                        listAssistances,
                        errorsList
                ), HttpStatus.OK);

        return response;
    }


}
