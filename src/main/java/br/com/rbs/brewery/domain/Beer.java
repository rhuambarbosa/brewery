package br.com.rbs.brewery.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Beer {

    public Beer() {
        super();
    }

    public Beer(String beerStyle, Integer temperatureMin, Integer temperatureMax) {
        this.beerStyle = beerStyle;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @NotNull(message = "Estilo de cerveja é obrigatório")
    @Column(name = "BEER_STYLE", unique = true)
    private String beerStyle;

    @NotNull(message = "Temperatura mínima é obrigatório")
    @Column(name = "TEMP_MIN")
    private Integer temperatureMin;

    @NotNull(message = "Temperatura máxima é obrigatório")
    @Column(name = "TEMP_MAX")
    private Integer temperatureMax;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBeerStyle() {
        return beerStyle;
    }

    public void setBeerStyle(String beerStyle) {
        this.beerStyle = beerStyle;
    }

    public Integer getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Integer temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public Integer getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Integer temperatureMax) {
        this.temperatureMax = temperatureMax;
    }
}
