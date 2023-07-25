package com.milkstgo.grasas.Controllers;

import com.milkstgo.grasas.Entities.GrasasEntity;
import com.milkstgo.grasas.Services.GrasasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@RequestMapping("/grasas")
public class GrasasController {

    @Autowired
    private GrasasService grasasService;

    @GetMapping
    public ResponseEntity<List<GrasasEntity>> traerGrasas() {
        List<GrasasEntity> grasas = grasasService.verGrasas();
        if(grasas.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(grasas);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<GrasasEntity> traerGrasaPorProveedor(@PathVariable("codigo") String codigo) {
        GrasasEntity grasas = grasasService.buscarPorProveedor(codigo);
        if(grasas == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(grasas);
    }

    @PostMapping
    public ResponseEntity<GrasasEntity> upload(@RequestParam("file") MultipartFile file) {

        GrasasEntity grasas;
        grasasService.guardar(file);
        grasas = grasasService.leerArchivo("Grasas.csv");
        return ResponseEntity.ok(grasas);
    }
}
