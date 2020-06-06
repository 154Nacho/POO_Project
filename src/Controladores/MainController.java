package Controladores;

import Modelos.TrazAquiModel;
import Readers.Input;
import Users.User;
import Users.Voluntario;
import Views.*;

import javax.naming.spi.ObjectFactoryBuilder;
import java.io.ObjectInputStream;
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
                case "7":
                    classificarEntregador();
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
        Collection<Object> aux = model.interpreta(17,new ArrayList<>());
        for(Object e : aux){
            view.show(e + "\n");
        }
        if(aux.isEmpty()) view.show("Não possui encomendas por aceitar de momento.\n");
        else {
            String opcao;
            List<Object> args = new ArrayList<>();
            view.show("Indique o código da encomenda a aceitar/recusar ou pressione Enter para sair\n");
            opcao = Input.lerString();
            if (opcao.isEmpty()) return;
            args.add(opcao);
            view.show("Pretender aceitar(S) ou recusar(N) a encomenda?\n");
            opcao = Input.lerString();
            opcao = opcao.toUpperCase();
            args.add(opcao);
            Collection<Object> res = model.interpreta(18, args);
            if(res.isEmpty()) view.show("A encomenda não foi aceite ou não existe!");
            else view.show("Encomenda recebida com sucesso!\n");
        }
        view.show("Press Enter to exit");
        if (Input.lerString().isEmpty()) return;
    }

    public void classificarEntregador(){
        Collection<Object> aux = model.interpreta(21,new ArrayList<>());
        List<Object> args = new ArrayList<>();
        String code;
        boolean valid = false;
        for(Object e : aux )
            view.show(e + "\n");
        do{
            view.show("Indique o entregador que pretende avaliar: ");
            code=Input.lerString();
            if(code.isEmpty()) return;
            else if(code.charAt(0) != 'v' && code.charAt(0) != 't')
                view.show("Código inválido\n");
            else {
                args.add(code);
                valid=true;
            }
        }while(!valid);
        view.show("Atribua uma classificação ao entregador ( 0 a 5 ): ");
        args.add(Input.lerInt());
        model.interpreta(22,args);
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
        Collection<Object> res = model.interpreta(8,aux);
        if (res.isEmpty()) view.show("A encomenda ja foi entregue!\n");
        else view.show("Entrega feita com sucesso!\n");
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
        String opcao;
        do {
            view.show("1 -> Informar sobre a loja\n");
            view.show("2 -> Adicionar produto ao stock\n");
            view.show("S -> Retroceder\n");
            opcao = Input.lerString();
            opcao = opcao.toUpperCase();
            switch (opcao) {
                case "1":
                    model.interpreta(13, new ArrayList<>());
                    break;
                case "2":
                    List<Object> aux = new ArrayList<>();
                    boolean valid = false;
                    String code,nome;
                    double p;
                    do{
                        view.show("Indique o código do produto que pretende adicionar: ");
                        code = Input.lerString();
                        if(code.isEmpty()) return;
                        else if (code.charAt(0) != 'p' ){
                            view.show("Código inválido\n");
                        }
                        else valid = true;
                    }while(!valid);
                    aux.add(code);
                    view.show("Indique o nome do produto: ");
                    nome = Input.lerString();
                    if(nome.isEmpty()) return;
                    else aux.add(nome);
                    view.show("Indique o preço a que pretende vender o produto: ");
                    p = Input.lerDouble();
                    aux.add(p);
                    model.interpreta(14, aux);
                    break;
                case "S":
                    break;
            }
        }while(!opcao.equals("S"));
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
                    entregarEncomendaTransportadora();
                    break;
                case "2":
                    consultarSistemaTransportadora();
                    break;
                case "3":
                    alterarDisponibilidadeTransportadora();
                    break;
                case "4":
                    calcularFaturado();
                    break;
                case "S":
                    this.view = new LoginView();
                    break;

            }

        }while(!(opcao.equals("S")));
    }

    public void entregarEncomendaTransportadora(){
        List<Object> aux = new ArrayList<>();
        String codEnc;
        view.show("Indique o código de encomenda que pretende entregar:\n");
        if((codEnc=Input.lerString()).isEmpty()) return;
        aux.add(codEnc);
        Collection<Object> res = model.interpreta(15,aux);
        if(res.isEmpty()) view.show("A encomenda já foi entregue!\n");
        else view.show("A entrega encontra-se pendente e à espera de aprovação do Utilizador!\n");
    }

    public void consultarSistemaTransportadora(){
        Collection<Object> aux = model.interpreta(16, new ArrayList<>());
        view.show("Encomendas Disponiveis para Entrega\n");
        if(aux.isEmpty()) view.show("-> Não existem encomendas disponiveis para poder entregar!\n");
        for (Object e : aux) {
            view.show(e + "\n");
        }
        view.show("Press Enter to exit\n");
        if(Input.lerString().isEmpty()) return;
    }

    public void alterarDisponibilidadeTransportadora(){
        List<Object> aux = new ArrayList<>();
        view.show("Pretende mostrar-se disponivel (1) ou indisponível(0)?\n");
        int opcao = Input.lerInt();
        aux.add(opcao);
        model.interpreta(19,aux);
        view.show("Press Enter to exit\n");
        if(Input.lerString().isEmpty()) return;
    }

    public void calcularFaturado(){
        List<Object> args = new ArrayList<>();
        view.show("Data de Início\n");
        view.show("Ano: ");
        int y1 = Input.lerInt();
        view.show("Mes: ");
        int m1 = Input.lerInt();
        view.show("Dia: ");
        int d1 = Input.lerInt();
        view.show("\nData de Fim\n");
        view.show("Ano: ");
        int y2 = Input.lerInt();
        view.show("Mes: ");
        int m2 = Input.lerInt();
        view.show("Dia: ");
        int d2 = Input.lerInt();
        args.add(y1);
        args.add(m1);
        args.add(d1);
        args.add(y2);
        args.add(m2);
        args.add(d2);
        Collection<Object> res = model.interpreta(20,args);
        view.show("O total faturado entre o dia " + d1 + " do mes " + m1 + " do ano " + y1 + " e o dia " + d2 + " do mes " + m2 + " do ano " + y2 + " é  " + res.toString() + "\n");
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
