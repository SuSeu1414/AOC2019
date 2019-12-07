package pl.suseu.aoc2019.day7;

import pl.suseu.aoc2019.FileUtils;
import pl.suseu.aoc2019.intcode.emulator.Cluster;
import pl.suseu.aoc2019.intcode.util.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day7 {

    private List<Integer> program = FileUtils.loadFileAsIntegerListBySplittingALongStringLikeThisFunctionName("day7", ",");

    public void firstPart() {
        int max = 0;
        loop:
        for (int i = 0; i < 43211; i++) {
            List<Integer> options = new ArrayList<>();
            for (int j = 1; j <= 5; j++) {
                options.add(Utils.getDigit(i, j));
            }

            for (int o : options) {
                if (o > 4) continue loop;
                if (options.size() != new HashSet<>(options).size()) continue loop;
            }

            Cluster cluster = new Cluster(5, new ArrayList<>(options), program);
            int result = cluster.run();
            if (result > max) max = result;
        }

        System.out.println("DONE " + max);
//
//        Cluster cluster = new Cluster(5, Arrays.asList(4,3,2,1,0), program);
//        cluster.run();
    }

    public void secondPart(){
        int max = 0;
        loop:
        for (int i = 55555; i < 100000; i++) {
            List<Integer> options = new ArrayList<>();
            for (int j = 1; j <= 5; j++) {
                options.add(Utils.getDigit(i, j));
            }

            for (int o : options) {
                if (o < 5) continue loop;
                if (options.size() != new HashSet<>(options).size()) continue loop;
            }

            Cluster cluster = new Cluster(5, new ArrayList<>(options), program);
            System.out.println(i);
            int result = cluster.run();
            if (result > max) max = result;
        }

        System.out.println("DONE " + max);
    }
}
