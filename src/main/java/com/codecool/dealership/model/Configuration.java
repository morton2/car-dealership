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
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long trim;
    private Long engine;
    private Long interior;
    private Long exterior;
    private Long equipment;
    @JsonIgnore
    @OneToMany(mappedBy = "configuration")
    private List<Car> cars;
}
