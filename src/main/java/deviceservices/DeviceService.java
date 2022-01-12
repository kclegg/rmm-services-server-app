package deviceservices;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class DeviceService {

    @EmbeddedId
    private DeviceServiceId id;
}
