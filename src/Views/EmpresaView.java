package Views;

public class EmpresaView implements TrazAquiView {
    @Override
    public void show() {
        System.out.println("1 -> Escolher encomenda para entregar");
        System.out.println("2 -> Consultar encomendas no Sistema para entregar");
        System.out.println("3 -> Alterar disponibilidade");
        System.out.println("4 -> Calcular faturado num dado período");
        System.out.println("5 -> Mostrar classificação");
        System.out.println("6 -> Top10 Transportadoras");
        System.out.println("G -> Gravar");
        System.out.println("S -> Logout");
    }

    @Override
    public void show(Object o) {
        System.out.print((String) o );
    }
}
