package com.example.cardealsapp.config;

import com.example.cardealsapp.util.ValidationUtil;
import com.example.cardealsapp.util.impl.ValidationUtilImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Configuration
public class ApplicationBeanConfiguration {



    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting().create();
    }

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl() {
        };
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addConverter(new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {
                return LocalDate.parse(mappingContext.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyy"));
            }
        });

        modelMapper.addConverter(new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(MappingContext<String, LocalDateTime> mappingContext) {
                return LocalDateTime.parse(mappingContext.getSource(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }
        });


        return  modelMapper;
    }
//    @Bean
//    public XmlParser xmlParser(){
//        return new XmlParserImpl()
//    }

}
