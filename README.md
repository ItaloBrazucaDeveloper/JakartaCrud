# ☕ Java no Frontend

![Imagem da listagem de usuários](preview.png)

[![Java](https://img.shields.io/badge/Java-21-blue.svg)](https://adoptopenjdk.net)
![Made in Brazil](https://img.shields.io/badge/made%20in-brazil-green.svg)
![Project Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)

Mini projeto de CRUD (Create, Read, Update, Delete) com conexão ao banco de dados MySQL.

## 🚀 Como rodar o projeto

1. **Pré-requisitos:**
   - [Java 21](https://adoptopenjdk.net/)
   - [MySQL Server](https://www.mysql.com/)
   - [Tomcat 11.0.6](https://tomcat.apache.org/)
   - [IntelliJ IDEA](https://www.jetbrains.com/idea/) (opcional, mas recomendado)

2. **Clone o projeto**
   ```bash
   git clone https://github.com/ItaloBrazucaDeveloper/JakartaCrud.git
   ```   

3. **Entre na pasta do projeto**
    ```bash
   cd JakartaCrud
   ```
   
4. **Instale as depedências**
   ```bash
   mvn install
   ```

5. **Defina as variáveis de ambiente, seguindo o padrão do arquivo .example**
   ```bash
   # Comando para copiar o arquivo '.example.env' e colar como '.env'
   cp .example.env .env
   ```
   
   Exemplo:

   ```plaintext
   DB_URL=jdbc:mysql://seu_host:porta/nome_do_banco
   DB_USER=bananinha
   DB_PASS=@gigawats
   ```

---

Sinta-se à vontade para contribuir!