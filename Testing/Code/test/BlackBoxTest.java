import Core.Clients.*;
import Core.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BlackBoxTest {
    private Address[] generateAddresses(){
        String[] streets = {"testStreet", "groenenborgerlaan", "antwerpsesteenweg", "Hallebaan"};
        String[] numbers = {"1", "123", "321", "999"};
        String[] codes = {"2390", "2980", "9000", "1234"};
        String[] towns = {"testTown", "Westmalle", "Halle", "Antwerpen"};
        Address[] addresses = new Address[4];
        for(int i = 0; i < 4; ++i){
            addresses[i] = new Address(streets[i], numbers[i], codes[i], towns[i]);
        }
        return addresses;
    }

    @Test
    public void AddressTest(){
        String[] streets = {"testStreet", "groenenborgerlaan", "antwerpsesteenweg", "Hallebaan"};
        String[] numbers = {"1", "123", "321", "999"};
        String[] codes = {"2390", "2980", "9000", "1234"};
        String[] towns = {"testTown", "Westmalle", "Halle", "Antwerpen"};
        for(int i = 0; i < 4; ++i){
            Address a = new Address(streets[i], numbers[i], codes[i], towns[i]);
            a.getFullAddress();
            String expected = streets[i] + ' ' + numbers[i] + "\n " + codes[i] + ' ' + towns[i];
            Assertions.assertEquals(expected, a.getFullAddress());
        }
    }

    @Test
    public void ClientTest(){
        String[] names = {"FJ Fekkes", "Benjamin Vandersmissen", "Len Feremans", "Mathias Ooms"};
        Address[] addresses = generateAddresses();
        for(int i = 0; i < 4; ++i){
            Client c = new Client(names[i], addresses[i]);
            Assertions.assertEquals(names[i], c.name());
            Assertions.assertEquals(addresses[i].getFullAddress(), c.address().getFullAddress());
        }

    }

    @Test
    public void RegisteredClientTest(){
        String[] names = {"FJ Fekkes", "Benjamin Vandersmissen", "Len Feremans", "Mathias Ooms"};
        Address[] addresses = generateAddresses();
        String[] passwords = {"test123", "qwerty", "1234", "ytrewq"};
        Mailing_Preferences[] preferences = {new Mailing_Preferences(true, true, true),
                                            new Mailing_Preferences(true, false, true),
                                            new Mailing_Preferences(false, false, false),
                                            new Mailing_Preferences(false, false, true)};


        for(int i = 0; i < 4; ++i){
            RegisteredClient client = new RegisteredClient(names[i], addresses[i], passwords[i], preferences[i]);
            Assertions.assertTrue(client.checkPassword(passwords[i]));

            client.updatePassword(passwords[(i+1)%4]);
            Assertions.assertTrue(client.checkPassword(passwords[(i+1)%4]));

            Assertions.assertEquals(client.getMailPreferences(), preferences[i]);

            client.updateMailPreferences(preferences[(i+1)%4]);
            Assertions.assertEquals(client.getMailPreferences(), preferences[(i+1)%4]);
        }
    }

    @Test
    public void Mailing_PreferencesTest(){
        Mailing_Preferences m1 = new Mailing_Preferences(true, true, true);
        Mailing_Preferences m2 = new Mailing_Preferences(false, false, false);

        Assertions.assertEquals(true, m1.promotions());
        Assertions.assertEquals(true, m1.newsletters());
        Assertions.assertEquals(true, m1.third_party());
        Assertions.assertEquals(false, m2.promotions());
        Assertions.assertEquals(false, m2.newsletters());
        Assertions.assertEquals(false, m2.third_party());

    }

    @Test
    public void ClientsTest(){
        String[] names = {"FJ Fekkes", "Benjamin Vandersmissen", "Len Feremans", "Mathias Ooms"};
        String[] usernames = {"user1", "user2", "user3", "user4"};
        Address[] addresses = generateAddresses();
        String[] passwords = {"test123", "qwerty", "1234", "ytrewq"};
        Mailing_Preferences[] preferences = {new Mailing_Preferences(true, true, true),
                new Mailing_Preferences(true, false, true),
                new Mailing_Preferences(false, false, false),
                new Mailing_Preferences(false, false, true)};
        Clients clients = new Clients();
        for(int i = 0; i < 4; ++ i){
            RegisteredClient regC = new RegisteredClient(names[i], addresses[i], passwords[i], preferences[i]);
            int id = regC.ID();
            clients.AddRegisteredClient(regC, usernames[i]);
            Assertions.assertEquals(regC, clients.getRegisteredClient(usernames[i], passwords[i]));
            Assertions.assertEquals(regC, clients.getClient(id));
        }
        for(int i = 0; i < 4; ++i){
            UnregisteredClient uC = new UnregisteredClient(names[i], addresses[i]);
            int id = uC.ID();
            clients.AddUnregisteredClient(uC);
            Assertions.assertEquals(uC, clients.getClient(id));
        }
    }

    @Test
    public void integrationTests(){
        String[] names = {"FJ Fekkes", "Benjamin Vandersmissen", "Len Feremans", "Mathias Ooms"};
        String[] usernames = {"user1", "user2", "user3", "user4"};
        Address[] addresses = generateAddresses();
        String[] passwords = {"test123", "qwerty", "1234", "ytrewq"};
        Mailing_Preferences[] preferences = {new Mailing_Preferences(true, true, true),
                new Mailing_Preferences(true, false, true),
                new Mailing_Preferences(false, false, false),
                new Mailing_Preferences(false, false, true)};

        Orders orders = new Orders();

        Item[] items = {new Item("ipod", "apple ipod", 999), new Item("iMac", "even pricier", 10000),
                        new Item("nokia", "cheap as dirt", 15), new Item("Rock", "sturdy!", 100000),
                        new Item("junk", "free junk", 0)};

        Cart[] carts = {new Cart(), new Cart(), new Cart(), new Cart()};

        carts[0].addItem(items[0], 10);
        carts[0].addItem(items[1], 10);
        carts[0].addItem(items[2], 10);

        carts[1].addItem(items[3], 1);
        carts[1].addItem(items[4], 1);

        carts[2].addItem(items[0], 3);
        carts[2].addItem(items[2], 3);
        carts[2].addItem(items[4], 3);

        carts[3].addItem(items[1], 7);
        carts[3].addItem(items[3], 5);

        for(int i = 0; i < 4; ++i){
            RegisteredClient regC = new RegisteredClient(names[i], addresses[i], passwords[i], preferences[i]);
            Order order = new Order(carts[0], regC);
            orders.addOrder(order);
            regC.addOrder(order.ID());
            ArrayList<Order> temp = regC.getOpenOrders(orders);
            Assertions.assertEquals(1, temp.size());
            Assertions.assertEquals(order, temp.get(0));
            Assertions.assertEquals(new ArrayList<Order>(), regC.getDeliveredOrders(orders));


        }
    }
}
