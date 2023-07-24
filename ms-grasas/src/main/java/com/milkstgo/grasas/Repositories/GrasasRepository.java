package com.milkstgo.grasas.Repositories;


import com.milkstgo.grasas.Entities.GrasasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface GrasasRepository extends JpaRepository<GrasasEntity, String>{

    @Query(value = "SELECT vg FROM GrasasEntity vg WHERE vg.proveedor = :proveedor")
    GrasasEntity findByProveedor(@Param("proveedor") String proveedor);

    @Query(value = "SELECT a FROM GrasasEntity a")
    ArrayList<GrasasEntity> findAll();

    @Modifying
    @Query(value = "DELETE FROM GrasasEntity")
    void deleteAll();

}
