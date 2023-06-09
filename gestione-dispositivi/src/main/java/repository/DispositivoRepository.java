package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Dispositivo;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {

}
