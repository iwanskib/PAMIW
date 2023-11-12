package com.yourcompany.taskapi.repository;

import com.yourcompany.taskapi.model.NewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewEntityRepository extends JpaRepository<NewEntity, Long> {
}
