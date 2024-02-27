package com.example.cardealsapp.util.impl;

import com.example.cardealsapp.util.XmlParser;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
@Component
public class XmlParserImpl implements XmlParser {
    @Override
    @SuppressWarnings("uncheked")
    public <T> T fromFile(String file, Class<T> object) throws JAXBException, FileNotFoundException {
        final JAXBContext context = JAXBContext.newInstance(object);
        final Unmarshaller unmarshaller = context.createUnmarshaller();

        final FileReader fileReader = new FileReader(file);

        return (T) unmarshaller.unmarshal(fileReader);
    }
}
