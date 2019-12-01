package com.libromante.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.libromante.entity.Payment;

@Repository("paymentrepository")
public interface PaymentRepository extends CrudRepository<Payment, Serializable>{
	
	Payment findByTxnId(String txnId);

}
