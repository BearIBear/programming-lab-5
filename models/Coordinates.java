package models;
public class Coordinates {
    private Long x; //Максимальное значение поля: 432, Поле не может быть null
    private float y;

    public Coordinates(Long x, float y) {
        if (x == null) {
            throw new RuntimeException("Значение аргумента x не может быть null"); // TODO: Заменить на Exception
        }
        if (x > 432) {
            throw new RuntimeException("Значение аргумента x не может превышать 432");
        }
        this.x = x;
        this.y = y;
    }
    
    public Long getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
