package folhadepagamento;

import java.util.ArrayList;
import java.util.Map;

public class Hourly extends Employees{
	private int arrivaltime;
	private int exittime;
	private double dailypayment;
	private int checksetofday;
	private ArrayList<Hourly> PaymentDaily = new ArrayList<Hourly>();
	
	
	public Hourly(int arrival,int exit,double paymentofday,int checksetofday,String ename,String eaddress,String etype,String pmethod,String epschedule,int eid,double esalary,int fday,int fmonth,int fyear,int fdayofweek,String esyndicate,double esyndicatetax) {
		super(ename,eaddress,etype,pmethod,epschedule,eid,esalary,fday,fmonth,fyear,fdayofweek,esyndicate,esyndicatetax);
		setArrivaltime(arrival);
		setExittime(exit);
		setDailypayment(paymentofday);
		setChecksetofday(checksetofday);
	}

	public int getArrivaltime() {
		return arrivaltime;
	}

	public void setArrivaltime(int arrivaltime) {
		this.arrivaltime = arrivaltime;
	}

	public int getExittime() {
		return exittime;
	}

	public void setExittime(int exittime) {
		this.exittime = exittime;
	}

	public double getDailypayment() {
		return dailypayment;
	}

	public void setDailypayment(double dailypayment) {
		this.dailypayment = dailypayment;
	}

	public ArrayList<Hourly> getPaymentDaily() {
		return PaymentDaily;
	}

	public void setPaymentDaily(ArrayList<Hourly> paymentDaily) {
		this.PaymentDaily = paymentDaily;
	}
	
	public static void CalculatePaymentHourly(Map<Integer,Employees> employee,int employeeindex) {
		int verifypayment = 0;
		if(employee.get(employeeindex).getType().equals("horista")) {
			if(employee.get(employeeindex) instanceof Employees) {
				boolean acceptindex = true;
				Hourly currentemployee = (Hourly) employee.get(employeeindex);
				for(int i = 0;i < currentemployee.getPaymentDaily().size();i++) {
					//Try
					try {
						currentemployee.getPaymentDaily().get(i).getArrivaltime();
						currentemployee.getPaymentDaily().get(i).getExittime();
					}
					catch(IndexOutOfBoundsException e){
						acceptindex = false;
					}
					if(acceptindex) {
						if((currentemployee.getPaymentDaily().get(i).getArrivaltime() != -1) && (currentemployee.getPaymentDaily().get(i).getExittime() != -1)) {
							verifypayment++;
							int begin = currentemployee.getPaymentDaily().get(i).getArrivaltime();
							int end = currentemployee.getPaymentDaily().get(i).getExittime();
							int totaltime = end-begin;
							if(totaltime <= 0) currentemployee.getPaymentDaily().get(i).setDailypayment(0);
							else if(totaltime <= 8) currentemployee.getPaymentDaily().get(i).setDailypayment(totaltime * (currentemployee.getSalary()));
							else {
								double currentpayment = 8 * (currentemployee.getSalary());
								int extrahours = totaltime - 8;
								double finalpayment = (extrahours*((currentemployee.getSalary())*1.5)) + currentpayment;
								currentemployee.getPaymentDaily().get(i).setDailypayment(finalpayment);
							}
						}
						else currentemployee.getPaymentDaily().get(i).setDailypayment(0);
						acceptindex = true;
					}
				}
				acceptindex = true;
				double finalpaymentsum = 0;
				if(verifypayment == 0) currentemployee.setPayment(0.0);
				else {
					for(int i = 0; i < currentemployee.getPaymentDaily().size();i++) {
						//Try
						try {
							currentemployee.getPaymentDaily().get(i).getDailypayment();
						}
						catch(IndexOutOfBoundsException e){
							acceptindex = false;
						}
						if(acceptindex){
							finalpaymentsum += currentemployee.getPaymentDaily().get(i).getDailypayment();
							acceptindex = true;
						}
					}
					currentemployee.setPayment(finalpaymentsum);
				}
			}
			else System.out.println("Nao foi possivel associar empregado.");
		}
		else System.out.println("Nao e horista.");
	}

	public int getChecksetofday() {
		return checksetofday;
	}

	public void setChecksetofday(int checksetofday) {
		this.checksetofday = checksetofday;
	}
}
