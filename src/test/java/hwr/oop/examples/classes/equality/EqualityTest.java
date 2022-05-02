package hwr.oop.examples.classes.equality;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EqualityTest {
    @Test
    void equals_TrainsWithDifferentIds_AreNotEqual() {
        Train trainA = new Train("A");
        Train trainB = new Train("B");
        boolean equalsResult = trainA.equals(trainB);
        assertThat(equalsResult).isFalse();
    }

    @Test
    void equals_TrainsWithSameIds_AreEqual() {
        Train firstTrainA = new Train("A");
        Train secondTrainA = new Train("A");
        boolean equalsResult = firstTrainA.equals(secondTrainA);
        assertThat(equalsResult).isTrue();
    }

    @Test
    void hashCode_TrainsWithDifferentIds_DifferentHashCodes() {
        Train trainA = new Train("A");
        Train trainB = new Train("B");
        int hashA = trainA.hashCode();
        int hashB = trainB.hashCode();
        assertThat(hashB).isNotEqualTo(hashA);
    }

    @Test
    void hashCode_TrainsWithSameIds_AreEqual() {
        Train firstTrainA = new Train("A");
        Train secondTrainA = new Train("A");
        int firstHashA = firstTrainA.hashCode();
        int secondHashA = secondTrainA.hashCode();
        assertThat(firstHashA).isEqualTo(secondHashA);
    }
}
