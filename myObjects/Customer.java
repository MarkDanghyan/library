package src.library.myObjects;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

public class Customer {
    private String name;
    private String surname;
    private String password;
    private String encodedPassword;

    public Customer(String name, String surname, String password) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.encodedPassword = encrypt(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) &&
                Objects.equals(surname, customer.surname) &&
                Objects.equals(password, customer.password) &&
                Objects.equals(encodedPassword, customer.encodedPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, password, encodedPassword);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static String encrypt(String string) {
        String encryptedString = BCrypt.gensalt();
        return BCrypt.hashpw(string, encryptedString);
    }
    public static boolean checkPassword(String password, String encryption){
        return BCrypt.checkpw(password, encryption);
    }
}
