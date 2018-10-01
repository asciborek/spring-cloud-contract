package be.faros.testing.tapasapp;

import be.faros.testing.tapasapp.catalogue.controller.CatalogueController;
import be.faros.testing.tapasapp.catalogue.domain.usecases.CatalogueSearching;
import be.faros.testing.tapasapp.catalogue.domain.usecases.dto.Tapas;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TapasApp.class)
public abstract class CatalogueBase {

    @Autowired
    CatalogueController catalogueController;

    @MockBean
    CatalogueSearching catalogueSearching;

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(catalogueController);

        List<Tapas> tapas = Arrays.asList(new Tapas("0", "All i oli", new BigDecimal(1.5)),
                                          new Tapas("1", "Banderillas", new BigDecimal(3)));

        Mockito.when(catalogueSearching.getTapas("1")).thenReturn(new Tapas("1", "Banderillas", new BigDecimal(3)));
        Mockito.when(catalogueSearching.getAllTapas()).thenReturn(tapas);
    }

}