package com.profuno.fingerprint_assistance.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AssistantRepository extends JpaRepository<Assistant, byte[]> {
}
