package Stock;

public class InfoProduto {
    private String nome;
    private double preco;

    /**
     * Construtor por omissão
     */
    public InfoProduto(){
        this.nome = "";
        this.preco = 0;
    }

    /**
     * Construtor por parâmetros
     * @param n que é a descrição do produto.
     * @param p que é o valor unitário.
     */
    public InfoProduto(String n , double p){
        this.nome = n;
        this.preco = p;
    }

    /**
     * Construtor por cópia
     * @param p que vai ser copiado.
     */
    public InfoProduto(InfoProduto p){
        this.nome = p.getNome();
        this.preco = p.getPreco();
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

    /**
     * Método que copia uma classe Stock.InfoProduto
     * @return Cópia.
     */
    public InfoProduto clone(){
        return  new InfoProduto(this);
    }
}
