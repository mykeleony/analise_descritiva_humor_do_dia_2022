package model;

public class Dia {
    private int diaDoMes;
    private int diaDoAno;
    public static final int ano = 2022;
    private String diaDaSemana;
    private String mes;
    private Humor humor;

    public Dia(int diaDoMes, String mes, int diaDoAno, String diaDaSemana) {
        this.diaDoMes = diaDoMes;
        this.diaDoAno = diaDoAno;
        this.diaDaSemana = diaDaSemana;
        this.mes = mes;
    }

    public void setHumor(Humor humor) {
        this.humor = humor;
    }

    @Override
    public String toString() {
        return "Dia{" +
                "diaDoMes=" + diaDoMes +
                ", diaDoAno=" + diaDoAno +
                ", ano=" + ano +
                ", diaDaSemana='" + diaDaSemana + '\'' +
                ", mes='" + mes + '\'' +
                ", humor=" + humor +
                '}';
    }
}
