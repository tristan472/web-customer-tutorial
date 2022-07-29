package spring.hibernate.dao;

import java.util.List;

import spring.hibernate.entity.Client;

public interface ClientDAO {

	public List<Client> getClients(int champTri);

	public void sauvegarderClient(Client client1);

	public Client getClient(int idClient);

	public void supprimerClient(int idClient);

	public List<Client> rechercherClients(String nomClient);
}