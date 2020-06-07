package Controladores;

import Modelos.TrazAquiModel;
import Views.TrazAquiView;

import java.io.IOException;

public interface TrazAquiController {
    void setModel(TrazAquiModel m);

    void setView(TrazAquiView v);

    void start() throws IOException, ClassNotFoundException;
}
