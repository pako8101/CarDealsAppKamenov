package com.example.cardealsapp.repository;

import com.example.cardealsapp.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {
    
}
