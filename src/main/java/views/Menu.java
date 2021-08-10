package views;

import controllers.AtletasController;
import java.util.Scanner;
import models.Equipe;
import controllers.EquipesController;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Atleta;
import models.Sexo;

/**
 *
 * @author FQA
 */
public class Menu {

    EquipesController equipesController = new EquipesController();
    AtletasController atletasController = new AtletasController();
    private final Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    private void menuDisplay() {
        System.out.println("");
        System.out.println("Informe o número da opção que desejas executar: ");
        System.out.println("1 - Inserir uma nova equipe");
        System.out.println("2 - Inserir um novo atleta");
        System.out.println("3 - Exibir todos as equipes");
        System.out.println("4 - Exibir todos os atletas");
        System.out.println("5 - Exibir todos as equipes e seus respectivos atletas");
        System.out.println("6 - Exibir a equipe com atleta mais velho");
        System.out.println("Digite qualquer outro valor para sair");
        System.out.println("Sua opção: ");
    }

    public void menu() {
        String resposta;
        do {
            this.menuDisplay();
            resposta = scanner.nextLine();
            try {
                switch (resposta) {
                    case "0" -> {
                        System.out.println("Saindo...");
                    }
                    case "1" -> {
                        Equipe equipe = pedirEquipe();
                        equipesController.create(equipe);
                        System.out.println("Equipe criada com sucesso");
                    }

                    case "2" -> {
                        Atleta atleta;
                        try {
                            atleta = pedirAtleta();
                            atletasController.create(atleta);
                            System.out.println("Atleta criado com sucesso");
                        } catch (Exception ex) {
                            System.err.println(ex.getMessage());
                            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    case "3" -> {
                        List<Equipe> equipes = equipesController.listar();
                        equipes.forEach(equipe -> {
                            System.out.println(equipe);
                        });
                    }
                    case "4" -> {
                        List<Atleta> atletas = atletasController.listar();
                        atletas.forEach(atleta -> {
                            System.out.println(atleta);
                        });
                    }
                    case "5" -> {
                        List<Equipe> equipes = equipesController.listarComAtletas();
                        equipes.forEach(equipe -> {
                            System.out.println(equipe);
                        });
                    }
                    case "6" -> {
                        List<Equipe> equipes = equipesController.listarComAtletaMaisVelho();
                        equipes.forEach(equipe -> {
                            System.out.println(equipe);
                        });
                    }
                    default -> {
                        System.out.println("Comando não reconhecido");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println(ex.getMessage());
            }
        } while (!resposta.equals("0"));
    }

    private Equipe pedirEquipe() {
        Equipe equipe = new Equipe();
        System.out.println("Digite o nome da equipe: ");
        equipe.setNome(scanner.nextLine());
        return equipe;
    }

    private Atleta pedirAtleta() throws Exception {
        Atleta atleta = new Atleta();
        try {
            System.out.println("Digite o nome do atleta: ");
            atleta.setNome(scanner.nextLine());

            System.out.println("Digite o documento do atleta: ");
            atleta.setDocumento(scanner.nextLine());

            System.out.println("Digite a data de nascimento do atleta (dd/MM/aaaa): ");
            String dataString = scanner.nextLine();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate ld = LocalDate.parse(dataString, dtf);
            atleta.setDataNascimento(ld);

            System.out.println("Digite o peso do atleta: ");
            atleta.setPeso(Float.valueOf(scanner.nextLine()));

            System.out.println("Digite a altura do atleta: ");
            atleta.setAltura(Float.valueOf(scanner.nextLine()));

            System.out.println("Digite o sexo do atleta: 1) Masculino 2) Feminino");
            atleta.setSexo(scanner.nextLine().equals("1") ? Sexo.Masculino : Sexo.Feminino);

            System.out.println("Digite a cidade do atleta: ");
            atleta.setCidade(scanner.nextLine());

            System.out.println("Digite o estado do atleta: ");
            atleta.setEstado(scanner.nextLine());

            System.out.println("Digite o pais do atleta: ");
            atleta.setPais(scanner.nextLine());

            System.out.println("Digite se o atleta é titular: 1) Sim 2) Não");
            atleta.setTitular(scanner.nextLine().equals("1"));

            System.out.println("Digite a função do atleta: ");
            atleta.setFuncao(scanner.nextLine());

            List<Equipe> equipes = equipesController.listar();
            equipes.forEach(equipe -> {
                System.out.println(equipe);
            });
            System.err.println("Digite o código da equipe do atleta");
            int codEquipe = Integer.valueOf(scanner.nextLine());
            atleta.setCodEquipe(codEquipe);

        } catch (NumberFormatException ex) {
            throw new Exception("Erro ao receber dados do usuário");
        }
        return atleta;
    }
}
