package br.com.fecaf.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    private String bank, server, user, password;
    public Connection conexao;

    public Conexao () {
        this.server = "LocalHost";
        this.bank = "sistema_automotivo";
        this.user = "admin";
        this.password = "admin";
    }

    public boolean connectDrive () {
        try {
            this.conexao = DriverManager.getConnection("jdbc:mysql://" + this.server + "/" + this.bank, this.user, this.password);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Connection getConnection () {
        boolean validaConexao = connectDrive();
        System.out.println(validaConexao);

        if (validaConexao) {
            return this.conexao;
        }
        return null;
    }
}