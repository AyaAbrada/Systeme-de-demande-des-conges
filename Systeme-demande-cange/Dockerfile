# استخدام Java 17 JDK
FROM openjdk:17-jdk-slim

# تعيين مجلد العمل داخل الحاوية
WORKDIR /app

# نسخ ملف jar الناتج من البناء
COPY target/Systeme-demande-cange-0.0.1-SNAPSHOT.jar app.jar

# فتح البورت
EXPOSE 8083

# تشغيل التطبيق
ENTRYPOINT ["java", "-jar", "app.jar"]
