package rmm.deviceservices;

import org.springframework.stereotype.Service;
import rmm.devices.Device;

import java.util.List;
import java.util.Set;

@Service
public class DeviceServicePlanService {

    public DeviceServicePlanService(DeviceServicePlanRepository deviceServicePlanRepository) {

    }

    public int calculateMonthlyBill(List<Device> devices, Set<DeviceServicePlan> servicePlans) {
        // TODO: Fixme, not exactly right for case where customer
        //  has more than 1 device (since services don't all duplicates)...

        // get device type counts ???? then multiple ???

        return servicePlans.stream()
                .map(DeviceServicePlan::getId)
                .map(DeviceServicePlanId::getPrice)
                .mapToInt(Integer::intValue)
                .sum();
    }

}
