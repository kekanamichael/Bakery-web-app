package com.techgiants.topiefor.dao;

import com.techgiants.topiefor.model.Payment;
import java.util.List;

public interface PaymentDao {

    List<Payment> getAllPayments();

    Payment getPaymentById(int paymentId);

    Payment getPaymentByInvoiceId(int invoiceId);

}
