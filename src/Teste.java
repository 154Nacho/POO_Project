import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        GPS a = new GPS(51.5, 0);
        GPS b = new GPS(38.8, -77.1);
        System.out.println(a.distanceTo(b));
        TrazAqui trazAqui = new TrazAqui();
        //sout
        Scanner sc = new Scanner(System.in);
        System.out.println("1 - Registar um user");
        System.out.println("2 - Login");

        Parser p = new Parser();
        p.parse(trazAqui);

        String username;
        String password;
        String nome = "";
        String input = sc.nextLine();


        if(input.equals("1")){
            System.out.print("Nome: ");
            nome = sc.nextLine();
            System.out.print("Username: ");
            username = sc.nextLine();
            System.out.print("Password: ");
            password = sc.nextLine();
            trazAqui.addUtilizador(new Utilizador(username,nome,password,new GPS()));
        }
        else if(input.equals("2")){
            System.out.print("Username: ");
            username = sc.nextLine();
            System.out.print("Password: ");
            password = sc.nextLine();
            if(trazAqui.checkLoggin(username,password))
                trazAqui.setLogged(1);
        }







        trazAqui.imprimeUtilizadores();
        trazAqui.imprimeVoluntarios();
    }
}
