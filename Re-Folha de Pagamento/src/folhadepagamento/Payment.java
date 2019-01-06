package folhadepagamento;

import java.util.Map;

public class Payment {
	String Schedule;
	String EmployeeType;
	
	public String getSchedule() {
		return Schedule;
	}
	public void setSchedule(String schedule) {
		this.Schedule = schedule;
	}
	public String getEmployeeType() {
		return EmployeeType;
	}
	public void setEmployeeType(String employeetype) {
		this.EmployeeType = employeetype;
	}
	
	public static void PassDay(Map<Integer,Employees> employee,int day,int month,int year,int dayofweek,int totalsize) {
		System.out.println("Hoje: " + day + "/" + month + "/" + year + " Dia da Semana: " + dayofweek);
		int hasemployees = 0;
		System.out.println("\n-----------FOLHA DE PAGAMENTO----------");
		for(int i = 1;i <= totalsize;i++) {
			if(employee.containsKey(i)) {
				if((employee.get(i).getPaymentday() == day) && (employee.get(i).getPaymentmonth() == month) && (employee.get(i).getPaymentyear() == year) && (employee.get(i).getPaymentdayofweek() == dayofweek)) {
					hasemployees++;
					System.out.println("Nome: " + employee.get(i).getName());
					System.out.println("Endereco: " + employee.get(i).getAddress());
					System.out.println("Tipo: " + employee.get(i).getType());
					System.out.println("O Modo de Pagamento: " + employee.get(i).getPaymentmethod());
					if(employee.get(i).getSyndicatestatus().equals("1")) {
						if(employee.get(i) instanceof Employees) {
							System.out.println("Faz parte de Sindicato");
							System.out.println("ID do Sindicato: " + employee.get(i).getSyndicateId());
							Employees.SyndicateCharge(employee, i);
							System.out.println("Taxa de Sindicato: " + employee.get(i).getSyndicateTax());
							if(employee.get(i).getSyndicateServiceTax() != 0) {
								System.out.println("Taxa de Servico do Sindicato: " + employee.get(i).getSyndicateServiceTax());
								employee.get(i).setSyndicateServiceTax(0);
							}
							else System.out.println("Nao ha Taxas de Servico do Sindicato");
						}
						else System.out.println("Nao foi possivel associar empregado.");
					}
					else System.out.println("Nao faz parte de Sindicato");
					System.out.println("Pagamento: " + employee.get(i).getPayment());
					System.out.println("---------------------------------------");
					employee.get(i).setFirstDay(day);
					employee.get(i).setFirstMonth(month);
					employee.get(i).setFirstYear(year);
					employee.get(i).setFirstDayOfWeek(dayofweek);
					Payment.SetPaymentSchedule(employee,i);
					if(employee.get(i).getType().equals("horista")) {
						if(employee.get(i) instanceof Employees) {
							Hourly currentemployee = (Hourly) employee.get(i);
							currentemployee.getPaymentDaily().clear();
						}
						else System.out.println("Nao foi possivel associar empregado.");
					}
					else if(employee.get(i).getType().equals("comissionado")) {
						if(employee.get(i) instanceof Employees) {
							Commissioned currentemployee = (Commissioned) employee.get(i);
							double currentsells = currentemployee.getSells();
							currentsells = currentsells * -1;
							currentemployee.setSells(currentsells);
						}
						else System.out.println("Nao foi possivel associar empregado.");	
					}
					employee.get(i).setPayment(0);
				}
			}
		}	
		if(hasemployees == 0) {
			System.out.println("Nao ha nenhum funcionario a ser pago hoje");
		}
		System.out.println("---------------------------------------");
	}
	
	public static void SetPaymentSchedule(Map<Integer,Employees> employee,int index) {
		if(employee.get(index).getType().equals("horista")) {
			String currentPaymentSchedule = employee.get(index).getPaymentschedule();
			int DayIWant = 0;
			if(currentPaymentSchedule.equals("semanal 1 segunda")) DayIWant = 2;
			else if(currentPaymentSchedule.equals("semanal 1 terca")) DayIWant = 3;
			else if(currentPaymentSchedule.equals("semanal 1 quarta")) DayIWant = 4;
			else if(currentPaymentSchedule.equals("semanal 1 quinta"))  DayIWant = 5;
			else if(currentPaymentSchedule.equals("semanal 1 sexta") || currentPaymentSchedule.equals("semanalmente")) DayIWant = 6;
				
			int dayp,monthp,yearp,dayofweekp;
			dayp = employee.get(index).getFirstDay();
			monthp = employee.get(index).getFirstMonth();
			yearp = employee.get(index).getFirstYear();
			dayofweekp = employee.get(index).getFirstDayOfWeek(); 
				
			for(int i = 0;i < 7;i++) {
				dayp++;
				dayofweekp++;
				if(dayofweekp == DayIWant) {
					break;
				}
				if(dayofweekp == 8) dayofweekp = 1;
				if(dayp == 29 && monthp == 2) {
					dayp = 1;
					monthp++;
				}
				else if(dayp == 31 && (monthp == 4 || monthp == 6 || monthp == 9 || monthp == 11)) {
					dayp = 1;
					monthp++;
				}
				else if(dayp == 32) {
					dayp = 1;
					monthp++;
				}
				if(monthp == 13) {
					monthp = 1;
					yearp++;
				}
			}
			employee.get(index).setPaymentday(dayp);
			employee.get(index).setPaymentmonth(monthp);
			employee.get(index).setPaymentyear(yearp);
			employee.get(index).setPaymentdayofweek(dayofweekp);
		}
		else if(employee.get(index).getType().equals("assalariado")){
			if(employee.get(index).getPaymentschedule().equals("mensalmente") || employee.get(index).getPaymentschedule().equals("mensal $")) {
				int dayp,monthp,yearp,dayofweekp;
				dayp = employee.get(index).getFirstDay();
				monthp = employee.get(index).getFirstMonth();
				yearp = employee.get(index).getFirstYear();
				dayofweekp = employee.get(index).getFirstDayOfWeek(); 
				for(int i = 0;i < 31;i++) {
					dayp++;
					dayofweekp++;
					if(dayofweekp == 8) dayofweekp = 1;
					if(dayp == 28 && monthp == 2) break;
					else if(dayp == 29 && monthp == 2) {
						dayp = 1;
						monthp++;
					}
					if(dayp == 30 && (monthp == 4 || monthp == 6 || monthp == 9 || monthp == 11)) break;
					else if(dayp == 31 && (monthp == 4 || monthp == 6 || monthp == 9 || monthp == 11)) {
						dayp = 1;
						monthp++;
					}
					else if(dayp == 31) break;
					else if(dayp == 32) {
						dayp = 1;
						monthp++;
					}
					if(monthp == 13) {
						monthp =1;
						yearp++;
					}
				}
				if(dayofweekp == 7) {
					dayp--;
				}
				else if(dayofweekp == 1) {
					dayp -= 2;
				}
				employee.get(index).setPaymentday(dayp);
				employee.get(index).setPaymentmonth(monthp);
				employee.get(index).setPaymentyear(yearp);
				employee.get(index).setPaymentdayofweek(dayofweekp);
			}
			else {
				String currentSchedule = employee.get(index).getPaymentschedule();
				String now = null;
				int DayOFMonth = 0;
				now = currentSchedule.replaceAll("[^0-9]", "");
				DayOFMonth = Integer.parseInt(now);
				
				int dayp,monthp,yearp,dayofweekp;
				dayp = employee.get(index).getFirstDay();
				monthp = employee.get(index).getFirstMonth();
				yearp = employee.get(index).getFirstYear();
				dayofweekp = employee.get(index).getFirstDayOfWeek();
				
				
				
				for(int i = 0;i < 31;i++) {
					if(DayOFMonth == 31 && (monthp == 4 || monthp == 6 || monthp == 9 || monthp == 11)) DayOFMonth = 30;
					else if(DayOFMonth == 31 && monthp == 2) DayOFMonth = 28;
					dayp++;
					dayofweekp++;
					if(dayp == DayOFMonth) break;
					if(dayofweekp == 8) dayofweekp = 1;
					else if(dayp == 29 && monthp == 2) {
						dayp = 1;
						monthp++;
					}
					if(dayp == 31 && (monthp == 4 || monthp == 6 || monthp == 9 || monthp == 11)) {
						dayp = 1;
						monthp++;
					}
					else if(dayp == 32) {
						dayp = 1;
						monthp++;
					}
					if(monthp == 13) {
						monthp = 1;
						yearp++;
					}
				}
				employee.get(index).setPaymentday(dayp);
				employee.get(index).setPaymentmonth(monthp);
				employee.get(index).setPaymentyear(yearp);
				employee.get(index).setPaymentdayofweek(dayofweekp);
			}
		}
		else if(employee.get(index).getType().equals("comissionado")) {
			
			String currentPaymentSchedule = employee.get(index).getPaymentschedule();
			int DayIWant = 0;
			if(currentPaymentSchedule.equals("semanal 2 segunda")) DayIWant = 2;
			else if(currentPaymentSchedule.equals("semanal 2 terca")) DayIWant = 3;
			else if(currentPaymentSchedule.equals("semanal 2 quarta")) DayIWant = 4;
			else if(currentPaymentSchedule.equals("semanal 2 quinta"))  DayIWant = 5;
			else if(currentPaymentSchedule.equals("semanal 2 sexta") || currentPaymentSchedule.equals("bi-semanalmente")) DayIWant = 6;
			
			int dayp,monthp,yearp,dayofweekp,totaldays = 0;
			dayp = employee.get(index).getFirstDay();
			monthp = employee.get(index).getFirstMonth();
			yearp = employee.get(index).getFirstYear();
			dayofweekp = employee.get(index).getFirstDayOfWeek(); 
			
			for(int i = 0;i < 15;i++) {
				dayp++;
				dayofweekp++;
				if(dayofweekp == 8) dayofweekp = 1;
				if(dayofweekp == DayIWant) {
					totaldays++;
				}
				if(dayofweekp == DayIWant && totaldays == 2) {
					break;
				}
				if(dayp == 29 && monthp == 2) {
					dayp = 1;
					monthp++;
				}
				if(dayp == 31 && (monthp == 4 || monthp == 6 || monthp == 9 || monthp == 11)) {
					dayp = 1;
					monthp++;
				}
				else if(dayp == 32) {
					dayp = 1;
					monthp++;
				}
				if(monthp == 13) {
					monthp = 1;
					yearp++;
				}
			}
			employee.get(index).setPaymentday(dayp);
			employee.get(index).setPaymentmonth(monthp);
			employee.get(index).setPaymentyear(yearp);
			employee.get(index).setPaymentdayofweek(dayofweekp);
		}
	}
}
