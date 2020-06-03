package Modelos;

import Stock.Encomenda;
import Users.User;

import java.util.List;
import java.util.Map;

public class Sistema implements TrazAquiModel{
    boolean logged;
    private Map<String, User> users; //Map de users, voluntários, lojas e transportadoras
    private List<Encomenda> encomendas;
    private User user;
    @Override
    public void loadDefault() {

    }

    /*------------------------------UTILIZADOR------------------------------*/


    /*------------------------------LOJA------------------------------*/

    /*------------------------------EMPRESA------------------------------*/

    /*------------------------------VOLUNTÁRIO------------------------------*/
}
