# Build frontend
FROM node:20 AS frontend-build

WORKDIR /wordle-app/frontend

# Install frontend dependencies
COPY frontend .
RUN npm install

# Build frontend for production
RUN npm run build --configuration frontend


# Build backend
FROM maven AS tfgwordle-builder

WORKDIR /wordle-app/backend

# Install backend dependencies
COPY TFGWordle/pom.xml .
COPY TFGWordle/src/ ./src

COPY --from=frontend-build /wordle-app/frontend/dist/frontend ./src/main/resources/static

# Build backend for production
RUN mvn clean package -DskipTests


# Build Docker Image
FROM openjdk:17

WORKDIR /wordle-app

# Create app JAR
COPY --from=tfgwordle-builder /wordle-app/backend/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]