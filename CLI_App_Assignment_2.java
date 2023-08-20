import java.util.Scanner;

public class CLI_App_Assignment_2{
    private static final Scanner scanner= new Scanner(System.in);

    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "Welcome To Smart Banking System";
        final String ADD_ACCOUNT = "Add New Account";
        final String DEPOSIT = "Deposit";
        final String WITHDRAW = "Withdraw";
        final String TRANSFER = "Transfer";
        final String PRINT_BALANCE = "Print Balance";
        final String DELETE_ACCOUNT = "Remove Exisiting Account";

        final String ERROR_MSG = String.format("\t%s%s%s\n",COLOR_RED_BOLD,"%s",RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n",COLOR_GREEN_BOLD,"%s",RESET);

        String[][] account = new String[0][];
        String screen = DASHBOARD;

        boolean valid;
        do{
            final String APP_TITLE = String.format("%s%s%s",COLOR_GREEN_BOLD,screen,RESET);

            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");

            switch(screen){
                case DASHBOARD:
                    System.out.println("\t[1]. Open New Account");
                    System.out.println("\t[2]. Deposit Money");
                    System.out.println("\t[3]. Withdraw Money");
                    System.out.println("\t[4]. Transfer Money");
                    System.out.println("\t[5]. Check Account Balance");
                    System.out.println("\t[6]. Drop Existing Account");
                    System.out.println("\t[7]. Exit");
                    System.out.print("\tEnter an option to continue: ");

                    int option = scanner.nextInt();
                    scanner.nextLine();

                    switch(option){
                        case 1:
                        screen = ADD_ACCOUNT;
                        break;
                        case 2:
                        screen = DEPOSIT;
                        break;
                        case 3:
                        screen = WITHDRAW;
                        break;
                        case 4:
                        screen = TRANSFER;
                        break;
                        case 5:
                        screen = PRINT_BALANCE;
                        break;
                        case 6:
                        screen = DELETE_ACCOUNT;
                        break;
                        case 7:
                        System.out.println(CLEAR);
                        System.exit(0);
                    default:
                    }
                    break;

                case ADD_ACCOUNT:
                int id = account.length + 1 ;
                String newID = String.format("SDB-%05d",id);
                System.out.print("\t"+newID+"\n");
                String name;
                Double deposit;

                loop:
                do{

                    valid=true;
                    System.out.print("\tEnter your name: ");
                    name=scanner.nextLine().strip();

                    if(name.isBlank() ){
                        System.out.printf(ERROR_MSG,"Invalid name: ");
                        valid=false;
                        continue;
                    }
                    for (int i = 0; i < name.length(); i++) {
                        if(!(Character.isLetter(name.charAt(i))) || Character.isSpaceChar(name.charAt(i))){

                            System.out.printf(ERROR_MSG,"Name is invalid");
                            valid=false;
                            continue loop;
                        }
                        break;
                    }
                }while(!valid);

                do{

                    valid=true;
                    System.out.print("\tEnter your deposit amount:");
                    deposit=scanner.nextDouble();
                    scanner.nextLine();

                    if(deposit<5000){
                        System.out.printf(ERROR_MSG, "Not Sufficient Amount In Your A/C");
                        valid=false;
                    }else{
                        System.out.println("\tInitial Deposit :" + deposit);
                        System.out.println();
                        
                        continue;
                    }
                }while(!valid);

                String[][] newAccount = new String[account.length+1][3];

                for (int i = 0; i < newAccount.length-1; i++) {
                    newAccount[i] = account[i];
                }
                newAccount[newAccount.length-1][0]=newID;
                newAccount[newAccount.length-1][1]=name;
                newAccount[newAccount.length-1][2]=deposit + "";

                account=newAccount;

                System.out.printf(SUCCESS_MSG,String.format("%s:%s has been saved successfully", account.length, name));

                System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (scanner.nextLine().strip().toUpperCase().equals("Y"))
                        continue;

                    screen = DASHBOARD;
                    break;
            }

        }while(true);

    }
}