package br.com.rbs.brewery.dto;

public class BestBeerDTO {

    private String beerStyle;

    public BestBeerDTO() {
    }

    public BestBeerDTO(String beerStyle) {
        this.beerStyle = beerStyle;
    }

    public String getBeerStyle() {
        return beerStyle;
    }

    public void setBeerStyle(String beerStyle) {
        this.beerStyle = beerStyle;
    }
}
