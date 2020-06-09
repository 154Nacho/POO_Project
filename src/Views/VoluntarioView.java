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
        if (o instanceof String) System.out.print((String) o );
        else if (o instanceof Boolean){
            System.out.print("                                      Disponibilidade : ");
            if((boolean)o)System.out.println("Disponível");
            else System.out.println("Não Disponível");
            System.out.println("1 -> Escolher encomenda para entregar");
            System.out.println("2 -> Consultar encomendas do Sistema para entregar");
            System.out.println("3 -> Alterar disponibilidade");
            System.out.println("4 -> Mostrar classificação");
            System.out.println("S -> Logout");
        }
    }
}
