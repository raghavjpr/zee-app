import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
//@AllArgsConstructor
//@NoArgsConstructor
public class Manager extends Employee {

	private String projectName;
	private float projectAllowance;

	// we have to check some validations for each and every field.

	public Manager(String empId, String empName, String address, float salary) {
		super(empId, empName, address, salary);
		// TODO Auto-generated constructor stub
	}
	public Manager() {
		// TODO Auto-generated constructor stub
		this("rg001", "Raghav", "Bangalore", 10000.0f);
		// to call the constructor from the same class.

	}
	public Manager(String projectName, float projectAllowance) {
		this("rg001", "Raghav", "Bangalore", 10000.0f);
		this.projectName = projectName;
		this.projectAllowance = projectAllowance;
	}// EDC
	// this method / super method must be the 1st call
	// inside the constructor either u can use this method or super method.

	@Override
	public float calculateSalary() {
		// TODO Auto-generated method stub
		return super.calculateSalary() + this.projectAllowance;
	}

	public void display() {
		System.out.println("this is display method");
	}

}
//Hello 