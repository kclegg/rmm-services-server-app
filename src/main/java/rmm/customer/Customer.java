package rmm.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import rmm.customer.customerdevices.CustomerDevice;
import rmm.deviceservices.DeviceServicePlan;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {

    @Id
    private String id;

    @OneToMany
    private List<CustomerDevice> devices;

    @OneToMany
    private Set<DeviceServicePlan> services;

    @JsonIgnore
    public List<String> getDeviceIds() {
        return devices.stream().map(device -> device.getDevice().getId()).collect(Collectors.toList());
    }

    @JsonIgnore
    public Set<String> getServicePlanIds() {
        return services.stream().map(DeviceServicePlan::getId).collect(Collectors.toSet());
    }
}
