package com.bootcamp.shoppingcart.domain.entities;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (unique = true, length = 30)
    @NotNull
    private String userName;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    @Email
    private String email;

    @NotNull
    private String pass;

    private String phone;
    /*Time they joined
      and time they were last seen
     */
    private Calendar dateJoined;
    private Calendar LastSeen;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    @NotNull
    private List<ItemEntity> cart = new ArrayList<>();
}
