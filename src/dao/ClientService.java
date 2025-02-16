package dao;

import java.util.ArrayList;
import java.util.List;

import models.Client;

public interface ClientService {
	
	Client seekByMatricule(String matricule);
	Client getClient(int id);
    ArrayList<Client> getAll();

    Boolean save(Client t);

    Boolean update(Client t);

    Boolean delete(Client t);
    

}
