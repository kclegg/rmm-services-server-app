package rmm.customer;

import rmm.devices.Device;
import org.springframework.stereotype.Service;
import rmm.deviceservices.DeviceServicePlan;
import rmm.exceptions.NotFoundException;

import java.util.List;
import java.util.Set;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findCustomerById(String customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("No Customer found by Customer ID: " + customerId));
    }

    public List<Device> findAllDevicesByCustomerId(String customerId) {
        return findCustomerById(customerId).getDevices();
    }

    public Set<DeviceServicePlan> findAllDeviceServicePlansByCustomerId(String customerId) {
        return findCustomerById(customerId).getServices();
    }

    public Customer deleteCustomerDevice(Customer customer, String deviceId) {
        List<Device> devices = customer.getDevices();

        devices.forEach(device -> {
            devices.removeIf(d -> d.getId().equals(deviceId));
            customer.setDevices(devices);
        });

        return customerRepository.save(customer);
    }

    public Customer saveNewDevice(Customer customer, Device device) {
        List<Device> devices = customer.getDevices();
        devices.add(device);
        customer.setDevices(devices);

        return customerRepository.save(customer);
    }

}
