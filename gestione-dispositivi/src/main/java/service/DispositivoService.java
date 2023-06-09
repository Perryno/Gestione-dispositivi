package service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;

import entities.Dispositivo;
import repository.DispositivoRepository;

public class DispositivoService {
	@Autowired
	private DispositivoRepository dispositivoRepository;

	public List<Dispositivo> getAllDispositivi() {
		return dispositivoRepository.findAll();
	}

	public Dispositivo getDispositivoById(Long id) {
		return dispositivoRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Dispositivo non trovato"));
	}

	public Dispositivo createDispositivo(Dispositivo dispositivo) {
		return dispositivoRepository.save(dispositivo);
	}

	public Dispositivo updateDispositivo(Long id, Dispositivo dispositivo) {
		Dispositivo existingDispositivo = dispositivoRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Dispositivo non trovato"));

		existingDispositivo.setTipo(dispositivo.getTipo());
		existingDispositivo.setStato(dispositivo.getStato());

		return dispositivoRepository.save(existingDispositivo);
	}

	public void deleteDispositivo(Long id) {
		dispositivoRepository.deleteById(id);
	}
}
