package com.example.ecomon.persistence.entity.pollution;

import com.example.ecomon.persistence.entity.object.Object;
import com.example.ecomon.persistence.entity.pollutant.Pollutant;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "pollutions", uniqueConstraints = { @UniqueConstraint(columnNames = {"object_id", "pollutant_id", "year" }) })
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pollution {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pollutions_seq")
    @SequenceGenerator(name = "pollutions_seq", sequenceName = "pollutions_sequence", initialValue = 15, allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "object_id")
    private Object object;

    @ManyToOne
    @JoinColumn(name = "pollutant_id")
    private Pollutant pollutant;

    @Column(name = "year")
    private int year;

    @Column(name = "value_pollution")
    private double valuePollution;

    @Column(name = "pollution_concentration")
    private double pollutionConcentration;

    @Column(name = "cr")
    private double cr;

    @Column(name = "hq")
    private double hq;

    @Column(name = "fee")
    private double fee;

    @Column(name = "addLadd")
    private double addLadd;

}
