Stream Video Platform
Stream Video Platform is a full-stack application designed to allow users to register, log in, and manage video content seamlessly. Built using Spring Boot for the backend and React for the frontend, this project implements secure authentication  integrates with a MySQL database for persistent data storage. The backend exposes RESTful APIs for user authentication, video management, and content streaming, while the frontend provides a simple yet intuitive interface for users to interact with the platform.

How to Run the Project
Prerequisites:
Ensure you have the following installed on your system:
Java 17 or later (for Spring Boot)
Node.js (for React frontend)
MySQL (for database)
Maven (for dependency management)
Steps to Run the Backend:


1.Clone the repository:

 git clone https://github.com/yourusername/StreamVideoPlatform.git
 cd StreamVideoPlatform


2.Configure the application.properties file in src/main/resources/ to match your MySQL database settings:

spring.datasource.url=jdbc:mysql://localhost:3306/your_database

spring.datasource.username=root

spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update


3. Run the application:

	mvn spring-boot:run


5. The backend will start on http://localhost:8080.



Steps to Run the Frontend:
Navigate to the frontend directory:


1. cd frontend

 2. Install dependencies:
	npm install

3. Start the React development server:
	npm start

 4. Open your browser and visit http://localhost:3000 to access the application.

 
