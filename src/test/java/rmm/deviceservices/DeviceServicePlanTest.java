package rmm.deviceservices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import rmm.customer.customerdevices.CustomerDevice;
import rmm.devices.Device;
import rmm.devices.DeviceType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DeviceServicePlanTest {

    @Mock
    private DeviceServicePlanRepository deviceServicePlanRepository;

    private DeviceServicePlanService deviceServicePlanService;

    @BeforeEach
    public void setup() {
        deviceServicePlanService = new DeviceServicePlanService(deviceServicePlanRepository);
    }

    @Test
    public void testMonthlyBill() {
        List<CustomerDevice> customerDevices = new ArrayList<>();
        customerDevices.add(new CustomerDevice("1", new Device("1", "Device1", DeviceType.WINDOWS_SERVER), 1));
        customerDevices.add(new CustomerDevice("2", new Device("2", "Device2", DeviceType.WINDOWS_WORKSTATION), 1));
        customerDevices.add(new CustomerDevice("3", new Device("3", "Device3", DeviceType.MAC), 1));
        customerDevices.add(new CustomerDevice("4", new Device("4", "Device4", DeviceType.MAC), 1));
        customerDevices.add(new CustomerDevice("5", new Device("5", "Device5", DeviceType.MAC), 1));

        Set<DeviceServicePlan> deviceServicePlans = new HashSet<>();
        deviceServicePlans.add(new DeviceServicePlan("A1", "Antivirus", DeviceType.WINDOWS_SERVER, 5, "software desc"));
        deviceServicePlans.add(new DeviceServicePlan("A2", "Antivirus", DeviceType.WINDOWS_WORKSTATION, 5, "software desc"));
        deviceServicePlans.add(new DeviceServicePlan("A3", "Antivirus", DeviceType.MAC, 7, "software desc"));
        deviceServicePlans.add(new DeviceServicePlan("C1", "Cloudberry", DeviceType.ALL_DEVICES, 3, "software desc"));
        deviceServicePlans.add(new DeviceServicePlan("T1", "TeamView", DeviceType.ALL_DEVICES, 1, "software desc"));

        assertEquals(71, deviceServicePlanService.calculateMonthlyBill(customerDevices, deviceServicePlans));
    }

}
