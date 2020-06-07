package Modelos;

import Users.User;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

public interface TrazAquiModel extends Serializable {
    void loadDefault();
    Collection<Object> interpreta(int num, Collection<Object> l) throws IOException;

    boolean checkLoggin(String o, String o1);

    User getLogged();
}
