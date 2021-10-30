package com.profuno.fingerprint_assistance.infrastructure.controllers.rest;

import com.profuno.fingerprint_assistance.application.AssistantRepoImpl;
import com.profuno.fingerprint_assistance.application.SuscribeListRepoImpl;
import com.profuno.fingerprint_assistance.domain.dto.AssistantDTO;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="assistant")
@Validated
@CrossOrigin("http://localhost:4200")
public class AssistantController {

    //region services
    @Autowired
    private LanguageResource languageResource;
    @Autowired
    private ListValidationService listValidationService;
    //endregion

    //region repositories
    @Autowired
    private AssistantRepoImpl assistantRepoImpl;
    @Autowired
    private SuscribeListRepoImpl suscribeListRepositoryImpl;
    //endregion

    @PostMapping(path = "/save")
    public @ResponseBody
    ResponseEntity<ResponseDTO> save(@RequestBody @Valid AssistantDTO assistantDTO) {
        return new ResponseEntity<ResponseDTO>(
                new ResponseDTO(
                        languageResource.getDefaultMessage("response.succes"),
                        assistantRepoImpl.save(assistantDTO),
                        null
                ), HttpStatus.OK);
    }

    @PostMapping(path = "/list-by-list")
    public @ResponseBody
    ResponseEntity<ResponseDTO> listAllByList(@RequestBody @Valid String listId) {
        List<AssistantDTO> suscribedAssistants = suscribeListRepositoryImpl.listSuscribeLists(
                (suscribeList) -> suscribeList.getList().getListId().equals(listId)
        ).stream().map(
                suscribeList -> { return assistantRepoImpl.listAssistants((assistant) -> Arrays.equals(suscribeList.getAssistantFingerprint(),assistant.getFingerprintImage())).get(0); }
        ).collect(Collectors.toList());

        List<ErrorDTO> errorsList = listValidationService.<AssistantDTO>validateList(suscribedAssistants);

        var response = new ResponseEntity<ResponseDTO>(
                new ResponseDTO(
                        languageResource.getDefaultMessage("response.succes"),
                        suscribedAssistants,
                        errorsList
                ), HttpStatus.OK);

        return response;
    }
}
