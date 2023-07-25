package com.milkstgo.acopio.Controllers;

import com.milkstgo.acopio.Entities.AcopioEntity;
import com.milkstgo.acopio.Services.AcopioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@RequestMapping("/acopios")
public class AcopioController {

    @Autowired
    private AcopioService acopioService;

    @GetMapping
    public ResponseEntity<List<AcopioEntity>> traerAcopios() {
        List<AcopioEntity> acopios = acopioService.verDatos();
        if(acopios.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(acopios);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<List<AcopioEntity>> traerAcopiosPorProveedor(@PathVariable("codigo") String codigo) {
        List<AcopioEntity> acopios = acopioService.findByProveedor(codigo);
        if(acopios.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(acopios);
    }

    @PostMapping
    public ResponseEntity<AcopioEntity> upload(@RequestParam("file") MultipartFile file) {

        AcopioEntity acopio;
        acopioService.guardar(file);
        acopio = acopioService.leerArchivo("Acopio.csv");
        return ResponseEntity.ok(acopio);
    }
}
