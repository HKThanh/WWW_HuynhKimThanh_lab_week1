package entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "log")
@NamedQueries({
        @NamedQuery(name = "Log.findByAccountId", query = "select l from Log l where l.accountId = :accountId order by l.loginTime desc limit 1")
})
public class Log {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", nullable = false, length = 50)
    private String accountId;

    @ColumnDefault("current_timestamp()")
    @Column(name = "login_time", nullable = false)
    private Instant loginTime;

    @ColumnDefault("current_timestamp()")
    @Column(name = "logout_time", nullable = false)
    private Instant logoutTime;

    @ColumnDefault("''")
    @Column(name = "notes", nullable = false, length = 250)
    private String notes;

    public Log() {
    }

    public Log(String accountId, Instant loginTime, Instant logoutTime, String notes) {
        this.accountId = accountId;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Instant getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Instant loginTime) {
        this.loginTime = loginTime;
    }

    public Instant getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Instant logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}