package Views;

public class EmpresaView implements TrazAquiView {
    @Override
    public void show() {
        System.out.println("1 -> Escolher encomenda para entregar");
        System.out.println("2 -> Alterar disponibilidade");
        System.out.println("3 -> Calcular faturado num dado período");
        System.out.println("S -> Logout");
    }

    @Override
    public void show(Object o) {
        System.out.println((String) o );
    }
}
