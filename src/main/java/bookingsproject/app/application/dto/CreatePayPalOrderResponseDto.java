package bookingsproject.app.application.dto;

public class CreatePayPalOrderResponseDto {

    private String orderId;
    private double amountEur;

    public CreatePayPalOrderResponseDto() {
    }

    public CreatePayPalOrderResponseDto(String orderId, double amountEur) {
        this.orderId = orderId;
        this.amountEur = amountEur;
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public double getAmountEur() { return amountEur; }
    public void setAmountEur(double amountEur) { this.amountEur = amountEur; }
}