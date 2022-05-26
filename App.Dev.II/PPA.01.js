/*
 * calculateSimpleInterest calculates and returns the simple interest
 * (floor value) for a fixed deposit. Formula used is,

 * calculateSimpleInterest calculates and returns the simple interest
 * for a fixed deposit. Formula used is,
 * Simple Interest: P X R X T / 100
 *   where:
 *   P = Principal
 *   I = Daily interest rate
 *   N = Number of days
 *
 *  In case of any input error (wrong date format, alphabets in daily interest etc.), return -1
 *
 * @param {number} principal  - Principal amount
 * @param {number} dailyInterest  - daily interest rate
 * @param {string} startingDate  - Starting date of the fixed deposit in "YYYY-MM-DD" format, example "2015-03-25"
 * @param {string} endingDate  - Ending date of the fixed deposit in "YYYY-MM-DD" format, example "2015-03-25"
 * @return {number} interest
*/

/*
 * calculateCompoundInterest calculates and returns the compound interest
 * (floor value) for a fixed deposit. Formula used is,
 *   Compound Interest=P[(1+I/100)^N - 1]
 *   where:
 *   P = Principal
 *   I = Daily interest rate
 *   N = Number of days
 *
 *  In case of any input error (wrong date format, alphabets in daily interest etc.), return -1
 *
 * @param {number} principal  - Principal amount
 * @param {number} dailyInterest  - daily interest rate
 * @param {string} startingDate  - Starting date of the fixed deposit in "YYYY-MM-DD" format, example "2015-03-25"
 * @param {string} endingDate  - Ending date of the fixed deposit in "YYYY-MM-DD" format, example "2015-03-25"
 * @return {number} interest
 */

/*
 * extraAmountPercentage calculates and returns the extra amount percentage borrower will have to pay in case of
 * compound interest (floor value) in comparison to the simple interest for a fixed deposit.

 *  In case of any input error (wrong date format, alphabets in daily interest etc.), return -1
 *
 * @param {number} principal  - Principal amount
 * @param {number} dailyInterest  - Daily interest rate.
 * @param {string} startingDate  - Starting date of the fixed deposit in "YYYY-MM-DD" format, example "2015-03-25"
 * @param {string} endingDate  - Ending date of the fixed deposit in "YYYY-MM-DD" format, example "2015-03-25"
 * @return {number} percentage
*/

const isValidDate = function (date) {
  res = new Date(date) !== "Invalid Date" && !isNaN(new Date(date));
  return !res;
};

function calculateSimpleInterest(
  principal,
  dailyInterest,
  startingDate,
  endingDate
) {
  interest = 0;

  if (isNaN(principal)) {
    interest = -1;
  }

  if (isNaN(dailyInterest)) {
    interest = -1;
  }

  if (isValidDate(startingDate)) {
    interest = -1;
  }

  if (isValidDate(endingDate)) {
    interest = -1;
  }

  if (interest != -1) {
    var date1 = new Date(startingDate);
    var date2 = new Date(endingDate);

    // To calculate the time difference of two dates
    var Difference_In_Time = date2.getTime() - date1.getTime();

    // To calculate the no. of days between two dates
    var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24);

    interest = parseInt((principal * dailyInterest * Difference_In_Days) / 100);
  }

  return Math.floor(interest);
}

function calculateCompoundInterest(
  principal,
  dailyInterest,
  startingDate,
  endingDate
) {
  interest = 0;

  if (isNaN(principal)) {
    interest = -1;
  }

  if (isNaN(dailyInterest)) {
    interest = -1;
  }

  if (isValidDate(startingDate)) {
    interest = -1;
  }

  if (isValidDate(endingDate)) {
    interest = -1;
  }

  if (interest != -1) {
    var date1 = new Date(startingDate);
    var date2 = new Date(endingDate);

    // To calculate the time difference of two dates
    var Difference_In_Time = date2.getTime() - date1.getTime();

    // To calculate the no. of days between two dates
    var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24);

    // P[(1+I/100)^N - 1]
    // p*Math.pow((1 +r/100),t

    interest = parseInt(
      principal * (Math.pow(1 + dailyInterest / 100, Difference_In_Days) - 1)
    );
  }

  return Math.floor(interest);
}

function extraAmountPercentage(
  principal,
  dailyInterest,
  startingDate,
  endingDate
) {
  percentage = 0;

  if (isNaN(principal)) {
    percentage = -1;
  }

  if (isNaN(dailyInterest)) {
    percentage = -1;
  }

  if (isValidDate(startingDate)) {
    percentage = -1;
  }

  if (isValidDate(endingDate)) {
    percentage = -1;
  }

  if (interest != -1) {
    var date1 = new Date(startingDate);
    var date2 = new Date(endingDate);

    // To calculate the time difference of two dates
    var Difference_In_Time = date2.getTime() - date1.getTime();

    // To calculate the no. of days between two dates
    var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24);

    // P[(1+I/100)^N - 1]
    // p*Math.pow((1 +r/100),t

    simpleInterest = parseInt(
      (principal * dailyInterest * Difference_In_Days) / 100
    );
    compoundInterest = parseInt(
      principal * (Math.pow(1 + dailyInterest / 100, Difference_In_Days) - 1)
    );

    percentage = ((compoundInterest - simpleInterest) / simpleInterest) * 100;
  }

  return Math.floor(percentage);
}
