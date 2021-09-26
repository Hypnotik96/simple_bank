package simple_bank;

public class Account {

	int id;
	String name, lastname;
	double balance, limit;
	boolean active;


	public Account (String name, String lastname, int id, double limit) {

		this.name = name;
		this.lastname = lastname;
		this.id = id;
		this.limit = limit;
		this.balance = 0;
		this.active = true;


	}

	public boolean activateAccount () {
		
		if (!checkActive ()) {
			
			this.active = true;
			
			return true;
		
		}
		
		return false;

	}

	public boolean deactivateAccount () {

		if (positiveBalance ()) {

			return false;

		} else if(!checkActive ()) {

			System.out.println("Account is already deactivated");

			return false;
		}

		this.active = false;
	
		return true;

	}

	public boolean withdraw (double amount) {

		if (!checkActive ()) {

			System.out.println("Inactive Account!");

			return false;

		}

		if (this.balance >= amount && this.limit >= amount) {

			this.balance -= amount;
			System.out.println("Operation was a success! New balance: $" +this.balance+ " ID: " +this.id);
			
			return true;

		}

		System.out.println("Insufficient balance or limit!");

		return false;

	}

	public boolean deposit (double amount) {

		if (checkActive ()) {

			this.balance += amount;
			System.out.println("Operation was a succes! New balance: $" +this.balance+ " ID: " +this.id);
			
			return true;

		}

		System.out.println("Can't deposit on a deactivated account");

		return false;
	}

	public void info () {
		
		if(checkActive ()) {
			
			System.out.println("Account owner: " + this.name + " " + this.lastname);
			System.out.println("ID: " +this.id);
			System.out.println("Current balance: $" + this.balance);
			System.out.println("Current account withdraw limit: $" + this.limit);
			System.out.println("Account Status -ACTIVE-");
			
		} else {
		
		System.out.println("Account owner: " + this.name + " " + this.lastname);
		System.out.println("ID: " +this.id);
		System.out.println("Account Status -INACTIVE-");
		
		}
	}
	
	private boolean checkActive () {

		return this.active;

	}

	private boolean positiveBalance () {

		if (this.balance > 0) {

			System.out.printf("Your account has a balance of $%.2f! Please withdrawn the money before deactivate you account.%n", this.balance);
			return true;
		}
		return false;
	}

}
