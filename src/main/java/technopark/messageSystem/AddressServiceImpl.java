package technopark.messageSystem;

import java.util.HashMap;
import java.util.Map;

import technopark.base.Abonent;
import technopark.base.Address;
import technopark.base.AddressService;

public class AddressServiceImpl implements AddressService {
    private Map<Class<? extends Abonent>, Address> addresses = new HashMap<Class<? extends Abonent>, Address>();

    @Override
    public Address getAddress(Class<? extends Abonent> abonentClass) {
        return addresses.get(abonentClass);
    }

    @Override
    public void setAddress(Class<? extends Abonent> serviceType, Abonent abonent) {
        addresses.put(serviceType, abonent.getAddress());
    }
}
