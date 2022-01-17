package rmm.devices;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    @Id
    private String id;

    @NonNull
    private String name;

    @NonNull
    @Enumerated(EnumType.STRING)
    private DeviceType type;

}
