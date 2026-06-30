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
        return scanner.nextLine();
    }

    private Conta menuLogin(Biblioteca biblioteca){
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

            if (escolha == 1) {
                System.out.println("Insira seu Email (ou digite 'sair' para voltar):");
                String email = lerString();

                if (email.equalsIgnoreCase("sair")) {
                    continue;
                }

                Conta contaEncontrada = biblioteca.buscarContaPorEmail(email);

                if (contaEncontrada == null) {
                    System.out.println("Erro: Nenhuma conta cadastrada com este e-mail.\n");
                    continue;
                }
                System.out.println("Insira sua Senha:");
                String senha = lerString();

                if (contaEncontrada.getSenha().equals(senha)) {
                    System.out.println("Login realizado com sucesso! Bem-vindo, " + contaEncontrada.getNome() + " \n");
                    return contaEncontrada;
                }
                System.out.println("Senha incorreta! Retornando ao menu.\n");
            }

            if(escolha == 2) {
                String nome, email, senha;

                while (true) {
                    System.out.println("Inserir um nome:");
                    nome = lerString();
                    if (nomeValido(nome)) break;
                    System.out.println("Nome inválido! Não pode ser vazio.");
                }

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

    private void menuCliente(Biblioteca biblioteca, Cliente cliente) {
        while(true) {
            System.out.println("\n--- ÁREA DO CLIENTE ---");
            System.out.println("1: Ver livros.");
            System.out.println("2: Alugar Livro.");
            System.out.println("3: Devolver Livro.");
            System.out.println("4: Mostrar Histórico.");
            System.out.println("5: Sair.");
            int escolha = lerEscolha();
            System.out.println("---");

            if(escolha == 5) {
                return; // CORRIGIDO: Retorna para fechar este menu e voltar ao fluxo principal
            }
            if(escolha < 1 || escolha > 5) {
                System.out.println("Opção inválida! Tente novamente.");
                continue;
            }
            if(escolha == 1) {
                biblioteca.mostrarLivros();
                while(true) {
                    System.out.println("---");
                    System.out.println("Digite '1' para voltar:");
                    int escolha2 = lerEscolha();
                    if (escolha2 == 1) { // CORRIGIDO: Validando a variável certa
                        break;
                    }
                }
            }
            if(escolha == 2) {
                while(true) {
                    System.out.println("Digite o ID do livro que você deseja alugar:");
                    int idLivro = lerEscolha();
                    if(cliente.alugarLivro(idLivro, biblioteca)) {
                        System.out.println("Livro ID " + idLivro + " adicionado ao histórico.");
                        System.out.println("---");
                        break;
                    }
                }
            }
            if(escolha == 3) {
                while(true) {
                    System.out.println("Digite o ID do livro que você deseja devolver:");
                    int idLivro = lerEscolha();
                    if(cliente.devolverLivro(idLivro, biblioteca)) {
                        System.out.println("Livro ID " + idLivro + " devolvido.");
                        System.out.println("---");
                        break;
                    }
                }
            }
            if(escolha == 4) {
                cliente.mostrarHistorico();
                while(true) {
                    System.out.println("---");
                    System.out.println("Digite '1' para voltar:");
                    int escolha2 = lerEscolha();
                    if (escolha2 == 1) { // CORRIGIDO: Validando a variável certa
                        break;
                    }
                }
            }
        }
    }

    private void menuAdministrador(Biblioteca biblioteca, Administrador administrador) {
        while(true) {
            System.out.println("\n--- ÁREA DO ADMINISTRADOR ---");
            System.out.println("1: Ver livros.");
            System.out.println("2: Adicionar Livro.");
            System.out.println("3: Remover Livro.");
            System.out.println("4: Sair.");
            int escolha = lerEscolha();
            System.out.println("---");

            if (escolha == 4) {
                return; // CORRIGIDO: Retorna para evitar loop recursivo
            }
            if(escolha == 1) {
                biblioteca.mostrarLivros();
                while(true) {
                    System.out.println("---");
                    System.out.println("Digite '1' para voltar:");
                    int escolha2 = lerEscolha();
                    if (escolha2 == 1) { // CORRIGIDO: Validando a variável certa
                        break;
                    }
                }
            }
            if(escolha == 2) {
                while(true) {
                    System.out.println("--- Adicionando um novo livro a biblioteca ---");
                    System.out.println("Escreva o nome do livro:");
                    String nome = lerString();
                    System.out.println("Escreva o nome do autor:");
                    String nomeAutor = lerString();
                    System.out.println("Digite o ano de lançamento do livro:");
                    int ano = lerEscolha();
                    System.out.println("Escreva o gênero do livro:");
                    String genero = lerString();

                    if(administrador.adicionarLivro(biblioteca, nome, nomeAutor, ano, genero)) {
                        System.out.println("Livro adicionado à biblioteca!");
                        System.out.println("---");
                        break;
                    }
                }
            }
            if(escolha == 3) {
                while(true) {
                    System.out.println("É importante lembrar que não é possível remover um livro que esteja alugado no momento!");
                    System.out.println("Digite o ID do livro que você deseja remover, ou digite '0' para sair:");
                    int idLivro = lerEscolha();
                    if(idLivro == 0) {
                        System.out.println("---");
                        break;
                    }
                    if(administrador.removerLivro(biblioteca, idLivro)) {
                        System.out.println("Livro removido da biblioteca!");
                        System.out.println("---");
                        break;
                    }
                }
            }
        }
    }

    public void menuBiblioteca(Biblioteca biblioteca) {
        // Colocamos o fluxo principal dentro de um loop para que, quando um usuário deslogar (return),
        // ele volte automaticamente para a tela de login até que decida encerrar o programa (opção 3 do login).
        while (true) {
            Conta conta = menuLogin(biblioteca);
            if (conta == null) {
                System.out.println("Encerrando o programa...");
                return;
            }

            if (conta instanceof Cliente) {
                Cliente clienteLogado = (Cliente) conta;
                menuCliente(biblioteca, clienteLogado);
            }
            else if (conta instanceof Administrador) {
                Administrador adminLogado = (Administrador) conta;
                menuAdministrador(biblioteca, adminLogado);
            }
        }
    }
}
