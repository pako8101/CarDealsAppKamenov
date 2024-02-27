package com.example.cardealsapp.service;


import com.example.cardealsapp.entities.Seller;
import com.example.cardealsapp.entities.dtos.SellerIdDto;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;

//ToDo - Before start App implement this Service and set areImported to return false
public interface SellerService {
    
    boolean areImported();

    String readSellersFromFile() throws IOException;

    String importSellers() throws IOException, JAXBException;

    Seller finById(Long id);
}
