# crawlNewsItems
Create a Spring Boot application that will poll a news feed every 5 minutes, and stores any changes in a
relational database.

The news feed to use is: http://feeds.nos.nl/nosjournaal?format=xml. Create a data model to store at
least the last 10 news items in the database. For each item at least the title, description, published date
and image needs to be stored. It could be an item is updated in the feed, in which case its record in the
database should be updated as well. Expose the news items using GraphQL, see
https://github.com/graphql-java-kickstart/graphql-springboot

Please deliver this as you would an application ready for production, using all the best practices you
normally use. The source code can be shared as an attachment, or via an online repository like GitHub
