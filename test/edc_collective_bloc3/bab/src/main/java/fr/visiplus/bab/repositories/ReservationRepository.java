package fr.visiplus.bab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.visiplus.bab.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
