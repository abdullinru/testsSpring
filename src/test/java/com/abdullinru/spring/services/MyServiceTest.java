package com.abdullinru.spring.services;

import com.abdullinru.spring.entities.Person;
import com.abdullinru.spring.repositories.MyRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
//@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class MyServiceTest {

    private static final Long PERSON_ID = 1L;
    private static final String DEFAULT_NAME = "default";
    private static final String COMMON_NAME = "Anonym";
    @Mock
    private MyRepo repo;

    @Captor
    private ArgumentCaptor<Long> captor1;

    @Captor
    private ArgumentCaptor<Integer> captor2;

    @InjectMocks
    private MyService service;

    private Person person;

    @BeforeAll
    public static void init() {

    }

    @BeforeEach
    public void reset() {
        person = Person.builder()
                .withId(1L)
                .withAge(22)
                .withName("Anonym")
                .build();
    }

    @Test
    public void getPersonPositiveTest() {
        Person expected = person;
        Mockito.when(repo.findPersonById(Mockito.any())).thenReturn(Optional.of(person));

        Person actual = service.getPersonById(PERSON_ID);

        Assertions.assertThat(actual).isEqualTo(expected);
        Assertions.assertThat(actual.getName()).isEqualTo(COMMON_NAME);
    }

    @Test
    public void getPersonPositiveTest2() {
        Person expected = person;
        Mockito.when(repo.findPersonById(Mockito.any())).thenReturn(Optional.empty());

        Person actual = service.getPersonById(PERSON_ID);

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getName()).isEqualTo(DEFAULT_NAME);
    }

    @ParameterizedTest
//    @MethodSource("source")
    @MethodSource("source2")
    public void addAgesTest(Integer age1, Integer age2, Integer sum) {
        Mockito.doThrow(new RuntimeException()).when(repo).addAges(age1, age2);

        service.addAges(age1, age2);

        Mockito.verify(repo).addAges(age1, age2);
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                 Arguments.of(1,1,2),
                 Arguments.of(1,2,3),
                 Arguments.of(2,2,4)
        );
    }

    private static Object[][] source2() {
        return new Object[][]{
                {1,1,2},
                {1,2,3},
                {2,2,4},
                {2,3,5}
        };
    }

    @Test
    public void getPersonCaptor() {

        service.getPersonById(PERSON_ID);

        Mockito.verify(repo, Mockito.times(2)).findPersonById(captor1.capture());

        Assertions.assertThat(captor1.getAllValues().get(0)).isEqualTo(PERSON_ID);
        Assertions.assertThat(captor1.getClass()).isEqualTo(ArgumentCaptor.class);
    }

    @Test
    @Disabled
    public void getPersonCaptor2() {

        service.addAges(2,8);

        Mockito.verify(repo, Mockito.times(1)).addAges(captor2.capture(), captor2.capture());

        Assertions.assertThat(captor2.getAllValues().get(0)).isEqualTo(2);
        Assertions.assertThat(captor2.getClass()).isEqualTo(ArgumentCaptor.class);
    }

}