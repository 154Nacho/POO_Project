package Views;

public class LoginView implements TrazAquiView {

    /**
     * Método que imprime uma mensagem pré definida.
     */
    @Override
    public void show() {
        System.out.println("1 -> Login");
        System.out.println("2 -> Register");
        System.out.println("L -> Load object file");
        System.out.println("S -> Exit");
    }

    /**
     * Método que imprime um Objeto como uma mensagem.
     * @param o Objeto a avaliar.
     */
    @Override
    public void show(Object o) {
        System.out.print((String) o );
    }
}
