package backend.people;

import java.util.Date;

public class Employee extends Person{
    public Employee(
            String name,
            Date birthday,
            String address,
            String email,
            String telephoneNumber)
    {
        super(name, birthday, address, email, telephoneNumber);
    }

    /**
     * Second constructor for recreation of Objects with ID
     * @param name
     * @param birthday
     * @param address
     * @param email
     * @param telephoneNumber
     * @param id
     */
    public Employee(
            String name,
            Date birthday,
            String address,
            String email,
            String telephoneNumber,
            Integer id)
    {
        super(name, birthday, address, email, telephoneNumber, id);
    }

}