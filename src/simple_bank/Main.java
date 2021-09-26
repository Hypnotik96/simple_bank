package simple_bank;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int i = 0, op, src;
		char rep;
		Account[] a = new Account[100];

		do {

			System.out.println("Choose which action you would like to take:");
			System.out.println("1 - Create Account");
			System.out.println("2 - Enable Account");
			System.out.println("3 - Disable Account");
			System.out.println("4 - Withdraw Money");
			System.out.println("5 - Deposit Money");
			System.out.println("6 - Transfer Money");
			System.out.println("7 - Show account info");

			op = sc.nextInt();
			
			switch (op) {

			case 1:

				if (!acclim (i)) {

					String name = fname (sc);
					String lastname = lname (sc);
					int id = uid (sc);
					double limit = ulim (sc);
					Account account = new Account (name, lastname, id, limit);
					a[i] = account;
					i++;

					System.out.println("Would you like to repeat the program? Y/N");
					rep = sc.next().charAt(0);

				} else {

					System.out.println("Maximum number of registred account achived!");

					System.out.println("Would you like to repeat the program? Y/N");
					rep = sc.next().charAt(0);

				}

				break;

			case 2:

				int id1 = uid(sc);
				int pos1 = findAccountById(a, id1, i);

				if(pos1 == 0) {

					System.out.println("Would you like to repeat the program? Y/N");
					rep = sc.next().charAt(0);

					break;
				}

				boolean success1 = a[pos1].activateAccount();

				if(!success1) {

					System.out.println("Account is already active!");

					System.out.println("Would you like to repeat the program? Y/N");
					rep = sc.next().charAt(0);

					break;
				}

				System.out.println("Account has been activated.");

				System.out.println("Would you like to repeat the program? Y/N");
				rep = sc.next().charAt(0);

				break;

			case 3:

				int id2 = uid(sc);
				int pos2 = findAccountById(a, id2, i);

				if(pos2 == 0) {

					System.out.println("Would you like to repeat the program? Y/N");
					rep = sc.next().charAt(0);

					break;
				}

				boolean success2 = a[pos2].deactivateAccount();

				if(!success2) {

					System.out.println("Account is already inactive!");

					System.out.println("Would you like to repeat the program? Y/N");
					rep = sc.next().charAt(0);

					break;
				}

				System.out.println("Account has been deactivated.");

				System.out.println("Would you like to repeat the program? Y/N");
				rep = sc.next().charAt(0);			

				break;

			case 4:

				int id3 = uid(sc);
				int pos3 = findAccountById(a, id3, i);

				if(pos3 == 0) {

					System.out.println("Would you like to repeat the program? Y/N");
					rep = sc.next().charAt(0);

					break;
				}

				double amount1 = amnt(sc);

				boolean success3 = a[pos3].withdraw(amount1);

				System.out.println("Would you like to repeat the program? Y/N");
				rep = sc.next().charAt(0);

				break;

			case 5:

				int id4 = uid(sc);
				int pos4 = findAccountById(a, id4, i);

				if(pos4 == 0) {

					System.out.println("Would you like to repeat the program? Y/N");
					rep = sc.next().charAt(0);

					break;
				}

				double amount2 = amnt(sc);

				boolean success4 = a[pos4].deposit(amount2);

				System.out.println("Would you like to repeat the program? Y/N");
				rep = sc.next().charAt(0);

				break;
			case 6:

				System.out.println("Withdraw from ");
				int idToWIT = uid(sc);

				System.out.println("Deposit on ");
				int idToDEP = uid(sc);

				double amountToTransfer = amnt(sc);

				int posToWIT = findAccountById(a, idToWIT, i);

				int posToDEP = findAccountById(a, idToDEP, i);

				if(posToWIT == 0 || posToDEP == 0) {

					System.out.println("One of the accounts does not exists!");

					System.out.println("Would you like to repeat the program? Y/N");
					rep = sc.next().charAt(0);

					break;
				}

				boolean successWithraw = a[posToWIT].withdraw(amountToTransfer);

				if(!successWithraw) {

					System.out.println("Error while trying to withdraw!");				

					System.out.println("Would you like to repeat the program? Y/N");
					rep = sc.next().charAt(0);

					break;
				}

				boolean successDeposit = a[posToDEP].deposit(amountToTransfer);

				if(!successDeposit) {

					System.out.println("Error while trying to deposit");

					a[posToWIT].deposit(amountToTransfer);
					System.out.println("Value returned to original account!");


					System.out.println("Would you like to repeat the program? Y/N");
					rep = sc.next().charAt(0);

					break;
				}

				System.out.println("Operation was a success!");

				System.out.println("Would you like to repeat the program? Y/N");
				rep = sc.next().charAt(0);

				break;

			case 7:

				int idinfo = uid(sc);
				int posInfo  =findAccountById(a, idinfo, i);

				if(posInfo == 0) {

					System.out.println("Would you like to repeat the program? Y/N");
					rep = sc.next().charAt(0);

					break;
				}

				a[posInfo].info ();

				System.out.println("Would you like to repeat the program? Y/N");
				rep = sc.next().charAt(0);
				
				break;
			
			default:

				System.out.println("Please select a valid option!");

				System.out.println("Would you like to repeat the program? Y/N");
				rep = sc.next().charAt(0);

				break;
			}

		} while (rep == 'Y' || rep == 'y');

	}

	private static String fname (Scanner sc) {

		System.out.print("Inform the client first name: ");
		return sc.next();

	}

	private static String lname (Scanner sc) {

		System.out.print("Inform the client last name: ");
		return sc.next();

	}

	private static int uid (Scanner sc) {

		System.out.print("Inform client ID: ");
		return sc.nextInt();

	}

	private static double ulim (Scanner sc) {

		System.out.print("Set client limit: ");
		return sc.nextDouble();

	}

	private static double amnt (Scanner sc) {

		System.out.print("Inform amount: $");
		return sc.nextDouble();

	}

	private static boolean acclim (int i) {

		if (i > 99) {

			return true;

		}
		return false;

	}

	private static int findAccountById(Account[] arr, int id, int i) {

		for(int x = 0; x <= (i-1); x++) {
			if(arr[x].id == id) {
				return x;
			}
		}
		System.out.println("Account not found!");
		return 0;
	}

}
