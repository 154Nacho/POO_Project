package Controladores;

import Modelos.TrazAquiModel;
import Views.TrazAquiView;

public interface TrazAquiController {
    void setModel(TrazAquiModel m);

    void setView(TrazAquiView v);

    void start();
}
