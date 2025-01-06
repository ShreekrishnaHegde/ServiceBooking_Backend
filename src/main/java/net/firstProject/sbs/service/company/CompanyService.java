package net.firstProject.sbs.service.company;

import net.firstProject.sbs.dto.AdDto;

import java.io.IOException;
import java.util.List;

public interface CompanyService {

    boolean postAd(Long userId, AdDto adDto)  throws IOException;
    List<AdDto> getAllAds(Long userId);
    public AdDto getAdById(Long adId);
}
