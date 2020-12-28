package dao;

import java.util.ArrayList;

import models.Fournisseur;


public interface FournisseurService {
	
	Fournisseur get(long id);

    ArrayList<Fournisseur> getAll();

    Boolean save(Fournisseur t);

    Boolean update(Fournisseur t);

    Boolean delete(Fournisseur t);
}
