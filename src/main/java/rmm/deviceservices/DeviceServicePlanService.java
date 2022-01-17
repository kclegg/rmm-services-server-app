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
    private final DeviceServicePlanRepository deviceServicePlanRepository;

    private final static int STANDARD_DEVICE_FEE = 4;

    public DeviceServicePlanService(DeviceServicePlanRepository deviceServicePlanRepository) {
        this.deviceServicePlanRepository = deviceServicePlanRepository;
    }

    public List<DeviceServicePlan> findAllDeviceServicePlans() {
        return deviceServicePlanRepository.findAll();
    }

    public int calculateMonthlyBill(List<Device> devices, Set<DeviceServicePlan> servicePlans) {
        Map<DeviceType, Integer> deviceCounts = devices.stream()
                .collect(Collectors.groupingBy(Device::getType, Collectors.summingInt(device -> 1)));

        int totalDeviceCost = (STANDARD_DEVICE_FEE * deviceCounts.values().stream().mapToInt(Integer::intValue).sum());

        int totalServicePlanCost = servicePlans.stream()
                .map(deviceServicePlanId -> Pair.of(deviceServicePlanId.getDeviceType(), deviceServicePlanId.getPrice()))
                .map(pair -> (deviceCounts.getOrDefault(pair.getFirst(), 0) * pair.getSecond()))
                .mapToInt(Integer::intValue)
                .sum();

        return Integer.sum(totalDeviceCost, totalServicePlanCost);
    }

}
