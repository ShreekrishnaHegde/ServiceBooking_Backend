package net.firstProject.sbs.service.company;

import jakarta.persistence.Id;
import net.firstProject.sbs.dto.AdDto;
import net.firstProject.sbs.entity.Ad;
import net.firstProject.sbs.entity.User;
import net.firstProject.sbs.repository.AdRepository;
import net.firstProject.sbs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    public boolean postAd(Long userId, AdDto adDto) throws IOException {
        Optional<User> optionalUser=userRepository.findById(userId);
        if(optionalUser.isPresent()){
            Ad ad=new Ad();
            ad.setServiceName(adDto.getServiceName());
            ad.setDescription(adDto.getDescription());
            ad.setImg(adDto.getImg().getBytes());
            ad.setPrice(adDto.getPrice());
            ad.setUser(optionalUser.get());
            adRepository.save(ad);
            return true;
        }
        return false;
    }




}
