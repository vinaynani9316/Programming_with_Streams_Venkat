import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.groupingBy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class Sample 
{
	public static List<Person> createPeople()
	{
		return Arrays.asList(
				 new Person("Sara", "F", 20),
				 new Person("Sara", "F", 22),
				 new Person("Bob", "M", 20),
				 new Person("Paula", "F", 32),
				 new Person("Paul", "M", 32),
				 new Person("Jack", "M", 2),
				 new Person("Jack", "M", 72),
				 new Person("jill", "F", 12)
				                    );
	}

	public static void main(String[] args) 
	{
		List<Person> people = createPeople();
		
		//get, in upper case, the names of the female older than 18
           List<String> names= new ArrayList<>();
           for(Person person: people)
        	   if(person.getAge() >18)
        		   names.add(person.getName().toUpperCase());
           System.out.println("Age above 18 imperative style:"+names);
           
           //Declarative
           List<String> names2=
        		   people.stream()
        		         .filter(person ->person.getAge() >18)
        		         .map(person ->person.getName()) // or Person::getName
        		         .map(name->name.toUpperCase())  // or String::upperCase
        		         .collect(toList());
           System.out.println("Age above 18 declarative style:"+names2);
           
           System.out.println("**********");
           
           //printing all males
           people.stream()
                 .filter(person-> person.getGender() == "M")
                 .forEach(System.out::println);
           System.out.println("*********");
           
           //print all the males, but with name in upper case of name & lower case of gender
           people.stream()
                 .filter(person -> person.getGender() =="M")
                 .map(person -> new Person (person.getName().toUpperCase(),    // Here stream non mutating the objects
                		 person.getGender().toLowerCase(), person.getAge()))
                 .forEach(System.out::println);
           System.out.println("*********");
           
           //reduce() - total age of every one
           System.out.println("total age :" +people.stream()
                 .map(Person::getAge)                          // or .map(Person::getAge)        .mapToInt(Person::getAge)
                 .reduce(0, (carry, age)-> carry +age));       // or .reduce(0, Integer::sum) or .sum
           
           System.out.println("***************");
           
           // to older person in age  max age & min age
           System.out.println(
        		   people.stream()
        		         .max((person1, person2)->                         // we can get minimum age by using min()
        		          person1.getAge() > person2.getAge() ? 1:-1));
           System.out.println("***************");
           
        	//counting non adults	
          System.out.println("Age below 18 count declarative style:" +people.stream()
                 .filter(person-> person.getAge() <18)
                 .count());
          System.out.println("***************");
          
          
          //getting all the names from list who is above 17 years old
          List<String> names3= new ArrayList<>();
          people.stream()
                .filter(person->person.getAge() >17)
                .map(person ->person.getName().toUpperCase())
                .forEach(name->names3.add(name));                // don't do in for each loop mutating elements
           System.out.println("Age above 17 using forEach method:" +names3);
           System.out.println("***************");
           
           List<String> names4=
        		   people.stream()
        		         .filter(person-> person.getAge() >17)
        		         .map(person -> person.getName().toUpperCase())
        		         .collect(                                              //or toList
        		        		 ()-> new ArrayList<>(),
        		        		 (list, name)->list.add(name),
        		        		 (list1, list2)->list1.addAll(list2));    // here activating concurrent multi threading
           System.out.println("Age above 17 using collect method:" +names4);
           System.out.println("***************");
           
           //list (non-distinctive)
           
           List<String> malesNames=
        		   people.stream()
        		         .filter(person->person.getGender()=="M")
        		         .map(Person::getName)
        		         .collect(toList());
           System.out.println("list of males with duplicates"+malesNames);
           System.out.println("***************");
           
           //set(distinctive) 
           
           Set<String> malesNames1=
        		   people.stream()
        		         .filter(person->person.getGender()=="M")
        		         .map(Person::getName)
        		         .collect(toSet());
           System.out.println("set of males with out duplicates"+malesNames1);
           System.out.println("***************");
           
           //Mapping
           Map<String, Person> map=
        		   people.stream()
        		         .collect(toMap(
        		        		 person->person.getName() + ":" +person.getAge(),   // here name & age are key & person are values.
        		        		 person->person));
           System.out.println("hashMap in streams"+map);
           System.out.println("***************");
           
           //grouping
           Map<String, List<Person>> map1=
        		   people.stream()
        		         .collect(groupingBy(Person::getName));  // here person acts a key.
                   map1.forEach((k,v)->
           System.out.println(k+ "--" +v)
                               );
           System.out.println("***************");        
           
           //find first person whose name is 4 letters but is older than 25
           
           System.out.println(
        		   people.stream()
        		         .filter(person->person.getName().length()==4)
        		         .filter(person->person.getAge() > 25)
        		         .findFirst()
        		   );
           System.out.println("***************"); 
           
           //Infinite values, but limiting to 10 values here
           
           Stream.iterate(1, e-> e+1)
                 .filter(e-> e%2==0)
                 .limit(10)
                 .forEach(System.out::println);
	}
	

}
