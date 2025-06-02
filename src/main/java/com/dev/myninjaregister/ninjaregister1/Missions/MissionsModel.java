package com.dev.myninjaregister.ninjaregister1.Missions;

import com.dev.myninjaregister.ninjaregister1.Ninjas.NinjaModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name="tb_missions")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MissionsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String difficulty;
    @OneToMany(mappedBy = "mission")
    @JsonIgnore
    private List<NinjaModel> ninjas;

}
