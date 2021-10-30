package com.profuno.fingerprint_assistance.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<List, String> {
    long deleteByListId(String listId);
}
