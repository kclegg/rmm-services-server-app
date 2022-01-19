package rmm.customer;

import rmm.customer.customerdevices.CustomerDevice;
import rmm.deviceservices.DeviceServicePlan;
import rmm.deviceservices.DeviceServicePlanService;
import rmm.devices.Device;
import rmm.devices.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    private final DeviceService deviceService;
    private final CustomerService customerService;
    private final DeviceServicePlanService deviceServicePlanService;

    public CustomerController(DeviceService deviceService, CustomerService customerService, DeviceServicePlanService deviceServicePlanService) {
        this.deviceService = deviceService;
        this.customerService = customerService;
        this.deviceServicePlanService = deviceServicePlanService;
    }

    @GetMapping(value = "/{customerId}/")
    public ResponseEntity<Customer> getCustomerByCustomerId(@PathVariable String customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}/getDevices")
    public ResponseEntity<List<CustomerDevice>> getDevicesByCustomerId(@PathVariable String customerId) {
        List<CustomerDevice> devices = customerService.findAllDevicesByCustomerId(customerId);
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}/getDeviceServices")
    public ResponseEntity<Set<DeviceServicePlan>> getDeviceServicesByCustomerId(@PathVariable String customerId) {
        Set<DeviceServicePlan> deviceServices = customerService.findAllDeviceServicePlansByCustomerId(customerId);
        return new ResponseEntity<>(deviceServices, HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}/computeMonthlyBill")
    public ResponseEntity<Integer> getMonthlyBillByCustomerId(@PathVariable String customerId) {
        Customer customer = customerService.findCustomerById(customerId);

        final int monthlyBill = deviceServicePlanService.calculateMonthlyBill(customer.getDevices(), customer.getServices());

        return new ResponseEntity<>(monthlyBill, HttpStatus.OK);
    }

    @PostMapping(value = "/{customerId}/addDevices")
    public ResponseEntity<Customer> addDevicesByCustomerId(@PathVariable String customerId, @RequestBody Device device) {
        Customer customer = customerService.findCustomerById(customerId);

        if(deviceService.deviceDoesNotExist(device.getId())) {
            deviceService.saveNewDevice(device);
        }

        customer = customerService.saveNewDevice(customer, device);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping(value = "/{customerId}/addDeviceServices")
    public ResponseEntity<Customer> addDeviceServicesByCustomerId(@PathVariable String customerId, @RequestBody DeviceServicePlan deviceServicePlan) {
        Customer customer = customerService.findCustomerById(customerId);

        if(deviceServicePlanService.deviceServicePlanDoesNotExist(deviceServicePlan.getId())) {
            deviceServicePlanService.saveNewDeviceServicePlan(deviceServicePlan);
        }

        customer = customerService.saveNewDeviceServicePlan(customer, deviceServicePlan);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PatchMapping(value = "/{customerId}/updateDevices")
    public ResponseEntity<Customer> updateDevicesByCustomerId(@PathVariable String customerId, @RequestBody Device device) {
        Customer customer = customerService.findCustomerById(customerId);

        customer = customerService.updateExistingDevice(customer, device);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{customerId}/removeDevices")
    public ResponseEntity<Customer> deleteCustomerDeviceByDeviceId(@PathVariable String customerId, @RequestParam String deviceId) {
        Customer customer = customerService.findCustomerById(customerId);

        customer = customerService.deleteCustomerDevice(customer, deviceId);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{customerId}/removeDeviceServices")
    public ResponseEntity<Customer> deleteCustomerDeviceServicesByDeviceServicePlanId(@PathVariable String customerId, @RequestParam String deviceServicePlanId) {
        Customer customer = customerService.findCustomerById(customerId);

        customer = customerService.deleteCustomerDeviceServicePlan(customer, deviceServicePlanId);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
