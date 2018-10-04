package br.com.rbs.brewery.service;

import br.com.rbs.brewery.domain.Beer;
import br.com.rbs.brewery.dto.BestBeerDTO;
import br.com.rbs.brewery.repository.BeerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BeerServiceTest {

    @Mock
    private BeerRepository beerRepository;

    @InjectMocks
    private BeerService beerService;

    private static Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void messageNotNullBeerStyle() {
        Beer beer = new Beer();
        beer.setTemperatureMin(-2);
        beer.setTemperatureMax(1);

        Set<ConstraintViolation<Beer>> violations = validator.validate(beer);
        Assert.assertEquals("Estilo de cerveja é obrigatório", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void messageNotNullTemperatureMin() {
        Beer beer = new Beer();
        beer.setBeerStyle("IPA");
        beer.setTemperatureMax(1);

        Set<ConstraintViolation<Beer>> violations = validator.validate(beer);
        Assert.assertEquals("Temperatura mínima é obrigatório", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void messageNotNulltemperatureMax() {
        Beer beer = new Beer();
        beer.setBeerStyle("IPA");
        beer.setTemperatureMin(-2);

        Set<ConstraintViolation<Beer>> violations = validator.validate(beer);
        Assert.assertEquals("Temperatura máxima é obrigatório", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void getDifferenceMatch() {
        List<Beer> beers = buildBeers();
        String expected = "Dunkel";
        int temperature = -2;

        when(beerRepository.findAll()).thenReturn(beers);

        BestBeerDTO bestBeer = beerService.retrieveBestBeer(temperature);
        Assert.assertEquals(expected, bestBeer.getBeerStyle());
    }

    @Test
    public void getDifferenceMin() {
        List<Beer> beers = buildBeers();
        String expected = "Imperial Stouts";
        int temperature = -20;

        when(beerRepository.findAll()).thenReturn(beers);

        BestBeerDTO bestBeer = beerService.retrieveBestBeer(temperature);
        Assert.assertEquals(expected, bestBeer.getBeerStyle());
    }

    @Test
    public void getDifferenceMax() {
        List<Beer> beers = buildBeers();
        String expected = "Brown ale";
        int temperature = 20;

        when(beerRepository.findAll()).thenReturn(beers);

        BestBeerDTO bestBeer = beerService.retrieveBestBeer(temperature);
        Assert.assertEquals(expected, bestBeer.getBeerStyle());
    }

    private List<Beer> buildBeers() {
        List<Beer> beers = new ArrayList<>();
        beers.add(new Beer("Weissbier", -1, 3));
        beers.add(new Beer("Pilsens", -2, 4));
        beers.add(new Beer("aWeizenbier", -4, 6));
        beers.add(new Beer("Red ale", -5, 5));
        beers.add(new Beer("India pale ale", -6, 7));
        beers.add(new Beer("IPA", -7, 10));
        beers.add(new Beer("Dunkel", -8, 2));
        beers.add(new Beer("Imperial Stouts", -10, 13));
        beers.add(new Beer("Brown ale", 0, 14));
        return beers;
    }
}