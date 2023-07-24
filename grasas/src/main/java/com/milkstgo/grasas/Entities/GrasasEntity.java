package com.milkstgo.grasas.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Grasas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GrasasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer ID;
    private String proveedor;
    private Integer grasas;
    private Integer solidoTotal;
}
