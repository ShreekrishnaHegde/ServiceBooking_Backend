package net.firstProject.sbs.service.company;

import net.firstProject.sbs.entity.User;
import net.firstProject.sbs.repository.AdRepository;
import net.firstProject.sbs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;


}
