package com.mj147.repository;

import com.mj147.domain.CardTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTableRepository extends CrudRepository<CardTable, Long> {

    boolean existsByName(String name);

}