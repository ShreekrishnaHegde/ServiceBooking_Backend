package net.firstProject.sbs.repository;

import net.firstProject.sbs.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    List<Reservation> findAllByCompanyId(Long companyId);

    List<Reservation> findAllByUserId(Long userId);
}
