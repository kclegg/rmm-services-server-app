package rmm.deviceservices;

import org.springframework.data.util.Pair;
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

        // TODO: Still need to add initial cost of devices
        //      Total deviceCounts values() * some constant $4 i suppose

        return servicePlans.stream()
                .map(deviceServicePlanId -> Pair.of(deviceServicePlanId.getDeviceType(), deviceServicePlanId.getPrice()))
                .map(pair -> (deviceCounts.getOrDefault(pair.getFirst(), 0) * pair.getSecond()))
                .mapToInt(Integer::intValue)
                .sum();
    }

}
