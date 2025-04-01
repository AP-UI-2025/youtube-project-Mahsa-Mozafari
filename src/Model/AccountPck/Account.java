package Model.AccountPck;

public abstract class Account {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String profileCover;
    private String phoneNumber;

    public Account(String username, String fullName, String phoneNumber, String email) {
        this.username=username;
        this.email=email;
        this.fullName=fullName;
        this.phoneNumber=phoneNumber;
        this.password="";
        this.profileCover="";
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getProfileCover() {
        return profileCover;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfileCover(String profileCover) {
        this.profileCover = profileCover;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

