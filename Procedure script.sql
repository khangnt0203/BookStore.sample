
alter PROCEDURE getAllBook @isAdmin bit
as
	if @isAdmin = 'false'
	select bookID, categoryName, bookTitle,bookDescription, B.author, price, image , amount
                            from tblBook as B, tblBookCategory as BC 
                            where B.categoryID = BC.categoryID and status = 'true' and amount > 0
	else if @isAdmin = 'true'
	select bookID, categoryName, bookTitle,bookDescription, B.author, price, image , amount, status
                            from tblBook as B, tblBookCategory as BC 
                            where B.categoryID = BC.categoryID
GO;
exec getAllBook 'true'
GO;

alter PROCEDURE searchBookByName @searchValue nvarchar(50), @isAdmin bit
as
	if @isAdmin = 'false'
	select bookID, categoryName, bookTitle,bookDescription, B.author, price, image , amount
                            from tblBook as B, tblBookCategory as BC 
                            where B.categoryID = BC.categoryID and status = 'true' and amount > 0
							and bookTitle like '%' + @searchValue + '%'
    else if @isAdmin = 'true'
	select bookID, categoryName, bookTitle,bookDescription, B.author, price, image , amount, status
                            from tblBook as B, tblBookCategory as BC 
                            where B.categoryID = BC.categoryID and bookTitle like '%' + @searchValue + '%'
GO;
exec searchBookByName '01', 'true';
GO;

alter PROCEDURE searchBookByCategory @categorySearch nvarchar(50), @isAdmin bit
as
	if @isAdmin = 'false'
	select bookID, categoryName, bookTitle,bookDescription, B.author, price, image , amount
                            from tblBook as B, tblBookCategory as BC 
                            where B.categoryID = BC.categoryID and status = 'true' and amount > 0
							and B.categoryID = @categorySearch
    else if @isAdmin = 'true'
	select bookID, categoryName, bookTitle,bookDescription, B.author, price, image , amount, status
                            from tblBook as B, tblBookCategory as BC 
                            where B.categoryID = BC.categoryID and B.categoryID = @categorySearch
GO:
exec searchBookByCategory 'JP', 'true';
GO;

alter PROCEDURE searchBookByRangeMoney @minMoney float, @maxMoney float, @isAdmin bit
as
	if @isAdmin = 'false'
	select bookID, categoryName, bookTitle,bookDescription, B.author, price, image , amount
                            from tblBook as B, tblBookCategory as BC 
                            where B.categoryID = BC.categoryID and status = 'true' and amount > 0
							and price >= @minMoney and price <= @maxMoney
    else if @isAdmin = 'true'
	select bookID, categoryName, bookTitle,bookDescription, B.author, price, image , amount, status
                            from tblBook as B, tblBookCategory as BC 
                            where B.categoryID = BC.categoryID and price >= @minMoney and price <= @maxMoney
GO;
exec searchBookByRangeMoney 15000, 16000, 'false';
GO;

/*alter TYPE listBookShopping as TABLE (
	bookID nvarchar(50),
	amount int,
	totalPrice float
);
*/
/*
GO;
alter PROCEDURE orderBook @customer nvarchar(50), @orderTotalPrice float,
			 @listBook as listBookShopping READONLY
as
    insert into tblOrder(customer, totalPrice) values(@customer, @orderTotalPrice);
	
	SET XACT_ABORT ON
	BEGIN TRAN
	BEGIN TRY
		declare @lastOrderID int;
		select @lastOrderID = MAX(orderID) from tblOrder

		insert into tblOrderDetail(orderID, bookID, amount, totalPrice)
		select @lastOrderID as orderID, bookID, amount, totalPrice from @listBook
		COMMIT
	END TRY
	BEGIN CATCH
		ROLLBACK
		DECLARE @ErrorMessage VARCHAR(2000)
		SELECT @ErrorMessage = 'Error: ' + ERROR_MESSAGE()
		RAISERROR(@ErrorMessage, 16, 1)
	END CATCH

GO;

*/