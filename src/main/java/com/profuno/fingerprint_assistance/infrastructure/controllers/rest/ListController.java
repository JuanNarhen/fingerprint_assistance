package com.profuno.fingerprint_assistance.infrastructure.controllers.rest;

import com.profuno.fingerprint_assistance.application.AssistanceRepoImpl;
import com.profuno.fingerprint_assistance.application.ListRepoImpl;
import com.profuno.fingerprint_assistance.application.SuscribeListRepoImpl;
import com.profuno.fingerprint_assistance.domain.dto.ListDTO;
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
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(path="list")
@Validated
@CrossOrigin("http://localhost:4200")
public class ListController {
    //region services
    @Autowired
    private LanguageResource languageResource;
    @Autowired
    private ListValidationService listValidationService;
    //endregion

    //region repositories
    @Autowired
    private ListRepoImpl listRepoImpl;
    @Autowired
    private AssistanceRepoImpl assistanceRepoImpl;
    @Autowired
    private SuscribeListRepoImpl suscribeListRepoImpl;
    //endregion

    @PostMapping(path = "/save")
    public @ResponseBody
    ResponseEntity<ResponseDTO> save(@RequestBody @Valid ListDTO listDTO) {
        return new ResponseEntity<ResponseDTO>(
                new ResponseDTO(
                        languageResource.getDefaultMessage("response.succes"),
                        listRepoImpl.save(listDTO),
                        null
                ), HttpStatus.OK);
    }

    @PostMapping(path = "/delete")
    public @ResponseBody
    ResponseEntity<ResponseDTO> delete(@RequestBody @Valid String listId) {
        assistanceRepoImpl.deleteByListId(listId);
        suscribeListRepoImpl.deleteByListId(listId);
        boolean deleteList = listRepoImpl.delete(listId);

        List<ErrorDTO> errors = null;
        if(!deleteList){
            errors = new LinkedList<ErrorDTO>();
            errors.add(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), languageResource.getDefaultMessage("response.invalid_delete")));
        }

        return new ResponseEntity<ResponseDTO>(
                new ResponseDTO(
                        languageResource.getDefaultMessage("response.succes"),
                        deleteList,
                        errors
                ), HttpStatus.OK);
    }

    @PostMapping(path = "/list-by-manager")
    public @ResponseBody
    ResponseEntity<ResponseDTO> listByManagerId(@RequestBody @Valid int managerId){
        List<ListDTO> lists = listRepoImpl.listAssistantsLists(
                (list) -> list.getManagerId() == managerId
        );

        List<ErrorDTO> errors = listValidationService.<ListDTO>validateList(lists);

        return new ResponseEntity<ResponseDTO>(
                new ResponseDTO(
                        languageResource.getDefaultMessage("response.succes"),
                        lists,
                        errors
                ), HttpStatus.OK);
    }

}
