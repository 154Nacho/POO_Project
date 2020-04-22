import jdk.jshell.execution.Util;

import java.io.*;
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

        String username;
        String password;
        String nome = "";
        String input = sc.nextLine();

        try {
            BufferedReader ubr = new BufferedReader(new FileReader("src/Resources/Utilizadores.txt"));
            BufferedReader vbr = new BufferedReader(new FileReader("src/Resources/Voluntarios.txt"));

            String lineu = ubr.readLine();
            String linev = vbr.readLine();
            String[] buffer;
            String[] vbuffer;

            while (lineu != null) {
                buffer = lineu.split(":");
                buffer = buffer[1].split(",");
                Utilizador u = new Utilizador(buffer[0], buffer[1], "", new GPS(Double.parseDouble(buffer[2]), Double.parseDouble(buffer[3])));
                trazAqui.addUtilizador(u);
                lineu = ubr.readLine();
            }
            ubr.close();
            while (linev != null) {
                vbuffer = linev.split(":");
                vbuffer = vbuffer[1].split(",");
                Voluntario v = new Voluntario(vbuffer[0], vbuffer[1], "", false, new GPS(Double.parseDouble(vbuffer[2]), Double.parseDouble(vbuffer[3])), Double.parseDouble(vbuffer[4]));
                trazAqui.addUtilizador(v);
                linev = vbr.readLine();
            }
            vbr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
            trazAqui.checkLoggin(username,password);
        }








        //trazAqui.imprimeVoluntarios();
        trazAqui.imprimeUtilizadores();
    }
}
