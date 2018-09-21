package java.test;

import java.util.ArrayList;
import java.util.List;

import springwork.dao.PaymentDAO;
import springwork.dao.*;
import springwork.model.*;

public class Test {

	public static void main(String[] args) {
		
//		UserAccountDAO userAccountDAO = new UserAccountDAO();
//		UserAccount userAccount = new UserAccount();
//		BankAccountDAO baDAO = new BankAccountDAO();
//		CreditCardsDAOTest ccDAO = new CreditCardsDAOTest();
//		List<UserAccount> userPayments = new ArrayList<UserAccount>();
		Payment payment = new Payment(2, 20, 45.0, "take my money",20);
		PaymentDAO paymentDAO = new PaymentDAO();
		
		try {
//			userAccount = userAccountDAO.getUserByEmail("allagorik2901@gmail.com", "brain2901");
//			List<BankAccount> allBankAccounts = new ArrayList<BankAccount>();
//	    	allBankAccounts = baDAO.showBankAccounts(userAccount.getUserID());
//			System.out.println(userAccount.getUserID());
//	    	List<CreditCards> allCreditCards = new ArrayList<CreditCards>();
//	    	allCreditCards = ccDAO.showALlCreditCards(userAccount.getUserID());
//			
//			System.out.println("firstName "+userAccount.getFirstName()+" lastName "+userAccount.getLastName()+" joinDate "+userAccount.getJoinDate());
//			System.out.println("Hello from the Test file");
			
//			
//			for (CreditCards cc : allCreditCards) {
//				System.out.println(cc.getAmountAvailable());
//			}
			
//			userPayments= paymentDAO.showALlPayments(2);
//			for (UserAccount p : userPayments) {
//				System.out.println(userAccountDAO.findUserByID(p.getPayment().getFromUserID()).getFirstName()+" "
//			+userAccountDAO.findUserByID(p.getPayment().getFromUserID()).getLastName()+" paid $ "
//			+p.getPayment().getPaymentAmount()+"USD to "
//			+userAccountDAO.findUserByID(p.getPayment().getToUserID()).getFirstName()+" "
//			+userAccountDAO.findUserByID(p.getPayment().getToUserID()).getLastName()+" on "
//			+p.getPayment().getDateTime()
//			);
//			}
			
			
			int result = paymentDAO.addPayment(payment);
			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
	}
		
	}

}
