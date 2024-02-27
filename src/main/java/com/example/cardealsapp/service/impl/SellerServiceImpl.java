package com.example.cardealsapp.service.impl;

import com.example.cardealsapp.entities.Seller;
import com.example.cardealsapp.entities.dtos.SellerRootDto;
import com.example.cardealsapp.entities.dtos.SellerSeedDto;
import com.example.cardealsapp.repository.SellerRepository;
import com.example.cardealsapp.service.SellerService;
import com.example.cardealsapp.util.ValidationUtil;
import com.example.cardealsapp.util.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private static String SELLERES_FILE_PATH = "C:\\Users\\Plamen Kamenov\\Desktop\\MyPrograming\\exercises\\SpringData\\MyDealAppSpringData\\CarDealsApp\\src\\main\\resources\\files\\xml\\sellers.xml";
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    public SellerServiceImpl(SellerRepository sellerRepository, ValidationUtil validationUtil, ModelMapper modelMapper, XmlParser xmlParser) {
        this.sellerRepository = sellerRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files.readString(Path.of(SELLERES_FILE_PATH));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        final StringBuilder sb = new StringBuilder();

        xmlParser.fromFile(SELLERES_FILE_PATH, SellerRootDto.class)
                .getSellers()
                .stream()
                .filter(sellerSeedDto -> {
                    boolean isValid = validationUtil.isValid(sellerSeedDto);
                    sb.append(isValid ? String.format("Successfully import seller %s - %s"
                                    , sellerSeedDto.getFirstName(), sellerSeedDto.getEmail())
                                    : "Invalid seller")
                            .append(System.lineSeparator());
                    return isValid;

                })
                .map(sellerSeedDto -> modelMapper.map(sellerSeedDto, Seller.class))
                .forEach(sellerRepository::save);

//       final List<SellerSeedDto> sellers =
//               Arrays.stream(xmlParser
//                       .fromFile(SELLERES_FILE_PATH, SellerSeedDto[].class)).toList();
//
//       for (SellerSeedDto seller : sellers){
//           sb.append(System.lineSeparator());
//
//           if (this.sellerRepository.findFirstByEmail(seller.getEmail()).isPresent()
//           || this.sellerRepository.findFirstByFirstName(seller.getFirstName()).isPresent()
//           ||!validationUtil.isValid(seller)){
//               sb.append("Invalid seller");
//               continue;
//           }
//
//           this.sellerRepository.save(this.modelMapper.map(seller, Seller.class));
//       sb.append(String.format("Successfully import seller %s - %s"
//       ,seller.getFirstName(),seller.getEmail()));
//
//       }


        return sb.toString();
    }
}
