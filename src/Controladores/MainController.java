package Controladores;

import Modelos.TrazAquiModel;
import Readers.Input;
import Stock.Encomenda;
import Users.User;
import Views.*;

import java.util.ArrayList;
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
                    apresentarLojas();
                    break;
                case "5":
                    apresentarProdutosLoja();
                    break;
                case "S":
                    break;

            }

        }while(!(opcao.equals("S")));
    }

    public void novaEncomenda(){
        List<Object> aux = new ArrayList<>();
        view.show("Qual a Loja onde pretende encomendar?\n");
        aux.add(Input.lerString());
        view.show("Qual o produto que pretende comprar?\n");
        aux.add(Input.lerString());
        view.show("Indique a quantidade que pretende comprar: ");
        aux.add(Input.lerInt());
        model.interpreta(2,aux);
    }

    public void apresentarLojas(){

    }

    public void apresentarProdutosLoja(){

    }

    public void encomendasFeitas(){

    }

    public void encomendasOnHold(){

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
                    break;

            }

        }while(!(opcao.equals("S")));
    }
    /*----------------------------------------------------LOJA----------------------------------------------------*/

    private void controladorAuxiliarLoja(){
        String opcao = "";
        do{
            this.view = new LojaView();
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
                    break;

            }

        }while(!(opcao.equals("S")));
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
        aux.add(username);
        view.show("Password: ");
        password = Input.lerString();
        aux.add(password);
        view.show("Indique o seu nome: ");
        nome = Input.lerString();
        aux.add(nome);
        view.show("Indique a sua localização\n");
        view.show("Latitude: ");
        latitude = Input.lerDouble();
        aux.add(latitude);
        view.show("Longitude: ");
        longitude = Input.lerDouble();
        aux.add(longitude);
        switch (username.charAt(0)){
            case 'v':
                view.show("Tem certificado para entregas médicas?\n");
                view.show("Sim(1) | Não(0)\n");
                aux.add(Input.lerInt()==1);
                view.show("Indique o seu raio de ação: ");
                aux.add(Input.lerDouble());
                break;
            case 'l':
                view.show("Pretende informar sobre a fila de espera?\n");
                view.show("Sim(1) | Não(0)\n");
                aux.add(Input.lerInt()==1);
                view.show("Indique o tempo médio de atendimento (em minutos): ");
                aux.add(Input.lerInt());
                break;
            case 't':
                view.show("Indique o seu NIF: ");
                aux.add(Input.lerString());
                view.show("Indique o seu raio de ação: ");
                aux.add(Input.lerDouble());
                view.show("Indique a taxa de transporte: ");
                aux.add(Input.lerDouble());
                view.show("Indique a capacidade total de encomendas que é capaz de realizar: ");
                aux.add(Input.lerInt());
                break;
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
