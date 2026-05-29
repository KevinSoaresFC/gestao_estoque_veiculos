package br.com.fecaf.controller;

import br.com.fecaf.database.Conexao;
import br.com.fecaf.model.Veiculo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class VeiculoController {

    private int buscarOuCadastrarMarca(String nomeMarca, Statement statement) throws Exception {
        String query = "SELECT idMarcas FROM marcas WHERE nome = '" + nomeMarca + "'";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            int id = rs.getInt("idMarcas");
            rs.close();
            return id;
        }
        rs.close();

        String insert = "INSERT INTO marcas (nome) VALUES ('" + nomeMarca + "')";
        statement.executeUpdate(insert, Statement.RETURN_GENERATED_KEYS);
        ResultSet keys = statement.getGeneratedKeys();
        if (keys.next()) {
            int idGerado = keys.getInt(1);
            keys.close();
            return idGerado;
        }
        return -1;
    }

    private int buscarOuCadastrarModelo(String nomeModelo, int idMarca, Statement statement) throws Exception {
        String query = "SELECT idModelos FROM modelos WHERE nome = '" + nomeModelo + "' AND idMarcas = " + idMarca;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            int id = rs.getInt("idModelos");
            rs.close();
            return id;
        }
        rs.close();

        String insert = "INSERT INTO modelos (nome, idMarcas) VALUES ('" + nomeModelo + "', " + idMarca + ")";
        statement.executeUpdate(insert, Statement.RETURN_GENERATED_KEYS);
        ResultSet keys = statement.getGeneratedKeys();
        if (keys.next()) {
            int idGerado = keys.getInt(1);
            keys.close();
            return idGerado;
        }
        return -1;
    }

    public void consultarVeiculos() {
        try {
            Conexao conexao = new Conexao();
            Connection objConnection = conexao.getConnection();
            Statement statement = objConnection.createStatement();

            String queryConsulta = "SELECT v.*, mo.nome AS nome_modelo, ma.nome AS nome_marca " +
                    "FROM veiculos v " +
                    "INNER JOIN modelos mo ON v.idModelos = mo.idModelos " +
                    "INNER JOIN marcas ma ON mo.idMarcas = ma.idMarcas";

            ResultSet resultset = statement.executeQuery(queryConsulta);

            while (resultset.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setIdVeiculos(resultset.getInt("idVeiculos"));
                veiculo.setAno(resultset.getInt("ano"));
                veiculo.setCor(resultset.getString("cor"));
                veiculo.setPreco(resultset.getDouble("preco"));
                veiculo.setQuilometragem(resultset.getInt("quilometragem"));
                veiculo.setStatus(resultset.getString("status"));
                veiculo.setNomeModelo(resultset.getString("nome_modelo"));
                veiculo.setNomeMarca(resultset.getString("nome_marca"));

                veiculo.exibirInfo();
            }
            resultset.close();
            statement.close();
            objConnection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void consultarVeiculosComFiltros(String marca, String modelo, double precoMax, int ano, String status) {
        try {
            Conexao conexao = new Conexao();
            Connection objConnection = conexao.getConnection();
            Statement statement = objConnection.createStatement();

            String queryConsulta = "SELECT v.*, mo.nome AS nome_modelo, ma.nome AS nome_marca " +
                    "FROM veiculos v " +
                    "INNER JOIN modelos mo ON v.idModelos = mo.idModelos " +
                    "INNER JOIN marcas ma ON mo.idMarcas = ma.idMarcas WHERE 1=1";

            if (marca != null && !marca.isEmpty()) {
                queryConsulta += " AND ma.nome LIKE '%" + marca + "%'";
            }
            if (modelo != null && !modelo.isEmpty()) {
                queryConsulta += " AND mo.nome LIKE '%" + modelo + "%'";
            }
            if (precoMax > 0) {
                queryConsulta += " AND v.preco <= " + precoMax;
            }
            if (ano > 0) {
                queryConsulta += " AND v.ano = " + ano;
            }
            if (status != null && !status.isEmpty()) {
                queryConsulta += " AND v.status LIKE '%" + status + "%'";
            }

            ResultSet resultset = statement.executeQuery(queryConsulta);

            while (resultset.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setIdVeiculos(resultset.getInt("idVeiculos"));
                veiculo.setAno(resultset.getInt("ano"));
                veiculo.setCor(resultset.getString("cor"));
                veiculo.setPreco(resultset.getDouble("preco"));
                veiculo.setQuilometragem(resultset.getInt("quilometragem"));
                veiculo.setStatus(resultset.getString("status"));
                veiculo.setNomeModelo(resultset.getString("nome_modelo"));
                veiculo.setNomeMarca(resultset.getString("nome_marca"));

                veiculo.exibirInfo();
            }
            resultset.close();
            statement.close();
            objConnection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean cadastrarVeiculo(Veiculo veiculo) {
        try {
            Conexao conexao = new Conexao();
            Connection objConnection = conexao.getConnection();
            Statement statement = objConnection.createStatement();

            int idMarca = buscarOuCadastrarMarca(veiculo.getNomeMarca(), statement);
            int idModelo = buscarOuCadastrarModelo(veiculo.getNomeModelo(), idMarca, statement);

            String queryCreate = "INSERT INTO veiculos (ano, cor, preco, quilometragem, status, idModelos) VALUES ("
                    + veiculo.getAno() + ", '"
                    + veiculo.getCor() + "', "
                    + veiculo.getPreco() + ", "
                    + veiculo.getQuilometragem() + ", '"
                    + veiculo.getStatus() + "', "
                    + idModelo + ")";

            statement.executeUpdate(queryCreate);
            statement.close();
            objConnection.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean atualizarVeiculo(int id, double preco, int km, String status) {
        try {
            Conexao conexao = new Conexao();
            Connection objConnection = conexao.getConnection();
            Statement statement = objConnection.createStatement();

            String queryUpdate = "UPDATE veiculos SET preco = " + preco +
                    ", quilometragem = " + km +
                    ", status = '" + status +
                    "' WHERE idVeiculos = " + id;

            statement.executeUpdate(queryUpdate);
            statement.close();
            objConnection.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deletarVeiculo(int id) {
        try {
            Conexao conexao = new Conexao();
            Connection objConnection = conexao.getConnection();
            Statement statement = objConnection.createStatement();

            String queryDelete = "DELETE FROM veiculos WHERE idVeiculos = " + id;

            statement.executeUpdate(queryDelete);
            statement.close();
            objConnection.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}