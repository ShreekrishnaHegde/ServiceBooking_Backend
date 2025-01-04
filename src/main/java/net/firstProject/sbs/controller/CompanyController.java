package net.firstProject.sbs.controller;

import net.firstProject.sbs.dto.AdDto;
import net.firstProject.sbs.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping("/ad/{userId}")
    public ResponseEntity<?> postAdd(@PathVariable Long userId, @ModelAttribute AdDto adDto) throws IOException {
        boolean success=companyService.postAd(userId,adDto);
        if(success)
            return ResponseEntity.status(HttpStatus.OK).build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
