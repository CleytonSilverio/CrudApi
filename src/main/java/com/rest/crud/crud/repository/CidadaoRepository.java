package com.rest.crud.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.crud.crud.entity.Cidadao;

@Repository
public interface CidadaoRepository extends JpaRepository<Cidadao, Integer>{

}
