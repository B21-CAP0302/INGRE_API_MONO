package com.pascal7.ingre_api_mono.repository;

import com.pascal7.ingre_api_mono.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageEntityRepository extends JpaRepository<ImageEntity, String>, JpaSpecificationExecutor<ImageEntity> {
    Optional<ImageEntity> findBySourceId(String id);
}
