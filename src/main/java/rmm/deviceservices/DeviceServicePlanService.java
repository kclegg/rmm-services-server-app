package rmm.deviceservices;

import org.springframework.stereotype.Service;
import rmm.devices.Device;
import rmm.devices.DeviceType;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DeviceServicePlanService {

    public int calculateMonthlyBill(List<Device> devices, Set<DeviceServicePlan> servicePlans) {
        Map<DeviceType, Integer> deviceCounts = devices.stream()
                .collect(Collectors.groupingBy(Device::getType, Collectors.summingInt(device -> 1)));

        return servicePlans.stream()
                .map(DeviceServicePlan::getId)
                .map(deviceServicePlanId -> (deviceCounts.getOrDefault(deviceServicePlanId.getDeviceType(), 0) * deviceServicePlanId.getPrice()))
                .mapToInt(Integer::intValue)
                .sum();
    }

}
