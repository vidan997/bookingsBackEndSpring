package bookingsproject.app.application.dto;

public class CapturePayPalOrderResponseDto {

    private BookingDto booking;
    private String paypalOrderId;
    private String paypalCaptureId;
    private String status;

    public CapturePayPalOrderResponseDto() {
    }

    public CapturePayPalOrderResponseDto(BookingDto booking, String paypalOrderId, String paypalCaptureId, String status) {
        this.booking = booking;
        this.paypalOrderId = paypalOrderId;
        this.paypalCaptureId = paypalCaptureId;
        this.status = status;
    }

    public BookingDto getBooking() { return booking; }
    public void setBooking(BookingDto booking) { this.booking = booking; }

    public String getPaypalOrderId() { return paypalOrderId; }
    public void setPaypalOrderId(String paypalOrderId) { this.paypalOrderId = paypalOrderId; }

    public String getPaypalCaptureId() { return paypalCaptureId; }
    public void setPaypalCaptureId(String paypalCaptureId) { this.paypalCaptureId = paypalCaptureId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}