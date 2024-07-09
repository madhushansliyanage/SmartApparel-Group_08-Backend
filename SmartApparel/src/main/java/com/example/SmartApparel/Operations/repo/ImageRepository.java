package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}