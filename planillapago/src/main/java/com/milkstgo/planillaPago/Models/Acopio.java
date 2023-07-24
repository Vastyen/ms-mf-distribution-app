package com.milkstgo.planillaPago.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Acopio{

    private LocalDate fecha;
    private String turno;
    private String proveedor;
    private Integer kgleche;
}
