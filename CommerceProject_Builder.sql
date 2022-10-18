--Drops

drop table if exists users;
drop table if exists roles;
drop table if exists products cascade;
drop table if exists product_categories;




--Roles
create table roles(
	id serial primary key,
	title varchar(8) not null
);

insert into roles(title) 
values
('Customer'),
('Retailer');


--Users
create table users(
	id serial primary key,
	first_name varchar(30),
	last_name varchar(30),
	email varchar(256) not null unique, --320 or 256 characters allowed? conflicting sources
	pwd varchar(20) not null,
	role_id int not null default 1,
	constraint fk_user_role foreign key(role_id) references roles(id) on delete cascade
);

insert into users(first_name, last_name, email, pwd, role_id)
values 
('Aidan', 'Shafer', 'shaferai210@gmail.com', 'A1dan$hafer', 1),
('Brandin', 'Randolph', 'brandinrandolph@gmail.com', 'password123', 1),
('Chris', 'McMillen', 'chrismcmillen4533@yahoo.com', 'password21', 1),
('Daniel', 'Rivera', 'daniel@javawockeez.com', 'password123', 2);


-- Products
create table products(
	id serial primary key,
	product_name varchar(100) not null,
	brand varchar(50) not null,
	price money not null,
	image_path varchar(50),
	weight numeric, --oz?
	--quantity int,
	description varchar(1000)
);
insert into products(product_name, brand, price, image_path, weight, description)
values 
('King''s Hawaiian Original Hawaiian Sweet Sliced Bread', 'King''s Hawaiian', 9.15, 'kingHawaiian_slicedsweetbread.jpg', 13.5, 'A 13.5 oz loaf, 12 slices, of KING''S HAWAIIAN Original Sliced Hawaiian Sweet Bread. Bread loaf baked with the original Hawaiian Sweet Bread recipe. Sliced bread with a melt-in-your-mouth texture leaves an impression on any meal. KING''S HAWAIIAN sandwich bread is made with no high fructose corn syrup, artificial dyes or trans fat. Sliced Hawaiian bread is perfect for breakfast toast, deli sandwiches and sweet lunch snacks.'),
('Sara Lee Butter Bread Sandwich Bread', 'Sara Lee', 3.79, 'saralee_butterbread.png', 20, 'One 20 oz loaf of Sara Lee Butter Sandwich Bread. Delicious taste combined with a soft, smooth texture makes this butter bread perfect for any meal. This sliced bread is ready to eat and use right out of the bag, making it convenient to share quality meals with your family in a pinch. Sara Lee sliced bread is made without high fructose corn syrup, artificial colors and artificial flavors. This bread for French toast is great for your and your family’s favorite creations, from cinnamon toast to ham and cheese sandwiches.'),
('Sara Lee Honey Wheat Sandwich Bread', 'Sara Lee', 4.19, 'saralee_honeywheat.png', 20, 'One 20 oz loaf of Sara Lee Honey Wheat Sandwich Bread. Delicious taste combined with a soft, smooth texture makes this sliced wheat bread perfect for any meal. This sliced bread is ready to eat and use right out of the bag, making it convenient to share quality meals with your family in a pinch. Sara Lee honey wheat bread is made without high fructose corn syrup, artificial colors and artificial flavors. This loaf of bread for French toast is great for your and your family’s favorite creations, from cinnamon toast to ham and cheese sandwiches'),
('Natures Own Honey Wheat Bread Sliced Loaf', 'Nature''s Own', 4.49, 'NaturesOwn_HoneyWheat.jpg', 20, 'One 20 oz loaf of Nature’s Own Honey Wheat Bread. Nature’s Own Honey Wheat Bread blends wheat, sweetened with a touch of honey for a delicious loaf. Pre-sliced bread for easy sandwich creations and delicious avocado toast. No artificial preservatives, colors or flavors. No high fructose corn syrup. This perfect blend of wheat and honey is a great addition to a variety of recipes. Nature’s Own Bread goes from scratch to shelf in about 48 hours, so you always have fresh bread.'),
('Seattle Sourdough Sliced Round Sourdough Bread', 'Seattle Sourdough', 5.64, 'SeattleSourdoughSourdoughBread.jpg', 24, 'The chewy crust, soft center, and true sourdough flavor combine to make the Waterfront Sourdough the perfect loaf. The sour notes will compliment any sandwich creation, or stand alone to make the amazing toast or dinner bread. The Seattle climate is the perfect setting for creating a truly unique sourdough. In 1950, after years of experimenting, we proudly mastered our sourdough starter. The result was a sourdough with such a delicious taste, texture, and aroma that we still use the original mother dough today. Our skilled and dedicated bakers continue to use the age old slow ferment process to guarantee a perfect crust and sour flavor.'),
('belVita Soft Baked Breakfast Biscuits, Banana Bread Flavor', 'Belvita', 26.12, 'BelvitaSoftBakedBiscuits.jpg', 1.76, 'Six boxes with 5 packs each (1 biscuit per pack), 30 total packs, of belVita Soft Baked Breakfast Biscuits, Banana Bread Flavor. Banana bread flavored soft biscuits made with whole grains. Specially baked to release up to 4 hours of nutritious steady energy. No high-fructose corn syrup and no artificial flavors or sweeteners. Individually wrapped breakfast bars are easy to grab and go.'),
('7 Up Glazed Cream Cake', 'Café Valley', 6.79, 'CafeValley7upGlazedCreamCake.jpg', 26, 'Refreshing Glazed cake made with Real 7UP flavor and glazed icing. It has a classic taste. Naturally flavored lemon lime cake. Sold frozen - thaw, serve and enjoy!'),
('8" Double Layer Variety Cake, Caramel, Carrot, Chocolate & Red Velvet', 'Our Specialty', 16.29, 'OurSpecialtyDoubleLayerVarietyCake.jpg', 46, 'PERFECT FOR ANY OCCASION This gourmet dessert is the perfect way to indulge, snack, celebrate and especially entertain. Four delicious options, sure to please everyone. Sold frozen - thaw, serve and enjoy'),
('DGP Rainbow Cotton Candy Cake Birthday Cake Celebration Cake', 'Damn Good Popcorn', 45, 'DGPrainbowBirthdayCake.jpg', 48, 'Freshly Made to Order. 5 Different Flavored Layers. Real Cotton Candy not Candy Fluff'),
('Layer Cake, Dreamin'' of Chocolate Dark and White Chocolate', 'The Original Cakerie', 13.49, 'DreaminOfChocolateInspiredChocolateCake.jpg', 21.5, 'Gluten Free Deliciously moist. Rich in taste Melt-in-your-mouth sponge cake. All natural flavors No artificial colors and artificial trans fat.');


-- Product Categories
create table categories(
	id serial primary key,
	category varchar(20)
);
insert into categories(category)
values
('Bread'),
('Cake'),
('Pie'),
('Cookie'),
('Doughnut'),
('Bagel'),
('Muffin'),
('Cupcake');


--Product category pairs
create table product_categories(
	product_id int not null,
	category_id int not null,
	constraint fk_product_id foreign key(product_id) references products(id) on delete cascade,
	constraint fk_category_id foreign key(category_id) references categories(id) on delete cascade
);
insert into product_categories(product_id, category_id)
values 
(1, 1), --Kings Hawaiian Bread - Bread
(2, 1), --SaraLee Butter Bread - Bread
(3, 1), --SaraLee HoneyWheat - Bread 
(4, 1), --Natures own HoneyWheat - Bread 
(5, 1), --Seattle Sourdough - Bread 
(6, 1), --BelVita softbread biscuits - Bread
(6, 2), --BelVita softbread biscuits - Cake
(7, 2), --7up cake - cake
(8, 2), --Variety cake - Cake
(9, 2), --Rainbow Cotton Candy Cake - Cake
(10, 2); --Chocolate cake - Cake


-- Ratings
create table ratings(
	product_id int, 
	customer_id int, 
	rating int check(0<=rating and rating<=5),
	constraint fk_product_id foreign key(product_id) references products(id) on delete cascade,
	constraint fk_customer_id foreign key(customer_id) references users(id) on delete cascade
);
insert into ratings(product_id, customer_id, rating)
values 
(1, 1, 4), --Kings Hawaiian, Aidan, 4 star
(2, 1, 3), --SL butterbread, Aidan, 3 star
(2, 2, 5), --SL butterbread, Brandin, 5 star
(3, 1, 1), --SL wheat, Aidan, 1 star
(3, 1, 0), --SL wheat, Brandin, 0 star
(4, 1, 2), --no wheat, Aidan, 2 star
(5, 1, 4), --Seattle sourdough, Aidan, 4 star
(5, 3, 3), --Seattle sourdough, Chris, 3 star
(6, 1, 5), --BelVita, Aidan, 5 star
(6, 2, 5), --BelVita, Brandin, 5 star
(6, 3, 4), --BelVita, Chris, 4 star
(7, 1, 3), --7up cake, Aidan, 3 star
(8, 1, 4), --Variety Cake, Aidan, 4 star
(9, 1, 2), --Cotton-candy cake, Aidan, 2 star
(10, 1, 5), --Chocolate cake, Aidan, 5 star
(10, 2, 3), --Chocolate cake, Brandin, 3 star
(10, 3, 4), --Chocolate cake, Chris, 4 star
(10, 4, 0); --Chocolate cake, Daniel, 0 star




