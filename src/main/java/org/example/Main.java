package org.example;

import java.util.Scanner;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        find10MostFrequentWords(string);
        scanner.close();
    }

    static void find10MostFrequentWords(final String input) {
        asList(
                requireNonNull(input, "input string can't be null")
                        .toLowerCase()
                        .split("[\\p{Blank}\\p{Punct}]+")
        )
                .stream()
                .collect(groupingBy(s -> s, counting()))
                .entrySet()
                .stream()
                .sorted(
                        (e0, e1) -> {
                            final int res = e1.getValue().compareTo(e0.getValue());
                            return res == 0 ? e0.getKey().compareTo(e1.getKey()) : res;
                        }
                )
                .limit(10)
                .forEach(s -> System.out.println(s.getKey()));
    }
}