package praktikum.model;


public class Client {
    private String email;
    private String password;
    private String name;



    public Client setEmail(String email) {
        this.email = email;
        return this;
    }

    public Client setPassword(String password) {
        this.password = password;
        return this;
    }

    public Client setName(String name) {
        this.name = name;
        return this;
    }




    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getName() {return name;}
}
