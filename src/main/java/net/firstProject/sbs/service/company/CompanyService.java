package net.firstProject.sbs.service.company;

import net.firstProject.sbs.dto.AdDto;
import net.firstProject.sbs.dto.ReservationDto;

import java.io.IOException;
import java.util.List;

public interface CompanyService {

    boolean postAd(Long userId, AdDto adDto)  throws IOException;
    List<AdDto> getAllAds(Long userId);
    public AdDto getAdById(Long adId);
    boolean updateAd(Long adId,AdDto adDto) throws IOException;
    public boolean deleteAd(Long adId);
    public List<ReservationDto> getAllAdBookings(Long companyId);
    public  boolean changeBookingStatus(Long bookingId,String status);
}
