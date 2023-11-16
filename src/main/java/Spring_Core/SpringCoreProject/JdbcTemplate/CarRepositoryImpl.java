package Spring_Core.SpringCoreProject.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class CarRepositoryImpl {

	//jdbc template
	//clean-up the resources automatically
	//The Spring JDBC template converts the standard JDBC SQLExceptions into RuntimeExceptions.
	//several ways to query the database. queryForList() returns a list
	//support for named parameters
	//map query results to Java objects
	//elimination of boilerplate code
	// Load the driver (Class.forName) ,
	//Get the Connection ,
	//Create the statement/prepared statement ,
	//Execute the query and get the ResultSet ,
	//iterate the resultset and get the results ,
	//close all handles you opened
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	//NamedParameterJdbcTemplate
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	//get brand name
	public String getName() {
		return (String) this.jdbcTemplate.queryForObject("select brands from laptop", String.class);
	}

	//total records
	public Integer getCarCount() {
		Integer rowCount = (Integer) this.jdbcTemplate.queryForObject("select count(*) from car", Integer.class);
		return rowCount;
	}

	//all records
	public List<Car> allCar() {
		List<Car> cars = this.jdbcTemplate.query("select * from car", new RowMapper<Car>() {

			@Override
			public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
				Car car = new Car();
				car.setId(rs.getInt(1));
				car.setBrands(rs.getString(2));
				car.setName(rs.getString(3));
				return car;
			}

		});
		return cars;
	}

	//name parameter
	public Car getByBrand(String brandName) {
		String sql = "select * from car where brands = :brand_name";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource("brand_name", brandName);
		namedParameters.addValue("brand_name", brandName);

		return this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new RowMapper<Car>() {

			@Override
			public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
				Car car = new Car();
				car.setId(rs.getInt(1));
				car.setBrands(rs.getString(2));
				car.setName(rs.getString(3));
				return car;
			}
		});
	}

	public Car findCar(String brand) {
		String sql = "select * from car where brands = ?";
		RowMapper<Car> mapper = new RowMapper<Car>() {

			@Override
			public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
				Car car = new Car();
				car.setId(rs.getInt(1));
				car.setBrands(rs.getString(2));
				car.setName(rs.getString(3));
				return car;
			}
		};

		return this.jdbcTemplate.queryForObject(sql, mapper, brand);
	}

	//batch update
	public int[] batchUpdate(List<Car> cars) {
		int[] updateCounts = jdbcTemplate.batchUpdate("update car set brands = ?, name = ? where id = ?",
		new BatchPreparedStatementSetter() {
		
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, cars.get(i).getBrands());
				ps.setString(2, cars.get(i).getName());
				ps.setInt(3, cars.get(i).getId());			
			}

			@Override
			public int getBatchSize() {
				return cars.size();			
			}
			
		});
		
		return updateCounts;
	}
}
