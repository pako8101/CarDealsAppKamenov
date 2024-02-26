package com.example.cardealsapp.service.impl;

import com.example.cardealsapp.service.OfferService;
import jakarta.xml.bind.JAXBException;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class OfferServiceImpl implements OfferService {
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return null;
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        return null;
    }
}
