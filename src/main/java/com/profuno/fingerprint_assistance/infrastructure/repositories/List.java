package com.profuno.fingerprint_assistance.infrastructure.repositories;

import com.profuno.fingerprint_assistance.domain.dto.ListDTO;
import com.profuno.fingerprint_assistance.utils.services.RandomStringService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "list", schema = "public", catalog = "profone_fingerprint")
public class List {
    @Id
    @Column(name = "list_id", nullable = false, length = 32)
    private String listId;
    @Basic
    @Column(name = "created_date", nullable = true)
    private LocalDate createdDate;
    @Basic
    @Column(name = "last_update_date", nullable = true)
    private LocalDate lastUpdateDate;
    @Basic
    @Column(name = "name", nullable = true, length = 40)
    private String name;
    @Basic
    @Column(name = "manager_id", nullable = true)
    private Integer managerId;

    public List(ListDTO listDTO){
        BeanUtils.copyProperties(listDTO, this);
        if(listDTO.getListId() == null || listDTO.getListId().length() == 0){
            this.listId = RandomStringService.generateRandom(32);
        }
    }

    public ListDTO toListDTO(){
        var listDTO = new ListDTO();
        BeanUtils.copyProperties(this, listDTO);
        return listDTO;
    }
}
