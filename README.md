# WorldEdit Clone + Spawn Plugin

Полный клон WorldEdit для Minecraft с системой спавна игроков.

🚀 Возможности

✅ WorldEdit команды:
- `//set <блок>` - Заполнение области
- `//regen` - Регенерация местности  
- `//copy` / `//paste` - Буфер обмена
- `//undo` / `//redo` - История изменений
- `//brush` - Система кистей
- `//pos1` / `//pos2` - Выделение области

✅ Кисти (Brushes):
- Сферическая кисть
- Цилиндрическая кисть  
- Кисть сглаживания
- Гравитационная кисть
- Кисть замены

✅ Спавн система:
- `/spawn` - Телепорт на спавн
- `/setspawn` - Установить точку спавна

📦 Установка

1. Скачайте `WorldEditClone.jar` из Releases
2. Поместите в папку `plugins/` сервера
3. Перезапустите сервер

🛠 Разработка

```bash
# Сборка проекта
mvn clean package

# Собранный плагин будет в target/

📄 Лицензия


4. Прокрути вниз, в разделе **"Commit new file"**:
   - Введи: `Add README.md`
   - Выбери **"Commit directly to the main branch"**
   - Нажми **"Commit new file"**

### **Шаг 3: Добавь файл `.gitignore`**
1. Снова **"Add file"** → **"Create new file"**
2. Имя файла: `.gitignore`
3. Вставь этот код:
Compiled class files
*.class
Package Files
*.jar
*.war
*.ear

Maven
target/
pom.xml.tag
pom.xml.releaseBackup
pom.xml.versionsBackup
pom.xml.next

IDE
.idea/
*.iml
.classpath
.project
.settings/
bin/

OS
.DS_Store
Thumbs.db

4. Commit: `Add .gitignore`

### **Шаг 4: Добавь `pom.xml`**
1. **"Add file"** → **"Create new file"**
2. Имя: `pom.xml`
3. Код:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.alexasdr</groupId>
    <artifactId>WorldEditClone</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <properties>
        <java.version>17</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <repositories>
        <repository>
            <id>spigot-repo</id>
            <url>https://hub.spigotmc.org/nexus/content/repositories/snapshots/</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>org.spigotmc</groupId>
            <artifactId>spigot-api</artifactId>
            <version>1.20.4-R0.1-SNAPSHOT</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
Commit: Add pom.xml
