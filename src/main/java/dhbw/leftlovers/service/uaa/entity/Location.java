package dhbw.leftlovers.service.uaa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tbl_standort")
@NoArgsConstructor
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long standortid;

    @Column(name = "name")
    private String long_name;

    @Column(name = "name_details")
    private String name_details;

    @Column(name = "latitude")
    private Double lat;

    @Column(name = "longitude")
    private Double lng;

    public Location(String long_name, String name_details, Double lat, Double lng) {
        this.long_name = long_name;
        this.name_details = name_details;
        this.lat = lat;
        this.lng = lng;
    }
}
