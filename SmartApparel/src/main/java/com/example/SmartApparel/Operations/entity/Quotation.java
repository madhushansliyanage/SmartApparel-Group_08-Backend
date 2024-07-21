package com.example.SmartApparel.Operations.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "quotation_2")
public class Quotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Embedded
    private UserInfo userInfo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClothItem> clothItems;
}