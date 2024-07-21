package com.example.SmartApparel.Operations.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Map;

@Entity
@Data
public class ClothItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String clothType;
    private int smallSize;
    private int mediumSize;
    private int largeSize;
    private int xtraLargeSize;

    @ElementCollection
    private Map<String, String> images;
}
