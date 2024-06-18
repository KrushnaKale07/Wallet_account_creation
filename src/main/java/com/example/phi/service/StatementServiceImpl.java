package com.example.phi.service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.phi.model.PaymentModel;
import com.example.phi.repository.PaymentRepository;

@Service
public class StatementServiceImpl implements StatementService {

	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public void generateStatementFile(String walletId, LocalDateTime startDate, LocalDateTime endDate)
			throws IOException {
		List<PaymentModel> transactions = paymentRepository.findByWalletIdAndTimestampBetween(walletId, startDate,
				endDate);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedStartDate = startDate.format(formatter);
        String formattedEndDate = endDate.format(formatter);
        String fileName = "statement_" + walletId + "_" + formattedStartDate + "_" + formattedEndDate + ".csv";
		FileWriter fileWriter = new FileWriter(fileName);

		// Writing CSV header
		fileWriter.append("ID,Wallet ID,Description,Amount,Timestamp\n");

		// Writing transaction data
		for (PaymentModel paymentModel : transactions) {

			fileWriter.append(String.valueOf(paymentModel.getId())).append(",").append(paymentModel.getWalletId())
					.append(",").append(paymentModel.getDescription()).append(",")
					.append(String.valueOf(paymentModel.getAmount())).append(",")
					.append(paymentModel.getTimestamp().toString()).append("\n");
		}

		fileWriter.flush();
		fileWriter.close();
	}

}
