package rmm.deviceservices;

import lombok.Getter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Getter
public class DeviceServicePlan {

    @EmbeddedId
    private DeviceServicePlanId id;

    private String description;
}
