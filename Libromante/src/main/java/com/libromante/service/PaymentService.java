package com.libromante.service;

import com.libromante.model.PaymentCallback;
import com.libromante.model.PaymentDetail;

public interface PaymentService {
	
	public PaymentDetail proceedPayment(PaymentDetail paymentDetail);
	public String payuCallback(PaymentCallback paymentResponse);
	
}
