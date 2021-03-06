USE [master]
GO
/****** Object:  Database [BookStore]    Script Date: 5/24/2021 6:54:03 AM ******/
CREATE DATABASE [BookStore]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'BookStore', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\BookStore.mdf' , SIZE = 3264KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'BookStore_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\BookStore_log.ldf' , SIZE = 832KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [BookStore] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [BookStore].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [BookStore] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [BookStore] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [BookStore] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [BookStore] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [BookStore] SET ARITHABORT OFF 
GO
ALTER DATABASE [BookStore] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [BookStore] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [BookStore] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [BookStore] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [BookStore] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [BookStore] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [BookStore] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [BookStore] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [BookStore] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [BookStore] SET  ENABLE_BROKER 
GO
ALTER DATABASE [BookStore] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [BookStore] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [BookStore] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [BookStore] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [BookStore] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [BookStore] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [BookStore] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [BookStore] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [BookStore] SET  MULTI_USER 
GO
ALTER DATABASE [BookStore] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [BookStore] SET DB_CHAINING OFF 
GO
ALTER DATABASE [BookStore] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [BookStore] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [BookStore] SET DELAYED_DURABILITY = DISABLED 
GO
USE [BookStore]
GO
/****** Object:  UserDefinedTableType [dbo].[listBookShopping]    Script Date: 5/24/2021 6:54:03 AM ******/
CREATE TYPE [dbo].[listBookShopping] AS TABLE(
	[bookID] [nvarchar](50) NULL,
	[amount] [int] NULL,
	[totalPrice] [float] NULL
)
GO
/****** Object:  Table [dbo].[tblBook]    Script Date: 5/24/2021 6:54:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblBook](
	[bookID] [nvarchar](50) NOT NULL,
	[categoryID] [nvarchar](50) NULL,
	[bookTitle] [nvarchar](50) NULL,
	[bookDescription] [nvarchar](50) NULL,
	[author] [nvarchar](50) NULL,
	[price] [float] NULL,
	[image] [nvarchar](50) NULL,
	[status] [bit] NULL,
	[amount] [int] NULL,
 CONSTRAINT [PK_tblBook] PRIMARY KEY CLUSTERED 
(
	[bookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblBookCategory]    Script Date: 5/24/2021 6:54:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblBookCategory](
	[categoryID] [nvarchar](50) NOT NULL,
	[categoryName] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblBookCategory] PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrder]    Script Date: 5/24/2021 6:54:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrder](
	[orderID] [int] IDENTITY(1,1) NOT NULL,
	[orderStatus] [bit] NULL,
	[createDate] [datetime] NULL,
	[totalPrice] [float] NULL,
	[customer] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblOrder] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrderDetail]    Script Date: 5/24/2021 6:54:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrderDetail](
	[orderDetailID] [int] IDENTITY(1,1) NOT NULL,
	[orderID] [int] NULL,
	[bookID] [nvarchar](50) NULL,
	[amount] [int] NULL,
	[totalPrice] [float] NULL,
 CONSTRAINT [PK_tblOrderID] PRIMARY KEY CLUSTERED 
(
	[orderDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 5/24/2021 6:54:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUser](
	[email] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NULL,
	[name] [nvarchar](50) NULL,
	[address] [nvarchar](50) NULL,
	[phone] [nvarchar](50) NULL,
	[roleID] [int] NULL,
 CONSTRAINT [PK_tblUser] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUserRole]    Script Date: 5/24/2021 6:54:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUserRole](
	[roleID] [int] NOT NULL,
	[roleName] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblUserRole] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tblBook] ([bookID], [categoryID], [bookTitle], [bookDescription], [author], [price], [image], [status], [amount]) VALUES (N'B01', N'ENG', N'This is book 01', N'asdasdsa', N'Long', 15000, N'./images/asian_girl_look_86437_1920x1080.jpg', 1, 5)
INSERT [dbo].[tblBook] ([bookID], [categoryID], [bookTitle], [bookDescription], [author], [price], [image], [status], [amount]) VALUES (N'B02', N'ENG', N'This is book 02', N'assad', N'Thang', 16000, N'./images/1519175756757551ab3zPCUCF.jpg', 1, 7)
INSERT [dbo].[tblBook] ([bookID], [categoryID], [bookTitle], [bookDescription], [author], [price], [image], [status], [amount]) VALUES (N'B03', N'VN', N'This is book 03', N'asdaww', N'Toan', 17000, N'./images/asian_girl_look_86437_1920x1080.jpg', 0, 4)
INSERT [dbo].[tblBook] ([bookID], [categoryID], [bookTitle], [bookDescription], [author], [price], [image], [status], [amount]) VALUES (N'B04', N'JP', N'This is Book 04', N'efwwef', N'Duy', 18000, N'./images/asian_girl_look_86437_1920x1080.jpg', 1, 2)
INSERT [dbo].[tblBook] ([bookID], [categoryID], [bookTitle], [bookDescription], [author], [price], [image], [status], [amount]) VALUES (N'B05', N'ENG', N'This is book 05', N'tyryr', N'Long', 19000, N'./images/asian_girl_look_86437_1920x1080.jpg', 1, 5)
INSERT [dbo].[tblBook] ([bookID], [categoryID], [bookTitle], [bookDescription], [author], [price], [image], [status], [amount]) VALUES (N'B06', N'JP', N'This is book 06', N'adsad', N'Thang', 23000, N'./images/asian_girl_look_86437_1920x1080.jpg', 1, 1)
GO
INSERT [dbo].[tblBookCategory] ([categoryID], [categoryName]) VALUES (N'ENG', N'English')
INSERT [dbo].[tblBookCategory] ([categoryID], [categoryName]) VALUES (N'JP', N'Japan')
INSERT [dbo].[tblBookCategory] ([categoryID], [categoryName]) VALUES (N'VN', N'Viet Name')
GO
SET IDENTITY_INSERT [dbo].[tblOrder] ON 

INSERT [dbo].[tblOrder] ([orderID], [orderStatus], [createDate], [totalPrice], [customer]) VALUES (1, 0, CAST(N'2021-05-22T12:59:00.150' AS DateTime), 46000, N'nguyenleduythang@gmail.com')
INSERT [dbo].[tblOrder] ([orderID], [orderStatus], [createDate], [totalPrice], [customer]) VALUES (2, 0, CAST(N'2021-05-22T13:00:13.813' AS DateTime), 46000, N'nguyenleduythang@gmail.com')
INSERT [dbo].[tblOrder] ([orderID], [orderStatus], [createDate], [totalPrice], [customer]) VALUES (3, 0, CAST(N'2021-05-22T13:00:37.247' AS DateTime), 46000, N'nguyenleduythang@gmail.com')
INSERT [dbo].[tblOrder] ([orderID], [orderStatus], [createDate], [totalPrice], [customer]) VALUES (4, 0, CAST(N'2021-05-22T13:02:00.557' AS DateTime), 31000, N'nguyenleduythang@gmail.com')
INSERT [dbo].[tblOrder] ([orderID], [orderStatus], [createDate], [totalPrice], [customer]) VALUES (5, 0, CAST(N'2021-05-22T13:07:01.233' AS DateTime), 46000, N'nguyenleduythang@gmail.com')
INSERT [dbo].[tblOrder] ([orderID], [orderStatus], [createDate], [totalPrice], [customer]) VALUES (6, 0, CAST(N'2021-05-22T13:10:24.417' AS DateTime), 46000, N'nguyenleduythang@gmail.com')
INSERT [dbo].[tblOrder] ([orderID], [orderStatus], [createDate], [totalPrice], [customer]) VALUES (7, 0, CAST(N'2021-05-22T13:13:50.407' AS DateTime), 58000, N'nguyenleduythang@gmail.com')
INSERT [dbo].[tblOrder] ([orderID], [orderStatus], [createDate], [totalPrice], [customer]) VALUES (8, 0, CAST(N'2021-05-22T13:14:26.223' AS DateTime), 88000, N'nguyenleduythang@gmail.com')
SET IDENTITY_INSERT [dbo].[tblOrder] OFF
GO
SET IDENTITY_INSERT [dbo].[tblOrderDetail] ON 

INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [bookID], [amount], [totalPrice]) VALUES (1, 5, N'B02', 1, 16000)
INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [bookID], [amount], [totalPrice]) VALUES (2, 6, N'B02', 1, 16000)
INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [bookID], [amount], [totalPrice]) VALUES (3, 7, N'B02', 1, 16000)
INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [bookID], [amount], [totalPrice]) VALUES (4, 7, N'B05', 1, 19000)
INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [bookID], [amount], [totalPrice]) VALUES (5, 7, N'B06', 1, 23000)
INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [bookID], [amount], [totalPrice]) VALUES (6, 8, N'B01', 2, 30000)
INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [bookID], [amount], [totalPrice]) VALUES (7, 8, N'B02', 1, 16000)
INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [bookID], [amount], [totalPrice]) VALUES (8, 8, N'B05', 1, 19000)
INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [bookID], [amount], [totalPrice]) VALUES (9, 8, N'B06', 1, 23000)
SET IDENTITY_INSERT [dbo].[tblOrderDetail] OFF
GO
INSERT [dbo].[tblUser] ([email], [password], [name], [address], [phone], [roleID]) VALUES (N'dongbaolong98@gmail.com', N'123', N'Dong Bao Long', N'14 A', N'0906608005', 1)
INSERT [dbo].[tblUser] ([email], [password], [name], [address], [phone], [roleID]) VALUES (N'nguyenleduythang@gmail.com', N'123', N'Nguyen Le Duy Thang', N'15 B', N'123456798', 2)
GO
INSERT [dbo].[tblUserRole] ([roleID], [roleName]) VALUES (1, N'Admin')
INSERT [dbo].[tblUserRole] ([roleID], [roleName]) VALUES (2, N'Customer')
GO
ALTER TABLE [dbo].[tblBook] ADD  CONSTRAINT [DF_tblBook_status]  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[tblOrder] ADD  CONSTRAINT [DF_tblOrder_orderStatus]  DEFAULT ((0)) FOR [orderStatus]
GO
ALTER TABLE [dbo].[tblOrder] ADD  CONSTRAINT [DF_tblOrder_createDate]  DEFAULT (getdate()) FOR [createDate]
GO
ALTER TABLE [dbo].[tblBook]  WITH CHECK ADD  CONSTRAINT [FK_tblBook_tblBookCategory] FOREIGN KEY([categoryID])
REFERENCES [dbo].[tblBookCategory] ([categoryID])
GO
ALTER TABLE [dbo].[tblBook] CHECK CONSTRAINT [FK_tblBook_tblBookCategory]
GO
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD  CONSTRAINT [FK_tblOrder_tblUser] FOREIGN KEY([customer])
REFERENCES [dbo].[tblUser] ([email])
GO
ALTER TABLE [dbo].[tblOrder] CHECK CONSTRAINT [FK_tblOrder_tblUser]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetail_tblBook] FOREIGN KEY([bookID])
REFERENCES [dbo].[tblBook] ([bookID])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK_tblOrderDetail_tblBook]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetail_tblOrder] FOREIGN KEY([orderID])
REFERENCES [dbo].[tblOrder] ([orderID])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK_tblOrderDetail_tblOrder]
GO
ALTER TABLE [dbo].[tblUser]  WITH CHECK ADD  CONSTRAINT [FK_tblUser_tblUserRole] FOREIGN KEY([roleID])
REFERENCES [dbo].[tblUserRole] ([roleID])
GO
ALTER TABLE [dbo].[tblUser] CHECK CONSTRAINT [FK_tblUser_tblUserRole]
GO
/****** Object:  StoredProcedure [dbo].[getAllBook]    Script Date: 5/24/2021 6:54:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[getAllBook] @isAdmin bit
as
	if @isAdmin = 'false'
	select bookID, categoryName, bookTitle,bookDescription, B.author, price, image , amount
                            from tblBook as B, tblBookCategory as BC 
                            where B.categoryID = BC.categoryID and status = 'true' and amount > 0
	else if @isAdmin = 'true'
	select bookID, categoryName, bookTitle,bookDescription, B.author, price, image , amount, status
                            from tblBook as B, tblBookCategory as BC 
                            where B.categoryID = BC.categoryID
GO
/****** Object:  StoredProcedure [dbo].[orderBook]    Script Date: 5/24/2021 6:54:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[orderBook] @customer nvarchar(50), @orderTotalPrice float,
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
GO
/****** Object:  StoredProcedure [dbo].[searchBookByCategory]    Script Date: 5/24/2021 6:54:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROCEDURE [dbo].[searchBookByCategory] @categorySearch nvarchar(50), @isAdmin bit
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
GO
/****** Object:  StoredProcedure [dbo].[searchBookByName]    Script Date: 5/24/2021 6:54:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[searchBookByName] @searchValue nvarchar(50), @isAdmin bit
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
GO
/****** Object:  StoredProcedure [dbo].[searchBookByRangeMoney]    Script Date: 5/24/2021 6:54:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROCEDURE [dbo].[searchBookByRangeMoney] @minMoney float, @maxMoney float, @isAdmin bit
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
GO
USE [master]
GO
ALTER DATABASE [BookStore] SET  READ_WRITE 
GO
