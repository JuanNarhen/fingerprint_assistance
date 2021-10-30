package com.profuno.fingerprint_assistance.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SuscribeListRepository extends JpaRepository<SuscribeList, String> {
    long deleteAllByList_ListId(String listId);
}
