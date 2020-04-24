import jdk.jshell.execution.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Loja extends Utilizador {

    private List<Encomenda> encomendas;
    private int qtd_pessoas_fila;

    /**
     * @brief Construtor por omissão
     */
    public Loja(){
        super();
        this.qtd_pessoas_fila = 0;
        this.encomendas = new ArrayList<>();
    }
    /**
     * @brief Construtor com passagem de argumentos
     */
    public Loja(String cL, String nL,String password, GPS l, int qt, List<Encomenda> ec){
        super(cL,nL,password,l);
        this.qtd_pessoas_fila = qt;
        setEncomendas(ec);
    }

    /**
     * @brief Construtor por clonagem
     */
    public Loja(Loja l){
        super(l);
        this.qtd_pessoas_fila = l.getQtd_pessoas_fila();
        setEncomendas(l.getEncomendas());
    }
    //Getters

    /**
     * @brief Getter da quantidade de pessoas na fila da Loja
     */
    public int getQtd_pessoas_fila() {
        return qtd_pessoas_fila;
    }
    /**
     * @brief Getter das encomendas já realizadas pela Loja
     */
    public List<Encomenda> getEncomendas(){
        return this.encomendas.stream().map(Encomenda::clone).collect(Collectors.toList());
    }


    //Setters

    /**
     * @brief setter da quantidade de pessoas na fila da Loja
     */
    public void setQtd_pessoas_fila(int qtd) {
        this.qtd_pessoas_fila = qtd;
    }
    /**
     * @brief Setter das encomendas já realizadas pela Loja
     */
    public void setEncomendas(List<Encomenda> e){
        this.encomendas = new ArrayList<>();
        for(Encomenda ec : e){
            this.encomendas.add(ec.clone());
        }
    }

    //Clone, toString, equals

    /**
     * @brief Cloner da Loja
     */
    public Loja clone(){
        return new Loja(this);
    }

    /**
     * @brief Conversor para String das informações da Loja
     */
    public String toStringShow(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nNome da Loja: ").append(this.getNome());
        sb.append("\nCódigo da Loja: ").append(this.getCodigo());
        sb.append("\nLocalização da Loja: ").append(this.getGps().toString());
        sb.append("\nEncomendas: ").append(this.encomendas);
        return sb.toString();
    }

    public String toString(){
        return "Loja:"+this.getCodigo()+","+this.getNome()+","+this.getGps().toString();
    }
    /**
     * @brief Comparador de igualdade
     */
    public boolean equals(Object obj){
        if(obj==this) return true;
        if(obj==null || obj.getClass() != this.getClass()) return false;
        Loja l = (Loja) obj;
        return (this.getCodigo().equals(l.getCodigo())
                && this.getNome().equals(l.getNome())
                && this.getGps().equals(l.getGps())
                && this.qtd_pessoas_fila == l.getQtd_pessoas_fila()
                && this.encomendas.equals(l.getEncomendas()));
    }
}