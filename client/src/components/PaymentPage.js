import React, { useEffect } from 'react';

function PaymentPage() {
  useEffect(() => {
    initiatePayment();
  }, []); // Empty dependency array to run the effect only once

  const initiatePayment = async () => {
    try {
      const response = await fetch('http://localhost:8080/pay', {
        method: 'POST',
      })
      .then(response => response.text())
      .then(approvalUrl => {
        // Redirect the user to the PayPal approval URL
        window.location.href = approvalUrl;
      })
    } catch (error) {
      console.error('Error initiating payment:', error);
      // Handle error scenario
    }
  };

  return (
    <div>
      <h1>Payment Page</h1>
    </div>
  );
}

export default PaymentPage;