package springguides.dataaccess;

import org.h2.Driver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import springguides.dataaccess.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by mengf on 1/19/2015.
 */
public class Application {

    public static void main(String[] args) {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriver(new Driver());
        dataSource.setUsername("sa");
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setPassword("");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        System.out.println("Creating tables");

        jdbcTemplate.execute("DROP TABLE CUSTOMERS IF EXISTS;");
        jdbcTemplate.execute("CREATE TABLE CUSTOMERS (ID SERIAL, FIRST_NAME VARCHAR(255), LAST_NAME VARCHAR(255))");

        String[] names="Meng Feng;Li NA;Steve Jobs;Rod Johnson".split(";");

        for(String fullname : names){
            String[] name = fullname.split(" ");
            System.out.printf("Inserting customer record for %s %s\n", name[0], name[1]);

            jdbcTemplate.update("INSERT INTO CUSTOMERS (FIRST_NAME, LAST_NAME) VALUES(?, ?)", name);
        }

        System.out.println("Querying for customer records where firstname:=Meng");

        List<Customer> customers = jdbcTemplate.query("SELECT * FROM CUSTOMERS WHERE FIRST_NAME = ?", new Object[]{"Meng"}, new RowMapper<Customer>() {

            @Override
            public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Customer(resultSet.getLong("ID"), resultSet.getString("FIRST_NAME"), resultSet.getString("LAST_NAME"));
            }
        });

        for(Customer customer: customers){
            System.out.println(customer);
        }
    }
}
