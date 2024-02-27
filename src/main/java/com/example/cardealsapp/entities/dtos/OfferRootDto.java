package com.example.cardealsapp.entities.dtos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "offers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferRootDto {
    @XmlElement(name = "offer")
    private List<OfferSeedDto>offers;

    public OfferRootDto() {
    }

    public List<OfferSeedDto> getOffers() {
        return offers;
    }

    public void setOffers(List<OfferSeedDto> offers) {
        this.offers = offers;
    }
}
