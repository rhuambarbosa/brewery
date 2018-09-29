package br.com.rbs.brewery.service;

import br.com.rbs.brewery.domain.Beer;
import br.com.rbs.brewery.repository.BeerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BeerServiceTest {

    @Autowired
    private BeerRepository beerRepository;

    private static Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void duplicatedSave() {
    //TODO: Implementar teste de verificação de duplicado
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
        Assert.assertEquals("Estilo máxima é obrigatório", violations.iterator().next().getMessageTemplate());
    }
}