package spring.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.hibernate.entity.Client;
import spring.hibernate.util.TriUtil;

@Repository
public class ClientDAOImpl implements ClientDAO {
	
	@Autowired
	private SessionFactory usine;

	@Override
	@Transactional
	public List<Client> getClients(int champTri) {
		// récupérer la session hibernate
		Session session = usine.getCurrentSession();
		
		// determiner le champ de tri 
		String nomDuChamp = null;
		
		switch (champTri) {
		case TriUtil.PRENOM:
			nomDuChamp = "firstName";
			break;
		case TriUtil.NOM:
			nomDuChamp = "lastName";
			break;
		case TriUtil.EMAIL:
			nomDuChamp = "email";
			break;
		default:
			
			nomDuChamp = "lastName";	
			
		}
			
		// créer une requête
			String stringRequete = "from Client order by " + nomDuChamp;
			Query<Client> requete = session.createQuery(stringRequete, Client.class);
			
			List<Client> lesClients = requete.getResultList();
			
			return lesClients;
			
			
		}
		

	@Transactional
	@Override
	public void sauvegarderClient(Client client1) {

		// récupérer la session hibernate
		Session session = usine.getCurrentSession();
		
		// sauvegarder le client
		session.saveOrUpdate(client1);
		
	}

	@Override
	@Transactional
	public Client getClient(int idClient) {
		// récupérer la session hibernate 
		Session session = usine.getCurrentSession();
		
		// récupérer le client à l'aide de son id
		Client client = session.get(Client.class, idClient);
		
		return client;
	}

	@Override
	@Transactional
	public void supprimerClient(int idClient) {
		// récupérer la session hibernate 
		Session session = usine.getCurrentSession();
		
		// supprimer client
		Query requete = session.createQuery("delete from Client where id=:idRequete");
		requete.setParameter("idRequete", idClient);
		requete.executeUpdate();
		
	}

	@Override
	@Transactional
	public List<Client> rechercherClients(String nomClient) {
		// récupérer la session hibernate 
		Session session = usine.getCurrentSession();
		
		Query<Client> requete = null;
		// rechercher le nom seulement si nomClient n'est pas vide
		
		if (nomClient != null && nomClient.trim().length() > 0 ) {
			requete = session.createQuery("from Client where lower(firstName) like :nom or lower(lastName) like :nom", Client.class);
			requete.setParameter("nom", "%" + nomClient.toLowerCase() + "%");
		} else {
			// nomClient est vide ->> on retourne tous les clients
			requete = session.createQuery("from Client", Client.class);
		}
		
		List<Client> clients = requete.getResultList();
		
		return clients;
		
	}

}
