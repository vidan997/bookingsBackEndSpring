package bookingsproject.app.application.dto;

import jakarta.validation.constraints.NotNull;

public class CapturePayPalOrderRequestDto {

    @NotNull
    private String paypalOrderId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    public CapturePayPalOrderRequestDto() {
    }

    public String getPaypalOrderId() { return paypalOrderId; }
    public void setPaypalOrderId(String paypalOrderId) { this.paypalOrderId = paypalOrderId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
}