The following are contributions from Mark that were not pushed, rather sent 
by direct messaging, thus wouldn't show in any pushes.

1(Return loan method in its entirety
        public void returnLoan()
            {          
                String confirm = "";
                Boolean loanBarcodeFound = false;  
                scan = new Scanner(System.in);
                //getting user input for the barcode in loans
                System.out.println("Please enter barcode for loan. ");
                inputBarcode = scan.nextLine();  
                loanBarcodeFound = this.findLoan(inputBarcode); //calling search method
                //decision structure to ensure loan is present and if so remove confirmation and loan removal 
                if (loanBarcodeFound == true)
                {      
                boolean responseLoop = true;//boolean to control loop
                while (responseLoop)
                {          
                        for (int i = 0; i < loanList.size(); i++)
                        {
                            if(loanList.get(i).getBarcode().equals(inputBarcode))
                            {   //converting format of date from / to - in csv                     
                                String dueDateLoan = loanList.get(i).getDueDate();                         
                                String formattedDueDate = dueDateLoan.replace("/", "-");                      
                                String[] divide = formattedDueDate.split("-");
                                String day = divide[0];  //changing D/M/Y to Y/M/D
                                String month = divide[1]; 
                                String year = divide[2];
                                String result = year + "-" + month + "-" + day;                       
                                LocalDate dueDate = LocalDate.parse(result);
                                boolean dateBefore = date.isAfter(dueDate);

                                //if current day is after return date display this
                                if(dateBefore) 
                                {
                                    System.out.println("This items was returned after due date");
                                }
                            } //finding loan which = inputBarcode
                            if(loanList.get(i).getBarcode().equals(inputBarcode))
                            {
                                loanList.remove(loanList.get(i)); //remove from arraylist
                                System.out.println("\nLoan has been removed removed.");
                                responseLoop = false;
                            }                   
                        }  
                }
                } else {
                    System.out.println("\nPlease enter valid barcode."); 
                }
            }

2)altered validation within main menu
        try 
        //            {
        //                x = Integer.parseInt(userInput);
        //                //if user doesnt enter number then catch exception
        //            }catch(NumberFormatException e) 
        //            {
        //                System.out.println("Please enter a valid response (1-7)"); 
        //                // catching exception here for input
        //             }
        //            // enuring input is within range of menu
        //                if (x < 1 || x > 7)
        //                {
        //                    System.out.println("Please enter a valid response (1-7)"); 
        //                    this.menu();
        //                }

3) constructors created for Loans, users
