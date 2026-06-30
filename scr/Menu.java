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

    private void menuCliente(Biblioteca biblioteca, Cliente cliente) {
        while(true) {
            System.out.println("1: Ver livros.");
            System.out.println("2: Alugar Livro.");
            System.out.println("3: Devolver Livro.");
            System.out.println("4: Mostrar Histórico.");
            System.out.println("5: Sair.");
            int escolha = lerEscolha();
            System.out.println("---");
            if(escolha == 5) {
                menuLogin(biblioteca);
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
                    if (escolha == 1) {
                        break;
                    }
                    continue;
                }
            }
            if(escolha == 2) {
                while(true) {
                    System.out.println("Digite o ID do livro que você deseja alugar:");
                    int idLivro = lerEscolha();
                    if(cliente.alugarLivro(idLivro, biblioteca)) {
                        System.out.println(biblioteca.buscarLivro(idLivro) + " adicionado ao histórico.");
                        System.out.println("---");
                        break;
                    }
                    continue;
                }
                continue;
            }
            if(escolha == 3) {
                while(true) {
                    System.out.println("Digite o livro que você deseja devolver:");
                    int idLivro = lerEscolha();
                    if(cliente.devolverLivro(idLivro, biblioteca)) {
                        System.out.println(biblioteca.buscarLivro(idLivro) + " devolvido.");
                        System.out.println("---");
                        break;
                    }
                    continue;
                }
                continue;
            }
            if(escolha == 4) {
                cliente.mostrarHistorico();
                while(true) {
                    System.out.println("---");
                    System.out.println("Digite '1' para voltar:");
                    int escolha2 = lerEscolha();
                    if (escolha == 1) {
                        break;
                    }
                    continue;
                }
            }
        }
    }

    private void menuAdministrador(Biblioteca biblioteca, Administrador administrador) {
        while(true) {
            System.out.println("1: Ver livros.");
            System.out.println("2: Adicionar Livro.");
            System.out.println("3: Remover Livro.");
            System.out.println("4: Sair.");
            int escolha = lerEscolha();
            System.out.println("---");
            if (escolha == 4) {
                menuLogin(biblioteca);
            }
            if(escolha == 1) {
                biblioteca.mostrarLivros();
                while(true) {
                    System.out.println("---");
                    System.out.println("Digite '1' para voltar:");
                    int escolha2 = lerEscolha();
                    if (escolha == 1) {
                        break;
                    }
                    continue;
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
                        System.out.println("Livro adicionado a biblioteca!");
                        System.out.println("---");
                        break;
                    }
                    continue;
                }
            }
            if(escolha == 3) {
                while(true) {
                    System.out.println("é importante lembrar que não é possivel remover um livro que esteja alugado no momento!");
                    System.out.println("Digite o ID do livro que você deseja remover, ou digite '0' para sair:");
                    int idLivro = lerEscolha();
                    if(administrador.removerLivro(biblioteca, idLivro)) {
                        System.out.println("Livro removido da biblioteca!");
                        System.out.println("---");
                        break;
                    }
                    if(idLivro == 0) {
                        System.out.println("---");
                        break;
                    }
                    continue;
                }
            }
        }
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
            menuCliente(biblioteca, clienteLogado);
        }
        else if (conta instanceof Administrador) {
            Administrador adminLogado = (Administrador) conta;

            System.out.println("\n--- ÁREA DO ADMINISTRADOR ---");
            menuAdministrador(biblioteca, adminLogado);
        }
    }
}
