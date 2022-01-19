package rmm.customer;

import rmm.customer.customerdevices.CustomerDevice;
import rmm.customer.customerdevices.CustomerDeviceRepository;
import rmm.devices.Device;
import org.springframework.stereotype.Service;
import rmm.deviceservices.DeviceServicePlan;
import rmm.exceptions.InvalidRequestException;
import rmm.exceptions.NotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDeviceRepository customerDeviceRepository;

    public CustomerService(CustomerRepository customerRepository, CustomerDeviceRepository customerDeviceRepository) {
        this.customerRepository = customerRepository;
        this.customerDeviceRepository = customerDeviceRepository;
    }

    public Customer findCustomerById(String customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("No Customer found by Customer ID: " + customerId));
    }

    public List<CustomerDevice> findAllDevicesByCustomerId(String customerId) {
        return findCustomerById(customerId).getDevices();
    }

    public Set<DeviceServicePlan> findAllDeviceServicePlansByCustomerId(String customerId) {
        return findCustomerById(customerId).getServices();
    }

    public Customer updateExistingDevice(Customer customer, Device newDevice) {
        List<String> deviceIds = customer.getDeviceIds();

        if(!deviceIds.contains(newDevice.getId())) {
            throw new NotFoundException("Customer " + customer.getId() + " doesn't contain existing device by id: " + newDevice.getId());
        }

        List<CustomerDevice> devices = customer.getDevices();
        devices.forEach(customerDevice -> {
            Device device = customerDevice.getDevice();

            if(device.getId().equals(newDevice.getId())) {
                device.setName(newDevice.getName());
                device.setType(newDevice.getType());
            }
        });
        customer.setDevices(devices);

        return customerRepository.save(customer);
    }

    public Customer saveNewDevice(Customer customer, Device device) {
        List<CustomerDevice> customerDevices = customer.getDevices();

        int index = IntStream.range(0, customerDevices.size())
                .filter(i -> customerDevices.get(i).getDevice().getId().equals(device.getId()))
                .findFirst()
                .orElse(-1);

        if(index != -1) {
            CustomerDevice customerDevice = customerDevices.get(index);
            customerDevice.setQuantity(customerDevice.getQuantity() + 1);
            customerDevices.set(index, customerDevice);
        } else {
            CustomerDevice customerDevice = new CustomerDevice(device.getId(), device, 1);
            customerDeviceRepository.save(customerDevice);
            customerDevices.add(customerDevice);
        }

        customer.setDevices(customerDevices);
        return customerRepository.save(customer);
    }

    public Customer saveNewDeviceServicePlan(Customer customer, DeviceServicePlan servicePlan) {
        Set<String> dspIds = customer.getServicePlanIds();

        if(Objects.isNull(dspIds) || dspIds.isEmpty() || !dspIds.contains(servicePlan.getId())) {
            Set<DeviceServicePlan> servicePlans = customer.getServices();
            servicePlans.add(servicePlan);
            customer.setServices(servicePlans);
        } else {
            throw new InvalidRequestException("Customer " + customer.getId() + " already contains device service plan " + servicePlan.getId());
        }
        return customerRepository.save(customer);
    }

    public Customer deleteCustomerDevice(Customer customer, String deviceId) {
        List<String> deviceIds = customer.getDeviceIds();

        if(Objects.nonNull(deviceIds) && !deviceIds.isEmpty() && deviceIds.contains(deviceId)) {
            List<CustomerDevice> devices = customer.getDevices();
            devices.removeIf(device -> device.getId().equals(deviceId));
            customer.setDevices(devices);
        } else {
            throw new InvalidRequestException("Customer " + customer.getId() + " does not contain device " + deviceId);
        }
        return customerRepository.save(customer);
    }

    public Customer deleteCustomerDeviceServicePlan(Customer customer, String deviceServicePlanId) {
        Set<String> dspIds = customer.getServicePlanIds();

        if(Objects.nonNull(dspIds) && !dspIds.isEmpty() && dspIds.contains(deviceServicePlanId)) {
            Set<DeviceServicePlan> servicePlans = customer.getServices();
            servicePlans.removeIf(servicePlan -> servicePlan.getId().equals(deviceServicePlanId));
            customer.setServices(servicePlans);
        } else {
            throw new InvalidRequestException("Customer " + customer.getId() + " does not contain device service plan " + deviceServicePlanId);
        }
        return customerRepository.save(customer);
    }
}
