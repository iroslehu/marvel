package com.albo.marvel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MarvelCharactersRepository extends JpaRepository<String, Integer> {
    String findByName(@Param("name") String name);

}
