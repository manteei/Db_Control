package Collection;
import java.io.Serializable;

/**
 * Класс описывающий координаты лабораторной работы.
 */

public class Coordinates implements Serializable {
    private long x;
    private long y; //Значение поля должно быть больше -732
    public Coordinates(){}
    public Coordinates(long x, long y){
        this.x = x;
        this.y = y;
    }
    public long getX(){
        return x;
    }
    public long getY(){
        return y;
    }


    public void setX(long x) {
        this.x = x;
    }


    public void setY(long y) {
        if (y > -732) {
            this.y = y;
        }
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
