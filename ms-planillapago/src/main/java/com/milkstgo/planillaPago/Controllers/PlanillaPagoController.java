package com.milkstgo.planillaPago.Controllers;

import com.milkstgo.planillaPago.Entities.PlanillaPagoEntity;
import com.milkstgo.planillaPago.Services.PlanillaPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/planillaPago")
public class PlanillaPagoController {

    @Autowired
    PlanillaPagoService planillaPagoService;

    @GetMapping
    public ResponseEntity<List<PlanillaPagoEntity>> traerProveedores() {
        List<PlanillaPagoEntity> planillas = planillaPagoService.traerPlanilla();
        if(planillas.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(planillas);
    }

    @PostMapping()
    public ResponseEntity<List<PlanillaPagoEntity>> save() {
        List<PlanillaPagoEntity> planillas = planillaPagoService.crearPlanillaPago();
        if(planillas.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(planillas);
    }
}
