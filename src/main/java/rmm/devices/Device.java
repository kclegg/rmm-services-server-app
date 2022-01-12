package rmm.devices;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
public class Device {

    @Id
    private final String id;

    @NonNull
    private final String name;

    @NonNull
    @Enumerated(EnumType.STRING)
    private final DeviceType type;

}
