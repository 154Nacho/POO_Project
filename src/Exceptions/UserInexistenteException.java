package Exceptions;

import java.io.Serializable;

public class UserInexistenteException extends Exception implements Serializable {
    public UserInexistenteException(){
        super();
    }
    public UserInexistenteException(String m){
        super(m);
    }

}
