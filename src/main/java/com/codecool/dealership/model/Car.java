package com.codecool.dealership.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="CAR")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelType;
    private Long serialNumber;
    @ManyToOne
    private Configuration configuration;
    @JsonIgnore
    @OneToMany(mappedBy = "car",cascade = CascadeType.ALL)
    private List<Customer> customers;
}
