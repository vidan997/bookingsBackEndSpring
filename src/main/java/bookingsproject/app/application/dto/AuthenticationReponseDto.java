package bookingsproject.app.application.dto;

import jakarta.validation.constraints.NotNull;
import java.util.Objects;

public class AuthenticationReponseDto implements ApplicationDto {

    @NotNull
    private String email;

    @NotNull
    private long id;

    @NotNull
    private String token;
    @NotNull
    private long tokenExperationDate;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String phone;

    public AuthenticationReponseDto() {}

    public AuthenticationReponseDto(
            String email,
            long id,
            String token,
            long tokenExperationDate,
            String firstName,
            String lastName,
            String phone
    ) {
        this.email = email;
        this.id = id;
        this.token = token;
        this.tokenExperationDate = tokenExperationDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public long getTokenExperationDate() { return tokenExperationDate; }
    public void setTokenExperationDate(long tokenExperationDate) { this.tokenExperationDate = tokenExperationDate; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public int hashCode() {
        return Objects.hash(email, id, token, tokenExperationDate, firstName, lastName, phone);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AuthenticationReponseDto other = (AuthenticationReponseDto) obj;
        return id == other.id
                && tokenExperationDate == other.tokenExperationDate
                && Objects.equals(email, other.email)
                && Objects.equals(token, other.token)
                && Objects.equals(firstName, other.firstName)
                && Objects.equals(lastName, other.lastName)
                && Objects.equals(phone, other.phone);
    }
}

