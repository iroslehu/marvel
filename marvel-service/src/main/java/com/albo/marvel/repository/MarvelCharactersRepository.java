package com.albo.marvel.repository;

import com.albo.marvel.model.entity.Characters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarvelCharactersRepository extends JpaRepository<Characters, Integer>{
    List<Characters> findByName(@Param("name") String name);
    Characters findTopByUid(@Param("uid") Integer uid);
}
