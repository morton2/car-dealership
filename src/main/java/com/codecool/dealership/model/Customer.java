package com.codecool.dealership.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String placeOfBirth;
    private String nationality;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Car> carList;
}
