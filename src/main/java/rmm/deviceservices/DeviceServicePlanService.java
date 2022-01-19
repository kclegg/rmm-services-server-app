package rmm.deviceservices;

import org.springframework.stereotype.Service;
import rmm.customer.customerdevices.CustomerDevice;
import rmm.common.Tuple;
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

    public boolean deviceServicePlanDoesNotExist(String dspId) {
        return !deviceServicePlanRepository.existsById(dspId);
    }

    public DeviceServicePlan saveNewDeviceServicePlan(DeviceServicePlan deviceServicePlan) {
        return deviceServicePlanRepository.save(deviceServicePlan);
    }

    public int calculateMonthlyBill(List<CustomerDevice> devices, Set<DeviceServicePlan> servicePlans) {
        Map<DeviceType, Integer> deviceCounts = devices.stream()
                .map(customerDevice -> new Tuple(customerDevice.getDevice().getType(), customerDevice.getQuantity()))
                .collect(Collectors.groupingBy(Tuple::getDeviceType, Collectors.summingInt(Tuple::getQuantity)));

        int totalDeviceQuantity = (devices.stream().map(CustomerDevice::getQuantity).mapToInt(Integer::intValue).sum());
        int maxDeviceCost = totalDeviceQuantity * STANDARD_DEVICE_FEE;

        int totalDeviceCost = 0;
        int totalServicePlanCost = 0;

        for(DeviceServicePlan servicePlan : servicePlans) {
            if(servicePlan.getDeviceType() == DeviceType.ALL_DEVICES) {
                totalDeviceCost = maxDeviceCost;
                totalServicePlanCost += totalDeviceQuantity * servicePlan.getPrice();
            }
            int deviceQuantity = deviceCounts.getOrDefault(servicePlan.getDeviceType(), 0);

            if(totalDeviceCost != maxDeviceCost) {
                totalDeviceCost += (deviceQuantity * STANDARD_DEVICE_FEE);
            }
            totalServicePlanCost += (deviceQuantity * servicePlan.getPrice());
        }

        return Integer.sum(totalDeviceCost, totalServicePlanCost);
    }

}
