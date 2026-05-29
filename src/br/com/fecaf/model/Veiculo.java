package br.com.fecaf.model;

public class Veiculo {
    private int idVeiculos;
    private int ano;
    private String cor;
    private double preco;
    private int quilometragem;
    private String status;
    private String nomeMarca;
    private String nomeModelo;

    public int getIdVeiculos() {
        return idVeiculos;
    }

    public void setIdVeiculos(int idVeiculos) {
        this.idVeiculos = idVeiculos;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

    public String getNomeModelo() {
        return nomeModelo;
    }

    public void setNomeModelo(String nomeModelo) {
        this.nomeModelo = nomeModelo;
    }

    public void exibirInfo() {
        System.out.println("ID: " + idVeiculos +
                " | Marca: " + nomeMarca +
                " | Modelo: " + nomeModelo +
                " | Ano: " + ano +
                " | Cor: " + cor +
                " | Preço: R$" + preco +
                " | KM: " + quilometragem +
                " | Status: " + status);
    }
}