# springboot-library-app

📚 LibraryApp – Java Spring Boot Case Study

Uygulama; yayınevi, kitap ve yazar ilişkilerini yönetir, RESTful API’ler üzerinden CRUD işlemleri sağlar ve modern Java backend geliştirme prensiplerini esas alır.

🎯 Proje Amacı

Bu case çalışmasının amacı:

Spring Boot ile modüler ve okunabilir bir backend mimarisi kurmak,

MVC yapısını, SOLID prensiplerini ve Clean Code yaklaşımını uygulamak,

JPA, Stream API, Feign Client gibi teknolojileri gerçek bir senaryo üzerinde kullanmaktır.

🧱 Domain Model

Case gereksinimlerine uygun olarak aşağıdaki yapı modellenmiştir:

Publisher (Yayınevi)
Bir yayınevinin birden fazla kitabı olabilir

Book (Kitap)
Her kitap:

Bir yayınevine

Bir yazara sahiptir

Author (Yazar)
Her yazar bir kitap ile ilişkilidir
