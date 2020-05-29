import java.util.ArrayList;


public class Team {
	
	// You'll need to add methods, constructors and fields here
	
	public String teamName;
	public ArrayList<Student> studentsInTeam;
	public int avgGrade;
	public double teamScore;
	public double teamCount;
	
	
	public Team(String teamName, ArrayList<Student> studentList) {
		this.teamName = teamName;
		this.studentsInTeam = studentList;
		this.teamScore = 0;
		this.teamCount = 0;
	}	
	
	
	public String getName() {
		return this.teamName;
	}
	
	
	public void addToTeam(Student studentToAdd) {
		studentsInTeam.add(studentToAdd);
		System.out.println(studentsInTeam.get(0));
	}
	
	
	public void addGrade(double grade) {
		for (Student studentName: this.studentsInTeam) {
			studentName.addGrade(grade);
		}
		this.teamScore+=grade;
		this.teamCount++;
	}
	
	
	public double avgGrade() {
		return (Math.round(this.teamScore/this.teamCount));
	}

}
