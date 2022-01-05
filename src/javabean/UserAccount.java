package javabean;

public class UserAccount {
    public UserAccount(){}
    public UserAccount(String accountName, String address, Integer age) {
        this.accountName = accountName;
        this.address = address;
        this.age = age;
    }

    private String accountName;
    private String address;
    private Integer age;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "accountName='" + accountName + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
