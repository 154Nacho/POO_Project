package Modelos;

import Users.User;

import java.util.Collection;

public interface TrazAquiModel {
    void loadDefault();
    Collection<Object> interpreta(int num, Collection<Object> l);

    boolean checkLoggin(String o, String o1);

    User getLogged();
}
