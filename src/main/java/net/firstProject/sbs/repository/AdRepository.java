package net.firstProject.sbs.repository;

import net.firstProject.sbs.dto.AdDto;
import net.firstProject.sbs.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad,Long> {

    List<Ad> findAllByUserId(Long id);
//    List<Ad> findAllServiceNameContaining(String serviceName);
}
