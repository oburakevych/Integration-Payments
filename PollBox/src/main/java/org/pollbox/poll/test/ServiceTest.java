package org.pollbox.poll.test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.pollbox.poll.accounts.Account;
import org.pollbox.poll.owners.Owner;
import org.pollbox.poll.managers.ServiceManager;
import org.pollbox.poll.statuses.Status;
import org.pollbox.poll.statuses.StatusDef;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ServiceTest {
	private static ServiceManager manager;
	
	private static final String[] CONTEXT_C0NFIG_LOCATIONS = {"META-INF/spring/root-context.xml", "META-INF/spring/applicationContext-hibernate.xml", "META-INF/spring/applicationContext-service.xml"};

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServiceTest test = new ServiceTest();
		
		//test.run();
		
		test.runAccount();
	}
	
	/*
	private void run() {
		ApplicationContext context = new ClassPathXmlApplicationContext(CONTEXT_C0NFIG_LOCATIONS);
		
		manager = context.getBean(ServiceManager.class);
		
		Long id = 1L;
		
		String name = "ob@gmail.com";
		
		System.out.println("Started");
		
		if (manager == null) {
			System.out.println("Manager is not initialized");
		} else {
			System.out.println(manager);
		}
		
		Owner owner = new Owner();
		
		owner.setFirstName("Alexander");
		owner.setLastName("Burakevych");
		owner.setEmail("ob2@test.com");
		owner.setTypeId(1L);
		
		//owner = manager.getOwnerService().getOwnerById(id);
		
		owner = manager.getOwnersService().getOwnerByUsername(name);
		
		System.out.println("\n" + owner.getFirstName() + " " + owner.getLastName());
		
		//owner = manager.getOwnerService().save(owner);
		
		//List<Owner> owners = manager.getOwnerService().getOwners();
		
		Set<Account> accounts = owner.getAccounts();
		
		System.out.println("Results are:");
		*/
		/*
		for (Owner owner1 : owners) {
			System.out.println(owner1.getUsername() + ": " + owner1.getFirstName() + " " + owner1.getLastName());
			System.out.println("Create Date: " + owner1.getCreatedDate().getTime());
		}
		*/
		/*
		for (Account account : accounts) {
			System.out.println(account.getName());
		}
		
		//System.out.println("\n" + owner.getFirstName() + " " + owner.getLastName()); 
		
	}
	*/
	
	
	private void runAccount() {
		ApplicationContext context = new ClassPathXmlApplicationContext(CONTEXT_C0NFIG_LOCATIONS);
		
		manager = context.getBean(ServiceManager.class);
	
		System.out.println("-------- Account -----------");

		Account account1 = new Account();
		
		account1.setDateCreated(Calendar.getInstance());
		account1.setName("Aquarium-2");
		account1.setStatus(new Status(StatusDef.ACTIVE_ACCOUNT));
		
		manager.getAccountsService().save(account1);
		
		Set<Account> accounts = new HashSet<Account>();
		
		accounts.add(account1);
		
		Owner owner = new Owner();
		
		long time = (new Date()).getTime();
		
		String number = String.valueOf(time);
		
		char prefix = number.charAt(number.length() - 1);
		
		int i = (int) (Integer.valueOf(prefix) + 10 * Math.random());
		
		owner.setFirstName("Sash");
		owner.setLastName("Info");
		owner.setCreatedDate(Calendar.getInstance());
		owner.setEmail(i + "@test.com");
		owner.setUsername(i +"@test.com");
		owner.setCity("Львів");
		manager.getOwnersService().save(owner);
		
		owner.setAccount(account1);
		
		Set<Owner> owners = new HashSet<Owner>();
		
		owners.add(owner);
		
		account1.setOwners(owners);
		
		manager.getOwnersService().save(owner);
		
		manager.getAccountsService().save(account1);
				
		manager.getOwnersService().save(owner);

	}
}
