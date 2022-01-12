package rmm.customer;

import rmm.devices.Device;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> findCustomerById(String customerId) {
        return customerRepository.findById(customerId);
    }

    public List<Device> findAllDevicesByCustomerId(String customerId) {
        return findCustomerById(customerId)
                .map(Customer::getDevices)
                .orElse(Collections.emptyList());
    }

    public Customer deleteCustomerDevice(Customer customer, String deviceId) {
        List<Device> devices = customer.getDevices();

        devices.forEach(device -> {
            if(device.getId().equals(deviceId)) {
                devices.removeIf(d -> d.getId().equals(deviceId));
                customer.setDevices(devices);
            }
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
