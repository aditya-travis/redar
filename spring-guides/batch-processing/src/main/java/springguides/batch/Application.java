package springguides.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import springguides.batch.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by meng on 1/25/15.
 */
@ComponentScan
@EnableAutoConfiguration(exclude = JmxAutoConfiguration.class)
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);

        List<Person> results = context.getBean(JdbcTemplate.class).query("SELECT first_name, last_name FROM people", new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Person(rs.getString(1), rs.getString(2));
            }
        });

        for(Person result: results){
            System.out.println("Found <" + result + "> in database");
        }
    }
}
