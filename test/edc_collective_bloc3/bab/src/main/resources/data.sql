DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS users;
 
CREATE TABLE books (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  status VARCHAR(255));
 
INSERT INTO books (name, description, status) VALUES
  ('example1', 'a book about something', 'BOOKED'),
  ('example2', 'a book about something else', 'BOOKED'),
  ('example3', 'a book about nothing', 'AVAILABLE'),
  ('example4', 'a book about ...', 'BORROWED');


 CREATE TABLE reservations (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  book_id INT NOT NULL,
  user_id INT NOT NULL,
  date_resa date NOT NULL);

INSERT INTO reservations (book_id, user_id, date_resa) VALUES
  (1,1,'2024-01-01'),
  (2,2,'2024-03-03');
  
CREATE TABLE users (
	id INT AUTO_INCREMENT  PRIMARY KEY,
	username VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL);

INSERT INTO users (username, password) VALUES
	('john', 'mdp1'),
	('james', 'mdp2');