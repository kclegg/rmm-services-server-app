package rmm.customer;

import rmm.deviceservices.DeviceServicePlan;
import rmm.deviceservices.DeviceServicePlanService;
import rmm.exceptions.NotFoundException;
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

    @GetMapping(value = "/{customerId}/getDevices")
    public ResponseEntity<List<Device>> getDevicesByCustomerId(@PathVariable String customerId) {
        List<Device> devices = customerService.findAllDevicesByCustomerId(customerId);
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
            // TODO: Should we be saving a new DEVICE or only let
            //  them add those which already exist in DEVICE table ???
            deviceService.saveNewDevice(device);
        }

        customer = customerService.saveNewDevice(customer, device);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping(value = "/{customerId}/addDeviceServices")
    public ResponseEntity<Customer> addDeviceServicesByCustomerId(@PathVariable String customerId, @RequestBody DeviceServicePlan deviceService) {
        Customer customer = customerService.findCustomerById(customerId);

        // TODO: implement me
        return null;
    }

    @PutMapping(value = "/{customerId}/updateDevices")
    public ResponseEntity<Customer> updateDevicesByCustomerId(@PathVariable String customerId, @RequestBody Device device) {
        Customer customer = customerService.findCustomerById(customerId);

        if(deviceService.deviceDoesNotExist(device.getId())) {
            throw new NotFoundException("Customer " + customerId + " doesn't contain existing device by id: " + device.getId());
        }

        // TODO: Update existing devices
//        customer = customerService.updateExistingDevice();

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{customerId}/removeDevices")
    public ResponseEntity<Customer> deleteDeviceByCustomerId(@PathVariable String customerId, @RequestParam String deviceId) {
        Customer customer = customerService.findCustomerById(customerId);
        List<Device> devices = customer.getDevices();

        if(!devices.isEmpty()) {
            customer = customerService.deleteCustomerDevice(customer, deviceId);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{customerId}/removeDeviceServices")
    public ResponseEntity<Customer> deleteDeviceServicesByCustomerId(@PathVariable String customerId, @RequestBody DeviceServicePlan deviceService) {

        // TODO: implement me
        return null;
    }
}
