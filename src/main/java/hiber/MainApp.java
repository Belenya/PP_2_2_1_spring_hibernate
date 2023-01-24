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

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));


      System.out.println("Вывод всех пользователей.");
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      User user5 = new User("User5", "Lastname5", "user5@mail.ru");
      Car car5 = new Car("Skoda", 1);
      car5.setOwner(user5);
      user5.setCar(car5);
      userService.add(user5);

      User user6 = new User("User6", "Lastname6", "user6@mail.ru");
      Car car6 = new Car("Skoda", 2);
      user6.setCar(car6);
      car6.setOwner(user6);
      userService.add(user6);

      User user7 = new User("User7", "Lastname7", "user7@mail.ru");
      Car car7 = new Car("Skoda", 2);
      user7.setCar(car7);
      car7.setOwner(user7);
      userService.add(user7);

      System.out.println("Вывод пользователей и их автомобилей.");
      users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      System.out.println("Вывод пользователей с опредеоенной машиной.");
      users = userService.getUserByCar(new Car("Skoda", 2));
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }
      context.close();
   }
}
