package utils;

public class Tijd {
    public static boolean isGeldigJaar(int jaar) {
        return jaar >= 2000 && jaar <= 2030;
    }

    public static boolean isGeldigeMaand(int maand) {
        return maand >= 0 && maand <= 12;
    }

}
