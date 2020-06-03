import jdk.jshell.execution.Util;

import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        GPS a = new GPS(51.5, 0);
        GPS b = new GPS(38.8, -77.1);
        System.out.println(a.distanceTo(b));
        TrazAqui trazAqui = new TrazAqui();
        //sout
        Scanner sc = new Scanner(System.in);
        String username;
        String password;
        String nome = "";
        Parser p = new Parser();
        p.parse(trazAqui);

        label:
        while (true) {
            System.out.println("1 - Registar um user");
            System.out.println("2 - Interfaces.Login");
            System.out.println("3 - Guardar");
            System.out.println("4 - Ver informações");
            System.out.println("0 - exit");
            String input = sc.nextLine();
            switch (input) {
                case "1":
                    System.out.println("1 - Utilizador");
                    System.out.println("2 - Voluntário");
                    System.out.println("3 - Loja");
                    System.out.println("4 - Transportadora");
                    input = sc.nextLine();

                    System.out.print("Nome: ");
                    nome = sc.nextLine();


                    System.out.print("Username: ");
                    username = sc.nextLine();

                    switch (input) {
                        case "1":
                            while(!username.startsWith("u") || trazAqui.checkUser(username)){
                                System.out.println("Username inválido");
                                System.out.print("Username: ");
                                username = sc.nextLine();
                            }
                            System.out.print("Password: ");
                            password = sc.nextLine();
                            trazAqui.addUtilizador(new Utilizador(username, nome, password, new GPS()));
                            break ;

                        case "2":
                            while(!username.startsWith("v") || trazAqui.checkUser(username)){
                                System.out.println("Username inválido");
                                System.out.print("Username: ");
                                username = sc.nextLine();
                            }
                            System.out.print("Password: ");
                            password = sc.nextLine();
                            trazAqui.addUtilizador(new Voluntario(username, nome, password, new GPS(), Double.parseDouble(input)));
                            break;
                    }
                    break;
                case "2":
                    System.out.print("Username: ");
                    username = sc.nextLine();
                    System.out.print("Password: ");
                    password = sc.nextLine();
                    if (trazAqui.checkLoggin(username, password)) {
                        Utilizador u = trazAqui.getUtilizador(username);
                        trazAqui.setLogged(true, u);
                        System.out.println("Interfaces.Login efetuado com sucesso");
                    }
                    else System.out.println("Username ou Palavra passe errada");
                    break;
                case "3":
                    p.wparser(trazAqui);
                    break;
                case "4":
                    System.out.println(trazAqui.getUserInformation());
                    sc.nextLine();
                    break;
                case "0":
                    break label;
            }

        }

    }
}
