package hwr.oop.examples.classes.meta;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Object: Class meta information (.class and #getClass)")
class MetaInformationTest {

  @Test
  @DisplayName("TDD Demo: tests used to create SUT to show case meta information about classes")
  void sut_ConsistsOfTrainClassAndIdentifiableInterface() {
    // given
    final String id = "RE-1337";
    final Identifiable train = new Train(id);
    // when
    final String retrievedId = train.identifier();
    // then
    assertThat(retrievedId).isEqualTo(id);
  }

  @Test
  void getClass_DotClass_EqualIfDotClassComesFromConcreteType() {
    // given
    final Train train = new Train("RE-1337");
    // when
    final Class<Train> metaInfoFromClass = Train.class;
    final Class<? extends Train> metaInfoFromObject = train.getClass();
    // then
    assertThat(metaInfoFromClass).isEqualTo(metaInfoFromObject);
  }

  @Test
  void getClass_DotClass_NotEqualIfDotClassComesFromSuperType() {
    // given
    final Identifiable train = new Train("RE-1337");
    // when
    final Class<? extends Identifiable> metaInfoFromObject = train.getClass();
    final Class<Identifiable> metaInfoFromInterface = Identifiable.class;
    // then
    assertThat(metaInfoFromInterface).isNotEqualTo(metaInfoFromObject);
  }
}
