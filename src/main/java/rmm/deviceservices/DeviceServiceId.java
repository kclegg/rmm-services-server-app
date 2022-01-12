package rmm.deviceservices;

import rmm.devices.DeviceType;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
public class DeviceServiceId implements Serializable {
    private static final long serialVersionUID = 4927165050389493969L;

    private String serviceName;

    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    private int price; //or double...

}
