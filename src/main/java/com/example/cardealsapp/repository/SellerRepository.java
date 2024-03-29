package com.example.cardealsapp.repository;


import com.example.cardealsapp.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long> {
    Optional<Seller> findFirstByEmail(String email);
    Optional<Seller>findFirstByFirstName(String firstName);
}
