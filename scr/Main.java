package org.example;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Biblioteca biblioteca = new Biblioteca();
        Conta adm = new Administrador("ADM", "ADM@gmail.com", "12345678");
        menu.menuBiblioteca(biblioteca);
    }
}
