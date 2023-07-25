package com.milkstgo.proveedor.Controllers;


import com.milkstgo.proveedor.Entities.ProveedorEntity;
import com.milkstgo.proveedor.Services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<ProveedorEntity>> traerProveedores() {
        List<ProveedorEntity> proveedores = proveedorService.traerProveedores();
        if(proveedores.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorEntity> traerProveedor(@PathVariable("id") String id) {
        ProveedorEntity proveedor = proveedorService.findByCode(id);
        if(proveedor == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(proveedor);
    }

    @GetMapping("/codigos")
    public ResponseEntity<List<String>> traercodigos() {
        List<String> codigos = proveedorService.obtenerCodProveedores();
        if(codigos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(codigos);
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> options() {
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<ProveedorEntity> save(@RequestBody ProveedorEntity proveedor) {
        ProveedorEntity nuevoProveedor = proveedorService.crearProveedor(proveedor);
        return ResponseEntity.ok(nuevoProveedor);
    }

}
