import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Data
//@Setter
//@Getter
//@ToString
//@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Employee {


	private String empId;
	private String empName;
	private String address;
	private float salary;

	public float calculateSalary() {
		// to calculate the for an empl.

		return salary + salary*0.1f + salary*0.15f + salary*0.2f;
	}

	public void test() {
		System.out.println("hello from test");
	}

}