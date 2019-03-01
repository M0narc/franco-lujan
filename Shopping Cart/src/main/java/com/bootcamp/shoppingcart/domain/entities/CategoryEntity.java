package com.bootcamp.shoppingcart.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class CategoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false, unique = true, updatable = false)
    @NotNull
    private String name;

    private String description;

    @ManyToMany (mappedBy = "categoryEntities",fetch = FetchType.EAGER)
    @JsonBackReference
    @NotNull
    private List<ItemEntity> itemEntityList = new ArrayList<>();
}
