package com.example.phi.service;

import java.io.IOException;
import java.time.LocalDateTime;

public interface StatementService  {

	void generateStatementFile(String walletId, LocalDateTime startDate, LocalDateTime endDate) throws IOException;

}
