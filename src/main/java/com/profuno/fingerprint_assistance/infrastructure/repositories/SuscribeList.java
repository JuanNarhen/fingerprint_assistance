package com.profuno.fingerprint_assistance.infrastructure.repositories;

import com.profuno.fingerprint_assistance.domain.dto.SuscribeListDTO;
import com.profuno.fingerprint_assistance.utils.services.RandomStringService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "suscribe_list", schema = "public", catalog = "profone_fingerprint")
public class SuscribeList {
    @Id
    @Column(name = "suscribe_list_id", nullable = false, length = 32)
    private String suscribeListId;
    @ManyToOne
    @JoinColumn(name = "assistant_fingerprint", referencedColumnName = "fingerprint_image", nullable = false)
    private Assistant assistant;
    @ManyToOne
    @JoinColumn(name = "list_id", referencedColumnName = "list_id", nullable = false)
    private List list;

    public SuscribeList(SuscribeListDTO suscribeListDTO){
        BeanUtils.copyProperties(suscribeListDTO, this);
        this.suscribeListId = RandomStringService.generateRandom(32);

        this.assistant = new Assistant();
        this.assistant.setFingerprintImage(suscribeListDTO.getAssistantFingerprint());
        this.list = new List();
        this.list.setListId(suscribeListDTO.getListId());
    }

    public SuscribeListDTO toSuscribeListDTO(){
        var suscribeListDTO = new SuscribeListDTO();
        BeanUtils.copyProperties(this, suscribeListDTO);
        suscribeListDTO.setListId(this.list.getListId());
        suscribeListDTO.setAssistantFingerprint(this.assistant.getFingerprintImage());
        return suscribeListDTO;
    }
}
