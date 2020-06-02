import jdk.jshell.execution.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Loja extends Utilizador {

    private boolean informa_sobre_loja;
    private List<Encomenda> encomendas;
    private int qtd_pessoas_fila;
    private double tempo_médio_atendimento;
    private boolean tem_encomendas;

    /**
     *   Construtor por omissão
     */
    public Loja(){
        super();
        this.qtd_pessoas_fila = 0;
        this.encomendas = new ArrayList<>();
        this.informa_sobre_loja = false;
        this.tem_encomendas = false;
        this.tempo_médio_atendimento = 0;
    }
    /**
     *   Construtor com passagem de argumentos
     */
    public Loja(String cL, String nL,String password, GPS l, int qt, List<Encomenda> ec, boolean informa, double t, boolean tem_encomendas){
        super(cL,nL,password,l);
        this.qtd_pessoas_fila = qt;
        setEncomendas(ec);
        this.informa_sobre_loja = informa;
        this.tempo_médio_atendimento = t;
        this.tem_encomendas = tem_encomendas;
    }

    /**
     *   Construtor por clonagem
     */
    public Loja(Loja l){
        super(l);
        this.qtd_pessoas_fila = l.getQtd_pessoas_fila();
        this.encomendas = l.getEncomendas();
        this.informa_sobre_loja = l.isInforma_sobre_loja();
        this.tempo_médio_atendimento = l.getTempo_médio_atendimento();
        this.tem_encomendas = l.isTem_encomendas();
    }
    //Getters

    /**
     *   Getter da quantidade de pessoas na fila da Loja
     */
    public int getQtd_pessoas_fila() {
        return qtd_pessoas_fila;
    }
    /**
     *   Getter das encomendas já realizadas pela Loja
     */
    public List<Encomenda> getEncomendas(){
        return this.encomendas.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    /**
     * Método que averigua se uma loja informa ou não sobre a sua fila de espera.
     * @return boolean.
     */
    public boolean isInforma_sobre_loja() {
        return informa_sobre_loja;
    }

    /**
     * Método que devolve o tempo médio de atendimento da loja.
     * @return Tempo médio de atendimento.
     */
    public double getTempo_médio_atendimento() {
        return tempo_médio_atendimento;
    }

    /**
     * Método que averigua se uma loja tem encomendas para entregar.
     * @return boolean.
     */
    public boolean isTem_encomendas() {
        return tem_encomendas;
    }

    //Setters

    /**
     *   setter da quantidade de pessoas na fila da Loja
     */
    public void setQtd_pessoas_fila(int qtd) {
        this.qtd_pessoas_fila = qtd;
    }
    /**
     *   Setter das encomendas já realizadas pela Loja
     */
    public void setEncomendas(List<Encomenda> e){
        this.encomendas = new ArrayList<>();
        for(Encomenda ec : e){
            this.encomendas.add(ec.clone());
        }
    }

    /**
     * Método que define se uma loja informa ou não sobre a sua fila de espera.
     * @param informa_sobre_loja que diz se informa ou não.
     */
    public void setInforma_sobre_loja(boolean informa_sobre_loja) {
        this.informa_sobre_loja = informa_sobre_loja;
    }

    /**
     * Método que define o tempo médio de atendimento na loja.
     * @param tempo_médio_atendimento que é o tempo médio.
     */
    public void setTempo_médio_atendimento(double tempo_médio_atendimento) {
        this.tempo_médio_atendimento = tempo_médio_atendimento;
    }

    /**
     * Método que define se uma loja tem encomendas para entregar.
     * @param tem_encomendas que diz se tem ou não.
     */
    public void setTem_encomendas(boolean tem_encomendas) {
        this.tem_encomendas = tem_encomendas;
    }

    //Clone, toString, equals

    /**
     *   Cloner da Loja
     */
    public Loja clone(){
        return new Loja(this);
    }

    /**
     *   Conversor para String das informações da Loja
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
     *   Comparador de igualdade
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