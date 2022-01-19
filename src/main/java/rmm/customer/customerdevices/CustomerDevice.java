package rmm.customer.customerdevices;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rmm.devices.Device;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDevice {

    @Id
    @JsonIgnore
    private String id;

    @OneToOne
    private Device device;

    private Integer quantity;
}
