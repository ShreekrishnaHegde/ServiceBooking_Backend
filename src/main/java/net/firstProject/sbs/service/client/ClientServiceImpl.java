package net.firstProject.sbs.service.client;

import net.firstProject.sbs.Enums.ReservationStatus;
import net.firstProject.sbs.Enums.ReviewStatus;
import net.firstProject.sbs.dto.AdDetailsForClientDto;
import net.firstProject.sbs.dto.AdDto;
import net.firstProject.sbs.dto.ReservationDto;
import net.firstProject.sbs.dto.ReviewDto;
import net.firstProject.sbs.entity.Ad;
import net.firstProject.sbs.entity.Reservation;
import net.firstProject.sbs.entity.Review;
import net.firstProject.sbs.entity.User;
import net.firstProject.sbs.repository.AdRepository;
import net.firstProject.sbs.repository.ReservationRepository;
import net.firstProject.sbs.repository.ReviewRepository;
import net.firstProject.sbs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;
    public List<AdDto> getAllAds(){
        return adRepository.findAll().stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

    public List<AdDto> searchAdByName(String name){
        return adRepository.findAllServiceNameContaining(name).stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

    public boolean bookService(ReservationDto reservationDto){
        Optional<Ad> optionalAd=adRepository.findById(reservationDto.getAdId());
        Optional<User> optionalUser=userRepository.findById(reservationDto.getUserId());
         if(optionalAd.isPresent() && optionalUser.isPresent()){
             Reservation reservation=new Reservation();
             reservation.setBookDate(reservationDto.getBookDate());
             reservation.setReservationStatus(ReservationStatus.PENDING);
             reservation.setUser(optionalUser.get());
             reservation.setAd(optionalAd.get());
             reservation.setCompany(optionalAd.get().getUser());
            reservation.setReviewStatus(ReviewStatus.FALSE);

            reservationRepository.save(reservation);
         }
         return false;
    }
    public AdDetailsForClientDto getAdDetailsByAdId(Long adId){
        Optional<Ad> optionalAd=adRepository.findById(adId);
        AdDetailsForClientDto adDetailsForClientDto=new AdDetailsForClientDto();
        if(optionalAd.isPresent()){
            adDetailsForClientDto.setAdDto(optionalAd.get().getAdDto());
            List<Review> reviewList=reviewRepository.findAllByAdId(adId);
            adDetailsForClientDto.setReviewDtoList(reviewList.stream()
                    .map(Review::getDto).collect(Collectors.toList()));

        }
        return adDetailsForClientDto;
    }

    public List<ReservationDto> getAllBookingsByUserId(Long userId){
        return reservationRepository.findAllByUserId(userId)
                .stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }

    public boolean giveReview(ReviewDto reviewDto){
        Optional<User> optionalUser=userRepository.findById(reviewDto.getUserId());
        Optional<Reservation> optionalBooking=reservationRepository.findById(reviewDto.getBookId());

        if(optionalBooking.isPresent() && optionalUser.isPresent()){
            Review review=new Review();
            review.setReviewDate(new Date());
            review.setReview(reviewDto.getReview());
            review.setRating(reviewDto.getRating());
            review.setUser(optionalUser.get());
            review.setAd(optionalBooking.get().getAd());
            reviewRepository.save(review);

            Reservation booking=optionalBooking.get();
            booking.setReviewStatus(ReviewStatus.TRUE);
            reservationRepository.save(booking);
            return true;

        }
    }

}
