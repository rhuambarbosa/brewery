package br.com.rbs.brewery.controller;

import br.com.rbs.brewery.domain.Beer;
import br.com.rbs.brewery.repository.BeerRepository;
import br.com.rbs.brewery.service.BeerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BeerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BeerRepository beerRepositoryMocked;

    @InjectMocks
    private BeerController beerController;

    @InjectMocks
    private BeerService beerServiceMocked;

    @Before
    public void init() {
        initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(beerController)
                .build();
    }

    @Test
    public void createOrUpdateBeer() throws Exception {
        Beer beer = new Beer("Beer 1", 1, 2);
        mockMvc.perform(
                post("/beer/beers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(beer)))
                .andExpect(status().isOk());

        verify(beerRepositoryMocked, times(1)).save(Mockito.any());
    }

    @Test
    public void createOrUpdateBeerHttpStatusINTERNAL_SERVER_ERROR() throws Exception {
        when(beerRepositoryMocked.save(any())).thenThrow(Exception.class);

        Beer beer = new Beer("Beer 1", 1, 2);
        this.mockMvc.perform(
                post("/beer/beers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(beer)))
                .andExpect(content().string("Falha ao salvar a entidade Beer"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void createOrUpdateBeerWithoutBeerStyle() throws Exception {
        Beer beer = new Beer();
        beer.setTemperatureMin(1);
        beer.setTemperatureMax(2);

        mockMvc.perform(
                post("/beer/beers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(beer)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createOrUpdateBeerWithoutTemperatureMin() throws Exception {
        Beer beer = new Beer();
        beer.setBeerStyle("Beer 1");
        beer.setTemperatureMax(2);

        mockMvc.perform(
                post("/beer/beers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(beer)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createOrUpdateBeerWithoutTemperatureMax() throws Exception {
        Beer beer = new Beer();
        beer.setBeerStyle("Beer 1");
        beer.setTemperatureMin(1);

        mockMvc.perform(
                post("/beer/beers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(beer)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createOrUpdateBeerList() throws Exception {
        List<Beer> beers = new ArrayList<>();
        beers.add(new Beer("Beer 1", 1, 2));
        beers.add(new Beer("Beer 2", 1, 2));
        beers.add(new Beer("Beer 3", 1, 2));

        mockMvc.perform(
                put("/beer/beers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(beers)))
                .andExpect(status().isOk());

        verify(beerRepositoryMocked, times(3)).save(Mockito.any());
    }

    @Test
    public void deleteStudent() throws Exception {
        mockMvc.perform(
                delete("/beer/beers/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(beerRepositoryMocked, times(1)).deleteById(Mockito.any());
    }

    @Test
    public void retrieveAllBeers() throws Exception {
        mockMvc.perform(
                get("/beer/beers/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(beerRepositoryMocked, times(1)).findAll();
    }

    @Test
    public void retrieveBeer() throws Exception {
        Optional<Beer> beer = Optional.of(new Beer());
        when(beerRepositoryMocked.findById(Mockito.any())).thenReturn(beer);

        MvcResult result = mockMvc
                .perform(get("/beer/beers/{1}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andReturn();
//                .andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();

        verify(beerRepositoryMocked, times(1)).findById(Mockito.any());
    }

    @Test
    public void retrieveBeerNotFound() throws Exception {
        when(beerRepositoryMocked.findById(Mockito.any())).thenReturn(Optional.empty());
        mockMvc.perform(
                get("/beer/beers/{1}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(beerRepositoryMocked, times(1)).findById(Mockito.any());
    }

    @Test
    public void findPaginated() throws Exception {
        List<Beer> beers = new ArrayList<>();
        Page<Beer> pagedResponse = new PageImpl(beers);
        PageRequest pageRequest = new PageRequest(0, 3, Sort.Direction.ASC, "beerStyle");
        when(beerRepositoryMocked.findAll(pageRequest)).thenReturn((pagedResponse));

        mockMvc.perform(
                get("/beer/beers", 0, 3)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void retrieveBestBeer() throws Exception {
        /*initMocks(this);
        when(beerServiceMocked.retrieveBestBeer(5)).thenReturn(new BestBeerDTO());
        mockMvc.perform(
                get("/beer/best-beers/{temperature}",5)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(beerServiceMocked, times(1)).retrieveBestBeer(Mockito.any());*/
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}