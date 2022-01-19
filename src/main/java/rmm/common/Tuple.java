package rmm.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import rmm.devices.DeviceType;

@Getter
@AllArgsConstructor
public class Tuple {
    public DeviceType deviceType;
    public Integer quantity;
}
