package org.projectx.designpatterns;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Currying
 */
public class FunctionalBuilder {

    public static void main(String[] args) {
        record Person (String firstName, String middleName, String startName) {}

        Function<String, Function<String, Function<String, Person>>> personBuilder
                = (firstName) -> (middleName) -> (lastName) -> new Person(firstName, middleName, lastName);

        Person person = personBuilder.apply("Thomas").apply("Falco").apply("ross");
        System.out.println(person);

        Stream<Optional<String>> abc = Stream.of(Optional.ofNullable("abc"), Optional.ofNullable("lkj"), Optional.empty());
        Stream<String> abb = Stream.of("abc", "lkj", "");

        List<String> list = abb.flatMap(Stream::ofNullable).toList();

        list.forEach(System.out::println);
    }
}
