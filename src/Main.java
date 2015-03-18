import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

    public static void main(String[] args) {

        String str = "";
        Integer intV = null;
        List<String> lst = Arrays.asList("One ", "Two ", "Three ", "Four ", "Five ");
        List<Integer> ints = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);

        String st = lst.get(0).substring(0, 2) + lst.get(1).substring(0, 2);

        //       System.out.println(st);
        //       st= st.substring(0,2).concat(lst.get(1).substring(0,2));

        st = lst.stream().reduce("", (x, y) -> x + y.substring(0, 2));

        //       System.out.println(st);


        Predicate<String> isEmpty = String::isEmpty;

        Predicate<String> isNotEmpty = isEmpty.negate();

        boolean bool = isNotEmpty.negate().test("fff");
        //       System.out.println(bool);


        //       List<String> lstWEmpty = Arrays.asList("first ", "", "", "", "five ");
        //      lstWEmpty.stream().filter(isNotEmpty::test).forEach(System.out::println);


        st = lst.stream().map(String::trim).filter(isEmpty.negate().and(s -> s.length() > 3)).collect(Collectors.joining());
//        System.out.println(st);


        //----------------------------------------------------------

        Stream<Integer> streamInt = ints.stream();
        Integer[] intArr = streamInt.toArray(Integer[]::new);

        //----------------------------------------------------------

        Temp t = new Temp();
        Converter<String, String> conv = Temp::startWith;

        conv.convert("Example");

        //----------------------------------------------------------

        PersonFactory personFactory = Person::new;

        Person person1 = personFactory.create("Дядя", "Вася");
        Person person2 = personFactory.create();

        personFactory = Student::new;
        Person student1 = personFactory.create("Студент", "Петя");

        //----------------------------------------------------------

        Function<String, Integer> strToInteger = Integer::valueOf;
        Function<String, String> strToIntegerToString = strToInteger.andThen(String::valueOf);

        //----------------------------------------------------------

        Optional <Integer> optV = Optional.ofNullable(null);

        //----------------------------------------------------------

//        Stream.generate(Math::random).limit(10L).map((d)-> {
//            d *= 10000; return d.shortValue();}).forEach(System.out::print);

        //----------------------------------------------------------


    }
}

interface Converter<F, T> {
    T convert(F from);
}

class Temp {
    public static String startWith(String s) {
        return String.valueOf(s.charAt(0));
    }

    @Override
    public String toString() {
        return "Class Temp{}";
    }
}

class Person {
    String firstN, lastN;

    public Person() {
    }

    public Person(String firstN, String lastN) {
        this.firstN = firstN;
        this.lastN = lastN;
    }

    @Override
    public String toString() {
        return "Person{" + "firstN = '" + firstN + '\'' + ", lastN = '" + lastN + '\'' + '}';
    }


    public Person toPrintln() {
        System.out.println(toString());
        return this;
    }
}

interface PersonFactory<P extends Person> {
    P create(String firstName, String secondName);

    default P create() {
        return create("NoFirstName", "NoSecondName");
    }
}

class Student extends Person {
    String status;

    public Student(String firstN, String lastN) {
        super(firstN, lastN);
        this.status = "Unknown";
    }

    @Override
    public String toString() {
        return "Student{" +
                "status='" + status + '\'' +
                '}' + "Person{" + "firstN = '" + firstN + '\'' + ", lastN = '" + lastN + '\'' + '}';
    }

    public void setStatus(String status) {
        this.status = status;
    }


}