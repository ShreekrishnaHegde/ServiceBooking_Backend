package net.firstProject.sbs.service.client;

import net.firstProject.sbs.dto.AdDetailsForClientDto;
import net.firstProject.sbs.dto.AdDto;
import net.firstProject.sbs.dto.ReservationDto;
import net.firstProject.sbs.dto.ReviewDto;

import java.util.List;

public interface ClientService {
    public List<AdDto> getAllAds();
    public List<AdDto> searchAdByName(String name);
    boolean bookService(ReservationDto reservationDto);
    AdDetailsForClientDto getAdDetailsByAdId(Long adId);
    public List<ReservationDto> getAllBookingsByUserId(Long userId);
    public boolean giveReview(ReviewDto reviewDto);

}
