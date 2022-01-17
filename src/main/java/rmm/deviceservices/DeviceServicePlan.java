package rmm.deviceservices;

import lombok.Getter;
import org.hibernate.annotations.SQLInsert;
import rmm.devices.DeviceType;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Getter
@SQLInsert(sql = "/insert-deviceserviceplan-data.sql")
public class DeviceServicePlan {

    @Id
    @GeneratedValue
    private String id;

    private String serviceName;

    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    @Min(value = 0, message = "Cannot store negative priced services")
    private int price;

    private String description;
}
