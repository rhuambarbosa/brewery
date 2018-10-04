package br.com.rbs.brewery.domain;

import javax.persistence.*;
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

    @Transient
    private int targetTemperature;

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

    public void setTargetTemperature(int targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    /*
        Retorna 0 se a temperatura informada estiver dentro do range de bebida ideal,
        caso negativo retorna a menor diferença de temperatura.
        Ex: -2 temperatura min e max -5 e 0 retorna 0
        EX: -2 temperatura min e max 0 e 3 retorna 2, pois para chegar em 0 precisa de 2
        EX: 4 temperatura min e max 0 e 3 retorna 1
     */
    public int getDifference() {
        return (targetTemperature >= temperatureMin && targetTemperature <= temperatureMax) ? 0 : lessDifference(targetTemperature);
    }

    private int lessDifference(int temperature) {
        int min = Math.abs(temperatureMin - temperature);
        int max = Math.abs(temperatureMax - temperature);
        return Math.min(min, max);
    }
}
