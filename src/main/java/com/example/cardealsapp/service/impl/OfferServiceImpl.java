package com.example.cardealsapp.service.impl;

import com.example.cardealsapp.entities.Offer;
import com.example.cardealsapp.entities.dtos.OfferRootDto;
import com.example.cardealsapp.repository.OfferRepository;
import com.example.cardealsapp.service.CarService;
import com.example.cardealsapp.service.OfferService;
import com.example.cardealsapp.service.SellerService;
import com.example.cardealsapp.util.ValidationUtil;
import com.example.cardealsapp.util.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class OfferServiceImpl implements OfferService {
    private static String OFFERS_FILE_PATH = "C:\\Users\\Plamen Kamenov\\Desktop\\MyPrograming\\exercises\\SpringData\\MyDealAppSpringData\\CarDealsApp\\src\\main\\resources\\files\\xml\\offers.xml";
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final CarService carService;
    private final SellerService sellerService;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper,
                            ValidationUtil validationUtil,
                            XmlParser xmlParser, CarService carService,
                            SellerService sellerService) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.carService = carService;
        this.sellerService = sellerService;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(Path.of(OFFERS_FILE_PATH));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {


        StringBuilder sb = new StringBuilder();

        xmlParser.fromFile(OFFERS_FILE_PATH, OfferRootDto.class)
                .getOffers()
                .stream()
                .filter(offerSeedDto -> {
                    boolean isValid = validationUtil.isValid(offerSeedDto);
                    sb.append(isValid ? String.format("Successfully import offer %s - %s ",
                                    offerSeedDto.getAddedOn(), offerSeedDto.isHasGoldStatus())
                                    : "Invalid offer")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(offerSeedDto -> {
                    Offer offer = modelMapper.map(offerSeedDto, Offer.class);
                    offer.setSeller(sellerService.finById(offerSeedDto.getSeller().getId()));
                    offer.setCar(carService.findById(offerSeedDto.getCar().getId()));
                    return offer;
                })
                .forEach(offerRepository::save);


        return sb.toString();
    }
}
