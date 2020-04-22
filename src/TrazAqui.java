import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class TrazAqui {
    private Map<String, Utilizador> utilizadores; //Map de utilizadores, voluntários, lojas e transportadoras

    /**
     * Construtor padrão
     */
    public TrazAqui() {
        this.utilizadores = new TreeMap<>();
    }

    /**
     * Adiciona um Utilizador
     *
     * @param u Utilizador
     */
    public void addUtilizador(Utilizador u) {
        utilizadores.put(u.getCodigo(), u);
    }

    public void checkLoggin(String username, String password){
        Utilizador u = this.utilizadores.get(username);
        if (u == null) System.out.println("não existe");
        else if(u.getPassword().equals(password)) System.out.println("existe");
        else System.out.println("password errada");
    }
    /**
     * Obtém a lista de Utilizadores
     *
     * @return List
     */
    public List<Utilizador> getListaUtilizadores() {
        return this.utilizadores.values().stream().filter(a -> a.getClass().getName().equals("Utilizador"))
                .map(Utilizador::clone).collect(Collectors.toList());
    }

    /**
     * Obém a lista de Voluntários
     *
     * @return List
     */
    public List<Voluntario> getListaVoluntarios() {
        return this.utilizadores.values().stream().filter(a -> a.getClass().getName().equals("Voluntario"))
                .map(a -> (Voluntario) a.clone()).collect(Collectors.toList());
    }

    /**
     * Imprime a lista de Utilizadores
     */
    public void imprimeUtilizadores() {
        for (Utilizador u : this.getListaUtilizadores())
            System.out.println(u.toString());
    }

    /**
     * Imprime a lista de Voluntarios
     */
    public void imprimeVoluntarios() {
        for (Voluntario u : this.getListaVoluntarios())
            System.out.println(u.toString());
    }
}
