package technopark.messageSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import technopark.base.Abonent;
import technopark.base.Address;
import technopark.base.AddressService;
import technopark.base.MessageSystem;
import technopark.base.Msg;

public class MessageSystemImpl implements MessageSystem {
    private Map<Address, ConcurrentLinkedQueue<Msg>> messages = new HashMap<Address, ConcurrentLinkedQueue<Msg>>();

    @Override
    public AddressService getAddressService() {
        return addressService;
    }

    private AddressService addressService = new AddressServiceImpl();

    @Override
    public void sendMessage(Msg message) {
        Queue<Msg> messageQueue = messages.get(message.getTo());
        messageQueue.add(message);
    }

    @Override
    public void execForAbonent(Abonent abonent) {
        Queue<Msg> messageQueue = messages.get(abonent.getAddress());
        if (messageQueue == null) {
            return;
        }
        while (!messageQueue.isEmpty()) {
            Msg message = messageQueue.poll();
            message.exec(abonent);
        }
    }

    @Override
    public void addService(Class<? extends Abonent> serviceClass, Abonent abonent) {
        addressService.setAddress(serviceClass, abonent);
        messages.put(abonent.getAddress(), new ConcurrentLinkedQueue<Msg>());
    }
}
