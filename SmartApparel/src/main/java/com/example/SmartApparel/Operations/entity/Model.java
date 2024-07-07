package com.example.SmartApparel.Operations.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

// Annotation to specify the table name in the database
@Table(name = "model")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int ModelId;
    private String ModelName;
    private Blob ModelImage;
    private String ClothMaterial;
    private int clothAmount;
    private int buttonAmount;
    private int zipperAmount;
    private int elasticAmount;
}
