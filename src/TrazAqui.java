import jdk.jshell.execution.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class TrazAqui {
    boolean logged;
    private Map<String, Utilizador> utilizadores; //Map de utilizadores, voluntários, lojas e transportadoras
    private List<Encomenda> encomendas;
    private Utilizador user;

    /**
     * Construtor padrão
     */
    public TrazAqui() {
        this.utilizadores = new TreeMap<>();
        this.encomendas = new ArrayList<>();
        this.logged = false;
        this.user = null;
    }

    /**
     * Adiciona um Utilizador
     *
     * @param u Utilizador
     */
    public void addUtilizador(Utilizador u) {
        utilizadores.put(u.getCodigo(), u);
    }

    /**
     * Verifica se existe user válido
     *
     * @param username Username inserido pelo Utilizador
     * @param password Password inserida pelo Utilizador
     * @return boolean
     */
    public boolean checkLoggin(String username, String password) {
        Utilizador u = this.utilizadores.get(username);
        if (u == null) return false;
        else if (u.getPassword().equals(password)) {
            this.user = this.utilizadores.get(username).clone();
            return true;
        } else return false;
    }

    public void addEncomenda(Encomenda e){
        this.encomendas.add(e);
    }
    /**
     * Obtém o logged number
     *
     * @return int
     */
    public boolean getLogged() {
        return logged;
    }

    /**
     * Define o logged number
     *
     * @param logged Logged number
     */
    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public List<Utilizador> getUtilizadores() {
        return this.utilizadores.values().stream()
                .map(Utilizador::clone)
                .collect(Collectors.toList());
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
