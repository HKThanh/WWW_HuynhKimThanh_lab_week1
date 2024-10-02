package dto;

public class AccountDTO {
    private String accountId;
    private String fullName;
    private String password;
    private String role;

    public AccountDTO() {
    }

    public AccountDTO(String accountId, String fullName, String password, String role) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "accountId='" + accountId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
