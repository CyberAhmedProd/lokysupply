package dao;

import java.util.ArrayList;

import models.Entreprise;

public interface EntrepriseService {
	Entreprise getEntreprise();

    Boolean save(Entreprise e);

    Boolean update(Entreprise e);

    Boolean delete(Entreprise e);

}
