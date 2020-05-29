import java.util.ArrayList;


public class Student {
	public String name;
	//TODO: You'll probably need to have some more fields here
	int absenceCount;
	ArrayList<Double> grades;
	double gradeSum;
	double gradeCount;
	
	
	
	
	/**
	 * makes a new student object
	 * 
	 * @param newName the name of the student
	 */
	public Student(String newName) {
		this.name = newName;
		this.gradeSum = 0;
		this.gradeCount = 0;
		this.absenceCount = 0;
	}


	public String getName() {
		return this.name;
	}
	

	public void addAbsence() {
		this.absenceCount+=1;
	}
	
	public void addGrade(double grade) {
		this.gradeSum+= grade;
		this.gradeCount++;
		
	}
	
	public double getAverage() {
		return Math.round(this.gradeSum/this.gradeCount);
	}
	
}
