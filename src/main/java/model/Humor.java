package model;

public enum Humor {
    FELIZ(1), TRISTE(2), CALMO(3), ESTRESSANTE(4), ENTEDIANTE(5), OK(6), CHEIO(7), CANSATIVO(8);

    private int value;

    Humor(int value) {
        this.value = value;
    }
}
