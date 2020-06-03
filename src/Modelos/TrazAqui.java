package Modelos;

import Stock.Encomenda;
import Users.User;
import Users.Utilizador;
import Users.Voluntario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class TrazAqui implements TrazAquiModel{
    boolean logged;
    private Map<String, User> users; //Map de users, voluntários, lojas e transportadoras
    private List<Encomenda> encomendas;
    private User user;

    /**
     * Construtor padrão
     */
    public TrazAqui() {
        this.users = new TreeMap<>();
        this.encomendas = new ArrayList<>();
        this.logged = false;
        this.user = null;
    }

    public User getUser(String codigo){
        return this.users.get(codigo).clone();
    }

    /**
     * Obtém uma String com todas as Encomendas
     * @return String
     */
    public String getEncomendasInformation(){
        StringBuilder sb = new StringBuilder();
        for(Encomenda e : this.encomendas)
            sb.append(e.toString()).append('\n');
        return sb.toString();
    }

    /**
     * Apresenta a informação de um user
     * @return String
     */
    public String getUserInformation(){
        return this.user.toString();
//        switch (this.user.getClass().getName()) {
//            case "Users.Utilizador":
//                return this.user.toStringShow();
//            case "Users.Voluntario":
//                Users.Voluntario v = (Users.Voluntario) this.user;
//                return v.toStringShow();
//            case "Users.Transportadora":
//                Users.Transportadora t = (Users.Transportadora) this.user;
//                return t.toStringShow();
//            default:
//                Users.Loja l = (Users.Loja) this.user;
//                return l.toStringShow();
//        }
    }

    /**
     * Adiciona um Users.Utilizador
     *
     * @param u Users.Utilizador
     */
    public void addUtilizador(User u) {
        users.put(u.getCode(), u);
    }

    /**
     * Verifica se existe user válido
     *
     * @param username Username inserido pelo Users.Utilizador
     * @param password Password inserida pelo Users.Utilizador
     * @return boolean
     */
    public boolean checkLoggin(String username, String password) {
        User u = this.users.get(username);
        if (u == null) return false;
        else if (u.getPassword().equals(password)) {
            this.user = this.users.get(username).clone();
            return true;
        } else return false;
    }

    public boolean checkUser(String codUser){
        User u = this.users.get(codUser);
        return u != null;
    }

    public void addEncomenda(Encomenda e){
        this.encomendas.add(e);
    }

    public List<Encomenda> getEncomendas(){
        ArrayList<Encomenda> aux = new ArrayList<>();
        for(Encomenda e : this.encomendas)
            aux.add(e);
        return aux;
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
    public void setLogged(boolean logged, User u) {
        this.logged = logged;
        this.user = u;
    }

    public List<User> getusers() {
        return this.users.values().stream()
                .map(User::clone)
                .collect(Collectors.toList());
    }

    /**
     * Obtém a lista de users
     *
     * @return List
     */
    public List<User> getListaUsers() {
        return this.users.values().stream().filter(a -> a.getClass().getName().equals("Users.Utilizador"))
                .map(User::clone).collect(Collectors.toList());
    }

    /**
     * Obém a lista de Voluntários
     *
     * @return List
     */
    public List<Voluntario> getListaVoluntarios() {
        return this.users.values().stream().filter(a -> a.getClass().getName().equals("Users.Voluntario"))
                .map(a -> (Voluntario) a.clone()).collect(Collectors.toList());
    }

    /**
     * Imprime a lista de users
     */
    public void imprimeusers() {
        for (User u : this.getListaUsers())
            System.out.println(u.toString());
    }

    /**
     * Imprime a lista de Voluntarios
     */
    public void imprimeVoluntarios() {
        for (Voluntario u : this.getListaVoluntarios())
            System.out.println(u.toString());
    }


    //passwords
    public void setPassword(String codUser, String pw){
        this.users.get(codUser).setPassword(pw);
    }

    @Override
    public void loadDefault() {

    }
}
