package pl.suseu.aoc2019.day3;

class Vector2d {

    private int x;
    private int y;

    public Vector2d() { }

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2d vector2d) {
        this.add(vector2d.getX(), vector2d.getY());
    }

    public void add(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public int distance(Vector2d vector2d) {
        return distance(vector2d.getX(), vector2d.getY());
    }

    public int distance(int x, int y){
        return Math.abs(this.getX() - x) + Math.abs(this.getY() - y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
