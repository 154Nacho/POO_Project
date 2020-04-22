import java.io.*;

public class Teste {
    public static void main(String[] args){
        GPS a = new GPS(51.5,0);
        GPS b = new GPS(38.8,-77.1);
        System.out.println(a.distanceTo(b));
        TrazAqui trazAqui = new TrazAqui();
        //sout

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
                Utilizador u = new Utilizador(buffer[0], buffer[1],"", Double.parseDouble(buffer[2]), Double.parseDouble(buffer[3]));
                trazAqui.addUtilizador(u);
                lineu = ubr.readLine();
            }
            ubr.close();
            while (linev != null){
                vbuffer = linev.split(":");
                vbuffer = vbuffer[1].split(",");
                Voluntario v = new Voluntario(vbuffer[0],vbuffer[1],"", Double.parseDouble(vbuffer[2]),Double.parseDouble(vbuffer[3]),Double.parseDouble(vbuffer[4]));
                trazAqui.addUtilizador(v);
                linev = vbr.readLine();
            }
            vbr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        trazAqui.imprimeVoluntarios();
        //trazAqui.imprimeUtilizadores();
    }
}
