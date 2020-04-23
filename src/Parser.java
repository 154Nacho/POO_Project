import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Parser {
    //Ler
    public void parse(TrazAqui trazAqui) {
        List<String> linhas = lerFicheiro("src/Resources/logs.txt");
        String[] linhaPartida;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch (linhaPartida[0]) {
                case "Utilizador":
                    Utilizador u = parseUtilizador(linhaPartida[1]); // criar um Utilizador
//                    System.out.println(u.toString()); //enviar para o ecrÃ¡n apenas para teste
                    trazAqui.addUtilizador(u);
                    break;
                case "Voluntario":
                    Voluntario v = parseVoluntario(linhaPartida[1]);
//                    System.out.println(v.toString());
                    trazAqui.addUtilizador(v);
                    break;
                case "Loja":
                    Loja l = parseLoja(linhaPartida[1]);
//                    System.out.println(l.toString());
                    break;
                case "Transportadora":
                    Transportadora t = parseTransportadora(linhaPartida[1]);
//                    System.out.println(t.toString());
                    break;
                    //...
                case "Encomenda":
                    Encomenda e = parseEncomenda(linhaPartida[1]);
                    trazAqui.addEncomenda(e);
                    break;
                default:
//                    System.out.println("Linha invÃ¡lida.");
                    break;
            }

        }
        System.out.println("done!");
    }


    public Utilizador parseUtilizador(String input) {
        String[] campos = input.split(",");
        String codUtilizador = campos[0];
        String nome = campos[1];
        double gpsx = Double.parseDouble(campos[2]);
        double gpsy = Double.parseDouble(campos[3]);
        return new Utilizador(codUtilizador, nome, "", new GPS(gpsx, gpsy));
    }

    public Voluntario parseVoluntario(String input) {
        String[] campos = input.split(",");
        String codVoluntario = campos[0];
        String nome = campos[1];
        double gpsx = Double.parseDouble(campos[2]);
        double gpsy = Double.parseDouble(campos[3]);
        double raio = Double.parseDouble(campos[4]);
        return new Voluntario(codVoluntario, nome, "", new GPS(gpsx, gpsy), raio);
    }

    public Loja parseLoja(String input) {
        String[] campos = input.split(",");
        String codLoja = campos[0];
        String nomeLoja = campos[1];
        double gpsx = Double.parseDouble(campos[2]);
        double gpsy = Double.parseDouble(campos[3]);
        Loja l = new Loja();
        l.setCodLoja(codLoja);
        l.setNomeLoja(nomeLoja);
        l.setLocation(new GPS(gpsx, gpsy));
        return l;
    }

    public Transportadora parseTransportadora(String input) {
        String[] campos = input.split(",");
        String codEmpresa = campos[0];
        String nomeEmpresa = campos[1];
        double gpsx = Double.parseDouble(campos[2]);
        double gpsy = Double.parseDouble(campos[3]);
        String nif = campos[4];
        double raio = Double.parseDouble(campos[5]);
        double ppk = Double.parseDouble(campos[6]);
        Transportadora t = new Transportadora();
        t.setCodEmpresa(codEmpresa);
        t.setNomeEmpresa(nomeEmpresa);
        t.setLocation(new GPS(gpsx, gpsy));
        t.setNif(nif);
        t.setRaio(raio);
        t.setPPK(ppk);
        return t;
    }

    public Encomenda parseEncomenda(String input){
        String[] campos = input.split(",");
        String codEncomenda = campos[0];
        String codUtilizador = campos[1];
        String codLoja = campos[2];
        double peso = Double.parseDouble(campos[3]);
        Map<String,LinhaEncomenda> encomendas = new TreeMap<>();

        int i;
        for(i=4;i<campos.length;){
            String codProd = campos[i++];
            String descricao = campos[i++];
            double quantidade = Double.parseDouble(campos[i++]);
            double valorUnitario = Double.parseDouble(campos[i++]);
            LinhaEncomenda l = new LinhaEncomenda(codProd,descricao,quantidade,valorUnitario);
            encomendas.put(codProd,l);
        }
        return new Encomenda(codEncomenda,codUtilizador,codLoja,peso,encomendas);
    }

    public List<String> lerFicheiro(String nomeFich) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8);
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
        return lines;
    }

    //Escrever
    public void wparser(TrazAqui trazAqui) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/Resources/newlogs.txt"));
            List<Utilizador> u = trazAqui.getUtilizadores();
            for (Utilizador utilizador : u)
                bw.write(utilizador.toString()+'\n');
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
