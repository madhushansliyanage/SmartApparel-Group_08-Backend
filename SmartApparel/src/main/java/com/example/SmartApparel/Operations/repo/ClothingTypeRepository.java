package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.ClothingType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothingTypeRepository extends JpaRepository<ClothingType, Long> {
}