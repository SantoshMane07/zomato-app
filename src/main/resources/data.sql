-- Insert Users
INSERT INTO app_user (contact_number, name, email, password) VALUES
('8169140541', 'Prem Mane', 'prem@gmail.com', '$2a$10$8GxoI/UERAw2v.oJzBVcM.OtcmZELOAdXRDTU92MjoFuS3oelREYi'),
('8169140545', 'Santosh Mane', 'santosh@gmail.com', '$2a$10$8GxoI/UERAw2v.oJzBVcM.OtcmZELOAdXRDTU92MjoFuS3oelREYi'),
('8169140551', 'Suresh Kumar', 'suresh.kumar@gmail.com', '$2a$10$8GxoI/UERAw2v.oJzBVcM.OtcmZELOAdXRDTU92MjoFuS3oelREYi'),
('8169140552', 'Amit Patel', 'amit.patel@gmail.com', '$2a$10$8GxoI/UERAw2v.oJzBVcM.OtcmZELOAdXRDTU92MjoFuS3oelREYi'),
('8169140553', 'Ravi Sharma', 'ravi.sharma@gmail.com', '$2a$10$8GxoI/UERAw2v.oJzBVcM.OtcmZELOAdXRDTU92MjoFuS3oelREYi'),
('8169140554', 'Deepak Mehta', 'deepak.mehta@gmail.com', '$2a$10$8GxoI/UERAw2v.oJzBVcM.OtcmZELOAdXRDTU92MjoFuS3oelREYi'),
('8169140555', 'Vivek Yadav', 'vivek.yadav@gmail.com', '$2a$10$8GxoI/UERAw2v.oJzBVcM.OtcmZELOAdXRDTU92MjoFuS3oelREYi'),
('8169140556', 'Rajesh Gupta', 'rajesh.gupta@gmail.com', '$2a$10$8GxoI/UERAw2v.oJzBVcM.OtcmZELOAdXRDTU92MjoFuS3oelREYi'),
('8169140557', 'Anita Sharma', 'anita.sharma@gmail.com', '$2a$10$8GxoI/UERAw2v.oJzBVcM.OtcmZELOAdXRDTU92MjoFuS3oelREYi'),
('8169140558', 'Karan Singh', 'karan.singh@gmail.com', '$2a$10$8GxoI/UERAw2v.oJzBVcM.OtcmZELOAdXRDTU92MjoFuS3oelREYi'),
('8169140559', 'Rahul Verma', 'rahul.verma@gmail.com', '$2a$10$8GxoI/UERAw2v.oJzBVcM.OtcmZELOAdXRDTU92MjoFuS3oelREYi'),
('8169140560', 'Manoj Kumar', 'manoj.kumar@gmail.com', '$2a$10$8GxoI/UERAw2v.oJzBVcM.OtcmZELOAdXRDTU92MjoFuS3oelREYi'),
('8169140561', 'Pooja Singh', 'pooja.singh@gmail.com', '$2a$10$8GxoI/UERAw2v.oJzBVcM.OtcmZELOAdXRDTU92MjoFuS3oelREYi'),
('8169140562', 'Anil Pandey', 'anil.pandey@gmail.com', '$2a$10$8GxoI/UERAw2v.oJzBVcM.OtcmZELOAdXRDTU92MjoFuS3oelREYi');

-- Assign Roles
INSERT INTO user_roles (user_id, roles) VALUES
(1, 'CUSTOMER'),
(2, 'ADMIN'),
(3, 'RESTAURANT_PARTNER'),
(4, 'RESTAURANT_PARTNER'),
(5, 'RESTAURANT_PARTNER'),
(6, 'RESTAURANT_PARTNER'),
(7, 'RESTAURANT_PARTNER'),
(8, 'RESTAURANT_PARTNER'),
(9, 'DELIVERY_PARTNER'),
(10, 'DELIVERY_PARTNER'),
(11, 'DELIVERY_PARTNER'),
(12, 'DELIVERY_PARTNER'),
(13, 'DELIVERY_PARTNER'),
(14, 'DELIVERY_PARTNER');

-- Create Wallets
INSERT INTO wallet (user_id, balance) VALUES
(1, 1000),
(2, 1000),
(3, 1000),
(4, 1000),
(5, 1000),
(6, 1000),
(7, 1000),
(8, 1000),
(9, 1000),
(10, 1000),
(11, 1000),
(12, 1000),
(13, 1000),
(14, 1000);

-- Assign Admin Role
INSERT INTO admin (user_id) VALUES
(2);

-- Insert Delivery Partners (6 delivery partners)
INSERT INTO delivery_partner (user_id, aadhar_no, vehicle_id, is_available, admin_id, current_location, rating) VALUES
(9, 1001, 'MH06-1111', true, 1, ST_GeomFromText('POINT(72.8510 19.3000)', 4326), 4.9),
(10, 1002, 'MH06-2222', true, 1, ST_GeomFromText('POINT(72.8520 19.2990)', 4326), 4.8),
(11, 1003, 'MH06-3333', true, 1, ST_GeomFromText('POINT(72.8530 19.2980)', 4326), 4.7),
(12, 1004, 'MH06-4444', true, 1, ST_GeomFromText('POINT(72.8540 19.2970)', 4326), 4.8),
(13, 1005, 'MH06-5555', true, 1, ST_GeomFromText('POINT(72.8550 19.2960)', 4326), 4.6),
(14, 1006, 'MH06-6666', true, 1, ST_GeomFromText('POINT(72.8560 19.2950)', 4326), 4.7);

-- Insert Restaurant Partners (6 restaurant partners)
INSERT INTO restaurant_partner (user_id, aadhar_no, admin_id) VALUES
(3, 1111, 1),
(4, 1112, 1),
(5, 1113, 1),
(6, 1114, 1),
(7, 1115, 1),
(8, 1116, 1);

-- Insert Restaurants (6 restaurants)
INSERT INTO restaurant (restaurant_partner_id, name, description, restaurant_type, is_open, address, rating) VALUES
(1, 'Spice Delight', 'A popular restaurant offering a wide variety of Indian spices and curries.', 'VEG_AND_NON_VEG', true, ST_GeomFromText('POINT(72.8515 19.3045)', 4326), 4.6),
(2, 'Biryani House', 'A specialist in authentic Hyderabadi Biryani.', 'NON_VEG', true, ST_GeomFromText('POINT(72.8550 19.2990)', 4326), 4.5),
(3, 'Taste of Punjab', 'Traditional Punjabi cuisine with a focus on tandoori dishes.', 'VEG_AND_NON_VEG', true, ST_GeomFromText('POINT(72.8560 19.3000)', 4326), 4.4),
(4, 'Green Leaf Diner', 'A cozy vegetarian restaurant offering a variety of healthy dishes.', 'VEG', true, ST_GeomFromText('POINT(72.8545 19.3015)', 4326), 4.3),
(5, 'Pizza Planet', 'Best place for gourmet pizzas in the city.', 'VEG_AND_NON_VEG', true, ST_GeomFromText('POINT(72.8555 19.3005)', 4326), 4.5),
(6, 'Curry Corner', 'A small place with authentic North Indian curries and breads.', 'VEG', true, ST_GeomFromText('POINT(72.8565 19.2995)', 4326), 4.4);

-- Insert Menus (1 menu per restaurant)
INSERT INTO menu (restaurant_id, menu_name, is_active) VALUES
(1, 'Indian Specials', true),
(2, 'Biryani Specials', true),
(3, 'Tandoori Treats', true),
(4, 'Healthy Veg Specials', true),
(5, 'Pizza Delights', true),
(6, 'North Indian Delights', true);

-- Insert Menu Items (2 items per menu)
INSERT INTO menu_item (menu_id, name, description, is_available, menu_item_type, price, rating) VALUES
(1, 'Paneer Butter Masala', 'Rich and creamy paneer curry cooked in buttery tomato gravy.', true, 'VEG_MAIN_COURSE', 350, 4.8),
(1, 'Dal Tadka', 'A classic yellow lentil dish tempered with Indian spices.', true, 'VEG_MAIN_COURSE', 150, 4.6),
(2, 'Chicken Biryani', 'Authentic Hyderabadi chicken biryani with rich spices.', true, 'NON_VEG_MAIN_COURSE', 400, 4.9),
(2, 'Mutton Biryani', 'Slow-cooked mutton biryani with traditional spices.', true, 'NON_VEG_MAIN_COURSE', 500, 4.7),
(3, 'Tandoori Chicken', 'Whole chicken marinated and grilled in a tandoor.', true, 'NON_VEG_STARTER', 450, 4.8),
(3, 'Paneer Tikka', 'Spiced paneer grilled to perfection.', true, 'VEG_STARTER', 300, 4.7),
(4, 'Vegetable Salad', 'Fresh vegetable salad with olive oil dressing.', true, 'VEG_STARTER', 200, 4.6),
(4, 'Palak Paneer', 'Spinach cooked with Indian cottage cheese.', true, 'VEG_MAIN_COURSE', 350, 4.6),
(5, 'Margherita Pizza', 'Classic pizza with fresh mozzarella and basil.', true, 'VEG_MAIN_COURSE', 500, 4.5),
(5, 'Pepperoni Pizza', 'Thin crust pizza topped with pepperoni.', true, 'NON_VEG_MAIN_COURSE', 600, 4.7),
(6, 'Butter Naan', 'Fluffy bread baked in a tandoor and brushed with butter.', true, 'VEG_MAIN_COURSE', 50, 4.8),
(6, 'Paneer Butter Masala', 'Paneer in a rich and creamy tomato-based gravy.', true, 'VEG_MAIN_COURSE', 350, 4.9);

-- Insert Customer (Prem Mane)
INSERT INTO customer (user_id, delivery_address,rating) VALUES
(1, ST_GeomFromText('POINT(72.8438 19.2992)', 4326),2);
