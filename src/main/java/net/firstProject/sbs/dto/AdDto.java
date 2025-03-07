package net.firstProject.sbs.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AdDto {
    private Long id;

    private String serviceName;

    private String description;

    private Double price;


    private MultipartFile img;

    private byte[] returnedImage;

    private Long UserId;

    private String companyName;
}
