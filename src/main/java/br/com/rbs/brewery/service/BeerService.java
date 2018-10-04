package br.com.rbs.brewery.service;

import br.com.rbs.brewery.domain.Beer;
import br.com.rbs.brewery.dto.BestBeerDTO;
import br.com.rbs.brewery.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class BeerService {

    @Autowired
    private BeerRepository beerRepository;

    public BestBeerDTO retrieveBestBeer(final int temperature) {
        List<Beer> beers = beerRepository.findAll();
        beers.stream().forEach(beer -> {beer.setTargetTemperature(temperature);});

        Beer beer = beers.stream()
                .sorted(Comparator.comparing(Beer::getBeerStyle))
                .min(Comparator.comparing(Beer::getDifference))
                .get();

        return new BestBeerDTO(beer.getBeerStyle());
    }

}
