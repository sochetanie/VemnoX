package springwork.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;

import org.aspectj.apache.bcel.generic.ReturnaddressType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import springwork.dao.*;
import springwork.model.*;

@Controller
@RequestMapping("/")
@SessionAttributes("user")
public class HomeController {
	private HttpSession session;
	private UserAccountDAO uDAO;
	private PaymentDAO pDAO;
	private TransferDAO tDAO;
	private StatementDAO sDAO;
	private CreditCardsDAO ccDAO;
	private BankAccountDAO baDAO;
	private List<BankAccount> allBankAccounts;
	private List<CreditCards> allCreditCards;
	private List<UserAccount> searchUsers;
	private List<UserAccount> allPayments;
	private List<Statement> statements;
	private UserAccount isUserLogin;
	
	@ModelAttribute("user")
	public UserAccount setUpUserAccount() {
		return new UserAccount();
	}
	
	@GetMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("loginSignUp");
        if(mav.equals(null) )
        	mav = new ModelAndView("errorPage");
        return mav;
    }
	
	@GetMapping("home")
    public ModelAndView home(@ModelAttribute("user") UserAccount user, ModelAndView mav, HttpServletRequest request) throws Exception {
		uDAO = new UserAccountDAO();
		isUserLogin = uDAO.getUserByEmail(user.getEmail(), user.getPassword());
		
		if (isUserLogin != null) {
			session = request.getSession();
			session.setAttribute("isUserLogin", isUserLogin);
			mav.addObject("isUserLogin", isUserLogin);
			mav.setViewName("home");
		} else {
			mav.setViewName("loginSignUp");
			session = request.getSession();
			session.setAttribute("errorMessage", "LogIn Again");
		}
		return mav;
    }
	
	@PostMapping("login")
	public ModelAndView login(@ModelAttribute("user") UserAccount user, ModelAndView mav, HttpServletRequest request) throws Exception {
		uDAO = new UserAccountDAO();
		isUserLogin = uDAO.getUserByEmail(user.getEmail(), user.getPassword());
		
		pDAO = new PaymentDAO();
		allPayments = pDAO.showALlPayments(isUserLogin.getUserID());
		
		if (isUserLogin != null) {
			session = request.getSession();
			session.setAttribute("isUserLogin", isUserLogin);
			mav.addObject("allPayments", allPayments);
			mav.addObject("isUserLogin", isUserLogin);
			mav.setViewName("home");
		} else {
			mav.setViewName("loginSignUp");
			session = request.getSession();
			session.setAttribute("errorLogin", "Invalid Email or Password");
		}
		
		return mav;
	}

		
	
	@RequestMapping(value="/signUp", method=RequestMethod.POST)
    public ModelAndView signUp(@ModelAttribute("user") UserAccount user, 
    		ModelAndView mav, HttpServletRequest request) throws Exception {
        
        uDAO = new UserAccountDAO();
        int id = uDAO.registerUser(user);
        
        mav = new ModelAndView("loginSignUp");
        session = request.getSession();
        
        if(id != 0) {
            mav.addObject("loggedUser", user);
			session.setAttribute("successSignUp", "Account have been created, please login");
        } else {
			session.setAttribute("errorSugnUp", "Some error accured, please try again");
        }
        return mav;
    }
	
	
	@RequestMapping("logOut")
	public ModelAndView logOut(HttpServletRequest request, WebRequest webReq, SessionStatus status) {
	    status.setComplete();
	    webReq.removeAttribute("user", WebRequest.SCOPE_SESSION);
		ModelAndView mav = new ModelAndView("loginSignUp");
		mav.getModel().clear();
		return mav;
	}
	
	
    @PostMapping("searchresult")
    public ModelAndView showSearchResult(
    		@ModelAttribute("user") UserAccount user, 
    		@RequestParam("searchInput") String searchResult,
    		ModelAndView mav, HttpServletRequest request) throws SQLException {
    	uDAO = new UserAccountDAO();
    	isUserLogin = uDAO.getUserByEmail(user.getEmail(), user.getPassword());
    	searchUsers = uDAO.findUserByFirstNameAndLastName(searchResult);

    	ccDAO = new CreditCardsDAO();
    	baDAO = new BankAccountDAO();
    	allBankAccounts = baDAO.showBankAccounts(isUserLogin.getUserID());
    	allCreditCards = ccDAO.showALlCreditCards(isUserLogin.getUserID());
    	
    	session = request.getSession();
		session.setAttribute("isUserLogin", isUserLogin);
		
		mav.addObject("allCreditCards", allCreditCards);
		mav.addObject("allBankAccounts", allBankAccounts);
		mav.addObject("searchUsers", searchUsers);
		mav.setViewName("searchResult");
		
		return mav;
    }
   
	
    @RequestMapping(value="/payMoney", method=RequestMethod.POST)
    public ModelAndView payMoney(
    		@ModelAttribute("user") UserAccount user, 
    		@RequestParam("paymentOptionID") int paymentOptionID,  
    		@RequestParam("toUserID") int toUserID, 
    		@RequestParam("paymentAmount") Double paymentAmount,
    		@RequestParam("description") String description,
    		ModelAndView mav, HttpServletRequest request) throws Exception {
    	
    	uDAO = new UserAccountDAO();
		isUserLogin = uDAO.getUserByEmail(user.getEmail(), user.getPassword());
		
		Payment payment = new Payment(isUserLogin.getUserID(), toUserID, paymentAmount, description, paymentOptionID);
		int id = pDAO.addPayment(payment);
		
        
        session = request.getSession();
        session.setAttribute("isUserLogin", isUserLogin);
        
        if(id != 0) {
        	mav = new ModelAndView("home");
        	mav.addObject("isUserLogin", isUserLogin);
			session.setAttribute("paymentSuccess", "Successful Payment!\r\n" + 
					"    Thank you for using Vemnox!");
        } else {
        	mav = new ModelAndView("searchResult");
			session.setAttribute("paymentFailed", "Payment Failed");
        }
        return mav;
    }
    
    
    @GetMapping("transfer")
	public ModelAndView transfer(@ModelAttribute("user") UserAccount user,
    		ModelAndView mav, HttpServletRequest request) throws Exception {
    	
    	uDAO = new UserAccountDAO();
    	isUserLogin = uDAO.getUserByEmail(user.getEmail(), user.getPassword());
    	
    	baDAO = new BankAccountDAO();
    	allBankAccounts = baDAO.showBankAccounts(isUserLogin.getUserID());
    	
    	session = request.getSession();
		session.setAttribute("isUserLogin", isUserLogin);
		
		mav.addObject("allBankAccounts", allBankAccounts);
		mav.setViewName("transfer");
		return mav;
	}
    
    
    @RequestMapping("/transferToBank")
    public ModelAndView transferToBank(
    		@ModelAttribute("user") UserAccount user, 
    		@RequestParam("transferAmount") Double transferAmount,  
    		@RequestParam("banckAccountID") int banckAccountID, 
    		ModelAndView mav, HttpServletRequest request) throws Exception {
    	
    	uDAO = new UserAccountDAO();
		isUserLogin = uDAO.getUserByEmail(user.getEmail(), user.getPassword());	
		
		Transfer transfer = new Transfer(isUserLogin.getUserID(), transferAmount, banckAccountID);
		tDAO = new TransferDAO();
		int id = tDAO.addTransfer(transfer);
		
		baDAO = new BankAccountDAO();
    	allBankAccounts = baDAO.showBankAccounts(isUserLogin.getUserID());
    	
        session = request.getSession();
        session.setAttribute("isUserLogin", isUserLogin);
        
        if(id != 0) {
        	mav = new ModelAndView("transfer");
        	mav.addObject("isUserLogin", isUserLogin);
        	mav.addObject("allBankAccounts", allBankAccounts);
			session.setAttribute("successTransfer", "Transfer Approved!");
        } else {
			session.setAttribute("errorTransfer", "Transfer Failed");
        }
        return mav;
    }

    
    
    @GetMapping("profile")
    public ModelAndView profile(@ModelAttribute("user") UserAccount user, HttpServletRequest request) throws Exception {
    	uDAO = new UserAccountDAO();
    	isUserLogin = uDAO.getUserByEmail(user.getEmail(), user.getPassword());
		ModelAndView mav = new ModelAndView("editDeleteProfile");
		return mav;
	}
    
   
    @GetMapping("statement")
    public ModelAndView statement(@ModelAttribute("user") UserAccount user, HttpServletRequest request) throws Exception {
    	uDAO = new UserAccountDAO();
    	isUserLogin = uDAO.getUserByEmail(user.getEmail(), user.getPassword());
    	
		ModelAndView mav = new ModelAndView("statement");
		
		sDAO = new StatementDAO();
		statements = sDAO.showAllStatement(isUserLogin.getUserID());
		
		mav.addObject("isUserLogin", isUserLogin);
		mav.addObject("statements", statements);
		return mav;
	}
   
    
    
  @RequestMapping(value="specificStatement")
  public ModelAndView specificStatement(
  		@ModelAttribute("user") UserAccount user, 
  		@RequestParam("dateFrom") String fromDate,  
  		@RequestParam("dateTo") String dateTo, 
  		HttpServletRequest request) throws Exception {
  	
  	uDAO = new UserAccountDAO();
  	isUserLogin = uDAO.getUserByEmail(user.getEmail(), user.getPassword());
  	
  	sDAO = new StatementDAO();
  	
  	session = request.getSession();
	session.setAttribute("isUserLogin", isUserLogin);
	
	ModelAndView mav = new ModelAndView("statement");
	
	String[] splitDate = dateTo.split("-");
	int day = Integer.parseInt(splitDate[1]) +1;
	String numberAsString = new Integer(day).toString();
	splitDate[1] = numberAsString;
	String dateToPlusOne = String.join("-", splitDate);
  	
  	if (!fromDate.equals(null) && !dateTo.equals(null)) {      	
      	statements = sDAO.showStatement(isUserLogin.getUserID(), fromDate, dateToPlusOne);
      	mav.addObject("statements", statements);
		} 
  	
      mav.addObject("statements", statements);
      mav.addObject("isUserLogin", isUserLogin);
      return mav;
  }
    
    
    @GetMapping("paymentOptions")
    public ModelAndView showAllPaymentMethods(
    		@ModelAttribute("user") UserAccount user, 
    		ModelAndView mav, HttpServletRequest request) throws Exception {
    	
    	uDAO = new UserAccountDAO();
    	isUserLogin = uDAO.getUserByEmail(user.getEmail(), user.getPassword());
    	
    	baDAO = new BankAccountDAO();
    	ccDAO = new CreditCardsDAO();
    	allBankAccounts = baDAO.showBankAccounts(isUserLogin.getUserID());
    	allCreditCards = ccDAO.showALlCreditCards(isUserLogin.getUserID());
    	
    	session = request.getSession();
		session.setAttribute("isUserLogin", isUserLogin);
		
		mav.addObject("isUserLogin", isUserLogin);
		mav.addObject("allCreditCards", allCreditCards);
		mav.addObject("allBankAccounts", allBankAccounts);
		mav.setViewName("paymentOption");
		return mav;
    }
    
    
    @GetMapping("addPaymentOption")
	public ModelAndView sendToAddCreditCard() {
		ModelAndView mav = new ModelAndView("addPaymentOpt");
		return mav;
	}
  
	
	
	@RequestMapping(value="/newCreditCard", method=RequestMethod.POST)
    public ModelAndView newCreditCard(
    		@ModelAttribute("user") UserAccount user,
    		@RequestParam("amountAvailable") Double amountAvailable,  
    		@RequestParam("cardHolder") String cardHolder, 
    		@RequestParam("cardNumber") String cardNumber,
    		@RequestParam("expirationDate") String expirationDate,
    		@RequestParam("zipCode") String zipCode,
    		@RequestParam("CVV") String CVV,
    		HttpServletRequest request) throws Exception {
		
        uDAO = new UserAccountDAO();
    	isUserLogin = uDAO.getUserByEmail(user.getEmail(), user.getPassword());
        
    	ccDAO = new CreditCardsDAO();
        CreditCards newCC = new CreditCards(amountAvailable, cardHolder, cardNumber, expirationDate, zipCode, CVV, isUserLogin.getUserID());
        int id = ccDAO.addCreditCard(newCC);
        allCreditCards = ccDAO.showALlCreditCards(isUserLogin.getUserID());
        
        baDAO = new BankAccountDAO();
        allBankAccounts = baDAO.showBankAccounts(isUserLogin.getUserID());
        
        session = request.getSession();
        session.setAttribute("isUserLogin", isUserLogin);
        
        ModelAndView mav = new ModelAndView("paymentOption");
        mav.addObject("isUserLogin", isUserLogin);
        mav.addObject("allCreditCards", allCreditCards);
        mav.addObject("allBankAccounts", allBankAccounts);
        mav.setViewName("paymentOption");
        
        if(id != 0) {
			session.setAttribute("successPayOpt", "Credit Card have been added");
        } else {
			session.setAttribute("errorPayOpt", "Failed to add credit card");
        }
        return mav;
    }
	
	
    
	@GetMapping("deleteCreditCard")
	public ModelAndView deleteCreditCard(
			@ModelAttribute("user") UserAccount user, 
			@RequestParam("creditCardID") int creditCardID,  
			HttpServletRequest request) throws Exception {
		
		uDAO = new UserAccountDAO();
    	isUserLogin = uDAO.getUserByEmail(user.getEmail(), user.getPassword());
    	
		ccDAO = new CreditCardsDAO();
		int id = ccDAO.deleteCreditCard(creditCardID);
		
		baDAO = new BankAccountDAO();
    	ccDAO = new CreditCardsDAO();
    	allBankAccounts = baDAO.showBankAccounts(isUserLogin.getUserID());
    	allCreditCards = ccDAO.showALlCreditCards(isUserLogin.getUserID());
		
		ModelAndView mav = new ModelAndView("paymentOption");
		
		session = request.getSession();
		session.setAttribute("isUserLogin", isUserLogin);
		
		mav.addObject("isUserLogin", isUserLogin);
        mav.addObject("allCreditCards", allCreditCards);
        mav.addObject("allBankAccounts", allBankAccounts);

		if(id != 0) {
			session.setAttribute("successPayOpt", "Credit Card have been deleted Successfully!");
		} else {
			session.setAttribute("errorPayOpt", "Failed to delete credit card, Please Try Again");
		}
		
		return mav;
	}
	
	
	
	@RequestMapping(value="/newBankAccount", method=RequestMethod.POST)
    public ModelAndView newBankAccount(@ModelAttribute("user") UserAccount user,
    		@RequestParam("amountAvailable") Double amountAvailable,  
    		@RequestParam("routingNumber") String routingNumber, 
    		@RequestParam("accountNumber") String accountNumber,
    		HttpServletRequest request) throws Exception {
	
        uDAO = new UserAccountDAO();
    	isUserLogin = uDAO.getUserByEmail(user.getEmail(), user.getPassword());
        
    	baDAO = new BankAccountDAO();
        BankAccount bewBA = new BankAccount(amountAvailable, routingNumber, accountNumber, isUserLogin.getUserID());
        int id = baDAO.addBankAccount(bewBA);
        allBankAccounts = baDAO.showBankAccounts(isUserLogin.getUserID());

    	ccDAO = new CreditCardsDAO();
    	allCreditCards = ccDAO.showALlCreditCards(isUserLogin.getUserID());

        session = request.getSession();
        session.setAttribute("isUserLogin", isUserLogin);
        
        ModelAndView mav = new ModelAndView("paymentOption");
        
        mav.addObject("isUserLogin", isUserLogin);
        mav.addObject("allCreditCards",allCreditCards);
        mav.addObject("allBankAccounts",allBankAccounts);
        mav.setViewName("paymentOption");
        
        if(id != 0) {
			session.setAttribute("successPayOpt", "Bank Account have been added");
        } else {
			session.setAttribute("errorPayOpt", "Failed to add Bank Account");
        }
        return mav;

    }
	
	
	
	@GetMapping("deleteBankAccount")
	public ModelAndView deleteBankAccount(
			@ModelAttribute("user") UserAccount user, 
			@RequestParam("bankAccountID") int bankAccountID,  
			HttpServletRequest request) throws Exception {
		
		uDAO = new UserAccountDAO();
    	isUserLogin = uDAO.getUserByEmail(user.getEmail(), user.getPassword());
    	
		baDAO = new BankAccountDAO();
		int id = baDAO.deleteBankAccount(bankAccountID);
		
		ModelAndView mav = new ModelAndView("paymentOption");
		
		baDAO = new BankAccountDAO();
    	ccDAO = new CreditCardsDAO();
    	allBankAccounts = baDAO.showBankAccounts(isUserLogin.getUserID());
    	allCreditCards = ccDAO.showALlCreditCards(isUserLogin.getUserID());
    	
    	session = request.getSession();
		session.setAttribute("isUserLogin", isUserLogin);
		
		mav.addObject("isUserLogin", isUserLogin);
		mav.addObject("allCreditCards", allCreditCards);
		mav.addObject("allBankAccounts", allBankAccounts);
		
		if(id != 0) {
			session.setAttribute("successPayOpt", "Deleted Successfully!");
		} else {
			session.setAttribute("errorPayOpt", "Failed to delete, Please Try Again");
		}
		
		return mav;
	}	
	
	
	
	@PostMapping("updateUserInfo")
	public ModelAndView updateUserInfo(
			@ModelAttribute("user") UserAccount user, 
			HttpServletRequest request) throws Exception {
		
		uDAO = new UserAccountDAO();
    	UserAccount isUserLogin = uDAO.getUserByEmail(user.getEmail(), user.getPassword());
    	
		session = request.getSession();
		
		ModelAndView mav = new ModelAndView("editDeleteProfile");
		int id = uDAO.updateUserInfo(user, isUserLogin.getUserID());
		if(id != 0) {
			session.setAttribute("isUserLogin", isUserLogin);
			session.setAttribute("userInfoUpdated", "Your Changes have been saved!");
		} else {
			session.setAttribute("faildToUpdateUserInfo", "Failed to save your chages, please try again");
		}
		
		return mav;
	}
	
	
	@PostMapping("deleteUser")
	public ModelAndView delete(
			@ModelAttribute("user") UserAccount user, 
			HttpServletRequest request) throws Exception {
		
		ModelAndView mav = new ModelAndView("loginSignUp");
		
		uDAO = new UserAccountDAO();
		int id;
    	UserAccount userA = (UserAccount) session.getAttribute("isUserLogin");
    	id = uDAO.removeUser(userA.getUserID());
		
		if(id != 0) {
			session.setAttribute("user", null);
			session.invalidate();
			session.setAttribute("success", "Your account have been deleted Successfully!");
		} else {
			session = request.getSession();
			session.setAttribute("error", "Failed to delete, Please Try Again");
		}
		
		return mav;
	}
	
	
}


