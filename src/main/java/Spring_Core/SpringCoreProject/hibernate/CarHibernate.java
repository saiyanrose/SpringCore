package Spring_Core.SpringCoreProject.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.stat.Statistics;

import Spring_Core.SpringCoreProject.JdbcTemplate.Car;

public class CarHibernate {
	private SessionFactory factory;
	private Session session;
	private Transaction transaction;	
	private Statistics statistic;
	
	public CarHibernate() {
		this.statistic=null;
		this.factory = null;
		this.session = null;
		this.transaction = null;
	}
	
	public void beginTransaction() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		this.factory = meta.getSessionFactoryBuilder().build();
		
		this.statistic=this.factory.getStatistics();		
		this.factory.getStatistics().setStatisticsEnabled(true);		
		
		this.session = this.factory.openSession();
		this.transaction = this.session.beginTransaction();
	}	
	
	
	public void saveCar(Car car) {
		try {
			beginTransaction();	
			
			//this.session.persist(car);
			// transitioned from a transient to persistent state.
			//The generation of INSERT statements will occur only upon committing the transaction,or flushing or closing the session.
			
			this.session.save(car);//persistent state
			
			Car car2=this.session.load(Car.class,car.getId());
			System.out.println("Before evict() session contains car2 : " + session.contains(car2));
			
			session.evict(car2);//removes a single object from Session cache in ORM
			System.out.println("After evict() session contains employee1 : " + session.contains(car2));
			
			this.transaction.commit();			
			System.out.println("car saved successfully");
		}catch (Exception e) {
			System.out.println(e.getMessage());
			this.transaction.rollback();
		}		
	}
	
	//Hibernate Query Language
	//similar to sql but does not depend on table 
	//use class name instead of table name
	//database independent query language.	
	@SuppressWarnings({"unchecked" })
	public List<Car> allCars(){	
		try {
			beginTransaction();			
			Query<Car> query=session.createQuery("from Car");			
			query.setCacheable(true);//first level cache			
			return query.list();
		}catch (Exception e) {
			System.out.println(e.getMessage());			
		}
		return null;		
	}
	

	@SuppressWarnings({ "unchecked", "deprecation"})
	public int updateCar(String name,Integer id) {
		int status=0;  
		try {			
			beginTransaction();			
			Query<Car> query=session.createQuery("update Car set brands=:name where id=:id");
			query.setParameter("name",name);  
			query.setParameter("id",id);
			status=query.executeUpdate();//update and delete
			
			// Evict the cache for the entity 
			this.factory.getCache().evictEntity(Car.class,id);			
			transaction.commit();			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		return status;
	}

	//HCQL
	//fetch the records based on the specific criteria.
	//asc,desc,gt,lt
	//createCriteria()
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Car>allCar(){		
		try {
			beginTransaction();			
			Criteria criteria=session.createCriteria(Car.class);			
			criteria.setCacheable(true);			
			return criteria.list();			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;		
	}	

	public void getStatistics() {		
		long cacheHitCount=statistic.getSecondLevelCacheHitCount();
		long cacheMissCount=statistic.getSecondLevelCacheMissCount();
		long fetchEntityCount=statistic.getEntityFetchCount();
		System.out.println("cache hit count:"+cacheHitCount);
		System.out.println("cache miss count:"+cacheMissCount);
		System.out.println("entity fetch count:"+fetchEntityCount);
	}
	
	public Car getCarById() {
		try {
			beginTransaction();	
			Car car= this.session.get(Car.class,1);//return null if data not present in cache as well as db
			Car car1= this.session.load(Car.class,1);//throws an object not found error.(lazy)
			System.out.println("get:"+car1);
			System.out.println("load:"+car1);
			transaction.commit();
			getStatistics();
			return car;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Car loadCarById() {
		try {
			beginTransaction();	
			Car car= this.session.load(Car.class,1);
			transaction.commit();			
			return car;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			this.factory.close();
			this.session.close();
		}
		return null;
	}

	
}
