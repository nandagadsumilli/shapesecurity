"# shapesecurity" 
This is the project for shape security screening test on Selenium automation.
There are two test cases:

Test#1:
•	Assert that the dynamic text (the lorem ipsum text block) on the page contains a word at least 10 characters in length.
•	Stretch goal:
1.	  Print the longest word on the page
Assumption:
The text should be extracted only from the dynamic text that is changing in the page, not the static header.

Test 2:
•	Assert that the "Punisher" image (silhouette with a skull on his chest) does not appear on the page.  This test may pass or fail on any given execution depending on whether the punisher happens to be on the page.
•	Stretch goal:
1.	Give names to each avatar that can appear on the page and print out each avatars name
Assumption:
There are only max of 7 images that will change dynamically on the page, so those images are given predefined name as per the mapping document attached in the email
I am printing the names that appear on the page that comes from those 7 images.
marking the test case failed if Punisher image appears on the page and pass when it doesn't appear.
