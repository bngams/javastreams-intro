import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamsTest {

    @Test
    void functionalInterfacesAndLambdas() {
        // Runnable => effectuer un traitement
        // Lambda => implementer la methode run de Runnable
        Runnable hello = () -> System.out.println("Hello Lambda!");
        hello.run();

        // Method reference
        Consumer<String> sayHello = (param) -> System.out.println(param);
        Consumer<String> sayHelloWithMethodRef = System.out::println;
        sayHello.accept("Hello without method Ref");
        sayHelloWithMethodRef.accept("Hello with method Ref");

        // Custom Interface
        NumericTest isEven = (a) -> a % 2 == 0;
        isEven.computeTest(12);

        // Same with existing FunctionalInterface
        Function<Integer, Boolean> isEvenWithFunctionInterface = (a) -> (int) a % 2 == 0;

        // Lambdas with multiple lines () -> { ... }
        BiFunction<Integer, Integer, Long> add = (a, b) -> {
            // try ... catch
            return (long) a + b;
        };

        // fake test
        Assertions.assertEquals(true, true);
    }

    @Test
    void streamMap() {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Boris", 29));
        people.add(new Person("Eddy", 29));

        Stream<Person> peopleStream = people.stream();
        peopleStream.map((p) -> {
            p.setAge(30);
            return p;
        });

        // calcul age total (sum age)

        // transformation du flux
        Stream<Integer> ages = people.stream().map( (p) -> p.getAge() );
        Integer ageTotalWithMap = people.stream().map(Person::getAge).reduce(0, (x, acc) -> x + acc );

        IntStream ageWithMapToInt = people.stream().mapToInt((p) -> p.getAge());
        // stream() -> mapToInt -> IntStream.sum
        Integer ageTotalWithMapToInt = people.stream().mapToInt(Person::getAge).sum();

        // fake test
        Assertions.assertEquals(29+29, ageTotalWithMap);
        Assertions.assertEquals(29+29, ageTotalWithMapToInt);
    }
}
