package com.example.ecomon.persistence.entity.object;

import com.example.ecomon.persistence.entity.pollution.Pollution;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "objects")
@NoArgsConstructor
@Getter
@Setter
public class Object {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name",unique = true)
    @NotBlank
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "object", cascade = {CascadeType.REMOVE})
    private List<Pollution> pollutions;

    public Object(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Object(String name) {
        this.name = name;
    }
}
