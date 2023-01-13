package doc_generator;

import model.Dia;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GeradorDeCSV {
    public static void main(String[] args) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        PreenchedorDeDias p = new PreenchedorDeDias();
        p.preencher();  // Inicia preenchimento dos humores
        List<Dia> dias = p.getDias();

        // Gerando CSV a partir da lista de dias com OpenCsv
        Writer writer = Files.newBufferedWriter(Paths.get("dias.csv"));
        StatefulBeanToCsv<Dia> beanToCsv = new StatefulBeanToCsvBuilder(writer).build();

        beanToCsv.write(dias);

        writer.flush();
        writer.close();
    }
}
