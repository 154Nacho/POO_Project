public class LinhaEncomenda {

    // Variáveis de instância
    private String codProduto;
    private String descricao;
    private int quantidade;
    private double valorUnitario;

    // Construtor por omissão
    public LinhaEncomenda() {
        this.codProduto = "";
        this.descricao = "";
        this.quantidade = 0;
        this.valorUnitario = 0;
    }

    // Construtor parametrizado
    public LinhaEncomenda(String codProduto, String descricao, int quantidade, double valorUnitario) {
        this.codProduto = codProduto;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    // Construtor de cópia
    public LinhaEncomenda(LinhaEncomenda l) {
        this.codProduto = l.getCodProduto();
        this.descricao = l.getDescricao();
        this.quantidade = l.getQuantidade();
        this.valorUnitario = l.getValorUnitario();
    }

    // Sets e Gets
    public String getCodProduto() {
        return this.codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return this.valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    // Equals
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        LinhaEncomenda l = (LinhaEncomenda) o;
        return (l.getCodProduto().equals(this.codProduto) &&
                l.getDescricao().equals(this.descricao) &&
                l.getQuantidade() == (this.quantidade) &&
                l.getValorUnitario() == (this.valorUnitario));
    }

    // toString
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.codProduto).append(",").append(this.descricao).append(",").append(this.quantidade).append(",").append(this.valorUnitario);
        return sb.toString();
    }

    // Clone
    public LinhaEncomenda clone() {
        return new LinhaEncomenda(this);
    }
}