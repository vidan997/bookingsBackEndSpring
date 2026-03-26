package bookingsproject.app.application.service;

import bookingsproject.app.application.dto.CapturePayPalOrderRequestDto;
import bookingsproject.app.application.dto.CapturePayPalOrderResponseDto;
import bookingsproject.app.application.dto.CreatePayPalOrderRequestDto;
import bookingsproject.app.application.dto.CreatePayPalOrderResponseDto;

public interface PayPalService {

    CreatePayPalOrderResponseDto createOrder(CreatePayPalOrderRequestDto requestDto);

    CapturePayPalOrderResponseDto captureOrder(CapturePayPalOrderRequestDto requestDto);
}