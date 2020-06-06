package Modelos;

import Geral.GPS;
import Stock.Encomenda;
import Stock.EncomendaRealizadaUtilizador;
import Stock.InfoProduto;
import Stock.LinhaEncomenda;
import Users.*;
import com.sun.management.VMOption;
import jdk.jshell.execution.Util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TrazAqui implements TrazAquiModel{
    boolean logged;
    private Map<String, User> users; //Map de utilizadores, voluntários, lojas e transportadoras
    private List<Encomenda> encomendas;
    private User logged_user;

    /**
     * Construtor padrão
     */
    public TrazAqui() {
        this.users = new TreeMap<>();
        this.encomendas = new ArrayList<>();
        this.logged = false;
        this.logged_user = null;
    }

    public Collection<Object> interpreta(int num, Collection<Object> l){
        List<Object> lista = new ArrayList<>(l);
        switch (num){
            case 1:
                addUser(lista);
            case 2:
                novaEncomenda((String) lista.get(0), (String) lista.get(1), (Integer) lista.get(2));
            case 3:
                return encomendasFeitasUtilizador();
            case 4:
                return encomendasEmEsperaUtilizador();
            case 5:
                return lojasDoSistema();
            case 6:
                return produtosDaLoja((String) lista.get(0));
            case 7:
                return encomendasDisponiveis();
            case 8:
                entregarEncomenda((String) lista.get(0));
            case 9:
                alterarDisponibilidade((Integer) lista.get(0));
            case 10:
                return classifcacaoVoluntario();
            case 11:
                return produtosDaLoja();
            case 12:
                return encomendasDaLoja();
            default:
                return null;
        }
    }

    /*-------------------------------------------FUNCIONALIDADES UTILIZADOR-------------------------------------------*/

    public void novaEncomenda(String codL, String codP, int qtd){
        Loja l = (Loja) this.users.get(codL);
        InfoProduto info = l.getProdutos().get(codP);
        List<LinhaEncomenda> aux = new ArrayList<>();
        aux.add(new LinhaEncomenda(codP,info.getNome(),qtd,info.getPreco()));
        String codEnc = "e"+randomNumber(4);
        this.encomendas.add(new Encomenda(codEnc,this.logged_user.getCode(),codL,info.getPeso(),aux));
        l.addEncomendaOnHold(new Encomenda(codEnc,this.logged_user.getCode(),codL,info.getPeso(),aux));
        Utilizador user = (Utilizador) this.logged_user;
        user.addEncomendaOnHold(new Encomenda(codEnc,this.logged_user.getCode(),codL,info.getPeso(),aux));
    }

    public List<Object> encomendasFeitasUtilizador(){
        if(logged_user == null){
            System.out.println("erro");
        }
        Utilizador user = (Utilizador) getUser(this.logged_user.getCode());
        List<Object> aux = new ArrayList<>();
        for(EncomendaRealizadaUtilizador e : user.getEncomendasFeitas())
            aux.add(e.toString());
        return aux;
    }

    public List<Object> encomendasEmEsperaUtilizador(){
        Utilizador user = (Utilizador) this.logged_user;
        List<Object> aux = new ArrayList<>();
        for(Encomenda e : user.getOnHold())
            aux.add(e.toString());
        return aux;
    }

    public List<Object> lojasDoSistema(){
        List<Object> aux = new ArrayList<>();
        for(User u : this.users.values()){
            if(u instanceof Loja)
                aux.add(u.getCode()+" -> " + ((Loja) u).getNome());
        }
        return aux;
    }

    public List<Object> produtosDaLoja(String codLoja){
        Loja l = (Loja) this.users.get(codLoja);
        List<Object> aux = new ArrayList<>();
        for(Map.Entry<String,InfoProduto> prod : l.getProdutos().entrySet()){
            aux.add(prod.getKey() + " -> " + prod.getValue().getNome());
        }
        return aux;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /*-------------------------------------------FUNCIONALIDADES VOLUNTÁRIO-------------------------------------------*/

    public List<Object> encomendasDisponiveis(){
        Voluntario v = (Voluntario) this.logged_user;
        List<Object> aux = new ArrayList<>();
        for (Encomenda e : this.encomendas){
            Utilizador u = (Utilizador) getUser(e.getCodUtilizador());
            if(u.getGps().distanceTo(v.getGps()) <= v.getRaio())
                aux.add(e.toString());
        }
        return aux;
    }

    public void entregarEncomenda(String codEnc){
        Voluntario v = (Voluntario) this.logged_user;
        for(Encomenda e : this.encomendas){
            if (e.getCodEncomenda().compareTo(codEnc)==0){
                e.setDataEntrega(LocalDateTime.now());
                v.addEncomendaRealizada(codEnc,e.getCodUtilizador(),e.getCodLoja(), Duration.between(e.getDataEncomenda(),e.getDataEntrega()).toMinutes());
                Utilizador u = (Utilizador) getUser(e.getCodUtilizador());
                u.addEncomendaRealizada(e,Duration.between(e.getDataEncomenda(),e.getDataEntrega()).toMinutes(),v.getCodigo());
                this.encomendas.remove(e);
                return;
            }
        }
    }

    public void alterarDisponibilidade(int e){
        Voluntario v = (Voluntario) this.logged_user;
        v.setDisponivel(e==1);
    }

    public List<Object> classifcacaoVoluntario(){
        Voluntario v = (Voluntario) this.logged_user;
        List<Object> aux = new ArrayList<>();
        aux.add(v.getClassificação());
        return aux;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /*----------------------------------------------FUNCIONALIDADES LOJA----------------------------------------------*/

    public Set<Object> produtosDaLoja(){
        Set<Object> aux = new TreeSet<>();
        Loja l = (Loja) this.logged_user;
        for(Map.Entry<String,InfoProduto> e : l.getProdutos().entrySet())
            aux.add(e.getValue().getNome()  + " -> " + e.getKey() );
        return aux;
    }

    public List<Object> encomendasDaLoja(){
        List<Object> aux = new ArrayList<>();
        Loja l = (Loja) this.logged_user;
        for(Encomenda e : l.getEncomendas())
            aux.add(e.toString());
        return aux;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /**
     * Método que adiciona uma encomenda à lista de encomendas em espera de um utilizador.
     * @param e Encomenda feita.
     */
    public void addEncomendaToUtilizador(Encomenda e){
        Utilizador u = (Utilizador) this.users.get(e.getCodUtilizador());
        u.addEncomendaOnHold(e.clone());
    }

    /**
     * Método que adiciona a uma da loja informação sobre um produto comprado na mesma.
     * @param e Encomenda feita.
     */
    public void addProdutoALoja(Encomenda e){
        Loja l = (Loja) this.users.get(e.getCodLoja());
        for(LinhaEncomenda le : e.getProdutos()){
            l.addProdLoja(le.getCodProduto(),le.getDescricao(),le.getValorUnitario(),(e.getPeso()/e.getProdutos().size()));
        }
    }

    /**
     * Método que adiciona uma encomenda à fila de espera de uma loja.
     * @param e Encomenda realizada.
     */
    public void addEncomendaALoja(Encomenda e){
        Loja l = (Loja) this.users.get(e.getCodLoja());
        l.addEncomendaOnHold(e.clone());
    }


    public User getUser(String codigo){
        return this.users.get(codigo).clone();
    }

    /**
     * Obtém uma String com todas as Encomendas.
     * @return String
     */
    public String getEncomendasInformation(){
        StringBuilder sb = new StringBuilder();
        for(Encomenda e : this.encomendas)
            sb.append(e.toString()).append('\n');
        return sb.toString();
    }

    /**
     * Apresenta a informação de um user.
     * @return String
     */
    public String getUserInformation(){
        return this.logged_user.toString();
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
     * Método que gera um numero aleatório com um dado número de digitos.
     * @param length número de digitos pretendido.
     * @return Número gerado.
     */
    public int randomNumber(int length){
        Random random = new Random();
        return random.nextInt(999);
    }

    /**
     * Adiciona um Utilizador
     *
     * @param u Utilizador
     */
    public void addUtilizador(User u) {
        users.put(u.getCode(), u);
    }

    /**
     * Método que adiciona um User com os devidos parâmetros.
     * @param l que contém a informação sobre o User.
     */
    public void addUser(List<Object> l){
        switch(((String) l.get(0)).charAt(0)){
            case 'u':
                users.put((String) l .get(0),new Utilizador((String) l.get(0), (String) l.get(2),(String) l.get(1),new GPS((Double) l.get(3),(Double) l.get(4))));
                break;
            case 'v':
                users.put((String) l .get(0),new Voluntario((String) l.get(0), (String) l.get(2),(String) l.get(1),new GPS((Double) l.get(3),(Double) l.get(4)),(Boolean) l.get(5), (Double) l.get(6)));
                break;
            case 't':
                users.put((String) l .get(0),new Transportadora((String) l.get(0), (String) l.get(2),(String) l.get(1),new GPS((Double) l.get(3),(Double) l.get(4)),(String) l.get(5),(Double) l.get(6),(Double) l.get(7),(Integer) l.get(8)));
                break;
            case 'l':
                users.put((String) l .get(0),new Loja((String) l.get(0), (String) l.get(2),(String) l.get(1),new GPS((Double) l.get(3),(Double) l.get(4)),(Boolean) l.get(5),(Double) l.get(6)));
                break;
        }
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
            setLogged(true,this.users.get(username).clone());
            return true;
        } else return false;
    }

    public boolean checkUser(String codUser){
        User u = this.users.get(codUser);
        return u != null;
    }

    /**
     * Método que adiciona uma encomenda ao Sistema.
     * @param e Encomenda lida.
     */
    public void addEncomenda(Encomenda e){
        this.encomendas.add(e);
    }

    public List<Encomenda> getEncomendas(){
        ArrayList<Encomenda> aux = new ArrayList<>();
        for(Encomenda e : this.encomendas)
            aux.add(e.clone());
        return aux;
    }
    /**
     * Obtém o logged user
     * @return int
     */
    public User getLogged() {
        return logged_user;
    }

    /**
     * Define o logged number
     * @param logged Logged number
     */
    public void setLogged(boolean logged, User u) {
        this.logged = logged;
        this.logged_user = u;
    }

    /**
     * Método que devolve uma lista de utilizadores.
     * @return
     */
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
