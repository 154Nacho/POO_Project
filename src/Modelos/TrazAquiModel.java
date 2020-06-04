package Modelos;

import java.util.Collection;

public interface TrazAquiModel {
    void loadDefault();
    void interpreta(int num, Collection<Object> l);

    boolean checkLoggin(String o, String o1);
}
