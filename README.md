# ☕ Java no Frontend

![Imagem da listagem de usuários](preview.png)

[![Java](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/technologies/downloads/)
![Made in Brazil](https://img.shields.io/badge/made%20in-brazil-green.svg)
![Project Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)

Mini projeto de CRUD (Create, Read, Update, Delete) com conexão ao banco de dados MySQL e Jakarta.

## 🚀 Como rodar o projeto

1. **Pré-requisitos:**
   - [Java 21](https://www.oracle.com/java/technologies/downloads/)
   - [MySQL](https://www.mysql.com/)
   - [Tomcat 11.0.6](https://tomcat.apache.org/)

2. **Clone o projeto**
   ```bash
   git clone https://github.com/ItaloBrazucaDeveloper/UsersCrud.git
   ```   

3. **Entre na pasta do projeto**
    ```bash
   cd UsersCrud
   ```
   
4. **Instale as depedências**
   ```bash
   mvn install
   ```

5. **Defina as credencias do seu banco MySql no arquivo [persistence.xml](src/main/resources/META-INF/persistence.xml)**

   ```xml
   <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/DB_NAME"/>
   <property name="jakarta.persistence.jdbc.user" value="DB_USERNAME"/>
   <property name="jakarta.persistence.jdbc.password" value="DB_PASS"/>
   ```

6. **Faça o build do projeto**
   ```bash
   mvn clean package
   ```

7. **Mova o arquivo .war gerado para a pasta webapp do tomcat**
   ```bash
   cp ./target/UsersCrud_1.0.war /caminho/para/o/tomcat/webapp/
   ```

8. **Acesse o projeto via localhost http://localhost:8080/UsersCrud-1.0/**

---

Sinta-se à vontade para contribuir!
