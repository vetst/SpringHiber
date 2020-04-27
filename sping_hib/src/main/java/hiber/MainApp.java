package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("bmv", 123);
        Car car2 = new Car("audi", 345);
        Car car3 = new Car("bmv", 543);
        Car car4 = new Car("honda", 756);
        userService.add(new User("Андрей", "Андреевич", "user1@mail.ru", car4));
        userService.add(new User("Сергей", "Сергеевич", "user2@mail.ru", car3));
        userService.add(new User("Олег", "Игоревич", "user3@mail.ru", car2));
        userService.add(new User("Иван", "Иванович", "user4@mail.ru", car1));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }
        System.out.println(userService.getUserByCar("audi", 345));
        context.close();
    }
}
