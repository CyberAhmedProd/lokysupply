package dao;

import java.util.ArrayList;

import models.Entreprise;

public interface EntrepriseService {
	Entreprise getEntreprise();

    ArrayList<Entreprise> getAll();
    
    Boolean save(Entreprise e);

    Boolean update(Entreprise e);

    Boolean delete(Entreprise e);

}
