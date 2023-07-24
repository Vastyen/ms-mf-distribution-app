package com.milkstgo.acopio.Repositories;

import com.milkstgo.acopio.Entities.AcopioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AcopioRepository extends JpaRepository<AcopioEntity, String> {

    @Query(value = "SELECT a FROM AcopioEntity a WHERE a.proveedor = :proveedor")
    ArrayList<AcopioEntity> findByProveedor(@Param("proveedor") String proveedor);

    @Query(value = "SELECT a FROM AcopioEntity a")
    ArrayList<AcopioEntity> findAll();

    @Modifying
    @Query(value = "DELETE FROM AcopioEntity")
    void deleteAll();
}
