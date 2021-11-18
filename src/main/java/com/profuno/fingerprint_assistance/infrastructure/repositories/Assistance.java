package com.profuno.fingerprint_assistance.infrastructure.repositories;

import com.profuno.fingerprint_assistance.domain.dto.AssistanceDTO;
import com.profuno.fingerprint_assistance.utils.services.RandomStringService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assistance", schema = "public", catalog = "profone_fingerprint")
public class Assistance {
    @Id
    @Column(name = "assistance_id", nullable = false, length = 32)
    private String assistanceId;
    @Basic
    @Column(name = "presence_date", nullable = true)
    private LocalDateTime presenceDate;
    @ManyToOne
    @JoinColumn(name = "list_id", referencedColumnName = "list_id", nullable = false)
    private List list;
    @ManyToOne
    @JoinColumn(name = "assistant_fingerprint", referencedColumnName = "fingerprint_image", nullable = false)
    private Assistant assistant;

    public Assistance(AssistanceDTO assistanceDTO){
        BeanUtils.copyProperties(assistanceDTO, this);
        this.assistanceId = RandomStringService.generateRandom(32);

        this.list = new List();
        this.list.setListId(assistanceDTO.getListId());
        this.assistant = new Assistant();
        this.assistant.setFingerprintImage(assistanceDTO.getAssistantFingerprint());
    }

    public AssistanceDTO toAssistanceDTO(){
        var assistanceDTO = new AssistanceDTO();
        BeanUtils.copyProperties(this, assistanceDTO);
        assistanceDTO.setListId(this.list.getListId());
        assistanceDTO.setAssistantFingerprint(this.assistant.getFingerprintImage());
        return assistanceDTO;
    }
}
