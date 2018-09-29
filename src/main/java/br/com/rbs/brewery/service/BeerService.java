package br.com.rbs.brewery.service;

import br.com.rbs.brewery.domain.Beer;
import br.com.rbs.brewery.exception.DuplicatedException;
import br.com.rbs.brewery.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeerService {

    @Autowired
    private BeerRepository beerRepository;

    public Beer save(final Beer beer) throws DuplicatedException {

        Beer duplicated = beerRepository.findByBeerStyleContainingIgnoreCase(beer.getBeerStyle());

        if (duplicated == null) {
            throw new DuplicatedException("Cerveja já cadastrada");
        }

        return beerRepository.save(beer);
    }

}
