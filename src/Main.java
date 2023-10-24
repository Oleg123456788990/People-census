import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );

        }
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Выберете номер функции: ");
            System.out.println("1.Количество несовершеннолетних !");
            System.out.println("2.Фамилии призывников !");
            System.out.println("3.Список мужчин с высшим образованием !");
            System.out.println("4.Список женщин с высшим образованием !");
            System.out.println("Введите end для выхода из программы !");
            String choiceOsUser = scanner.nextLine();
            if (choiceOsUser.equals("end")) {
                System.out.println("До свидания !");
                break;
            }
            switch (choiceOsUser) {
                case "1":
                    Stream<Person> stream1 = persons.stream();
                    Stream<Person> stream2 = stream1.filter(x -> x.getAge() < 18);
                    long count = stream2.count();
                    System.out.println(count + " человек не достигших 18 лет");
                    System.out.println();
                    break;
                case "2":
                    Stream<Person> stream3 = persons.stream();
                    Stream<Person> stream4 = stream3.filter(x -> x.getAge() >= 18 && x.getAge() <= 27);
                    Stream<Person> stream5 = stream4.filter(x -> x.getSex() == Sex.MAN);
                    List<String> familes = stream5.map(x -> x.getFamily()).collect(Collectors.toList());
                    System.out.println("Список призывников: ");
                    familes.forEach(System.out::println);
                    System.out.println();
                    break;
                case "3":
                    Stream<Person> stream6 = persons.stream();
                    Stream<Person> stream7 = stream6.filter(x -> x.getSex() == Sex.MAN && x.getAge() >= 18 && x.getAge() <= 65);
                    Stream<Person> stream8 = stream7.filter(x -> x.getEducation() == Education.HIGHER);
                    Stream<Person> stream9 = stream8.sorted(Comparator.comparing(Person::getFamily));
                    List<Person> stream10 = stream9.collect(Collectors.toList());
                    stream10.forEach(System.out::println);
                    System.out.println();
                    break;
                case "4":
                    Stream<Person> stream11 = persons.stream();
                    Stream<Person> stream12 = stream11.filter(x -> x.getSex() == Sex.WOMAN && x.getAge() >= 18 && x.getAge() <= 60);
                    Stream<Person> stream13 = stream12.filter(x -> x.getEducation() == Education.HIGHER);
                    Stream<Person> stream14 = stream13.sorted(Comparator.comparing(Person::getFamily));
                    List<Person> stream15 = stream14.collect(Collectors.toList());
                    stream15.forEach(System.out::println);
                    System.out.println();
                    break;

                default:
                    System.out.println("Введен неверный номер функции !");
                    System.out.println();
            }
        }
    }
}