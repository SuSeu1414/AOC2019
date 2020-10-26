package pl.suseu.aoc2019.day14;

import java.util.*;
import java.util.stream.Collectors;

public class Reaction {

    private final Map<String, Long> ingredients;
    private final Map<String, Long> products;

    public Reaction(Map<String, Long> ingredients, Map<String, Long> products) {
        this.ingredients = Collections.unmodifiableMap(ingredients);
        this.products = Collections.unmodifiableMap(products);
    }

    public Map<String, Long> getIngredients() {
        return ingredients;
    }

    public Map<String, Long> getProducts() {
        return products;
    }

    public static List<Reaction> generateReactions(List<String> strings) {
        List<Reaction> reactions = new ArrayList<>();
        for (String reaction : strings) {
            reactions.add(new Reaction(Arrays.stream(reaction.split(" => ")[0].split(", "))
                    .map(Chemical::getFromString)
                    .collect(Collectors.toMap(Chemical::getType, Chemical::getAmount)),
                    Arrays.stream(reaction.split(" => ")[1].split(", "))
                    .map(Chemical::getFromString)
                    .collect(Collectors.toMap(Chemical::getType, Chemical::getAmount))));
        }

        return Collections.unmodifiableList(reactions);
    }
}
