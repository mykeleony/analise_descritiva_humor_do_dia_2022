package doc_generator;

import model.Dia;
import model.Humor;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class PreenchedorDeDias {
    private List<Dia> dias;

    public void preencher() {
        LocalDate primeiroDia = LocalDate.of(Dia.ano, Month.JANUARY, 1);
        LocalDate ultimoDia = primeiroDia.plusYears(1).minusDays(1);
        LocalDate diaAtual = primeiroDia;

        dias = new ArrayList<>();

        while (diaAtual.getYear() < (Dia.ano + 1)) {
            Dia atual = new Dia(diaAtual.getDayOfMonth(), diaAtual.getMonth().toString(), diaAtual.getDayOfYear(), diaAtual.getDayOfWeek().toString());

            System.out.println("Insira o humor de " + diaAtual + ": ");

            int humor = new Scanner(System.in).nextInt();

            if (humor < 1 || humor > 8) {
                try {
                    dias.remove(dias.size() - 1);
                } catch(IndexOutOfBoundsException e) {
                    System.out.println("Não é possível alterar o dia anterior pois nenhum dia foi inserido. Por favor, tente novamente.");
                    return;
                }

                diaAtual = diaAtual.minusDays(1);

                continue;
            }

            for (Humor h : Humor.values()) {
                if (h.ordinal() + 1 == humor) {
                    atual.setHumor(h);
                    System.out.println(h);
                }
            }

            dias.add(atual);
            diaAtual = diaAtual.plusDays(1);
        }
    }

    public List<Dia> getDias() {
        return dias;
    }
}
