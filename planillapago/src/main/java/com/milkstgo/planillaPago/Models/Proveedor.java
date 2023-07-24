package com.milkstgo.planillaPago.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor{

    private String codigo;
    private String nombre;
    private String categoria;
    private String afecto;
}
