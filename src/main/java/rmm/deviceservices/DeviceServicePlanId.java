package rmm.deviceservices;

import lombok.Getter;
import rmm.devices.DeviceType;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
@Getter
public class DeviceServicePlanId implements Serializable {
    private static final long serialVersionUID = 4927165050389493969L;

    private String serviceName;

    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    private int price; //TODO: decide if int or double...

}
