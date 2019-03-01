package com.bootcamp.shoppingcart.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// esto indica que esta clase es una representacion de tabla SQL
@Entity
public class TestEntity {

    //el campo que esta anotado es una key value (campo identificador de tabla)
    @Id
    //eso especifica la estrategia para generar automaticamente la ID cuando estas por guardar en repositorio
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //generates the value for the column of database table. In case of GenerationType. IDENTITY , value is set by table itself that should be unique. It is used as
    private Integer id;
    private String txt;

}
