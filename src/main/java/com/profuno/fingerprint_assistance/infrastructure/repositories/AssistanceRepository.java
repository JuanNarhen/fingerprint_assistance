package com.profuno.fingerprint_assistance.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AssistanceRepository extends JpaRepository<Assistance, String> {
    long deleteAllByList_ListId(String listId);
}
