package technopark.base;

public interface AddressService {
    Address getAddress(Class<? extends Abonent> abonentClass);

    void setAddress(Class<? extends Abonent> serviceClass, Abonent abonent);
}
