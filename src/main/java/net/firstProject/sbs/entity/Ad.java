package net.firstProject.sbs.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import net.firstProject.sbs.dto.AdDto;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "ads")
@Data
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceName;

    private String description;

    private Double price;


    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public AdDto getAdDto(){
        AdDto adDto=new AdDto();
        adDto.setId(id);
        adDto.setServiceName(serviceName);
        adDto.setDescription(description);
        adDto.setPrice(price);
        adDto.setCompanyName(user.getName());
        adDto.setReturnedImage(img);

        return adDto;
    }
}
