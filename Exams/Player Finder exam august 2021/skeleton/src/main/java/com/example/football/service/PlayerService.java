package com.example.football.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

//ToDo - Implement all methods
public interface PlayerService {
    boolean areImported();

    String readPlayersFileContent() ;

    String importPlayers() throws JAXBException, IOException;

    String exportBestPlayers();
}
