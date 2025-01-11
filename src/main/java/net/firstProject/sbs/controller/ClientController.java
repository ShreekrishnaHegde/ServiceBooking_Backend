package net.firstProject.sbs.controller;

import net.firstProject.sbs.dto.ReservationDto;
import net.firstProject.sbs.dto.ReviewDto;
import net.firstProject.sbs.service.client.ClientService;
import org.hibernate.engine.spi.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/ads")
    public ResponseEntity<?> getAllAds(){
        return ResponseEntity.ok(clientService.getAllAds());

    }

//    @GetMapping("/search/name")
//    public ResponseEntity<?> searchAdByService(@PathVariable String name){
//        return ResponseEntity.ok(clientService.searchAdByName(name));
//    }

    @PostMapping("/book-service")
    public ResponseEntity<?> bookService(@RequestBody ReservationDto reservationDto){
        boolean success= clientService.bookService(reservationDto);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else{
            return ResponseEntity.status((HttpStatus.NOT_FOUND)).build();
        }

    }

    @GetMapping("/ad/{adId}")
    public ResponseEntity<?> getAdDetailsByAdId(@PathVariable Long adId){
        return ResponseEntity.ok(getAdDetailsByAdId(adId));
    }

    @GetMapping("/my-bookings/{userId}")
    public ResponseEntity<?> getAllBookingsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(clientService.getAllBookingsByUserId(userId));
    }

    @PostMapping("/review")
    public ResponseEntity<?> giveReview(@RequestBody ReviewDto reviewDto){
        boolean success= clientService.giveReview(reviewDto);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
