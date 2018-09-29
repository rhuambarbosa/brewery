package br.com.rbs.brewery.domain;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Beer {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @NotNull(message = "Estilo de cerveja é obrigatório")
    @Column(name = "BEER_STYLE")
    private String beerStyle;

    @NotNull(message = "Temperatura mínima é obrigatório")
    @Column(name = "TEMP_MIN")
    private Integer temperatureMin;

    @NotNull(message = "Estilo máxima é obrigatório")
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
