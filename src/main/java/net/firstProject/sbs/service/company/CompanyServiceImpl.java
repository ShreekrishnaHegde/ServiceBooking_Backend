package net.firstProject.sbs.service.company;

import jakarta.persistence.Id;
import net.firstProject.sbs.Enums.ReservationStatus;
import net.firstProject.sbs.dto.AdDto;
import net.firstProject.sbs.dto.ReservationDto;
import net.firstProject.sbs.entity.Ad;
import net.firstProject.sbs.entity.Reservation;
import net.firstProject.sbs.entity.User;
import net.firstProject.sbs.repository.AdRepository;
import net.firstProject.sbs.repository.ReservationRepository;
import net.firstProject.sbs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public boolean postAd(Long userId, AdDto adDto) throws IOException {
        Optional<User> optionalUser=userRepository.findById(userId);
        if(optionalUser.isPresent()){
            Ad ad=new Ad();
            ad.setServiceName(adDto.getServiceName());
            ad.setDescription(adDto.getDescription());
            ad.setImg(adDto.getImg().getBytes());
            ad.setPrice(adDto.getPrice());
            ad.setUser(optionalUser.get());
            adRepository.save(ad);
            return true;
        }
        return false;
    }

    public List<AdDto> getAllAds(Long userId){
        return adRepository.findAllByUserId(userId).stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

    public AdDto getAdById(Long adId){
        Optional<Ad> optionalAd=adRepository.findById(adId);
        return optionalAd.map(Ad::getAdDto).orElse(null);
    }

    public boolean updateAd(Long adId,AdDto adDto) throws IOException {
        Optional<Ad> optionalAd=adRepository.findById(adId);
        if(optionalAd.isPresent()){
            Ad ad=optionalAd.get();
            ad.setServiceName(adDto.getServiceName());
            ad.setDescription(adDto.getDescription());
            ad.setPrice(adDto.getPrice());

            if(adDto.getImg()!=null){
                ad.setImg(adDto.getImg().getBytes());
            }
            adRepository.save(ad);
            return true;
        }
        else{
            return false;
        }
    }
    public boolean deleteAd(Long adId){
        Optional<Ad> optionalAd=adRepository.findById(adId);
        if(optionalAd.isPresent()){
            adRepository.delete(optionalAd.get());
            return true;
        }
        return false;
    }

        public List<ReservationDto> getAllAdBookings(Long companyId){
        return reservationRepository.findAllByCompanyId(companyId)
                .stream().map(Reservation::getReservationDto).collect(Collectors.toList());

    }

    public  boolean changeBookingStatus(Long bookingId,String status){
        Optional<Reservation> optionalReservation=reservationRepository.findById(bookingId);

        if(optionalReservation.isPresent()){
            Reservation existingReservation=optionalReservation.get();
            if(Objects.equals(status,"Approve")){
                existingReservation.setReservationStatus(ReservationStatus.APPROVED);
            }
            else{
                existingReservation.setReservationStatus(ReservationStatus.REJECTED);
            }

            reservationRepository.save(existingReservation);
            return true;
        }
        return false;
    }






}
