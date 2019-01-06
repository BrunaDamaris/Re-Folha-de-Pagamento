package folhadepagamento;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Employees{
	private String name;
	private String address;
	private String type;
	private String paymentmethod;
	private int employeeid;
	private double salary;
	private double payment;
	private String paymentschedule;
	private int paymentday;
	private int paymentmonth;
	private int paymentyear;
	private int paymentdayofweek;
	private int firstDay;
	private int firstMonth;
	private int firstYear;
	private int firstDayOfWeek;
	private String syndicatestatus;
	private int syndicateId;
	private double syndicateTax;
	private double syndicateServiceTax;
	
	public Employees(String ename, String eaddress, String etype, String pmethod,String epschedule,int eid, double esalary, int fday,int fmonth, int fyear, int fdayofweek,String esyndicate,double esyndicatetax) {
		setName(ename);
		setAddress(eaddress);
		setType(etype);
		setPaymentmethod(pmethod);
		setEmployeeid(eid);
		setSalary(esalary);
		setPayment(0);
		setPaymentschedule(epschedule);
		setPaymentday(0);
		setPaymentmonth(0);
		setPaymentyear(0);
		setPaymentdayofweek(0);
		setFirstDay(fday);
		setFirstMonth(fmonth);
		setFirstYear(fyear);
		setFirstDayOfWeek(fdayofweek);
		setSyndicatestatus(esyndicate);
		setSyndicateId(eid+1000);
		setSyndicateTax(esyndicatetax);
		setSyndicateServiceTax(0);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPaymentmethod() {
		return paymentmethod;
	}
	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}
	public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
	public String getPaymentschedule() {
		return paymentschedule;
	}
	public void setPaymentschedule(String paymentschedule) {
		this.paymentschedule = paymentschedule;
	}
	public int getPaymentday() {
		return paymentday;
	}
	public void setPaymentday(int paymentday) {
		this.paymentday = paymentday;
	}
	public int getPaymentmonth() {
		return paymentmonth;
	}
	public void setPaymentmonth(int paymentmonth) {
		this.paymentmonth = paymentmonth;
	}
	public int getPaymentyear() {
		return paymentyear;
	}
	public void setPaymentyear(int paymentyear) {
		this.paymentyear = paymentyear;
	}
	public int getPaymentdayofweek() {
		return paymentdayofweek;
	}
	public void setPaymentdayofweek(int paymentdayofweek) {
		this.paymentdayofweek = paymentdayofweek;
	}
	public int getFirstDay() {
		return firstDay;
	}
	public void setFirstDay(int firstDay) {
		this.firstDay = firstDay;
	}
	public int getFirstMonth() {
		return firstMonth;
	}
	public void setFirstMonth(int firstMonth) {
		this.firstMonth = firstMonth;
	}
	public int getFirstYear() {
		return firstYear;
	}
	public void setFirstYear(int firstYear) {
		this.firstYear = firstYear;
	}
	public int getFirstDayOfWeek() {
		return firstDayOfWeek;
	}
	public void setFirstDayOfWeek(int firstDayOfWeek) {
		this.firstDayOfWeek = firstDayOfWeek;
	}
	public String getSyndicatestatus() {
		return syndicatestatus;
	}
	public void setSyndicatestatus(String syndicatestatus) {
		this.syndicatestatus = syndicatestatus;
	}
	public int getSyndicateId() {
		return syndicateId;
	}
	public void setSyndicateId(int syndicateId) {
		this.syndicateId = syndicateId;
	}
	public double getSyndicateTax() {
		return syndicateTax;
	}
	public void setSyndicateTax(double syndicateTax) {
		this.syndicateTax = syndicateTax;
	}
	public double getSyndicateServiceTax() {
		return syndicateServiceTax;
	}
	public void setSyndicateServiceTax(double syndicateServiceTax) {
		this.syndicateServiceTax += syndicateServiceTax;
	}
	
	public static void addEmployee(Map<Integer,Employees> employee,String ename,String eaddress,String etype,String pmethod,String epschedule,int eid,double esalary,int fday,int fmonth,int fyear,int fdayofweek,String esyndicate,double esyndicatetax,double ecommission) {
		Employees newemployee;
		boolean acceptadd = true;
		//Try
		try {
			etype.equals("assalariado");
		}
		catch(NullPointerException e) {
			System.out.println("Nao foi possivel adicionar.");
			acceptadd = false;
		}
		try {
			etype.equals("horista");
		}
		catch(NullPointerException e) {
			System.out.println("Nao foi possivel adicionar.");
			acceptadd = false;
		}
		try {
			etype.equals("comissionado");
		}
		catch(NullPointerException e) {
			System.out.println("Nao foi possivel adicionar.");
			acceptadd = false;
		}
		if(acceptadd) {
			if(etype.equals("assalariado")) {
				newemployee = new Employees(ename,eaddress,etype,pmethod,epschedule,eid,esalary,fday,fmonth,fyear,fdayofweek,esyndicate,esyndicatetax);
			}
			else if(etype.equals("horista")) {
				newemployee = new Hourly(-1,-1,0,0,ename,eaddress,etype,pmethod,epschedule,eid,esalary,fday,fmonth,fyear,fdayofweek,esyndicate,esyndicatetax);
			}
			else {
				newemployee = new Commissioned(0,0,ename,eaddress,etype,pmethod,epschedule,eid,esalary,fday,fmonth,fyear,fdayofweek,esyndicate,esyndicatetax,ecommission);
			}
			if(employee.size() != 0) {
				if(employee.containsValue(newemployee)) {
						System.out.println("Empregado ja existe.");
				}
				else {
					employee.put(eid,newemployee);
					Payment.SetPaymentSchedule(employee, eid);
				}
			}
			else {
				employee.put(eid,newemployee);
				Payment.SetPaymentSchedule(employee, eid);
			}
		}
		
	}
	
	public static void EditProfile(Map<Integer,Employees> employee,int currentid,Scanner input) {
		String option,trash = null;
		boolean correctInput = false;
		System.out.println("\nO nome atual e: " + employee.get(currentid).getName() + "\nDeseja mudar o nome, se sim pressione 1. Se nao pressione enter");
		option = input.nextLine();
		if(option.equals("1")) {
			String newname;
			System.out.println("Informe o novo nome: ");
			newname = input.nextLine();
			employee.get(currentid).setName(newname);
		}
		System.out.println("\nO endereco atual e: " + employee.get(currentid).getAddress() + "\nDeseja mudar o endereco, se sim pressione 1. Se nao pressione enter");
		option = input.nextLine();
		if(option.equals("1")) {
			String newaddress;
			System.out.println("Informe o novo endereco: ");
			newaddress = input.nextLine();
			employee.get(currentid).setAddress(newaddress);
		}
		System.out.println("\nO tipo atual e: " + employee.get(currentid).getType() + "\nDeseja mudar o tipo, se sim pressione 1. Se nao pressione enter");
		option = input.nextLine();
		if(option.equals("1")) {
			String newtype;
			boolean approve = true;
			System.out.println("Informe o novo tipo(assalariado,horista,comissionado): ");
			newtype = input.nextLine();
			employee.get(currentid).setType(newtype);
			if(employee.get(currentid).getType().equals("assalariado")) {
				if(employee.get(currentid) instanceof Employees) {
					System.out.println("Informe o salario mensal: ");
					double salary = 0;
					//Try
					while(!correctInput) {
						try{
							salary = input.nextDouble();
							correctInput = true;
						}
						catch(NumberFormatException e){
							System.out.println("Valor no formato incorreto.");
						}
						catch(InputMismatchException e) {
							System.out.println("Valor no formato incorreto.");
						}
						if(salary <= 0) {
							System.out.println("Valor nao aceito. Digite novamente: ");
							correctInput = false;
						}
						trash = input.nextLine();
					}
					correctInput = false;
					Employees.addEmployee(employee, employee.get(currentid).getName(), employee.get(currentid).getAddress(), "assalariado", employee.get(currentid).getPaymentmethod(), "mensalmente", currentid, salary, employee.get(currentid).getFirstDay(), employee.get(currentid).getFirstMonth(), employee.get(currentid).getFirstYear(), employee.get(currentid).getFirstDayOfWeek(), employee.get(currentid).getSyndicatestatus(), employee.get(currentid).getSyndicateTax(),0);
				}
				else System.out.println("Nao foi possivel associar.");
			}
			else if(employee.get(currentid).getType().equals("horista")) {
				if(employee.get(currentid) instanceof Employees) {
					System.out.println("Informe o salario por hora: ");
					double salary = 0;
					//Try
					while(!correctInput) {
						try{
							salary = input.nextDouble();
							correctInput = true;
						}
						catch(NumberFormatException e){
							System.out.println("Valor no formato incorreto.");
						}
						catch(InputMismatchException e) {
							System.out.println("Valor no formato incorreto.");
						}
						if(salary <= 0) {
							System.out.println("Valor nao aceito. Digite novamente: ");
							correctInput = false;
						}
						trash = input.nextLine();
					}
					correctInput = false;
					Employees.addEmployee(employee, employee.get(currentid).getName(), employee.get(currentid).getAddress(), "horista", employee.get(currentid).getPaymentmethod(), "semanalmente", currentid,salary, employee.get(currentid).getFirstDay(), employee.get(currentid).getFirstMonth(), employee.get(currentid).getFirstYear(), employee.get(currentid).getFirstDayOfWeek(), employee.get(currentid).getSyndicatestatus(), employee.get(currentid).getSyndicateTax(),0);
				}
				else System.out.println("Nao foi possivel associar.");
			}
			else if(employee.get(currentid).getType().equals("comissionado")) {
				if(employee.get(currentid) instanceof Employees) {
					System.out.println("Informe o salario quinzenal: ");
					double salary = 0;
					//Try
					while(!correctInput) {
						try{
							salary = input.nextDouble();
							correctInput = true;
						}
						catch(NumberFormatException e){
							System.out.println("Valor no formato incorreto.");
						}
						catch(InputMismatchException e) {
							System.out.println("Valor no formato incorreto.");
						}
						if(salary <= 0) {
							System.out.println("Valor nao aceito. Digite novamente: ");
							correctInput = false;
						}
						trash = input.nextLine();
					}
					correctInput = false;
					double commission = 0;
					System.out.println("Informe a comissao(Exemplo: 0,15): ");
					//Try
					while(!correctInput) {
						try{
							commission = input.nextDouble();
							correctInput = true;
						}
						catch(NumberFormatException e){
							System.out.println("Valor no formato incorreto. Digite novamente:");
						}
						catch(InputMismatchException e) {
							System.out.println("Valor no formato incorreto. Digite novamente:");
						}
						if(commission >= 1 || commission <= 0) {
							System.out.println("Valor nao aceito. Digite novamente:");
							correctInput = false;
						}
						trash = input.nextLine();
					}
					correctInput = false;
					Employees.addEmployee(employee, employee.get(currentid).getName(), employee.get(currentid).getAddress(), "comissionado", employee.get(currentid).getPaymentmethod(), "bi-semanalmente", currentid, salary, employee.get(currentid).getFirstDay(), employee.get(currentid).getFirstMonth(), employee.get(currentid).getFirstYear(), employee.get(currentid).getFirstDayOfWeek(), employee.get(currentid).getSyndicatestatus(), employee.get(currentid).getSyndicateTax(),commission);
				}
				else System.out.println("Nao foi possivel associar.");
			}
			else {
				System.out.println("Tipo de empregado nao aceito");
				approve = false;
			}
			if(approve) Payment.SetPaymentSchedule(employee,currentid);
			
		}
		System.out.println("\nO Metodo de pagamento atual e: " + employee.get(currentid).getPaymentmethod() + "\nDeseja mudar o Metodo de pagamento, se sim pressione 1. Se nao pressione enter");
		option = input.nextLine();
		if(option.equals("1")) {
			String newpmethod;
			System.out.println("Informe o novo Metodo de pagamento: ");
			newpmethod = input.nextLine();
			if(newpmethod.equals("cheque")) {
				String optionmethod;
				System.out.println("Cheque em maos(1)\nCheque pelos correios(2)");
				optionmethod = input.nextLine();
				if(optionmethod.equals("1")) {
					newpmethod = "cheque em maos";
					employee.get(currentid).setPaymentmethod(newpmethod);
				}
				else if(optionmethod.equals("2")) {
					newpmethod = "cheque pelos correios";
					employee.get(currentid).setPaymentmethod(newpmethod);
				}
			}
		}
		System.out.println("\nO Salario atual e: " + employee.get(currentid).getSalary() + "\nDeseja mudar o Salario, se sim pressione 1. Se nao pressione enter");
		option = input.nextLine();
		if(option.equals("1")) {
			double newsalary = 0;
			System.out.println("Informe o novo Salario: ");
			//Try
			while(!correctInput) {
				try{
					newsalary = input.nextDouble();
					correctInput = true;
				}
				catch(NumberFormatException e){
					System.out.println("Valor no formato incorreto.");
				}
				catch(InputMismatchException e) {
					System.out.println("Valor no formato incorreto.");
				}
				if(newsalary <= 0) {
					System.out.println("Valor nao aceito. Digite novamente:");
					correctInput = false;
				}
				trash = input.nextLine();
			}
			correctInput = false;
			employee.get(currentid).setSalary(newsalary);
			
		}
		System.out.print("\nO status de Sindicato atual e: " );
		if(employee.get(currentid).getSyndicatestatus().equals("1")) System.out.println("Faz parte de Sindicato" + "\nDeseja mudar o status de Sindicato, se sim pressione 1. Se nao pressione enter");
		else if(employee.get(currentid).getSyndicatestatus().equals("2")) System.out.println("Nao faz parte de Sindicato" + "\nDeseja mudar o status de Sindicato, se sim pressione 1. Se nao pressione enter");
		option = input.nextLine();
		if(option.equals("1")) {
			String entry;
			if(employee.get(currentid).getSyndicatestatus().equals("1")) {
				
				System.out.println("ID do Sindicato: " + employee.get(currentid).getSyndicateId());
				System.out.println("Deseja sair do Sindicato? Se sim,pressione 1. Caso contrario, pressione enter.");
				entry = input.nextLine();
				if(entry.equals("1")) {
					employee.get(currentid).setSyndicatestatus("2");
					employee.get(currentid).setSyndicateId(-1);
					employee.get(currentid).setSyndicateServiceTax(-1);
					employee.get(currentid).setSyndicateTax(-1);
				}
				else {
					System.out.println("Taxa de Sindicato atual: " + employee.get(currentid).getSyndicateTax());
					System.out.println("Deseja modificar a Taxa de Sindicato? Se sim, pressione 1. Caso contrario, pressione enter");
					entry = input.nextLine();
					if(entry.equals("1")) {
						double newtax = 0;
						System.out.println("Informe a nova Taxa de Sindicato: ");
						//Try
						while(!correctInput) {
							try{
								newtax = input.nextDouble();
								correctInput = true;
							}
							catch(NumberFormatException e){
								System.out.println("Valor no formato incorreto.");
							}
							catch(InputMismatchException e) {
								System.out.println("Valor no formato incorreto.");
							}
							if(newtax >= 1 || newtax <= 0) {
								System.out.println("Valor nao aceito. Digite novamente:");
								correctInput = false;
							}
							trash = input.nextLine();
						}
						correctInput = false;
						employee.get(currentid).setSyndicateTax(newtax);
					}
				}
			}
			else if(employee.get(currentid).getSyndicatestatus().equals("2")) {
				System.out.println("Deseja entrar em Sindicato? Se sim,pressione 1. Caso contrario, pressione enter.");
				entry = input.nextLine();
				if(entry.equals("1")) {
					double syndicatetax = 0;
					System.out.println("Informe a Taxa de Sindicato: ");
					//Try
					while(!correctInput) {
						try{
							syndicatetax = input.nextDouble();
							correctInput = true;
						}
						catch(NumberFormatException e){
							System.out.println("Valor no formato incorreto.");
						}
						catch(InputMismatchException e) {
							System.out.println("Valor no formato incorreto.");
						}
						if(syndicatetax >= 1 || syndicatetax <= 0) {
							System.out.println("Valor nao aceito. Digite novamente:");
							correctInput = false;
						}
						trash = input.nextLine();
					}
					correctInput = false;
					employee.get(currentid).setSyndicatestatus("1");
					employee.get(currentid).setSyndicateId(currentid+1000);
					employee.get(currentid).setSyndicateTax(syndicatetax);
				}
			}
		}
		System.out.println("Pressione enter para continuar.");
		trash = input.nextLine();
		System.out.println(trash);
	}
	
	public static void SyndicateCharge(Map<Integer,Employees> employee,int employeeindex) {
		if(employee.get(employeeindex).getSyndicatestatus().equals("1") && employee.get(employeeindex).getPayment() != 0) {
			double salary = employee.get(employeeindex).getPayment();
			double tax =  employee.get(employeeindex).getSyndicateTax();
			double servicetax = employee.get(employeeindex).getSyndicateServiceTax();
			tax = salary * tax;
			salary = salary - tax;
			if(servicetax != 0) {
				servicetax = salary * servicetax;
				salary = salary - servicetax;
			}
			employee.get(employeeindex).setPayment(salary);
		}
	}
	
	public static void CalculatePaymentSalaried(Map<Integer,Employees> employee,int employeeindex) {
		employee.get(employeeindex).setPayment(employee.get(employeeindex).getSalary());
	}
}
