package net.firstProject.sbs.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdDetailsForClientDto {
    private AdDto adDto;

    private List<ReviewDto> reviewDtoList;


}
