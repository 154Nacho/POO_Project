package Views;

public class UtilizadorView implements TrazAquiView{

    @Override
    public void show() {
        System.out.println("1 -> Fazer nova encomenda");
        System.out.println("2 -> Ver encomendas feitas");
        System.out.println("3 -> Ver encomendas em espera");
        System.out.println("4 -> Ver Lojas disponiveis");
        System.out.println("5 -> Ver produtos de uma dada loja");
        System.out.println("S -> Logout");
    }

    @Override
    public void show(Object o) {
        System.out.print((String) o );
    }
}
