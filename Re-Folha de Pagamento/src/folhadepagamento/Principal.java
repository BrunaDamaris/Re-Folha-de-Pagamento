package folhadepagamento;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Principal{
	public static void main(String[] args) {
		String option1 = null,trash = null;
		Scanner input = new Scanner(System.in);
		int day,month,year,dayofweek,id = 0,totalsize = 0, positioninarray = -1;
		Map<Integer,Employees> employee = new HashMap<Integer,Employees>();
		ArrayList<Payment> PaymentSchedules = new ArrayList<Payment>();
		boolean correctInput = false, acceptentry = false;
		
		//Initialize Current date
		Calendar newCalendar = Calendar.getInstance();
		day = newCalendar.get(Calendar.DAY_OF_MONTH);
		month = newCalendar.get(Calendar.MONTH);
		month++;
		year = newCalendar.get(Calendar.YEAR);
		dayofweek = newCalendar.get(Calendar.DAY_OF_WEEK);
		//Done
		//Initialize Default Payment Schedules
		Payment SetDefault0 = new Payment();
		SetDefault0.setSchedule("semanalmente");
		SetDefault0.setEmployeeType("horista");
		PaymentSchedules.add(SetDefault0);
		
		Payment SetDefault1 = new Payment();
		SetDefault1.setSchedule("mensalmente");
		SetDefault1.setEmployeeType("assalariado");
		PaymentSchedules.add(SetDefault1);
		
		Payment SetDefault2 = new Payment();
		SetDefault2.setSchedule("bi-semanalmente");
		SetDefault2.setEmployeeType("comissionado");
		PaymentSchedules.add(SetDefault2);
		//Done
		Undo_Redo.Save(option1,day,month,year,dayofweek,totalsize,positioninarray,employee);
		while(true) {
			System.out.println("Adicionar Empregado(1)\n\nVisualizar Empregados(2)\n\nRemover Empregado(3)\n\nLancar Cartao de Ponto(4)\n\nLancar Resultado de Venda(5)\n\nLancar Taxa de Servico(6)\n\nAlterar Empregado(7)\n\nRodar a folha de Pagamento(8)\n\nUndo/Redo(9)\n\nAgenda de Pagamento(10)\n\nCriar Agenda de Pagamento(11)\n\nSair(0)\n");
			option1 = input.nextLine();
			//Option 1
			if(option1.equals("1")) {
				String emname = null,emaddress = null ,emtype = null,empmethod = null,empschedule = null,emsyndicate = null;
				double salary = 0,syndicatetax = 0,commission = 0;
				System.out.println("Informe o nome do novo empregado: ");
				emname = input.nextLine();
				System.out.println("Informe o endereco do novo empregado: ");
				emaddress = input.nextLine();
				while(!acceptentry) {
					System.out.println("Informe o tipo do novo empregado(assalariado,horista,comissionado): ");
					emtype = input.nextLine();
					if(emtype.equals("assalariado")) {
						System.out.println("Informe o salario mensal: ");
						empschedule = "mensalmente";
						acceptentry = true;
					}
					else if(emtype.equals("horista")) {
						System.out.println("Informe o salario por hora: ");
						empschedule = "semanalmente";
						acceptentry = true;
					}
					else if(emtype.equals("comissionado")) {
						System.out.println("Informe a comissao(Exemplo: 0,15): ");
						//Try
						while(!correctInput) {
							try{
								commission = input.nextDouble();
								correctInput = true;
							}
							catch(NumberFormatException e){
								input.reset();
								System.out.println("Valor no formato incorreto.");
							}
							catch(InputMismatchException e) {
								input.reset();
								System.out.println("Valor no formato incorreto.");
							}
							if(commission >= 1 || commission <= 0) {
								System.out.println("Valor nao aceito. Digite novamente: ");
								correctInput = false;
							}
							trash = input.nextLine();
						}
						correctInput = false;
						System.out.println("Informe o salario quinzenal: ");
						empschedule = "bi-semanalmente";
						acceptentry = true;
					}
					else {
						System.out.println("Tipo de empregado nao aceito. Digite novamente\n");
					}
					
				}
				acceptentry = false;
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
				System.out.println("Faz parte de algum sindicato, se sim pressione 1. Se nao pressione 2");
				emsyndicate = input.nextLine();
				if(emsyndicate.equals("1")) {
					System.out.println("Informe a Taxa de Sindicato(Exemplo: 0,15): ");
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
				}
				System.out.println("Informe o metodo de pagamento(deposito,cheque): ");
				empmethod = input.nextLine();
				if(empmethod.equals("cheque")) {
					String optionmethod;
					System.out.println("Cheque em maos(1)\nCheque pelos correios(2)");
					optionmethod = input.nextLine();
					if(optionmethod.equals("1")) empmethod = "cheque em maos";
					else if(optionmethod.equals("2")) empmethod = "cheque pelos correios";
				}
				id++;
				totalsize++;
				Employees.addEmployee(employee, emname, emaddress, emtype, empmethod, empschedule,id, salary, day, month, year, dayofweek,emsyndicate,syndicatetax,commission);
				Undo_Redo.Save(option1,day,month,year,dayofweek,totalsize,positioninarray,employee);
				System.out.println("Pressione enter para continuar.");
				trash = input.nextLine();
			}
			//Option 2
			else if(option1.equals("2")) {
				boolean accept = false;
				if(employee.size() != 0) {
					for(int i = 1; i <= totalsize;i++) {
						if(employee.containsKey(i)) {
							accept = true;
							System.out.println("Nome: " + employee.get(i).getName());
							System.out.println("Id: " + employee.get(i).getEmployeeid());
							System.out.println("Tipo: " + employee.get(i).getType());
							System.out.println("Pagamento em: " + employee.get(i).getPaymentday() + "/" + employee.get(i).getPaymentmonth() + "/" + employee.get(i).getPaymentyear() + " -Dia da Semana: " + employee.get(i).getPaymentdayofweek());
							System.out.println("Salario: " + employee.get(i).getSalary());
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
							System.out.println("----------------------------------");
						}
					}
					if(!accept) System.out.println("Nao existem empregados.");
				}
				else System.out.println("Nao existem empregados.");
				
				System.out.println("Pressione enter para continuar.");
				trash = input.nextLine();
			}
			//Option 3
			else if(option1.equals("3")) {
				int currentemployeeid = -1;
				System.out.println("Informe o Id do empregado: ");
				//Try
				while(!correctInput) {
					try{
						currentemployeeid = input.nextInt();
						correctInput = true;
					}
					catch(NumberFormatException e){
						System.out.println("Valor no formato incorreto. Digite novamente:");
					}
					catch(InputMismatchException e) {
						System.out.println("Valor no formato incorreto. Digite novamente:");
					}
					trash = input.nextLine();
				}
				correctInput = false;
				if(employee.containsKey(currentemployeeid)) {
					employee.remove(currentemployeeid);
					System.out.println("Removido");
				}
				else System.out.println("Nao foi possivel encontrar o empregado");
				
				Undo_Redo.Save(option1,day,month,year,dayofweek,totalsize,positioninarray,employee);
				System.out.println("Pressione enter para continuar.");
				trash = input.nextLine();
			}
			//Option 4
			else if(option1.equals("4")) {
				int currentemployeeid = -1;
				System.out.println("Informe o Id do empregado: ");
				//Try
				while(!correctInput) {
					try{
						currentemployeeid = input.nextInt();
						correctInput = true;
					}
					catch(NumberFormatException e){
						System.out.println("Valor no formato incorreto. Digite novamente:");
					}
					catch(InputMismatchException e) {
						System.out.println("Valor no formato incorreto. Digite novamente:");
					}
					trash = input.nextLine();
				}
				correctInput = false;
				if(employee.containsKey(currentemployeeid)) {
					if(employee.get(currentemployeeid).getType().equals("horista")) {
						int arrival = -1,exit = -1;
						if(employee.get(currentemployeeid) instanceof Employees) {
							boolean acceptindex = true;
							Hourly currentemployee = (Hourly) employee.get(currentemployeeid);
							if(currentemployee.getChecksetofday() == 0) {
								Hourly newhourly = new Hourly(-1,-1,0,0,currentemployee.getName(),currentemployee.getAddress(),currentemployee.getType(),currentemployee.getPaymentmethod(),currentemployee.getPaymentschedule(),currentemployee.getEmployeeid(),currentemployee.getSalary(),currentemployee.getFirstDay(),currentemployee.getFirstMonth(),currentemployee.getFirstYear(),currentemployee.getFirstDayOfWeek(),currentemployee.getSyndicatestatus(),currentemployee.getSyndicateTax());
								newhourly.setArrivaltime(-1);
								newhourly.setExittime(-1);
								newhourly.setDailypayment(0);
								currentemployee.getPaymentDaily().add(newhourly);
								currentemployee.setChecksetofday(1);
								positioninarray = currentemployee.getPaymentDaily().size()-1;
							}
							if(currentemployee.getChecksetofday() == 1){
								//Try
								try {
									currentemployee.getPaymentDaily().get(positioninarray).getArrivaltime();
								}
								catch(IndexOutOfBoundsException e) {
									System.out.println("Nao foi possivel acessar.Necessario informar hora de chegada novamente.Selecione a opcao novamente.");
									positioninarray--;
									acceptindex = false;
								}
								if(acceptindex)
								{
									if((currentemployee.getPaymentDaily().get(positioninarray).getArrivaltime() == -1)) {
										System.out.println("Informe a hora de chegada do empregado: ");
										//Try
										while(!correctInput) {
											try{
												arrival = input.nextInt();
												correctInput = true;
											}
											catch(NumberFormatException e){
												System.out.println("Valor no formato incorreto. Digite novamente:");
											}
											catch(InputMismatchException e) {
												System.out.println("Valor no formato incorreto. Digite novamente:");
											}
											trash = input.nextLine();
										}
										correctInput = false;
										currentemployee.getPaymentDaily().get(positioninarray).setArrivaltime(arrival);
										Undo_Redo.Save(option1,day,month,year,dayofweek,totalsize,positioninarray,employee);
									}
									else if(currentemployee.getPaymentDaily().get(positioninarray).getExittime() == -1) {
											System.out.println("Informe a hora de saida do empregado: ");
											//Try
											while(!correctInput) {
												try{
													exit = input.nextInt();
													correctInput = true;
												}
												catch(NumberFormatException e){
													System.out.println("Valor no formato incorreto. Digite novamente:");
												}
												catch(InputMismatchException e) {
													System.out.println("Valor no formato incorreto. Digite novamente:");
												}
												trash = input.nextLine();
											}
											correctInput = false;
											currentemployee.getPaymentDaily().get(positioninarray).setExittime(exit);
											Undo_Redo.Save(option1,day,month,year,dayofweek,totalsize,positioninarray,employee);
									}
								}
							}
						}
						else System.out.println("Nao foi possivel associar empregado.");
					}
					else System.out.println("Empregado nao e horista.");
				}
				else System.out.println("Nao foi possivel encontrar o empregado.");
				
				System.out.println("Pressione enter para continuar.");
				trash = input.nextLine();
			}
			//Option 5
			else if(option1.equals("5")) {
				int currentemployeeid = -1;
				System.out.println("Informe o Id do empregado: ");
				//Try
				while(!correctInput) {
					try{
						currentemployeeid = input.nextInt();
						correctInput = true;
					}
					catch(NumberFormatException e){
						System.out.println("Valor no formato incorreto. Digite novamente:");
					}
					catch(InputMismatchException e) {
						System.out.println("Valor no formato incorreto. Digite novamente:");
					}
					trash = input.nextLine();
				}
				correctInput = false;
				if(employee.containsKey(currentemployeeid)) {
					if(employee.get(currentemployeeid).getType().equals("comissionado")) {
						if(employee.get(currentemployeeid) instanceof Employees) {
							double sellvalue = 0;
							String selldate = null;
							Commissioned currentemployee = (Commissioned) employee.get(currentemployeeid);
							System.out.println("Informe a data da venda(dia/mes/ano): ");
							selldate = input.nextLine();
							System.out.println("Informe o valor da venda: ");
							//Try
							while(!correctInput) {
								try{
									sellvalue = input.nextDouble();
									correctInput = true;
								}
								catch(NumberFormatException e){
									System.out.println("Valor no formato incorreto.");
								}
								catch(InputMismatchException e) {
									System.out.println("Valor no formato incorreto.");
								}
								if(sellvalue <= 0) {
									System.out.println("Valor nao aceito. Digite novamente:");
									correctInput = false;
								}
								trash = input.nextLine();
							}
							correctInput = false;
							System.out.println("Informacoes da venda: " + " - Data da Venda: " + selldate + " - Valor da venda: " + sellvalue);
							currentemployee.setSells(sellvalue);
							Undo_Redo.Save(option1,day,month,year,dayofweek,totalsize,positioninarray,employee);
						}
						else System.out.println("Nao foi possivel associar empregado.Tente novamente");
					}
					else System.out.println("Empregado nao e horista.Tente novamente");
				}
				else System.out.println("Nao foi possivel encontrar o empregado.Tente novamente");
				
				System.out.println("Pressione enter para continuar.");
				trash = input.nextLine();
			}
			//Option 6
			else if(option1.equals("6")) {
				int currentemployeeid = -1;
				System.out.println("Informe a Id do empregado");
				//Try
				while(!correctInput) {
					try{
						currentemployeeid = input.nextInt();
						correctInput = true;
					}
					catch(NumberFormatException e){
						System.out.println("Valor no formato incorreto. Digite novamente:");
					}
					catch(InputMismatchException e) {
						System.out.println("Valor no formato incorreto. Digite novamente:");
					}
					trash = input.nextLine();
				}
				correctInput = false;
				if(employee.containsKey(currentemployeeid)) {
					if(employee.get(currentemployeeid).getSyndicatestatus().equals("1")){
						if(employee.get(currentemployeeid)instanceof Employees) {
							double taxvalue = 0;
							System.out.println("Informe o valor da taxa de servico(Exemplo: 0,15): ");
							//Try
							while(!correctInput) {
								try{
									taxvalue = input.nextDouble();
									correctInput = true;
								}
								catch(NumberFormatException e){
									System.out.println("Valor no formato incorreto.");
								}
								catch(InputMismatchException e) {
									System.out.println("Valor no formato incorreto.");
								}
								if(taxvalue >= 1 || taxvalue <= 0) {
									System.out.println("Valor nao aceito. Digite novamente:");
									correctInput = false;
								}
								trash = input.nextLine();
							}
							correctInput = false;
							employee.get(currentemployeeid).setSyndicateServiceTax(taxvalue);
							Undo_Redo.Save(option1,day,month,year,dayofweek,totalsize,positioninarray,employee);
						}
						else System.out.println("Nao foi possivel associar empregado.");
					}
					else System.out.println("Nao faz parte de Sindicato.");
				}
				else System.out.println("Nao foi possivel encontrar o empregado.");
				
				System.out.println("Pressione enter para continuar.");
				trash = input.nextLine();
			}
			//Option 7
			else if(option1.equals("7")) {
				int currentemployeeid = -1;
				System.out.println("Informe o Id do empregado: ");
				//Try
				while(!correctInput) {
					try{
						currentemployeeid = input.nextInt();
						correctInput = true;
					}
					catch(NumberFormatException e){
						System.out.println("Valor no formato incorreto. Digite novamente:");
					}
					catch(InputMismatchException e) {
						System.out.println("Valor no formato incorreto. Digite novamente:");
					}
					trash = input.nextLine();
				}
				correctInput = false;
				if(employee.containsKey(currentemployeeid)) {
					Employees.EditProfile(employee,currentemployeeid,input);
					Undo_Redo.Save(option1,day,month,year,dayofweek,totalsize,positioninarray,employee);
				}
				else System.out.println("Nao foi possivel encontrar o empregado.");
			}
			//Option 8
			else if(option1.equals("8")) {
				for(int i = 1;i <= totalsize;i++) {
					if(employee.containsKey(i)) {
						if(employee.get(i).getType().equals("assalariado")) Employees.CalculatePaymentSalaried(employee,i);
						if(employee.get(i).getType().equals("horista")) {
							Hourly currentemployee = (Hourly) employee.get(i);
							currentemployee.setChecksetofday(0);
							Hourly.CalculatePaymentHourly(employee,i);
						}
						else if(employee.get(i).getType().equals("comissionado")) Commissioned.CalculatePaymentCommissioned(employee,i);
					}
				}
				Payment.PassDay(employee, day, month, year, dayofweek,totalsize);
				day++;
				dayofweek++;
				if(dayofweek == 8) dayofweek = 1;
				if(day == 29 && month == 2) {
					day = 1;
					month++;
				}
				else if(day == 31 && (month == 4 || month == 6 || month == 9 || month == 11)) {
					day = 1;
					month++;
				}
				else if(day == 32) {
					day = 1;
					month++;
				}
				if(month == 13) {
					month = 1;
					year++;
				}
				Undo_Redo.Save(option1,day,month,year,dayofweek,totalsize,positioninarray,employee);
				System.out.println("Pressione enter para continuar.");
				trash = input.nextLine();
			}
			//Option 9
			else if(option1.equals("9")) {
				String entry;
				System.out.println("Undo ou Redo?");
				entry = input.nextLine();
				if(entry.equals("Undo")) {
					Undo_Redo undo;
					undo = Undo_Redo.undo();
					if(undo != null) {
						Undo_Redo.SaveRedo(option1,day,month,year,dayofweek,totalsize,positioninarray,employee);
						option1 = undo.getOldoption();
						day = undo.getOldday();
						month = undo.getOldmonth();
						year = undo.getOldyear();
						dayofweek = undo.getOlddayofweek();
						employee.clear();
						double commission = 0;
						for(int currentid = 1;currentid <= totalsize;currentid++) {
							if(undo.getOldmap().containsKey(currentid)) {
								if(undo.getOldmap().get(currentid) instanceof Employees) {
									if(undo.getOldmap().get(currentid).getType().equals("comissionado")) {
										Commissioned current = (Commissioned) undo.getOldmap().get(currentid);
										commission = current.getCommission();
									}
									Employees.addEmployee(employee, undo.getOldmap().get(currentid).getName(), undo.getOldmap().get(currentid).getAddress(), undo.getOldmap().get(currentid).getType(), undo.getOldmap().get(currentid).getPaymentmethod(),undo.getOldmap().get(currentid).getPaymentschedule(), currentid, undo.getOldmap().get(currentid).getSalary(), undo.getOldmap().get(currentid).getFirstDay(), undo.getOldmap().get(currentid).getFirstMonth(), undo.getOldmap().get(currentid).getFirstYear(), undo.getOldmap().get(currentid).getFirstDayOfWeek(), undo.getOldmap().get(currentid).getSyndicatestatus(), undo.getOldmap().get(currentid).getSyndicateTax(),commission);
								}
							}
						}
						positioninarray = undo.getOldpositioninarray();
						System.out.println("Undone.");
						Undo_Redo.Save(option1,day,month,year,dayofweek,totalsize,positioninarray,employee);
					}
					else System.out.println("Nao pode Undo.");
					
				}
				else if(entry.equals("Redo")) {
					Undo_Redo redo;
					redo = Undo_Redo.redo();
					if(redo != null) {
						option1 = redo.getOldoption();
						day = redo.getOldday();
						month = redo.getOldmonth();
						year = redo.getOldyear();
						dayofweek = redo.getOlddayofweek();
						employee.clear();
						double commission = 0;
						for(int currentid = 1;currentid <= totalsize;currentid++) {
							if(redo.getOldmap().containsKey(currentid)) {
								if(redo.getOldmap().get(currentid) instanceof Employees) {
									if(redo.getOldmap().get(currentid).getType().equals("comissionado")) {
										Commissioned current = (Commissioned) redo.getOldmap().get(currentid);
										commission = current.getCommission();
									}
									Employees.addEmployee(employee, redo.getOldmap().get(currentid).getName(), redo.getOldmap().get(currentid).getAddress(), redo.getOldmap().get(currentid).getType(), redo.getOldmap().get(currentid).getPaymentmethod(),redo.getOldmap().get(currentid).getPaymentschedule(), currentid, redo.getOldmap().get(currentid).getSalary(), redo.getOldmap().get(currentid).getFirstDay(), redo.getOldmap().get(currentid).getFirstMonth(), redo.getOldmap().get(currentid).getFirstYear(), redo.getOldmap().get(currentid).getFirstDayOfWeek(), redo.getOldmap().get(currentid).getSyndicatestatus(), redo.getOldmap().get(currentid).getSyndicateTax(),commission);
								}
							}
						}
						positioninarray = redo.getOldpositioninarray();
						System.out.println("Redone.");
						Undo_Redo.Save(option1,day,month,year,dayofweek,totalsize,positioninarray,employee);
					}
					else System.out.println("Nao pode Redo.");
				}
				else System.out.println("Entrada Invalida.");
			}
			//Option 10
			else if(option1.equals("10")) {
				int currentemployeeid = -1;
				System.out.println("Informe o Id do empregado: ");
				//Try
				while(!correctInput) {
					try{
						currentemployeeid = input.nextInt();
						correctInput = true;
					}
					catch(NumberFormatException e){
						System.out.println("Valor no formato incorreto. Digite novamente:");
					}
					catch(InputMismatchException e) {
						System.out.println("Valor no formato incorreto. Digite novamente:");
					}
					trash = input.nextLine();
				}
				correctInput = false;
				if(employee.containsKey(currentemployeeid)) {
					if(employee.get(currentemployeeid) instanceof Employees) {
						String entry;
						System.out.println("Agenda de Pagamento atual - O Empregado e pago: " + employee.get(currentemployeeid).getPaymentschedule());
						System.out.println("Deseja modificar a Agenda de Pagamento? Se sim, pressione 1. Se nao, pressione enter");
						entry = input.nextLine();
						if(entry.equals("1")) {
							boolean acceptindex = true;
							System.out.println("Informe uma das opcoes abaixo: ");
							for(int i = 0;i < PaymentSchedules.size();i++) {
								//Try
								try {
									PaymentSchedules.get(i);
								}
								catch(IndexOutOfBoundsException e) {
									acceptindex = false;
								}
								if(acceptindex) {
									if(employee.get(currentemployeeid).getType().equals(PaymentSchedules.get(i).getEmployeeType())) {
										System.out.println(PaymentSchedules.get(i).getSchedule());
									}
									acceptindex = true;
								}
							}
							acceptindex = true;
							boolean validateschedule = false;
							String schedule;
							schedule = input.nextLine();
							for(int i = 0;i < PaymentSchedules.size();i++) {
								//Try
								try {
									PaymentSchedules.get(i);
								}
								catch(IndexOutOfBoundsException e) {
									acceptindex = false;
								}
								if(acceptindex) {
									if(schedule.equals(PaymentSchedules.get(i).getSchedule()) && schedule.equals(PaymentSchedules.get(i).getEmployeeType())) {
										validateschedule = true;
										break;
									}
									acceptindex = true;
								}
							}
							if(validateschedule) {
								if(employee.get(currentemployeeid).getType().equals("assalariado") && (!(schedule.equals("mensalmente") && !(schedule.equals("mensal $"))))) {
									String now = null;
									int DayOFMonth = 0;
									now = schedule.replaceAll("[^0-9]", "");
									DayOFMonth = Integer.parseInt(now);
									if(DayOFMonth > day) {
										employee.get(currentemployeeid).setPaymentschedule(schedule);
										Payment.SetPaymentSchedule(employee, currentemployeeid);
									}
									else System.out.println("Nao foi possivel atualizar, data invalida");
								}
								else {
									employee.get(currentemployeeid).setPaymentschedule(schedule);
									Payment.SetPaymentSchedule(employee, currentemployeeid);
								}
							}
							else System.out.println("Agenda nao existe.");
						}
					}
					else System.out.println("Nao foi possivel associar empregado.");
				}
				else System.out.println("Nao foi possivel encontrar o empregado.");
				System.out.println("Pressione enter para continuar.");
				trash = input.nextLine();
			}
			//Option 11
			else if(option1.equals("11")) {
				boolean verify = true,acceptindex = true;
				String newPaymentSchedule,employeetype;
				System.out.println("Informe a nova Agenda de Pagamento(Exemplo: mensal 12, semanal 1 segunda, semanal 2 terca): ");
				newPaymentSchedule = input.nextLine();
				System.out.println("Para qual tipo de empregado?(assalariado,horista,comissionado): ");
				employeetype = input.nextLine();
				for(int i = 0;i < PaymentSchedules.size();i++) {
					//Try
					try {
						PaymentSchedules.get(i);
					}
					catch(IndexOutOfBoundsException e) {
						acceptindex = false;
					}
					if(acceptindex) {
						if(PaymentSchedules.get(i).getSchedule().equals(newPaymentSchedule) && PaymentSchedules.get(i).getEmployeeType().equals(employeetype)) {
							System.out.println("Agenda ja existe");
							verify = false;
							break;
						}
					}
					
				}
				if(verify) {
					Payment SetNew = new Payment();
					SetNew.setSchedule(newPaymentSchedule);
					SetNew.setEmployeeType(employeetype);
					PaymentSchedules.add(SetNew);
					System.out.println("Criado.");
				}
				System.out.println("Pressione enter para continuar.");
				trash = input.nextLine();
			}
			else if(option1.equals("0")) {
				System.out.println(trash);
				System.out.println("Fim!");
				break;
			}
		}
		input.close();
	}
}
