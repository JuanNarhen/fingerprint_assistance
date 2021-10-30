package com.profuno.fingerprint_assistance.infrastructure.repositories;

import com.profuno.fingerprint_assistance.domain.dto.AssistantDTO;
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
@Table(name = "assistant", schema = "public", catalog = "profone_fingerprint")
public class Assistant {
    @Basic
    @Column(name = "email", nullable = false, length = 80)
    private String email;
    @Basic
    @Column(name = "name", nullable = true, length = 70)
    private String name;
    @Basic
    @Column(name = "last_update_date", nullable = true)
    private LocalDate lastUpdateDate;
    @Id
    @Column(name = "fingerprint_image", nullable = false)
    private byte[] fingerprintImage;

    public Assistant(AssistantDTO assistantDTO){
        BeanUtils.copyProperties(assistantDTO, this);
    }

    public AssistantDTO toAssistantDTO(){
        var assistantDTO = new AssistantDTO();
        BeanUtils.copyProperties(this, assistantDTO);
        return assistantDTO;
    }
}
