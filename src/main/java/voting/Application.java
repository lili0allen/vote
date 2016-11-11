package voting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import voting.infrastructure.SQLiteJDBC;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SQLiteJDBC sqLiteJDBC = new SQLiteJDBC();
        SpringApplication.run(Application.class, args);
    }

}