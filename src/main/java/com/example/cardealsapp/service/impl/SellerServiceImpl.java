package com.example.cardealsapp.service.impl;

import com.example.cardealsapp.service.SellerService;
import jakarta.xml.bind.JAXBException;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class SellerServiceImpl implements SellerService {
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return null;
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        return null;
    }
}
