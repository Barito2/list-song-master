# List-Song

This is a simple list of songs and singers using spring-boot.

# Tools

- Intellij IDEA
- MySQL Workbench
- spring-boot

# How To Install

1. clone this repository

   https://github.com/Barito2/list-song-master.git

2. create scheme in MySQL Workbench

3. Configure application.yml file at src/main/resources directory or you can configure these environment variables :

   | Name    | Description       | Default      |
   | ------- | ----------------- | ------------ |
   | PORT    | Server Port       | 8081         |
   | Jwt     | JWT Secret        | techgeeknext |
   | DB_HOST | Database Host     | localhost    |
   | DB_PORT | Database Port     | 3306         |
   | DB_NAME | Database Name     | inventory    |
   | DB_USER | Database User     | root         |
   | DB_PASS | Database Password |              |

4. Run the application (development).

   ```
   mvn spring-boot:run
   ```

5. Open the swagger docs at this URL for a list of available API endpoints. To get an authorization token, use signup then signin.

   ```
   http://localhost:8081/swagger-ui.html
   ```

