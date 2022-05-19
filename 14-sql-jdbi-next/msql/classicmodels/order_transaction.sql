USE classicmodels;
SELECT @@autocommit;
SET autocommit=OFF;
SET autocommit=ON;

SET TRANSACTION ISOLATION LEVEL REPEATABLE READ, READ WRITE;

-- 1. start a new transaction
START TRANSACTION;

-- 2. Get the latest order number
SELECT 
    @orderNumber:=MAX(orderNumber)+1
FROM
    orders;

-- 3. insert a new order for customer 145
INSERT INTO `orders` (`orderNumber`,
                   `orderDate`,
                   `requiredDate`,
                   `shippedDate`,
                   `status`,
                   `customerNumber`)
VALUES(@orderNumber,
       '2005-05-31',
       '2005-06-10',
       '2005-06-11',
       'In Process',
        145);
   
-- 4. Insert order line items
INSERT INTO orderdetails(orderNumber,
                         productCode,
                         quantityOrdered,
                         priceEach,
                         orderLineNumber)
VALUES(@orderNumber,'S18_1749', 30, '136', 1),
      (@orderNumber,'S18_2248', 50, '55.09', 2);    
   
-- 5. commit changes    
COMMIT;   
   
# ROLLBACK;
SET autocommit=ON;

-- 6 check results
SELECT 
    a.orderNumber,
    orderDate,
    requiredDate,
    shippedDate,
    status,
    comments,
    customerNumber,
    orderLineNumber,
    productCode,
    quantityOrdered,
    priceEach
FROM
    orders a
        INNER JOIN
    orderdetails b USING (orderNumber)
WHERE
    a.ordernumber = 10428;	