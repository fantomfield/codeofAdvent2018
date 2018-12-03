package adventOfCode;

import org.apache.commons.math3.util.Pair;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class day02 {


    @Test
    public void star03() throws IOException {
        Pair<Integer, Integer> fileNamePairs = Files.lines(Paths.get("src/test/resources/input2.txt"))
        .map(this::getThreeAndFours)
        .reduce(this::reduce)
        .get();

        System.out.println("Result:" + Math.multiplyExact(fileNamePairs.getSecond(),fileNamePairs.getFirst()));
    }

    @Test
    public void star04() throws IOException {
        List<String> rawFileName = Files.lines(Paths.get("src/test/resources/input2.txt"))
                .collect(Collectors.toList());

                rawFileName.stream().forEach(stringToCheck -> differsBy1(stringToCheck,rawFileName));

    }

    private boolean differsBy1(String s, List<String> rawFileName) {
        return rawFileName.stream().anyMatch( s2 -> differsBy1(s,s2));
    }

    private boolean differsBy1(String s1, String s2){
        if(s1.equals(s2)){
            return false; //dirty hack to prevent checking same indexed
        }
        return IntStream.range(0,s1.length()).boxed()
                .anyMatch( index -> differsByOne(index,s1, s2));
    }

    private boolean differsByOne(Integer index, String s1, String s2){
        List<Integer> smallerS1 = removeSubstring(index, s1.chars().boxed().collect(Collectors.toList()));
        List<Integer> smallerS2 = removeSubstring(index,s2.chars().boxed().collect(Collectors.toList()));
        if(smallerS1.equals(smallerS2)){
            System.out.println("S1:"+s1);
            System.out.println("S2:"+s2);
            System.out.println("Matching:" + smallerS1);
        }
        return smallerS1.equals(smallerS2);
    }

    private List<Integer> removeSubstring(Integer index, List<Integer> s){
        s.remove(index);
        return s;
    }

    private Pair<Integer,Integer> reduce(Pair<Integer,Integer> pair1, Pair<Integer,Integer> pair2){
        return Pair.create(pair1.getFirst() + pair2.getFirst(), pair1.getSecond() + pair2.getSecond());
    }

    private Pair<Integer,Integer> getThreeAndFours(String fileName){
        Integer three = containsThree(fileName) ? 1 : 0;
        Integer four = containsTwo(fileName) ? 1 : 0;
        return Pair.create(three,four);
    }

    private boolean containsTwo(String fileName) {
        return containsNumber(fileName, 2);
    }

    private boolean containsThree(String fileName) {
        return containsNumber(fileName, 3);
    }

    private boolean containsNumber(String fileName, Integer i) {
        List<Integer> searchableArray = fileName.chars().boxed().collect(Collectors.toList());
        Set<Integer> comparing = fileName.chars().boxed().collect(Collectors.toSet());
        return comparing.stream().anyMatch( cha -> checkChar(cha,i,searchableArray));
    }

    private boolean checkChar(Integer cha, Integer i, List<Integer> searchableArray) {
        return i.equals(Math.toIntExact(searchableArray.stream()
                .filter( character -> character.equals(cha)).count()));
    }
}
