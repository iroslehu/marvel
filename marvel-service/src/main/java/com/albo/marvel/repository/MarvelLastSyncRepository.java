package com.albo.marvel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface MarvelLastSyncRepository extends JpaRepository<Date, Integer> {
    Date findTopByOrderByIdDesc();
}
