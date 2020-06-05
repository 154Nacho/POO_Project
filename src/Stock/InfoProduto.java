package Stock;

public class InfoProduto {
    private String nome;
    private double preco;
    private double peso;

    /**
     * Construtor por omissão
     */
    public InfoProduto(){
        this.nome = "";
        this.preco = 0;
        this.peso = 0;
    }

    /**
     * Construtor por parâmetros
     * @param n que é a descrição do produto.
     * @param p que é o valor unitário.
     */
    public InfoProduto(String n , double p, double peso){
        this.nome = n;
        this.preco = p;
        this.peso = peso;
    }

    /**
     * Construtor por cópia
     * @param p que vai ser copiado.
     */
    public InfoProduto(InfoProduto p){
        this.nome = p.getNome();
        this.preco = p.getPreco();
        this.peso = p.getPeso();
    }

    /**
     * Método que devolve a descrição de um produto.
     * @return Descrição.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método que define a descrição de um produto.
     * @param nome que é a descrição.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método que devolve o valor unitário de um produto.
     * @return Valor unitário
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Método que define o valor unitário de um produto.
     * @param preco que é o valor unitário.
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Método que copia uma classe Stock.InfoProduto
     * @return Cópia.
     */
    public InfoProduto clone(){
        return  new InfoProduto(this);
    }
}
