package net.firstProject.sbs.controller;

import net.firstProject.sbs.dto.AdDto;
import net.firstProject.sbs.dto.ReservationDto;
import net.firstProject.sbs.service.company.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/company")
public class  CompanyController {

    private static final Logger log = LoggerFactory.getLogger(CompanyController.class);
    @Autowired
    private CompanyService companyService;


    @PostMapping("/ad/{userId}")
    public ResponseEntity<?> postAd(@PathVariable Long userId, @ModelAttribute AdDto adDto) throws IOException {
        log.info("Received request to post the ad: {}",userId);
        log.info("Ad detail: {}",adDto);
        boolean success=companyService.postAd(userId,adDto);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/ads/{userId}")
    public ResponseEntity<?> getAllAdsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(companyService.getAllAds(userId));

    }

    @GetMapping("/ad/{adId}")
    public ResponseEntity<?> geAdById(@PathVariable Long adId){
        AdDto adDto=companyService.getAdById(adId);
        if(adDto!=null){
            return ResponseEntity.ok(adDto);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/ad/{adId}")
    public ResponseEntity<?> updateAd(@PathVariable Long adId,@ModelAttribute AdDto adDto) throws IOException {
        boolean success=companyService.updateAd(adId,adDto);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/ad/{adId}")
    public ResponseEntity<?> deleteAd(@PathVariable Long adId){
        boolean success=companyService.deleteAd(adId);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/bookings/{companyId}")
    public ResponseEntity<List<ReservationDto>> getAllAdsBookings(@PathVariable Long companyId){
        return ResponseEntity.ok(companyService.getAllAdBookings(companyId));
    }

    @GetMapping("/booking/{bookingId}/{status}")
    public ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId, @PathVariable String status){
        boolean success=companyService.changeBookingStatus(bookingId,status);
        if(success){
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
