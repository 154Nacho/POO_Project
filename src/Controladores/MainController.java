package Controladores;

import Modelos.TrazAquiModel;
import Readers.Input;
import Users.User;
import Users.Voluntario;
import Views.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainController implements TrazAquiController{
    private TrazAquiView view;
    private TrazAquiModel model;

    @Override
    public void setModel(TrazAquiModel m) {
        this.model = m;
    }

    @Override
    public void setView(TrazAquiView v) {
        this.view = v;
    }

    @Override
    public void start() {
        String opcao = "";
        boolean logged = false;
        do {
            view.show();
            opcao = Input.lerString();
            opcao = opcao.toUpperCase();
            switch (opcao) {
                case "1":
                    logged = login();
                    User u = model.getLogged();
                    switch (u.getCode().charAt(0)){
                        case 'u':
                            controladorAuxiliarUtilizador();
                            break;
                        case 'v':
                            controladorAuxiliarVoluntario();
                            break;
                        case 'l':
                            controladorAuxiliarLoja();
                            break;
                        case 't':
                            controladorAuxiliarTransportadora();
                            break;
                    }

                    break;
                case "2":
                    register();
                    view = new LoginView();
                    break;
                case "S":
                    break;
            }
        } while (!opcao.equals("S") || !logged);
    }

    /*--------------------------------------------------UTILIZADOR--------------------------------------------------*/

    private void controladorAuxiliarUtilizador(){
        String opcao = "";
        do{
            this.view = new UtilizadorView();
            view.show();
            opcao = Input.lerString();
            opcao = opcao.toUpperCase();
            switch(opcao){
                case "1":
                    novaEncomenda();
                    break;
                case "2":
                    encomendasFeitas();
                    break;
                case "3":
                    encomendasOnHold();
                    break;
                case "4":
                    encomendasToAccept();
                    break;
                case "5":
                    apresentarLojas();
                    break;
                case "6":
                    apresentarProdutosLoja();
                    break;
                case "S":
                    this.view = new LoginView();
                    break;

            }

        }while(!(opcao.equals("S")));
    }

    public void novaEncomenda(){
        List<Object> aux = new ArrayList<>();
        String loja,produto;
        int qtd;
        view.show("Qual a Loja onde pretende encomendar?\n");
        loja = Input.lerString();
        if(loja.isEmpty()) return;
        view.show("Qual o produto que pretende comprar?\n");
        produto = Input.lerString();
        if(produto.isEmpty()) return;
        view.show("Indique a quantidade que pretende comprar: ");
        qtd = Input.lerInt();
        aux.add(loja);
        aux.add(produto);
        aux.add(qtd);
        model.interpreta(2,aux);
        view.show("Encomenda efetuada com sucesso\n");
    }

    public void apresentarLojas(){
        Collection<Object> aux = model.interpreta(5,new ArrayList<>());
        if(aux.size()==0) view.show("Something's wrong");
        for(Object e : aux){
            view.show(e + "\n");
        }
        view.show("Press Enter to exit");
        if (Input.lerString().isEmpty()) return;
    }

    public void apresentarProdutosLoja(){
        List<Object> aux = new ArrayList<>();
        String codLoja;
        view.show("Inserir código da loja que pretende consultar:\n");
        codLoja=Input.lerString();
        if (codLoja.isEmpty()) return;
        aux.add(codLoja);
        Collection<Object> prods = model.interpreta(6,aux);
        for(Object e : prods)
            view.show(e + "\n");


    }

    public void encomendasFeitas(){
        Collection<Object> aux = model.interpreta(3,new ArrayList<>());
        if(aux.size()==0){
            view.show("Não possui encomendas feitas de momento!\n");
            return;
        }
        else {
            for (Object e : aux) {
                view.show(e + "\n");
            }
        }
    }

    public void encomendasOnHold(){
        Collection<Object> aux = model.interpreta(4,new ArrayList<>());
        if(aux.size()==0){
            view.show("Não possui encomendas feitas de momento!\n");
            return;
        }
        else {
            for (Object e : aux) {
                view.show(e + "\n");
            }
        }
    }

    public void encomendasToAccept(){

    }

    /*--------------------------------------------------VOLUNTARIO--------------------------------------------------*/

    private void controladorAuxiliarVoluntario(){
        String opcao = "";
        do{
            this.view = new VoluntarioView();
            view.show();
            opcao = Input.lerString();
            opcao = opcao.toUpperCase();
            switch(opcao){
                case "1":
                    Voluntario v = (Voluntario) model.getLogged();
                    if(!v.isDisponivel()) break;
                    entregarEncomenda();
                    break;
                case "2":
                    consultarSistema();
                    break;
                case "3":
                    alterarDisponibilidade();
                    break;
                case "4":
                    mostrarClassificacao();
                    break;
                case "S":
                    this.view = new LoginView();
                    break;

            }

        } while (!(opcao.equals("S")));
    }

    public void entregarEncomenda() {
        List<Object> aux = new ArrayList<>();
        String codEnc;
        view.show("Indique o código de encomenda que pretende entregar:\n");
        if((codEnc=Input.lerString()).isEmpty()) return;
        aux.add(codEnc);
        model.interpreta(8,aux);
    }

    public void consultarSistema() {
        Collection<Object> aux = model.interpreta(7, new ArrayList<>());
        view.show("Encomendas Disponiveis para Entrega\n");
        if(aux.isEmpty()) view.show("-> Não existem encomendas disponiveis para poder entregar!\n");
        for (Object e : aux) {
            view.show(e + "\n");
        }
        view.show("Press Enter to exit\n");
        if(Input.lerString().isEmpty()) return;
    }

    public void alterarDisponibilidade() {
        List<Object> aux = new ArrayList<>();
        view.show("Pretende mostrar-se disponivel (1) ou indisponível(0)?\n");
        int opcao = Input.lerInt();
        aux.add(opcao);
        model.interpreta(9,aux);
        view.show("Press Enter to exit\n");
        if(Input.lerString().isEmpty()) return;
    }

    public void mostrarClassificacao() {
        Collection<Object> aux= model.interpreta(10,new ArrayList<>());
        double c = 0;
        for (Object e : aux) c = (double) e;
        view.show("A sua classificação é de " + c + " em 5\n");
        view.show("Press Enter to exit\n");
        if(Input.lerString().isEmpty()) return;
    }


    /*----------------------------------------------------LOJA----------------------------------------------------*/

    private void controladorAuxiliarLoja() {
        String opcao = "";
        do {
            this.view = new LojaView();
            view.show();
            opcao = Input.lerString();
            opcao = opcao.toUpperCase();
            switch(opcao){
                case "1":
                    verEncomendas();
                    break;
                case "2":
                    consultarStock();
                    break;
                case "3":
                    updateInfo();
                    break;
                case "S":
                    this.view = new LoginView();
                    break;

            }

        }while(!(opcao.equals("S")));
    }

    public void verEncomendas(){
        view.show("Encomendas prontas a entregar\n");
        Collection<Object> aux = model.interpreta(12,new ArrayList<>());
        for (Object e : aux){
            view.show(e + "\n");
        }
        view.show("Press Enter to Exit");
        if(Input.lerString().isEmpty()) return;
    }

    public void consultarStock(){
        view.show("Produtos dispoiníveis na Loja\n");
        view.show("PRODUTO   |   CÓDIGO\n");
        Collection<Object> aux = model.interpreta(11,new ArrayList<>());
        for (Object e : aux){
            view.show(e + "\n");
        }
        view.show("Press Enter to Exit");
        if(Input.lerString().isEmpty()) return;
    }

    public void updateInfo(){

    }

    /*--------------------------------------------------EMPRESA--------------------------------------------------*/

    private void controladorAuxiliarTransportadora(){
        String opcao = "";
        do{
            this.view = new EmpresaView();
            view.show();
            opcao = Input.lerString();
            opcao = opcao.toUpperCase();
            switch(opcao){
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "S":
                    this.view = new LoginView();
                    break;

            }

        }while(!(opcao.equals("S")));
    }

    /*--------------------------------------------------COMMON--------------------------------------------------*/

    private void register(){
        view = new WhatUserLoginView();
        boolean valid = false;
        String username, password,nome;
        double latitude,longitude;
        List<Object> aux = new ArrayList<>();
        do{
            view.show("Username: ");
            username = Input.lerString();
            if(username.isEmpty()) return;
            else if (username.charAt(0) != 'u' && username.charAt(0) != 'v' && username.charAt(0) != 't' && username.charAt(0) != 'l'){
                view.show("Username inválido\n");
            }
            else valid = true;
        }while(!valid);
        aux.add(username); // 0
        view.show("Password: ");
        password = Input.lerString();
        aux.add(password); // 1
        view.show("Indique o seu nome: ");
        nome = Input.lerString();
        aux.add(nome); // 2
        view.show("Indique a sua localização\n");
        view.show("Latitude: ");
        latitude = Input.lerDouble();
        aux.add(latitude); // 3
        view.show("Longitude: ");
        longitude = Input.lerDouble();
        aux.add(longitude); // 4
        switch (username.charAt(0)){
            case 'v':
                view.show("Tem certificado para entregas médicas?\n");
                view.show("Sim(1) | Não(0)\n");
                aux.add(Input.lerInt()==1); // 5
                view.show("Indique o seu raio de ação: ");
                aux.add(Input.lerDouble()); // 6
                break;
            case 'l':
                view.show("Pretende informar sobre a fila de espera?\n");
                view.show("Sim(1) | Não(0)\n");
                int info = Input.lerInt();
                aux.add(info==1); // 5
                if(info==1) {
                    view.show("Indique o tempo médio de atendimento (em minutos): ");
                    aux.add(Input.lerDouble()); // 6
                }else aux.add(0.0);
                break;
            case 't':
                view.show("Indique o seu NIF: ");
                aux.add(Input.lerString()); // 5
                view.show("Indique o seu raio de ação: ");
                aux.add(Input.lerDouble()); // 6
                view.show("Indique a taxa de transporte: ");
                aux.add(Input.lerDouble()); // 7
                view.show("Indique a capacidade total de encomendas que é capaz de realizar: ");
                aux.add(Input.lerInt()); // 8
                break;
            default:
        }
        model.interpreta(1,aux);
    }

    private boolean login() {
        view = new WhatUserLoginView();
        boolean valid = false;
        view.show("Username: ");
        String username = Input.lerString();
        view.show("Password: ");
        String pass = Input.lerString();
        valid = model.checkLoggin(username,pass);
        return valid;
    }
}
