package Comparadores;

import Users.Transportadora;

import java.util.Comparator;

public class TotalKMsComparator implements Comparator<Transportadora> {
    @Override
    public int compare(Transportadora o1, Transportadora o2) {
        if(o2.getTotalKM() > o1.getTotalKM()) return 1;
        else if(o2.getTotalKM() < o1.getTotalKM()) return -1;
        else return o1.getCode().compareTo(o2.getCode());
    }
}
