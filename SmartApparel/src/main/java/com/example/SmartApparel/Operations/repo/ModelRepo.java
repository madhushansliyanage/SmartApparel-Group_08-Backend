package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModelRepo extends JpaRepository <Model,String>{
    Optional<Model> findById(String ModelName);
}