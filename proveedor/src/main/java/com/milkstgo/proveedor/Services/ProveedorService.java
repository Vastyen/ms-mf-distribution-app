package com.milkstgo.proveedor.Services;

import com.milkstgo.proveedor.Entities.ProveedorEntity;
import com.milkstgo.proveedor.Repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class ProveedorService {

    @Autowired
    ProveedorRepository proveedorRepository;

    public ProveedorEntity crearProveedor(ProveedorEntity proveedor){
        return proveedorRepository.save(proveedor);
    }

    public ArrayList<ProveedorEntity> traerProveedores(){
        return (ArrayList<ProveedorEntity>) proveedorRepository.findAll();
    }

    public ArrayList<String> obtenerCodProveedores(){
        return proveedorRepository.findAllCodigo();
    }

    public ProveedorEntity findByCode(String codigo) {return proveedorRepository.findByCodigo(codigo); }

    public void borrarProveedores(){proveedorRepository.deleteAll();}
}