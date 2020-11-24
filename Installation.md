# Installation av stöd för MySQL i IntelliJ-projekt

1. Ladda ner biblioteket Connector/J från [MySQLs hemsida](https://dev.mysql.com/downloads/connector/j/)
2. Packa upp filen `mysql-connector-java-8.0.22.jar` till en bra plats
3. Från projektet i IntelliJ gå in under menyn `File -> Project Structure` och välj `Libraries`
    1. Tryck på + (new Project Library) 
    2. Välj Java
    3. Leta fram filen `mysql-connector-java-8.0.22.jar` och tryck Ok två gånger
4. Klart!

# Starta MySQL

Se till att WSL och MySQL är installerade. Om inte gå till [Jens instruktion](https://github.com/jensnti/Webbserverprogrammering/blob/master/utvecklarmiljo/wsl.md)
och följ den. Du behöver bara MySQL. Om du vill kan det vara bra att installera `phpmyAdmin` eller [Tableplus](https://tableplus.com/). Det är viktigt att portarna är ok. 
Min Windowsmaskin startar ibland en massa andra SQL-servrar trots att jag stängt av det.

# Skapa en databas
Skapa databasen i tableplus, phpmyAdmin eller direkt i terminalen. Du kan om du vill använda min dump `bookshop.sql'.

# Anslut till databasen från java    
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshop? allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",user,password);

Observera att du behöver ange din user och ditt password i anropet!

# Skapa ett statement och gör din SQL-fråga
    Statement stmt = conn.createStatement();
    String SQLquery = "SELECT * FROM BOOK";
    ResultSet rset = stmt.executeQuery(strSelect);

# Gör något med resultatet
    rset.next()

plockar fram nästa rad i resultatet från mySQL frågan. 
    
    String bookTitle = rset.getString("title");
    
Hämtar resultatet för `title` ur den aktuella resultatraden