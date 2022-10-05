package com.cruzpet.repositorys;

import com.cruzpet.entitys.GeneroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("generosRepository")
public interface GenerosRepository extends JpaRepository<GeneroEntity, Integer> {

    GeneroEntity findByIdGenero(Integer idGenero);

    GeneroEntity findByGenero(String genero);

}
