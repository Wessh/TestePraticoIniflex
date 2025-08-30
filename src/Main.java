import model.Funcionario;
import model.Pessoa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- Teste Prático - Iniflex ---");

        // 3.1 – Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9826.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 – Remover o funcionário “João” da lista.
        funcionarios.removeIf(f -> f.getNome().equals("João"));

        // 3.3 – Imprimir todos os funcionários com todas suas informações
        System.out.println("\n--- Todos os Funcionários ---");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        funcionarios.forEach(f -> {
            System.out.printf("Nome: %s, Data Nascimento: %s, Salário: %,.2f, Função: %s%n",
                    f.getNome(), f.getDataNascimento().format(dateFormatter), f.getSalario(), f.getFuncao());
        });

        // 3.4 – Os funcionários receberam 10% de aumento de salário
        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.10"))));

        System.out.println("\n--- Funcionários com 10% de aumento ---");
        funcionarios.forEach(f -> {
            System.out.printf("Nome: %s, Data Nascimento: %s, Salário: %,.2f, Função: %s%n",
                    f.getNome(), f.getDataNascimento().format(dateFormatter), f.getSalario(), f.getFuncao());
        });

        // 3.5 – Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 – Imprimir os funcionários, agrupados por função
        System.out.println("\n--- Funcionários agrupados por função ---");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(f -> System.out.printf("  - Nome: %s, Data Nascimento: %s, Salário: %,.2f%n",
                    f.getNome(), f.getDataNascimento().format(dateFormatter), f.getSalario()));
        });

        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        System.out.println("\n--- Aniversariantes de Outubro e Dezembro ---");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .forEach(f -> System.out.printf("Nome: %s, Data Nascimento: %s%n", f.getNome(), f.getDataNascimento().format(dateFormatter)));

        // 3.9 – Imprimir o funcionário com a maior idade
        System.out.println("\n--- Funcionário com a maior idade ---");
        funcionarios.stream()
                .min(Comparator.comparing(Pessoa::getDataNascimento))
                .ifPresent(funcionarioMaisVelho -> System.out.printf("Nome: %s, Idade: %d anos%n", funcionarioMaisVelho.getNome(),
                        LocalDate.now().getYear() - funcionarioMaisVelho.getDataNascimento().getYear()));

        // 3.10 – Imprimir a lista de funcionários por ordem alfabética.
        System.out.println("\n--- Funcionários em ordem alfabética ---");
        funcionarios.stream()
                .sorted(Comparator.comparing(Pessoa::getNome))
                .forEach(f -> System.out.printf("Nome: %s, Data Nascimento: %s, Salário: %,.2f, Função: %s%n",
                        f.getNome(), f.getDataNascimento().format(dateFormatter), f.getSalario(), f.getFuncao()));

        // 3.11 – Imprimir o total dos salários dos funcionários.
        System.out.println("\n--- Total dos Salários ---");
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("Total de Salários: %,.2f%n", totalSalarios);

    }
}