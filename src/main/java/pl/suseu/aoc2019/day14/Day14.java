package pl.suseu.aoc2019.day14;

import pl.suseu.aoc2019.FileUtils;

import java.util.HashMap;
import java.util.Map;

public class Day14 {

    private static final long initialOre = 1000000000000L;

    private final Map<String, Reaction> reactions = new HashMap<>();
    private final Map<String, Long> inventory = new HashMap<>();

    public void firstPart() {
        inventory.put("ORE", initialOre);
        for (Reaction r : Reaction.generateReactions(FileUtils.loadFile("day14test"))) {
            r.getProducts().keySet().forEach(s -> reactions.put(s, r));
        }

        String toProduce = "XD";
        String missing = null;

        while (true) {
            if (canProduce(toProduce)) {
                produce(toProduce);
                break;
            }

            if (missing == null) {
                missing = missingProduct(toProduce);
            }

            if (canProduce(missing)) {
                produce(missing);
                missing = null;
            } else {
                missing = missingProduct(missing);
            }
        }

        System.out.println((initialOre - inventory.get("ORE")));
    }

    public void secondPart() {
        inventory.put("ORE", initialOre);
        for (Reaction r : Reaction.generateReactions(FileUtils.loadFile("day14"))) {
            r.getProducts().keySet().forEach(s -> reactions.put(s, r));
        }

        String toProduce = "FUEL";
        String missing = null;

        long i = 0;
        while (true) {
            if (canProduce(toProduce)) {
                produce(toProduce);
            }

            if (missing == null) {
                missing = missingProduct(toProduce);
            }

            if (missing.equals("ORE")) {
                break;
            }

            if (canProduce(missing)) {
                produce(missing);
                missing = null;
            } else {
                missing = missingProduct(missing);
            }

            i++;
            if (i == 1000000) {
                System.out.println((inventory.get("ORE") / (double) initialOre) * 100 + "%");
                i = 0;
            }
        }

        System.out.println((inventory.get("FUEL").longValue()));
    }

    public void produce(String type) {
        Map<String, Long> ingredients = reactions.get(type).getIngredients();
        Map<String, Long> products = reactions.get(type).getProducts();

        for (Map.Entry<String, Long> entry : ingredients.entrySet()) {
            this.inventory.put(entry.getKey(), this.inventory.get(entry.getKey()) - entry.getValue());
        }

        for (Map.Entry<String, Long> entry : products.entrySet()) {
            this.inventory.put(entry.getKey(), this.inventory.computeIfAbsent(entry.getKey(), s -> 0L) - entry.getValue());
        }
    }

    public String missingProduct(String type) {
        Map<String, Long> ingredients = reactions.get(type).getIngredients();
        for (Map.Entry<String, Long> entry : ingredients.entrySet()) {
            if (!inventoryContains(entry.getKey(), entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public boolean canProduce(String type) {
        return missingProduct(type) == null;
    }

    public boolean inventoryContains(String type, long amount) {
        return inventory.getOrDefault(type, 0L) >= amount;
    }

}
