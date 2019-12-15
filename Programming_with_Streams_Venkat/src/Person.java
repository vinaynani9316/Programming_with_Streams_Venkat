
public class Person 
{
	private final String name;
	private final String gender;
	private final  int age;
	public Person(String name, String gender, int age) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", gender=" + gender + ", age=" + age + "]";
	}
	public int getAge() {
		return age;
	}
	public String getName() {
		return name;
	}
	public String getGender() {
		return gender;
	}
	

}
