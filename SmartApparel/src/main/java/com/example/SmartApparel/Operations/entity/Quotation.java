package com.example.SmartApparel.Operations.entity;
//import javax.persistence.*;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity//class mapped to database
@AllArgsConstructor //generate a constructor with all fields as parameters
@NoArgsConstructor//generate a no-arguments constructor
@Data//generate getters, setters, equals, hashCode, and toString methods
@Table(name = "Quotation")

public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String contactNumber;


    @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL)
    private List<ClothingType> clothingTypes;

    @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL)
    private List<Quantity> quantities;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
}


