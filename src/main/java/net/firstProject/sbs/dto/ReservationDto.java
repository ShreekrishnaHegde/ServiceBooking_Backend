package net.firstProject.sbs.dto;

import lombok.Data;
import net.firstProject.sbs.Enums.ReservationStatus;
import net.firstProject.sbs.Enums.ReviewStatus;

import java.util.Date;


@Data
public class ReservationDto {

    private Long id;

    private Date bookDate;

    private String serviceName;

    private ReservationStatus reservationStatus;

    private ReviewStatus reviewStatus;

    private Long userId;

    private String userName;

    private Long companyId;

    private Long adId;

}
