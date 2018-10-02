package br.com.rbs.brewery.repository;

import br.com.rbs.brewery.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Integer> {

    Beer findByBeerStyleContainingIgnoreCase(String beerStyle);
}