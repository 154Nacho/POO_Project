package Exceptions;

import java.io.Serializable;

public class AlreadyEvaluatedException extends Exception implements Serializable {
    public AlreadyEvaluatedException(){
        super();
    }
    public AlreadyEvaluatedException(String m){
        super(m);
    }

}
