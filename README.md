README for firstapp
==========================
Generating relationships with other entities
? Do you want to add a relationship to another entity? No
===========Author==============
author (String)
birthDay (LocalDate)
-------------------
book - book (one-to-many)
? Do you want to use a Data Transfer Object (DTO)? No, use the entity directly
? Do you want pagination on your entity? No
Everything is configured, generating the entity...
   create .jhipster\Author.json
   create src\main\java\org\daniels\jhipster\firstapp\domain\Author.java
   create src\main\java\org\daniels\jhipster\firstapp\repository\AuthorRepository.java
   create src\main\java\org\daniels\jhipster\firstapp\web\rest\AuthorResource.java
   create src\main\resources\config\liquibase\changelog\20151121111813_added_entity_Author.xml
   create src\main\webapp\scripts\app\entities\author\authors.html
   create src\main\webapp\scripts\app\entities\author\author-detail.html
   create src\main\webapp\scripts\app\entities\author\author.js
   create src\main\webapp\scripts\app\entities\author\author.controller.js
   create src\main\webapp\scripts\app\entities\author\author-detail.controller.js
   create src\main\webapp\scripts\components\entities\author\author.service.js
   create src\test\java\org\daniels\jhipster\firstapp\web\rest\AuthorResourceTest.java
   create src\test\gatling\simulations\AuthorGatlingTest.scala
   create src\main\webapp\i18n\en\author.json
   create src\main\webapp\i18n\fr\author.json

c:\daniels\jHipster\firstapp>


-----
How to add a column:
1. Add column to .jhipster\Simple.json file
2. Run yo jhipster:entity simple
3. Remove changelog from master changelog
4. create new file with changeSet and add it to master.xml changelog
Example of ChangeSet
	<changeSet author="daniels (generated)" id="1448128734359-9">
		<addColumn tableName="SIMPLE">
			<column name="price" type="DECIMAL(10, 2)"/>
		</addColumn>
	</changeSet>
5. Run mvn spring-boot:run	
 