package folhadepagamento;

import java.util.Map;

public class Commissioned extends Employees{
	private double commission;
	private double sells;
	
	public Commissioned(double commission,double sells,String ename,String eaddress,String etype,String pmethod,String epschedule,int eid,double esalary,int fday,int fmonth,int fyear,int fdayofweek,String esyndicate,double esyndicatetax,double ecommission) {
		super(ename,eaddress,etype,pmethod,epschedule,eid,esalary,fday,fmonth,fyear,fdayofweek,esyndicate,esyndicatetax);
		setCommission(ecommission);
		setSells(0);
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public double getSells() {
		return sells;
	}

	public void setSells(double sells) {
		this.sells += sells;
	}
	
	public static void CalculatePaymentCommissioned(Map<Integer,Employees> employee,int employeeindex) {
		if(employee.get(employeeindex).getType().equals("comissionado")) {
			if(employee.get(employeeindex) instanceof Employees) {
				Commissioned currentemployee = (Commissioned) employee.get(employeeindex);
				double sellstotal = currentemployee.getSells();
				if(sellstotal > 0) {
					sellstotal = sellstotal * (currentemployee.getCommission());
				}
				double finalsalary = sellstotal + (currentemployee.getSalary());
				currentemployee.setPayment(finalsalary);
			}
			else System.out.println("Nao foi possivel associar o empregado.");
		}
		else System.out.println("Nao e comissionado.");
	}
}
