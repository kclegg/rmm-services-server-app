package rmm.customer;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import rmm.devices.Device;
import rmm.deviceservices.DeviceServicePlan;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    private String id;

    @OneToMany
    private List<Device> devices;

    @OneToMany
    private Set<DeviceServicePlan> services;
}
