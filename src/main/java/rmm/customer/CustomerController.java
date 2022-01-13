package rmm.customer;

import rmm.deviceservices.DeviceServicePlanService;
import rmm.exceptions.NotFoundException;
import rmm.devices.Device;
import rmm.devices.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rmm/customer")
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
        List<Device> devices = Optional.of(customerService.findAllDevicesByCustomerId(customerId))
                .orElseThrow(() -> new NotFoundException("No Customer found by Customer ID: " + customerId));

        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}/computeMonthlyBill")
    public ResponseEntity<Integer> getMonthlyBillByCustomerId(@PathVariable String customerId) {
        Customer customer = findExistingCustomer(customerId);

        // TODO: fixme
        int monthlyBill = deviceServicePlanService.calculateMonthlyBill(customer.getServices());

        return new ResponseEntity<>(monthlyBill, HttpStatus.OK);
    }

    // TODO: @Post Or @Put ..??
    @PostMapping(value = "/{customerId}/addDevices")
    public ResponseEntity<Customer> addDevicesByCustomerId(@PathVariable String customerId, @RequestBody Device device) {
        Customer customer = findExistingCustomer(customerId);

        if(deviceService.deviceDoesNotExist(device.getId())) {
            // TODO: ensure valid device before adding..
            deviceService.saveNewDevice(device);
        }

        customer = customerService.saveNewDevice(customer, device);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PatchMapping(value = "/{customerId}/updateDevices")
    public ResponseEntity<Customer> updateDevicesByCustomerId(@PathVariable String customerId, @RequestBody Device device) {
        Customer customer = findExistingCustomer(customerId);

        if(deviceService.deviceDoesNotExist(device.getId())) {
            throw new NotFoundException("Customer " + customerId + " doesn't contain existing device by id: " + device.getId());
        }

        // TODO: Update existing
//        rmm.customer = customerService.updateExistingDevice();

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{customerId}/deleteDevices")
    public ResponseEntity<Customer> deleteDeviceByCustomerId(@PathVariable String customerId, @RequestParam String deviceId) {
        Customer customer = findExistingCustomer(customerId);
        List<Device> devices = customer.getDevices();

        if(!devices.isEmpty()) {
            customer = customerService.deleteCustomerDevice(customer, deviceId);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    private Customer findExistingCustomer(String customerId) {
        return customerService.findCustomerById(customerId)
                .orElseThrow(() -> new NotFoundException("No Customer found by Customer ID: " + customerId));
    }
}
