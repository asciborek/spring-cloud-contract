package be.faros.testing.tapaseater;

import be.faros.testing.tapaseater.gateway.dto.Tapas;
import be.faros.testing.tapaseater.presenter.TapasEaterPresenter;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TapasEater.class)
@AutoConfigureStubRunner(ids = {"be.faros.testing:tapasapp:+:stubs:8080"}, stubsMode = StubsMode.LOCAL)
public class TapasEaterTests {

  @Autowired
  private TapasEaterPresenter tapasEaterPresenter;

  @Test
  public void shouldListAllAvailableTapas() {
    //when
    List<Tapas> tapases = tapasEaterPresenter.listAvailableTapas();
    //then
    Assert.assertEquals(2, tapases.size());
  }

  @Test
  public void shouldReturnTapasWithGivenId() {
    Tapas tapas = tapasEaterPresenter.getTapasById("1");
    assertThat(tapas).isNotNull();
    assertThat(tapas.getId()).isEqualTo("1");
    assertThat(tapas.getName()).isEqualTo("Banderillas");
  }
}