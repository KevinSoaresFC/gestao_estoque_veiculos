package br.com.fecaf.controller;

import br.com.fecaf.model.Veiculo;

import java.util.Locale;
import java.util.Scanner;

public class Menu {

    public void executarMenu() {

        boolean exit = false;
        VeiculoController veiculoController = new VeiculoController();
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        while (!exit) {
            System.out.println("/=====================================/");
            System.out.println("/     SISTEMA DE GESTÃO AUTOMOTIVA    /");
            System.out.println("/=====================================/");
            System.out.println("/ 1 - Listar Todos os Veículos        /");
            System.out.println("/ 2 - Filtrar Busca Avançada          /");
            System.out.println("/ 3 - Cadastrar Novo Veículo          /");
            System.out.println("/ 4 - Atualizar Dados (Preço/KM/St)   /");
            System.out.println("/ 5 - Remover Veículo do Estoque      /");
            System.out.println("/ 6 - Sair do Sistema                 /");
            System.out.println("/=====================================/");

            System.out.println("Escolha uma Opção:");
            int optionUser = scanner.nextInt();
            scanner.nextLine();

            switch (optionUser) {
                case 1:
                    veiculoController.consultarVeiculos();
                    break;

                case 2:
                    System.out.print("Marca (Enter para ignorar): ");
                    String fMarca = scanner.nextLine();
                    System.out.print("Modelo (Enter para ignorar): ");
                    String fModelo = scanner.nextLine();
                    System.out.print("Preço Máximo (0 para ignorar): ");
                    double fPreco = scanner.nextDouble();
                    System.out.print("Ano (0 para ignorar): ");
                    int fAno = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Status (Enter para ignorar): ");
                    String fStatus = scanner.nextLine();

                    veiculoController.consultarVeiculosComFiltros(fMarca, fModelo, fPreco, fAno, fStatus);
                    break;

                case 3:
                    Veiculo novoVeiculo = new Veiculo();

                    System.out.print("Marca (ex: Volkswagen): ");
                    novoVeiculo.setNomeMarca(scanner.nextLine());
                    System.out.print("Modelo (ex: Gol): ");
                    novoVeiculo.setNomeModelo(scanner.nextLine());
                    System.out.print("Ano: ");
                    novoVeiculo.setAno(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Cor: ");
                    novoVeiculo.setCor(scanner.nextLine());
                    System.out.print("Preço: ");
                    novoVeiculo.setPreco(scanner.nextDouble());
                    System.out.print("Quilometragem: ");
                    novoVeiculo.setQuilometragem(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Status: ");
                    novoVeiculo.setStatus(scanner.nextLine());

                    boolean validaCadastro = veiculoController.cadastrarVeiculo(novoVeiculo);

                    if (validaCadastro) {
                        System.out.println("Veículo cadastrado com sucesso!!");
                    } else {
                        System.out.println("Veículo não cadastrado... ");
                    }
                    break;

                case 4:
                    veiculoController.consultarVeiculos();
                    System.out.println("Informe o ID do Veículo para atualizar:");
                    int idUpdate = scanner.nextInt();
                    System.out.print("Novo Preço: ");
                    double novoPreco = scanner.nextDouble();
                    System.out.print("Nova KM: ");
                    int novaKm = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Novo Status: ");
                    String novoStatus = scanner.nextLine();

                    boolean validaUpdate = veiculoController.atualizarVeiculo(idUpdate, novoPreco, novaKm, novoStatus);

                    if (validaUpdate) {
                        System.out.println("Veículo atualizado com sucesso!!!");
                    } else {
                        System.out.println("Veículo não atualizado!!");
                    }
                    break;

                case 5:
                    veiculoController.consultarVeiculos();
                    System.out.println("Informe o ID do Veículo:");
                    int idDelete = scanner.nextInt();

                    boolean validaDelete = veiculoController.deletarVeiculo(idDelete);

                    if (validaDelete) {
                        System.out.println("Veículo removido com sucesso!!!");
                    } else {
                        System.out.println("Veículo não removido!!");
                    }
                    break;

                case 6:
                    exit = true;
                    break;

                default:
                    System.out.println("Escolha uma opção válida!!!");
            }
        }
    }
}