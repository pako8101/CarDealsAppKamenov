package com.example.cardealsapp.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Component;


import java.io.FileNotFoundException;


@Component
public interface XmlParser {
     <T> T fromFile(String file, Class<T> object) throws JAXBException, FileNotFoundException;

    }

