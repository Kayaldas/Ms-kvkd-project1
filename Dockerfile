#Start with a base image containing Java runtime
FROM eclipse-temurin:17

# MAINTAINER instruction is deprecated in favor of using label
# MAINTAINER eazybytes.com
#Information around who maintains the image
LABEL maintainer="kayal241987@gmail.com"

WORKDIR /app

# Add the application's jar to the image
COPY accounts/target/accounts-0.0.1-SNAPSHOT.jar /app/accounts.jar

# execute the application
ENTRYPOINT ["java", "-jar", "accounts.jar"]