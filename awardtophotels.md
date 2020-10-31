## Problem:
At Booking.com we want to recognize k performing hotels. We plan to identify these by analyzing their user reviews and calculating a review score for ech of the hotel.

to calculate the score, we have:
* a list of user reviews for each hotel,
* a list of positive keywords and
* a lit of negative keywords 

Positive keywords weigh 3 point each and negative keywords weigh -1 each.

Complete the function awardTopKHotels. The function must return list of hotel ids sorted in descending order of their total score.