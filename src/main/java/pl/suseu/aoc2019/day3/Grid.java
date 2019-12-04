package pl.suseu.aoc2019.day3;

import java.util.HashMap;
import java.util.Map;

class Grid {

    private Map<Vector2d, Integer> grid = new HashMap<>();

    public int get(Vector2d vector2d) {
        try {
            return grid.get(vector2d);
        } catch (Exception e) {
            return 0;
        }
    }

    public void put(Vector2d vector2d, int value) {
        grid.put(vector2d, value);
    }

    public Map<Vector2d, Integer> getGrid() {
        return grid;
    }
}
