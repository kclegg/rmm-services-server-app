package rmm.devices;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    @Id
    @GeneratedValue
    private String id;

    @NonNull
    private String name;

    @NonNull
    @Enumerated(EnumType.STRING)
    private DeviceType type;

}
