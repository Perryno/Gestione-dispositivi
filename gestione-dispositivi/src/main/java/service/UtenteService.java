package service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entities.Utente;
import repository.UtenteRepository;

@Service
public class UtenteService {
	@Autowired
	private UtenteRepository utenteRepository;

	public List<Utente> getAllUtenti() {
		return utenteRepository.findAll();
	}

	public Utente getUtenteById(Long id) {
		return utenteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Utente non trovato"));
	}

	public Utente createUtente(Utente utente) {
		return utenteRepository.save(utente);
	}

	public Utente updateUtente(Long id, Utente utente) {
		Utente existingUtente = utenteRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Utente non trovato"));

		existingUtente.setUsername(utente.getUsername());
		existingUtente.setNome(utente.getNome());
		existingUtente.setCognome(utente.getCognome());
		existingUtente.setEmail(utente.getEmail());

		return utenteRepository.save(existingUtente);
	}

	public void deleteUtente(Long id) {
		utenteRepository.deleteById(id);
	}
}
