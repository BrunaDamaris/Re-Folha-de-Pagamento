package folhadepagamento;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Undo_Redo {
	private static Stack<Undo_Redo> Undo = new Stack<Undo_Redo>();
	private static Stack<Undo_Redo> Redo = new Stack<Undo_Redo>();
	private String oldoption;
	private int oldday,oldmonth,oldyear,olddayofweek,oldtotalsize,oldpositioninarray;
	private Map<Integer,Employees> oldmap =  new HashMap<Integer,Employees>();
	
	
	public Undo_Redo(String option1,int day,int month,int year,int dayofweek,int totalsize,int positioninarray,Map<Integer,Employees> employee) {
		setOldoption(option1);
		setOldday(day);
		setOldmonth(month);
		setOldyear(year);
		setOlddayofweek(dayofweek);
		setOldmap(employee,totalsize);
		setOldpositioninarray(positioninarray);
	}
	
	public static void Save(String option1,int day,int month,int year,int dayofweek,int totalsize,int positioninarray,Map<Integer,Employees> employee) {
		Undo_Redo undo_redo = new Undo_Redo(option1,day,month,year,dayofweek,totalsize,positioninarray,employee);
		Undo.push(undo_redo);
	}
	
	public static void SaveRedo(String option1,int day,int month,int year,int dayofweek,int totalsize,int positioninarray,Map<Integer,Employees> employee) {
		Undo_Redo undo_redo = new Undo_Redo(option1,day,month,year,dayofweek,totalsize,positioninarray,employee);
		Redo.push(undo_redo);
	}

	public static Undo_Redo undo() {
		Undo_Redo current = null;
		if(Undo.size() != 0) {
			current = Undo.pop();
			if(Undo.size() != 0) current = Undo.pop();
			Redo.push(current);
		}
		return current;
	}

	public static Undo_Redo redo() {
		Undo_Redo current = null;
		if(Redo.size() != 0) {
			current = Redo.pop();
			Undo.push(current);
		}
		return current;
	}

	public String getOldoption() {
		return oldoption;
	}

	public void setOldoption(String oldoption) {
		this.oldoption = oldoption;
	}

	public int getOldmonth() {
		return oldmonth;
	}

	public void setOldmonth(int oldmonth) {
		this.oldmonth = oldmonth;
	}

	public int getOldyear() {
		return oldyear;
	}

	public void setOldyear(int oldyear) {
		this.oldyear = oldyear;
	}

	public int getOldday() {
		return oldday;
	}

	public void setOldday(int oldday) {
		this.oldday = oldday;
	}

	public int getOlddayofweek() {
		return olddayofweek;
	}

	public void setOlddayofweek(int olddayofweek) {
		this.olddayofweek = olddayofweek;
	}

	public int getOldpositioninarray() {
		return oldpositioninarray;
	}

	public void setOldpositioninarray(int oldpositioninarray) {
		this.oldpositioninarray = oldpositioninarray;
	}

	public int getOldtotalsize() {
		return oldtotalsize;
	}

	public void setOldtotalsize(int oldtotalsize) {
		this.oldtotalsize = oldtotalsize;
	}

	public Map<Integer,Employees> getOldmap() {
		return oldmap;
	}

	public void setOldmap(Map<Integer,Employees> employee,int totalsize) {
		//Set the whole Map to save state
		double commission = 0;
		for(int currentid = 1;currentid <= totalsize;currentid++) {
			if(employee.containsKey(currentid)) {
				if(employee.get(currentid) instanceof Employees) {
					if(employee.get(currentid).getType().equals("comissionado")) {
						Commissioned current = (Commissioned) employee.get(currentid);
						commission = current.getCommission();
					}
					Employees.addEmployee(this.oldmap, employee.get(currentid).getName(), employee.get(currentid).getAddress(), employee.get(currentid).getType(), employee.get(currentid).getPaymentmethod(), employee.get(currentid).getPaymentschedule(), currentid, employee.get(currentid).getSalary(), employee.get(currentid).getFirstDay(), employee.get(currentid).getFirstMonth(), employee.get(currentid).getFirstYear(), employee.get(currentid).getFirstDayOfWeek(), employee.get(currentid).getSyndicatestatus(), employee.get(currentid).getSyndicateTax(),commission);
				}
			}
		}
	}
}
