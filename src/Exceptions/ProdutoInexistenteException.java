package Exceptions;

import java.io.Serializable;

public class ProdutoInexistenteException extends Exception implements Serializable {
    public ProdutoInexistenteException(){
        super();
    }

    public ProdutoInexistenteException(String m){
        super(m);
    }
}
