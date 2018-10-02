package br.com.rbs.brewery.repository;

import br.com.rbs.brewery.domain.Beer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends CrudRepository<Beer, Integer> {

    Beer findByBeerStyleContainingIgnoreCase(String beerStyle);
}
