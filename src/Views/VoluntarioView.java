package Views;

public class VoluntarioView implements TrazAquiView {
    @Override
    public void show() {
        System.out.println("1 -> Escolher encomenda para entregar");
        System.out.println("2 -> Consultar encomendas do Sistema para entregar");
        System.out.println("3 -> Alterar disponibilidade");
        System.out.println("4 -> Mostrar classificação");
        System.out.println("S -> Logout");
    }

    @Override
    public void show(Object o) {
        System.out.print((String) o );
    }
}
