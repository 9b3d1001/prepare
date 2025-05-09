package collections;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class FlatmapExample {

    public static void main(String[] args) {
        List<String> input = Arrays.asList(
            "apple,banana,orange",
            "banana,grape",
            "apple,kiwi"
        );

        Set<String> output = input
                                .stream()
                                .flatMap(s -> Arrays.stream(s.split(",")))
                                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(output);

        List<Person> people = Arrays.asList(
            new Person("Alice", Arrays.asList("Reading", "Hiking")),
            new Person("Bob", Arrays.asList("Cycling", "Reading")),
            new Person("Charlie", Arrays.asList("Hiking", "Swimming"))
        );

        Set<String> hobbies = people.stream()
                                    .flatMap(p -> p.getHobbies().stream()).collect(Collectors.toCollection(TreeSet::new));

        System.out.println(hobbies);
    }

}


class Person {
    String name;
    List<String> hobbies;

    Person(String name, List<String> hobbies) {
        this.name = name;
        this.hobbies = hobbies;
    }

    public List<String> getHobbies() {
        return hobbies;
    }
}