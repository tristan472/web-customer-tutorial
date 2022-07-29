package spring.hibernate.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.hibernate.entity.Client;
import spring.hibernate.util.TriUtil;
import spring.hibernate.dao.ClientDAO;

@RequestMapping("/client")
@Controller
public class ControleurClient {
	
	// injection de dépendance clientDAO dans le controleur
	@Autowired
	private ClientDAO clientDAO;

	@RequestMapping("/liste")
	public String listerClients(Model modele, @RequestParam(required=false) String tri) {
		// récupérer les clients du DAO
		List<Client> lesClients = null;
		
		// vérifier les chammp de tri 
		if (tri != null) {
			int champTri = Integer.parseInt(tri);
			lesClients = clientDAO.getClients(champTri);
		} else {
			lesClients = clientDAO.getClients(TriUtil.NOM);
		}
		
		//ajouter les clients au modèle
		modele.addAttribute("clients", lesClients);
		
		// nom du fichier jsp dans la view
		return "liste-clients"; 
	}
	
	@GetMapping("/montrerFormulaireAjout")
	public String montrerFormulaireAjout(Model modele) {
		
		// créer un attribut du modèle pour lier les données du formulaire
		
		Client client1 = new Client();
		modele.addAttribute("client", client1);
		return "formulaire-client";
	}
	
	@PostMapping("/sauvegarderClient")
	public String sauvegarderClient(@ModelAttribute("client") Client client1) {
		
		// sauvegarder le client à l'aide eu DAO
		clientDAO.sauvegarderClient(client1);
		return "redirect:/client/liste";
	}
	
	@GetMapping("/afficherFormulaire")
	public String afficherFormulaire(@RequestParam("idClient")
	int idClient, Model modele) {
		
		// récupérer le client depuis la bdd
		Client client1 = clientDAO.getClient(idClient);
		// set le client en attribut du modèle pour pré-peupler le formulaire
		modele.addAttribute("client", client1);
		// on l'envoie vers notre formulaire
		return "formulaire-client";
	}
	
	@GetMapping("/supprimerClient")
	public String supprimerClient(@RequestParam("idClient")
	int idClient) {
		
		// supprimer le client
		clientDAO.supprimerClient(idClient);
		
		return "redirect:/client/liste";
	}
	
	@GetMapping("/recherche")
	public String rechercherClients(@RequestParam("rechercheNom") String nomClient, Model modele) {
		List<Client> lesClients = clientDAO.rechercherClients(nomClient);
		
		modele.addAttribute("clients" , lesClients);
		return "liste-clients";
	}
}
