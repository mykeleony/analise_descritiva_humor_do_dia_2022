package doc_generator;

import model.Dia;
import model.Humor;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class PreenchedorDeDias {
    private List<Dia> dias;

    // Armazena os inputs de humor de cada dia do ano. Os inputs são números inteiros correspondentes a
    // a cada humor. Os valores estão disponíveis no enum "Humor".
    public void preencher() {
        LocalDate primeiroDia = LocalDate.of(Dia.ano, Month.JANUARY, 1);
        LocalDate ultimoDia = primeiroDia.plusYears(1).minusDays(1);
        LocalDate diaAtual = primeiroDia;

        dias = new ArrayList<>();

        // Aguarda inputs até o último dia do ano atual
        while (diaAtual.getYear() < (Dia.ano + 1)) {
            Dia atual = new Dia(diaAtual.getDayOfMonth(), diaAtual.getMonth().toString(), diaAtual.getDayOfYear(), diaAtual.getDayOfWeek().toString());

            System.out.println("Insira o humor de " + diaAtual + ": ");

            int humor = new Scanner(System.in).nextInt();

            // Para corrigir um input errado, basta inserir um número que não corresponda a nenhum humor.
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

            // Busca o humor correspondente ao valor inserido
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
