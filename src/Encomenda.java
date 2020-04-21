import java.util.Map;
import java.util.TreeMap;

public class Encomenda {

    // Variáveis de instância
    private String codEncomenda;
    private String codUtilizador;
    private String codLoja;
    private double peso;
    private Map<String,LinhaEncomenda> encomendas;

    // Contrutor por omissão
    public Encomenda() {
        this.codEncomenda = "";
        this.codUtilizador = "";
        this.codLoja = "";
        this.peso = 0;
        this.encomendas = new TreeMap<>();
    }

    // Construtor parametrizado
    public Encomenda(String codEncomenda, String codUtilizador, String codLoja, String peso, Map<String,LinhaEncomenda> encomendas) {
        this.codEncomenda = codEncomenda;
        this.codUtilizador = codUtilizador;
        this.codLoja = codLoja;
        this.peso = peso;
        setEncomendas(encomendas);
    }

    // Construtor de cópia
    public Encomenda(Encomenda e) {
        this.codEncomenda = e.getCodEncomenda();
        this.codUtilizador = e.getCodUtilizador();
        this.codLoja = e.getCodLoja();
        this.peso = e.getPeso();
        setEncomendas(e.getEncomendas());
    }

    // Sets e Gets

}