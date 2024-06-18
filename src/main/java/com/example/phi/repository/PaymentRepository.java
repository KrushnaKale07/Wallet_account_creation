package com.example.phi.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.phi.model.PaymentModel;

@Repository
public interface PaymentRepository  extends JpaRepository<PaymentModel, Long> {

	List<PaymentModel> findByWalletIdAndTimestampBetween(String walletId, LocalDateTime startDate,
			LocalDateTime endDate);
}
