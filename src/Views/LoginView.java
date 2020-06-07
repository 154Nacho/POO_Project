package Views;

public class LoginView implements TrazAquiView {
    @Override
    public void show() {
        System.out.println("1 -> Login");
        System.out.println("2 -> Register");
        System.out.println("L -> Load object file");
        System.out.println("S -> Exit");
    }

    @Override
    public void show(Object o) {
        System.out.print((String) o );
    }
}
