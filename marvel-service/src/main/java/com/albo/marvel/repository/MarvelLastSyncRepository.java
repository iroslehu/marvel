package com.albo.marvel.repository;

import com.albo.marvel.model.entity.LastSync;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarvelLastSyncRepository extends JpaRepository<LastSync, Integer>{
    LastSync findTopByOrderByIdDesc();
}
