package br.com.rbs.brewery.service;

import br.com.rbs.brewery.dto.BestBeerDTO;
import br.com.rbs.brewery.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeerService {

    @Autowired
    private BeerRepository beerRepository;

    public BestBeerDTO retrieveBestBeer(final int temperature) {
        return new BestBeerDTO();
    }

}
