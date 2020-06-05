package Views;

public class LojaView implements TrazAquiView {
    @Override
    public void show() {
        System.out.println("1 -> Ver encomendas prontas a entregar");
        System.out.println("2 -> Consultar stock");
        System.out.println("S -> Logout");
    }

    @Override
    public void show(Object o) {
        System.out.print((String) o );
    }
}
