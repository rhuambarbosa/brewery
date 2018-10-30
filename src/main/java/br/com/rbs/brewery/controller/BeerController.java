package br.com.rbs.brewery.controller;

import br.com.rbs.brewery.domain.Beer;
import br.com.rbs.brewery.dto.BestBeerDTO;
import br.com.rbs.brewery.repository.BeerRepository;
import br.com.rbs.brewery.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/beer")
public class BeerController {

    @Autowired
    private BeerService beerService;

    @Autowired
    private BeerRepository beerRepository;

    @PostMapping("/beers")
    public ResponseEntity<Beer> createOrUpdateBeer(@RequestBody @Validated Beer beer) {
        Object body;
        HttpStatus status = HttpStatus.OK;

        try {
            body = beerRepository.save(beer);
        } catch (Exception e) {
            e.printStackTrace();
            body = "Falha ao salvar a entidade Beer";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity(body, status);
    }

    @PutMapping("/beers")
    public ResponseEntity<List<Beer>> createOrUpdateBeer(@RequestBody Beer[] beers) {
        Object body;
        HttpStatus status = HttpStatus.OK;
        final List<Beer> response = new ArrayList();
        try {

            for (Beer beer : beers) {
                response.add(beerRepository.save(beer));
            }

            body = response;

        } catch (Exception e) {
            e.printStackTrace();
            body = "Falha ao salvar a entidade Beer";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity(body, status);
    }

    @DeleteMapping("/beers/{id}")
    public ResponseEntity<String> deleteBeer(@PathVariable Integer id) {
        Object body = "Entidade " + id + " removido com sucesso.";
        HttpStatus status = HttpStatus.OK;
        try {
            beerRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            body = "Falha ao remover a entidade Beer id: " + id;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity(body, status);
    }

    @GetMapping("/beers")
    public ResponseEntity<List<Beer>> retrieveAllBeers() {
        Object body;
        HttpStatus status = HttpStatus.OK;
        List<Beer> beers;

        final List<Beer> response = new ArrayList();
        try {
            beers = beerRepository.findAll();

            if (beers.isEmpty()) {
                body = "Nenhuma beers cadastrada.";
                status = HttpStatus.NO_CONTENT;
            } else {
                body = beers;
            }
        } catch (Exception e) {
            e.printStackTrace();
            body = "Falha ao recureprar lista";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity(body, status);
    }

    @GetMapping("/beers/{id}")
    public ResponseEntity<Beer> retrieveBeer(@PathVariable Integer id) {
        Object body;
        HttpStatus status = HttpStatus.OK;

        Optional<Beer> beer = beerRepository.findById(id);

        try {
            if (!beer.isPresent()) {
                body = "ID: " + id + " não localizado";
                status = HttpStatus.NO_CONTENT;
            } else {
                body = beer.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
            body = "Falha ao recuperar o ID: " + id;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity(body, status);
    }

    @GetMapping(value = "/beers", params = {"page", "size"})
    public ResponseEntity<List<Beer>> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
        Object body = null;
        HttpStatus status = HttpStatus.OK;
        Page<Beer> beers = null;

        try {
            PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.ASC, "beerStyle");
            beers = beerRepository.findAll(pageRequest);
            if (!beers.hasContent()) {
                body = "Nenhuma cerveja localizada.";
                status = HttpStatus.NO_CONTENT;
            }
        } catch (Exception e) {
            e.printStackTrace();
            body = "Falha ao recuperar cervejas.";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return body == null ? new ResponseEntity(beers.getContent(), status) : new ResponseEntity(body, status);
    }

    @GetMapping("/best-beers/{temperature}")
    public ResponseEntity<BestBeerDTO> retrieveBestBeer(@PathVariable Integer temperature) {
        Object body;
        HttpStatus status = HttpStatus.OK;
        try {
            body = beerService.retrieveBestBeer(temperature);

            if (body == null) {
                body = "Temperatura: " + temperature + " não localizado a cerveja ideal";
                status = HttpStatus.NO_CONTENT;
            }
        } catch (Exception e) {
            e.printStackTrace();
            body = "falha ao recuperar a cerveja ideal.";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity(body, status);
    }
}