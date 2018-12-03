package adventOfCode;

import org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple;
import org.apache.commons.math3.util.Pair;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class day01 {

    @Test
    public void star01() throws IOException {
        int result = Files.lines(Paths.get("src/test/resources/input.txt"))
                .mapToInt(line -> Integer.valueOf(line))
                .sum();
        System.out.println("Result:" + result);
    }

    @Test
    public void star02() throws IOException {
        List<Integer> rawNumbers = Files.lines(Paths.get("src/test/resources/input.txt"))
                .map(line -> Integer.valueOf(line))
                .collect(Collectors.toList());
        Integer current = 0;
        List<Integer> pastFrequency = new ArrayList<>(rawNumbers.size());

        for(Integer number : rawNumbers){
            current = Math.addExact(number,current);
            System.out.println(number + ", cur:"+ current);
            if(pastFrequency.contains(current)){
                System.out.println("Result:" + current);
                break;
            }
            System.out.println(pastFrequency);
            pastFrequency.add(current);
        }
    }

}
