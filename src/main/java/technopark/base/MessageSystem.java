package technopark.base;

public interface MessageSystem {
    //TODO Javadoc
    void addService(Class<? extends Abonent> serviceClass, Abonent abonent);

    void execForAbonent(Abonent abonent);

    void sendMessage(Msg message);

    AddressService getAddressService();

}
