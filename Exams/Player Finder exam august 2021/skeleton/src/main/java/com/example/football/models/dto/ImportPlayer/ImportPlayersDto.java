package com.example.football.models.dto.ImportPlayer;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "players")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPlayersDto {

    @XmlElement(name = "player")
    List<ImportPlayerDto> players;

    public ImportPlayersDto() {
    }

    public List<ImportPlayerDto> getPlayers() {
        return players;
    }
}
