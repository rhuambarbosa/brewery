package br.com.rbs.brewery.repository;

import br.com.rbs.brewery.domain.Beer;
import org.springframework.data.repository.CrudRepository;

public interface BeerRepository extends CrudRepository<Beer, Integer> {

    Beer findByBeerStyleContainingIgnoreCase(String beerStyle);
}
