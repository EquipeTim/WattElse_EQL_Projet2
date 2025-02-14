package fr.eql.ai116.proj2.tim.business;

import fr.eql.ai116.proj2.tim.entity.Plug;
import fr.eql.ai116.proj2.tim.entity.dto.CarDto;

import java.util.List;

public interface ComponentsBusiness {
//Méthode  utilisée pour rechercher ou trouver des modèles de prises (ou "plugs") pour un modèle
// de voiture donné, identifié par l'ID idModelCar.
    List<String> findPlug(CarDto carDto);
    //Extraire certaines informations relatives à un type spécifique de prise (d'où le plug_type), et renvoie sous forme de chaîne de caractères
    // ces informations extraites.

    List<Plug> getAllPlug();
}
