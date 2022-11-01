--Drops

drop table if exists users cascade;
drop table if exists roles cascade;
drop table if exists products cascade;
drop table if exists product_categories cascade;
drop table if exists categories cascade;
drop table if exists ratings cascade;
drop table if exists cart cascade;
drop table if exists order_history cascade;



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
	description varchar(1000),
	stripe_key varchar(100)
	
);
insert into products(product_name, brand, price, image_path, weight, description, stripe_key)
values 
-- bread 1-5
('King''s Hawaiian Original Hawaiian Sweet Sliced Bread', 'King''s Hawaiian', 9.15, 'KingsOriginalHawaiianSweet.jpg', 13.5, 'A 13.5 oz loaf, 12 slices, of KING''S HAWAIIAN Original Sliced Hawaiian Sweet Bread. Bread loaf baked with the original Hawaiian Sweet Bread recipe. Sliced bread with a melt-in-your-mouth texture leaves an impression on any meal. KING''S HAWAIIAN sandwich bread is made with no high fructose corn syrup, artificial dyes or trans fat. Sliced Hawaiian bread is perfect for breakfast toast, deli sandwiches and sweet lunch snacks.', 'price_1Ly1neHXQUUlyKrgZf8PT2xa'),
('Sara Lee Butter Bread Sandwich Bread', 'Sara Lee', 3.79, 'SaraLeeButterbread.jpg', 20, 'One 20 oz loaf of Sara Lee Butter Sandwich Bread. Delicious taste combined with a soft, smooth texture makes this butter bread perfect for any meal. This sliced bread is ready to eat and use right out of the bag, making it convenient to share quality meals with your family in a pinch. Sara Lee sliced bread is made without high fructose corn syrup, artificial colors and artificial flavors. This bread for French toast is great for your and your family’s favorite creations, from cinnamon toast to ham and cheese sandwiches.', 'price_1Ly1ocHXQUUlyKrg8oeJmZQE'),
('Sara Lee Honey Wheat Sandwich Bread', 'Sara Lee', 4.19, 'SaraLeeHoneyWheat.jpg', 20, 'One 20 oz loaf of Sara Lee Honey Wheat Sandwich Bread. Delicious taste combined with a soft, smooth texture makes this sliced wheat bread perfect for any meal. This sliced bread is ready to eat and use right out of the bag, making it convenient to share quality meals with your family in a pinch. Sara Lee honey wheat bread is made without high fructose corn syrup, artificial colors and artificial flavors. This loaf of bread for French toast is great for your and your family’s favorite creations, from cinnamon toast to ham and cheese sandwiches', 'price_1Ly1pIHXQUUlyKrg6vzg0ZDp'),
('Natures Own Honey Butterbread', 'Nature''s Own', 4.49, 'NaturesOwnHoneyWheat.jpg', 20, 'One 20 oz loaf of Nature’s Own Honey Wheat Bread. Nature’s Own Honey Wheat Bread blends wheat, sweetened with a touch of honey for a delicious loaf. Pre-sliced bread for easy sandwich creations and delicious avocado toast. No artificial preservatives, colors or flavors. No high fructose corn syrup. This perfect blend of wheat and honey is a great addition to a variety of recipes. Nature’s Own Bread goes from scratch to shelf in about 48 hours, so you always have fresh bread.', 'price_1Ly1q9HXQUUlyKrgvyNmdgbg'),
('Seattle Sourdough Sliced Round Sourdough Bread', 'Seattle Sourdough', 5.79, 'SeattleSourdoughBread.jpg', 24, 'The chewy crust, soft center, and true sourdough flavor combine to make the Waterfront Sourdough the perfect loaf. The sour notes will compliment any sandwich creation, or stand alone to make the amazing toast or dinner bread. The Seattle climate is the perfect setting for creating a truly unique sourdough. In 1950, after years of experimenting, we proudly mastered our sourdough starter. The result was a sourdough with such a delicious taste, texture, and aroma that we still use the original mother dough today. Our skilled and dedicated bakers continue to use the age old slow ferment process to guarantee a perfect crust and sour flavor.', 'price_1Ly1qqHXQUUlyKrg25bag5Lq'),
-- cakes 6-10
('Dulce de Leche Cake', 'Karl''s Quality Bakery', 27.00, 'KarlsQualityBakeryDulcedeLecheCake.jpg', 25, 'White Cake with a Caramel soak, tartine and cream. Frosted with a Caramel Cream and dressed with a soft Caramel. Contains dairy, egg, and wheat.Made in a facility that uses peanuts, tree nuts, dairy, egg, and wheat.', 'price_1Ly2MrHXQUUlyKrgtcoGH8mB'),
('7 Up Glazed Cream Cake', 'Café Valley', 6.79, '7UpGlazedCreamCake.jpg', 26, 'Refreshing Glazed cake made with Real 7UP flavor and glazed icing. It has a classic taste. Naturally flavored lemon lime cake. Sold frozen - thaw, serve and enjoy!', 'price_1Ly2NgHXQUUlyKrgg16dZDdG'),
('8" Double Layer Variety Cake', 'Rich''s', 16.29, 'DoubleLayerVarietyCake.jpg', 46, 'PERFECT FOR ANY OCCASION This gourmet dessert is the perfect way to indulge, snack, celebrate and especially entertain. Four delicious options, sure to please everyone. Sold frozen - thaw, serve and enjoy', 'price_1Ly2OPHXQUUlyKrgAzmfdagQ'),
('DGP Rainbow Cotton Candy Cake', 'Damn Good Popcorn', 45.00, 'DGPRainbowCottonCandyCake.jpg', 48, 'Freshly Made to Order. 5 Different Flavored Layers. Real Cotton Candy not Candy Fluff', 'price_1Ly2PFHXQUUlyKrgs402LdZN'),
('Dreamin'' of Chocolate Dark and White Chocolate Layer Cake', 'The Original Cakerie', 13.49, 'TheOriginalCakerieDreamCake.jpg', 21.5, 'Gluten Free Deliciously moist. Rich in taste Melt-in-your-mouth sponge cake. All natural flavors No artificial colors and artificial trans fat.', 'price_1Ly2QgHXQUUlyKrguaMKju4e'),
--cookies 11-15
('Captiva Soft Baked Dark Chocolate Brownie Cookies', 'Pepperidge Farm', 4.99, 'PepperidgeCaptiva.jpg', 8.6, 'Filled with chunks of rich dark chocolate, these melt-in-your-mouth chocolate cookies are made with real butter, eggs and vanilla flavor - it is  the kind of cookie that is more than a treat, it is an experience!', 'price_1LxyssHXQUUlyKrgTDRAr2JK'),
('Oatmeal Raisin', 'Matts Cookies', 7.99, 'MattsOatmealRaisin.jpg', 10.5, 'Dairy free. Non GMO Project verified. Estd. 1979. Ridiculously good. The real stuff. It is no wonder they taste ridiculously good! Matts cookies are packed warm from the oven to preserve their home baked goodness', 'price_1Lxz28HXQUUlyKrg1QBvAegR'),
('Fudge Mint, Plant Based', 'Back to Nature', 5.79, 'BackToNatureFudgeMint.jpg', 6.4, 'Simply crafted with premium ingredients inspired by nature, our cookies are made with organic wheat flour. They are certified NON-GMO Project Verified, Kosher, and made without high-fructose corn syrup or hydrogenated oils. With Back to Nature products, you can enjoy fudge mint cookies that are simply delicious and simply good.', 'price_1LxzCgHXQUUlyKrgqWfib908'),
('Fudge Striped', 'Goodie Girl Cookies', 4.98, 'GoodieGirlFudgeStriped.jpg', 7, 'Goodie girl fudge striped cookies are a delicious gluten-free treat for kids of all ages. Our special flour blend contains rice, tapioca and potato starch, and oats, and our recipe is free of nuts for a healthy snack option suitable for individuals with sensitivities.', 'price_1LxzQ8HXQUUlyKrgVa41zE3M'),
('Petit Ecolier European Milk Chocolate Biscuit Cookies', 'LU', 5.59, 'LuEuropeanMilkChocolate.jpg', 5.29, 'Lu is a leading french brand specialized in cakes, cookies and desserts. Its a classic success story of hard work and commitment to french traditions. The brand is a global leader in its field. And what makes it so trusted original recipes, guaranteed quality, and the promise of tradition.', 'price_1LxzXPHXQUUlyKrgX8sPnHrf'),
--muffins 16-20
('English Muffins, Sliced, 100% Whole Wheat - 6 muffins', 'Oroweat', 4.69, 'OroweatEnglishMuffins.jpg', 13.75, 'Fill the kitchen with the aroma of baked whole wheat when you toast these English muffins before buttering them up and spreading thick with jam or jelly. With no high fructose corn syrup and with 4 grams of fiber per serving, Oroweat Whole Wheat English Muffins are a fast and easy breakfast option you can feel good about feeding to your family.', 'price_1LxzpdHXQUUlyKrgcrdpSLOl'),
('Minute Muffins, Double Dark Chocolate', 'Kodiak Cakes', 2.75, 'KodiakCakesMinuteMuffins.jpg', 2.36, 'Kodiak Cakes Minute Muffins are an all-natural muffin mix served in a convenient, microwavable cup. Good, that''s all you''ll need to make yourself a hot, healthy breakfast that will satisfy and energize you for the rest of the morning. This muffin mix is add-water-only and packed full of fiber, protein, and whole grains without compromising on taste.', 'price_1LxztqHXQUUlyKrgWD8jakyM'),
('Blueberry Muffins', 'Udi''s Gluten Free', 4.92, 'UdisBlueberryMuffins.jpg', 10, 'Enjoy muffins, even while limiting your gluten intake, with Udi''s Gluten Free Blueberry Muffins. Indulge in delicious, moist and tasty muffins that are not only gluten free, but also don''t contain dairy, soy, tree nut or peanut ingredients. Great for on-the-go breakfast or anytime gluten free snacking.', 'price_1LxzywHXQUUlyKrgHsPeyeEH'),
('Strawberry & Creme Mini Muffins - 9 Count', 'Marketside', 4.98, 'MarketsideMiniMuffins.jpg', 14, 'Enjoy the sweet and delicious flavor of our Marketside Strawberry and Creme Mini Muffins, 14 ounces. Keep them on hand as a snack option or to a party to share with friends and family. Make Marketside Strawberry and Creme Mini Muffins your favorite go-to sweet treat today.', 'price_1Ly034HXQUUlyKrgp3L8W6uq'),
('Banana Chocolate Chip Muffins - 6 muffins', 'Veggies Made Great', 6.19, 'VeggiesMadeGreatMuffins.jpg', 12, 'Veggies Made Great makes plant-based solutions easier for every consumer and for any occasion!? You''ll never believe the first ingredient in our Banana Chocolate Chip Muffins is zucchini! Heat in the microwave to enjoy a delightfully warm and bakery-like experience for only 120 calories. Perfect for even the pickiest eaters.', 'price_1Ly07IHXQUUlyKrggOPYdlJu'),
--pies 20-25
('Sweet Potato Pie', 'Patti Labelle', 4.98, 'PattiLabelleSweetPotatoPie.jpg', 21, 'Patti''s Good Life Sweet Potato Pie exclusive recipe combines a tender, flaky crust and creamy filling made with sweet potatoes, butter, and spice. Be sure to try the rest of the delicious desserts straight from Pattis kitchen and into your home because the good life can be enjoyed by everyone.', 'price_1Ly0SsHXQUUlyKrgTEv5VO5g'),
('Fudge Pecan Pie', 'Collin Street Bakery', 23.95, 'CollinFudgePecanPie.jpg', 26, 'The perfect cross between a slice of our Pecan and Fudge Pies, our Fudge Pecan Pie combines the best parts of both desserts. Baked inside a light and flaky crust with toasted pecans resting atop a base of silky, fudge custard, this pie indulges both chocolate and nut lovers alike!' ,'price_1Ly1CqHXQUUlyKrgkbKXQPEM'),
('American-Style Apple Pie', 'Harry & David', 54.99, 'HarryDavidApplePie.jpg', 40, 'Serve an all-American favorite that everyone will enjoy. This exceptional apple pie has a light and flaky crust with a flavorful filling of apples, butter, and cinnamon. Perfect for family dinners and special occasions.', 'price_1Ly1HSHXQUUlyKrgJV6ZKLsc'),
('Pumpkin Pie', 'Village Piemaker', 16.99, 'VillagePiemakerPumpkinPie.jpg', 40, 'Rich, golden delicious pumpkin with notes of warm spices and brown sugar. Our hand-crimped pastry crust adds a flaky crunch to this decadently rich pie filling.  Every pie starts with carefully-sourced fruits that are prepared by mindful hands.', 'price_1Ly1LzHXQUUlyKrgk81OPPrn'),
('Vegan Blueberry Pie', 'Michele''s Pies', 69.95, 'MichelesPiesVeganBlueberryPie.jpg', 50, 'Michele''s Pies'' blueberry pie is made by hand with love, fresh blueberries, just a hint of lemon juice, and a plant-based crust! Every pie at Michele''s Pies is made by hand at Michele Stuart''s eponymous gourmet pie shop in Norwalk, Connecticut.', 'price_1Ly1RIHXQUUlyKrgKePHSxUM');

-- Product Categories
create table categories(
	id serial primary key,
	category varchar(20)
);

insert into categories(category)
values
('Bread'),
('Cake'),
('Cookie'),
('Muffin'),
('Pie');


--Product category pairs
create table product_categories(
	product_id int not null,
	category_id int not null,
	constraint fk_product_id foreign key(product_id) references products(id) on delete cascade,
	constraint fk_category_id foreign key(category_id) references categories(id) on delete cascade
);
insert into product_categories(product_id, category_id)
values 
(1, 1), -- bread
(2, 1), 
(3, 1),  
(4, 1),  
(5, 1), -- end of breads 
(6, 2), -- cakes
(7, 2), 
(8, 2), 
(9, 2), 
(10, 2), -- end of cakes
(11, 3), -- cookies
(12, 3),
(13, 3),
(14, 3),
(15, 3), --end of cookie
(16, 4), --muffins
(17, 4),
(18, 4),
(19, 4),
(20, 4),  --end of muffin
(21, 5), -- pies
(22, 5),
(23, 5),
(24, 5),
(25, 5); -- end of pies

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


--Cart 
create table cart(
	id serial primary key,
	user_id int,
	product_id int,
	quantity int,
	constraint fk_user_id foreign key(user_id) references users(id) on delete cascade,
	constraint fk_product_id foreign key(product_id) references products(id) on delete cascade
);
insert into cart(user_id, product_id, quantity)
values 
(3, 10, 1),
(3, 6, 1),
(3, 1, 3),
(1, 6, 2),
(1, 25, 1);

--Order History 
create table order_history(
	id serial primary key,
	user_id int,
	product_id int,
	quantity int,
	purchase_date date default current_timestamp,
	constraint fk_user_id foreign key(user_id) references users(id) on delete cascade,
	constraint fk_product_id foreign key(product_id) references products(id) on delete cascade
);
insert into order_history(user_id, product_id, quantity)
values 
(1, 10, 3),
(1, 1, 1),
(2, 2, 2),
(4, 4, 4),
(4, 1, 2);






