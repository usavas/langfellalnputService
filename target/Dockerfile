FROM openjdk:8-jre-alpine

COPY NLPUtil-0.1.jar /usr/app/

COPY en_lemmatizer /usr/app/

COPY en_pos_maxent.bin /usr/app/

# COPY en_sent.bin /usr/app/

WORKDIR /usr/app/

ENTRYPOINT ["java","-jar","NLPUtil-0.1.jar"]
