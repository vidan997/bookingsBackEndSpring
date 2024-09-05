/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsproject.app.application.dto;

import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Munja
 */
public class AuthenticationReponseDto implements ApplicationDto{
     
    @NotNull
    private String email;
    @NotNull
    private long id;
    @NotNull
    private String token;
    @NotNull
    private Date tokenExperationDate;

    public AuthenticationReponseDto(String email, long id, String token, Date tokenExperationDate) {
        this.email = email;
        this.id = id;
        this.token = token;
        this.tokenExperationDate = tokenExperationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenExperationDate() {
        return tokenExperationDate;
    }

    public void setTokenExperationDate(Date tokenExperationDate) {
        this.tokenExperationDate = tokenExperationDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.email);
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 89 * hash + Objects.hashCode(this.token);
        hash = 89 * hash + Objects.hashCode(this.tokenExperationDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AuthenticationReponseDto other = (AuthenticationReponseDto) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.token, other.token)) {
            return false;
        }
        return Objects.equals(this.tokenExperationDate, other.tokenExperationDate);
    }
    
    
}
