package org.example;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);

    public boolean nomeValido(String nome) {
        return nome != null && !nome.trim().isEmpty();
    }

    public boolean senhaValida(String senha) {
        return senha != null && !senha.trim().isEmpty() && senha.length() > 7;
    }

    public int lerEscolha() {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, digite um número válido!");
            scanner.next();
        }
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do Enter
        return escolha;
    }

    public String lerString() {
        return scanner.nextLine(); // Apenas lê a String digitada
    }

    public Conta menuLogin(Biblioteca biblioteca){
        while(true) {
            System.out.println("--- Login ---");
            System.out.println("1: Entrar (Login)");
            System.out.println("2: Criar Conta");
            System.out.println("3: Sair");
            System.out.print("Digite sua escolha: ");
            int escolha = lerEscolha();
            System.out.println("---");

            if (escolha == 3) {
                return null;
            }
            if (escolha < 1 || escolha > 2) {
                System.out.println("Opção inválida! Tente novamente.");
                continue;
            }

            // --- OPÇÃO 1: LOGIN ---
            if (escolha == 1) {
                System.out.println("Insira seu Email (ou digite 'sair' para voltar):");
                String email = lerString();
                if(email.equalsIgnoreCase("sair")) continue;

                Cliente clienteEncontrado = biblioteca.buscarClientePorEmail(email);
                if (clienteEncontrado == null) {
                    System.out.println("Erro: Nenhum cliente cadastrado com este e-mail.\n");
                    continue; // Volta para o menu principal do Login
                }

                System.out.println("Insira sua Senha:");
                String senha = lerString();

                if (clienteEncontrado.getSenha().equals(senha)) {
                    System.out.println("Login realizado com sucesso! Bem-vindo, " + clienteEncontrado.getNome() + "\n");
                    return clienteEncontrado;
                } else {
                    System.out.println("Senha incorreta! Retornando ao menu.\n");
                }
            }

            // --- OPÇÃO 2: CADASTRO ---
            if(escolha == 2) {
                String nome, email, senha;

                // Valida Nome
                while (true) {
                    System.out.println("Inserir um nome:");
                    nome = lerString();
                    if (nomeValido(nome)) break;
                    System.out.println("Nome inválido! Não pode ser vazio.");
                }

                // Valida Email
                while (true) {
                    System.out.println("Inserir seu e-mail:");
                    email = lerString();
                    if (!nomeValido(email)) {
                        System.out.println("E-mail inválido!");
                        continue;
                    }
                    if (biblioteca.buscarClientePorEmail(email) != null) {
                        System.out.println("Este e-mail já está cadastrado!");
                        continue;
                    }
                    break;
                }

                // Valida Senha
                while (true) {
                    System.out.println("Crie uma senha (mínimo 8 caracteres):");
                    senha = lerString();
                    if (senhaValida(senha)) break;
                    System.out.println("Senha inválida! Deve conter no mínimo 8 caracteres.");
                }

                biblioteca.cadastrarCliente(nome, email, senha);
                System.out.println("Conta criada com sucesso!");
                return biblioteca.buscarClientePorEmail(email);
            }
        }
    }

    public void menuCliente() {

    }

    public void menuAdministrador() {

    }

    public void menuBiblioteca(Biblioteca biblioteca) {
        Conta conta = menuLogin(biblioteca);
        if (conta == null) {
            System.out.println("Encerrando o programa...");
            return;
        }
        
        if (conta instanceof Cliente) {
            // Faz o "Casting" (avisa o Java para tratar a 'conta' especificamente como Cliente)
            Cliente clienteLogado = (Cliente) conta;

            System.out.println("\n--- ÁREA DO CLIENTE ---");
            // Aqui você chama o menu específico do cliente, ex:
            // menuCliente(clienteLogado, biblioteca);
        }
        else if (conta instanceof Administrador) {
            Administrador adminLogado = (Administrador) conta;

            System.out.println("\n--- ÁREA DO ADMINISTRADOR ---");
            // Aqui você chama o menu específico do administrador, ex:
            // menuAdmin(adminLogado, biblioteca);
        }
    }
}
