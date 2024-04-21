# mobile-testing-software
This is a java based software to view/book/return phones

# Pre-requisite to run this software:
1. JDK 21.0.3
2. Gradle 7.4
  
# Tools Used
1. Intellij
2. Github


# Main class of project:
com.mobile.MobileSoftwareMain.java

# Jar Details :
created executable jar (mobile_testing_jar)  
executable jar path :  out/artifacts/mobile_testing_jar

# command to run this executable jar:
java -jar <jar path>/mobile-testing.jar

# Result after executing above jar :

=== MOBILE SOFTWARE TESTING ===
1. View available phones
2. View Phone by name
3. Book a phone
4. Return a phone
5. Exit
===============================
Please enter a choice (1 - 5) :
1
=== Available Mobiles ===
-*-*-*-
Mobile Name : Motorola Nexus 6
Mobile Details : Motorola Nexus 6
Quantity : 1
-*-*-*-
Mobile Name : Nokia 3310
Mobile Details : Nokia 3310
Quantity : 1
-*-*-*-
Mobile Name : Samsung Galaxy S9
Mobile Details : Galaxy S9 of Samsung
Quantity : 1
-*-*-*-
Mobile Name : Oneplus 9
Mobile Details : Oneplus 9
Quantity : 1
-*-*-*-
Mobile Name : iPhone X
Mobile Details : iPhone X
Quantity : 1
-*-*-*-
Mobile Name : Samsung Galaxy S8
Mobile Details :  Samsung Galaxy S8
Quantity : 2
-*-*-*-
Mobile Name : Apple iPhone 11
Mobile Details : Apple iPhone 11
Quantity : 1
-*-*-*-
Mobile Name : Apple iPhone 13
Mobile Details : Apple iPhone 13
Quantity : 1
-*-*-*-
Mobile Name : Apple iPhone 12
Mobile Details : Apple iPhone 12
Quantity : 1
=== MOBILE SOFTWARE TESTING ===
1. View available phones
2. View Phone by name
3. Book a phone
4. Return a phone
5. Exit
===============================
Please enter a choice (1 - 5) :
2
Enter phone name :  Apple iPhone 13
--- Mobile details are ---
Name : Apple iPhone 13
Available : true
Details : Apple iPhone 13
Quantity : 1
=== MOBILE SOFTWARE TESTING ===
1. View available phones
2. View Phone by name
3. Book a phone
4. Return a phone
5. Exit
===============================
Please enter a choice (1 - 5) :
3
Are you new User (Y/N) :
n
Enter user name : suman
Enter phone name you want to book:  Apple iPhone 13
Enter number of days for phone to be returned : 6
User with id : suman not found
=== MOBILE SOFTWARE TESTING ===
1. View available phones
2. View Phone by name
3. Book a phone
4. Return a phone
5. Exit
===============================
Please enter a choice (1 - 5) :
3
Are you new User (Y/N) :
y
Enter Username : suman
Enter your name : suman devi
Enter phone : 9711065577
Enter address : gfjdgke
User with userId : suman created
Created user with username : suman
Enter phone name you want to book:  Apple iPhone 13
Enter number of days for phone to be returned : 1
Booking added with Id : suman_Apple iPhone 13_21-04-2024
Booking was successful with id : suman_Apple iPhone 13_21-04-2024
=== MOBILE SOFTWARE TESTING ===
1. View available phones
2. View Phone by name
3. Book a phone
4. Return a phone
5. Exit
===============================
Please enter a choice (1 - 5) :
4
Enter Booking id : suman_Apple iPhone 13_21-04-2024
Enter phone name : Apple iPhone 13
Returned Apple iPhone 13
Removed the booking with Id : suman_Apple iPhone 13_21-04-2024
=== MOBILE SOFTWARE TESTING ===
1. View available phones
2. View Phone by name
3. Book a phone
4. Return a phone
5. Exit
===============================
Please enter a choice (1 - 5) :
5
Thanks for visiting application
