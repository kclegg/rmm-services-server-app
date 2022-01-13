package rmm.customer;

import rmm.devices.Device;
import rmm.deviceservices.DeviceServicePlan;
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
    private Set<DeviceServicePlan> services;
}
