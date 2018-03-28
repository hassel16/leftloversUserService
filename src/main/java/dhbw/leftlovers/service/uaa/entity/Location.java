package dhbw.leftlovers.service.uaa.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tbl_standort")
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long standortid;

    private String name;
    private Double longitude;
    private Double latitude;
}
