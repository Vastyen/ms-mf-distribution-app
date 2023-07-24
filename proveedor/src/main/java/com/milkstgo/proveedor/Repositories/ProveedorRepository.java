package com.milkstgo.proveedor.Repositories;

import com.milkstgo.proveedor.Entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, String>{

    @Query(value = "SELECT e.codigo FROM ProveedorEntity e")
    ArrayList<String> findAllCodigo();

    @Query(value = "SELECT e FROM ProveedorEntity e WHERE e.codigo = :codigo")
    ProveedorEntity findByCodigo(@Param("codigo") String codigo);
}
