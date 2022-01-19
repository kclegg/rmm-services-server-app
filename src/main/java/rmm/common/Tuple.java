package rmm.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import rmm.devices.DeviceType;

@Getter
@AllArgsConstructor
public class Tuple {
    private final DeviceType deviceType;
    private final Integer quantity;
}
