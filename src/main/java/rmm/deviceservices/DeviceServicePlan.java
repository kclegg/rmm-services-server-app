package rmm.deviceservices;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import rmm.devices.DeviceType;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceServicePlan {

    @Id
    private String id;

    private String serviceName;

    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    @Min(value = 0, message = "Cannot store negative priced services")
    private int price;

    private String description;
}
