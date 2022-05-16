package hwr.oop.examples.polymorphism.parametric;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class CollectionsTest {

    @DisplayName("Teaser: Set")
    @ParameterizedTest(name = "Add same train {0} times, size still 1")
    @ValueSource(ints = {1, 15, 42})
    void add_AddSame2TrainsNTimes_SetPreventsReAdding(int times) {
        // given
        Set<Train> trains = new HashSet<>();
        Train iceTrain = new Train("ICE");
        Train regionalTrain = new Train("RE");
        // when
        for (int i = 0; i < times; i++) {
            trains.add(regionalTrain);
            trains.add(iceTrain);
        }
        // then
        assertThat(trains)
                .isNotEmpty()
                .containsExactly(regionalTrain, iceTrain)
                .containsOnly(iceTrain, regionalTrain)
                .size().isEqualTo(2);
    }

    @DisplayName("Teaser: SortedSet")
    @ParameterizedTest(name = "Add same trains {0} times, size still 1, and sorted!")
    @ValueSource(ints = {1, 13, 42})
    void teaserTest_SortedTests(int times) {
        // given
        Train iceTrain = new Train("ICE");
        Train regionalTrain = new Train("RE");
        SortedSet<Train> trains = new TreeSet<>();
        // when
        for (int i = 0; i < times; i++) {
            trains.add(iceTrain);
            trains.add(regionalTrain);
        }
        // then
        assertThat(trains)
                .isNotEmpty()
                .containsExactly(iceTrain, regionalTrain)
                .containsOnly(iceTrain, regionalTrain)
                .doesNotContainSequence(regionalTrain, iceTrain)
                .size().isEqualTo(2);
    }

    @Nested
    @DisplayName("Unordered Collections: Use Collection!")
    class CollectionTest {
        private Collection<Train> trains;

        @BeforeEach
        void setUp() {
            this.trains = new ArrayList<>();
        }

        @Test
        void isEmpty_NonAdded_CollectionEmpty() {
            boolean isEmpty = trains.isEmpty();
            assertThat(isEmpty).isTrue();
            assertThat(trains).isEmpty();  // assertj supports Collections
        }

        @Test
        void isEmpty_AddOnce_CollectionNotEmpty() {
            trains.add(new Train());
            boolean isEmpty = trains.isEmpty();
            assertThat(isEmpty).isFalse();
            assertThat(trains).isNotEmpty();  // assertj supports Collections
        }

        @Test
        void size_NoTrainAdded_SizeIsZero() {
            int size = trains.size();
            assertThat(size).isZero();
            assertThat(trains).size().isZero();  // assertj supports Collections
        }

        @Test
        void size_OneTrainAdded_SizeIsOne() {
            // given
            Train iceTrain = new Train("ICE");
            trains.add(iceTrain);
            // when
            int size = trains.size();
            // then
            assertThat(size).isOne();
            assertThat(trains).size().isOne();  // assertj supports Collections
        }

        @Test
        void size_SingleTrainAddedTwice_SizeIsTwo() {
            // given
            Train iceTrain = new Train("ICE");
            trains.add(iceTrain);
            trains.add(iceTrain);
            // when
            int size = trains.size();
            // then
            assertThat(size).isEqualTo(2);
            assertThat(trains).size().isEqualTo(2);  // assertj supports Collections
        }

        @Test
        void contains_NoTrainAdded_CollectionDoesNotContainDefaultTrain() {
            // given
            Train first = new Train();
            // when
            boolean contains = trains.contains(first);
            // then
            assertThat(contains).isFalse();
            assertThat(trains).doesNotContain(first);  // assertj supports Collections
        }

        @Test
        void contains_AddFirstTrain_CollectionContainsTrain() {
            // given
            Train first = new Train();
            trains.add(first);
            // when
            boolean contains = trains.contains(first);
            // then
            assertThat(contains).isTrue();
            assertThat(trains).contains(first);  // assertj supports Collections
        }

        @Test
        void contains_AddFirstTrain_CollectionDoesNotContainOtherTrain() {
            // given
            Train iceTrain = new Train("ICE");
            Train regionalTrain = new Train("RE");
            trains.add(iceTrain);
            // when
            boolean contains = trains.contains(regionalTrain);
            // then
            assertThat(contains).isFalse();
            assertThat(trains).doesNotContain(regionalTrain);  // assertj supports Collections
        }

        @Test
        void containsAll_AddFirstButNotSecond_DoesNotContainBoth() {
            // given
            Train iceTrain = new Train("ICE");
            Train regionalTrain = new Train("RE");
            // when
            trains.add(iceTrain);
            // then
            assertThat(trains)
                    .contains(iceTrain)
                    .doesNotContain(regionalTrain);
        }

        @Test
        void remove_AddFirstTrainRemoveFirstTrain_DoesNotContainFirstTrain() {
            // given
            Train first = new Train("ICE");
            trains.add(first);
            // when
            trains.remove(first);
            // then
            assertThat(trains).doesNotContain(first);
        }

        @Test
        void removeAll_AddFirstAndSecondTrainThenRemoveBoth_CollectionEmpty() {
            // given
            Train iceTrain = new Train("ICE");
            Train regionalTrain = new Train("RE");
            trains.addAll(List.of(iceTrain, regionalTrain));
            // when
            trains.removeAll(List.of(iceTrain, regionalTrain));
            // then
            assertThat(trains).isEmpty();
        }
    }

    @Nested
    @DisplayName("Ordered Collections: Use List!")
    class ListTest {
        private List<Train> trains;

        @BeforeEach
        void setUp() {
            this.trains = new ArrayList<>();
        }

        @Test
        void listsAreAlsoCollections() {
            assertThat(trains)
                    .isInstanceOf(List.class)
                    .isInstanceOf(Collection.class);
        }

        @Test
        void get_AddOnceGetIndexZero_ReturnsAddedTrain() {
            // given
            Train iceTrain = new Train("ICE");
            trains.add(iceTrain);
            // when
            Train retrievedTrain = trains.get(0);
            // then
            assertThat(retrievedTrain).isSameAs(iceTrain);
        }

        @Test
        void get_AddNoneGetIndexZero_ThrowsException() {
            try {
                Train someTrain = trains.get(0);
                fail("should not be able to retrieve " + someTrain);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }

        @Test
        void get_AddTwiceGetIndexOneAndTwo_RetrievedInOrderAsAdded() {
            // given
            Train iceTrain = new Train("ICE");
            Train regionalTrain = new Train("RE");
            trains.add(iceTrain);
            trains.add(regionalTrain);
            // when
            Train first = trains.get(0);
            Train second = trains.get(1);
            // then
            assertThat(first).isSameAs(iceTrain);
            assertThat(second).isSameAs(regionalTrain);
            assertThat(trains).containsExactly(first, second);  // assertj supports collections!
        }
    }

    @Nested
    @DisplayName("Key-value Collections: Use Map!")
    class MapTest {
        private Map<String, Train> trainMap;

        @BeforeEach
        void setUp() {
            this.trainMap = new HashMap<>();
        }

        @Test
        void keySet_AddOneAndTwo_ContainsOneAndTwo() {
            // given
            trainMap.put("1", new Train());
            trainMap.put("2", new Train());
            // when
            Set<String> keys = trainMap.keySet();
            // then
            assertThat(keys).contains("1", "2");
            assertThat(trainMap).containsKeys("1", "2");  // assertj supports maps!
        }

        @Test
        void keySet_AddOneTwice_ContainsOnlyOne() {
            // given
            trainMap.put("1", new Train());
            trainMap.put("1", new Train());
            // when
            Set<String> keys = trainMap.keySet();
            // then
            assertThat(keys).containsOnly("1");
            assertThat(trainMap).containsOnlyKeys("1");  // assertj supports maps!
        }

        @Test
        void values_AddTrainsAtOneAndTwo_ContainsBothAddedTrains() {
            // given
            Train iceTrain = new Train("ICE");
            Train regionalTrain = new Train("RE");
            trainMap.put("1", iceTrain);
            trainMap.put("2", regionalTrain);
            // when
            Collection<Train> values = trainMap.values();
            // then
            assertThat(values).containsOnly(iceTrain, regionalTrain);
            assertThat(trainMap)
                    .containsValues(iceTrain, regionalTrain);  // assertj supports maps!
        }

        @Test
        void get_AddAtOne_CanRetrieveAdded() {
            // given
            Train iceTrain = new Train("ICE");
            trainMap.put("1", iceTrain);
            // when
            Train retrieved = trainMap.get("1");
            // then
            assertThat(retrieved).isSameAs(iceTrain);
        }

        @Test
        void get_NoneAdded_ReturnsNull() {
            Train train = trainMap.get("1");
            assertThat(train).isNull();  // no exception?!
        }

        @Test
        void containsKey_AddedAtOne_ContainsKeyOne() {
            // given
            // when
            boolean contains = trainMap.containsKey("1");
            // then
            assertThat(contains).isFalse();
            assertThat(trainMap).doesNotContainKey("1");
        }

        @Test
        void containsKey_NoneAdded_DoesNotContainKeyOne() {
            // given
            trainMap.put("1", new Train());
            // when
            boolean contains = trainMap.containsKey("1");
            // then
            assertThat(contains).isTrue();
            assertThat(trainMap).containsKeys("1");
        }

        @Test
        void putIfAbsent_AddOneTwice_FirstAddedTrainRemains() {
            // given
            Train iceTrain = new Train("ICE");
            Train regionalTrain = new Train("RE");
            trainMap.putIfAbsent("1", iceTrain);
            trainMap.putIfAbsent("1", regionalTrain);
            // when
            Train train = trainMap.get("1");
            // then
            assertThat(train).isSameAs(iceTrain);
        }
    }

}
