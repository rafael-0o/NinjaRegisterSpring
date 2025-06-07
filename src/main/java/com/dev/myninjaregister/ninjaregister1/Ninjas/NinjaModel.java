package com.dev.myninjaregister.ninjaregister1.Ninjas;

import com.dev.myninjaregister.ninjaregister1.Missions.MissionsModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name="tb_ninja_register")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class NinjaModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    //só pode ter um com cada nome, não pode ser igual
    @Column(unique = true)
    private String email;
    private int age;
    private String rank;
    @ManyToOne

    @JoinColumn(name = "missoes_id")
    private MissionsModel mission;

}
