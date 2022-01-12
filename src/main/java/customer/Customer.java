package customer;

import devices.Device;
import devices.DeviceService;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    private String id;

    @OneToMany
    private List<Device> devices;

    @OneToMany
    private Set<DeviceService> services;
}
