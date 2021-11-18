package com.profuno.fingerprint_assistance.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SuscribeListRepository extends JpaRepository<SuscribeList, String> {
    long deleteAllByList_ListId(String listId);
    boolean existsByList_ListId(String listId);
    boolean existsByList_ListIdAndAndAssistant_FingerprintImage(String listId, byte[] assistantFingerprintImage);
}
