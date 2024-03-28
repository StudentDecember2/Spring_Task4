package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      Car car1 = new Car("бмв",2000);
      Car car2 = new Car("мерс", 2400);
      Car car3 = new Car("гелебага",2012);
      Car car4 = new Car("ford", 2014);

      userService.add(user1.setCars(car1).setUsers(user1));
      userService.add(user2.setCars(car2).setUsers(user2));
      userService.add(user3.setCars(car3).setUsers(user3));
      userService.add(user4.setCars(car4).setUsers(user4));

      for (User user : userService.listUsers() ) {
         System.out.println(user+ " "+user.getCars());
         System.out.println("___________________________");
      }

         System.out.println(userService.getUserByCar("бмв", 2000));
         System.out.println("_______________________________");

      try{
         User userNotFound = userService.getUserByCar("нигрила",1980);
      }catch (NoResultException e){
         System.out.println("Такой машины не существует");
      }

      context.close();
   }
}
