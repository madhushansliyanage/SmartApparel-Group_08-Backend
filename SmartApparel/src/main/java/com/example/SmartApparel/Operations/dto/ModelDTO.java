package com.example.SmartApparel.Operations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ModelDTO {
    // Properties of the CustomerDTO class
//    private int ModelId;
    private String ModelName;
    private Blob ModelImage;
    private String ClothMaterial;
    private int clothAmount;
    private int buttonAmount;
    private int zipperAmount;
    private int elasticAmount;
}
