# springboot-library-app

📚 LibraryApp – Java Spring Boot Case Study

Uygulama; yayınevi, kitap ve yazar ilişkilerini yönetir, RESTful API’ler üzerinden CRUD işlemleri sağlar ve modern Java backend geliştirme prensiplerini esas alır.

🎯 Proje Amacı

Bu case çalışmasının amacı:
Spring Boot ile modüler ve okunabilir bir backend mimarisi kurmak,
MVC yapısını, SOLID prensiplerini ve Clean Code yaklaşımını uygulamak,
JPA, Stream API, Feign Client gibi teknolojileri gerçek bir senaryo üzerinde kullanmaktır.

🧱 Domain Model

Publisher - Bir yayınevinin birden fazla kitabı olabilir
Book - Her kitap, bir yayınevine, bir yazara sahiptir
Author - Her yazar bir kitap ile ilişkilidir

🚀 Sağlanan Fonksiyonlar

Listeleme İşlemleri:
-Tüm yayınevlerini listeleme
-Tüm kitapları listeleme
-Tüm yazarları listeleme
-İki yayınevine ait kitapları yazarlarıyla birlikte listeleme

Filtreleme & Query
-Stream API kullanarak, “A” harfiyle başlayan kitapları getiren yapı
-JPA Query kullanarak, 2023 yılından sonra basılan kitapları filtreleme

✏️ CRUD İşlemleri

Kitap için Create / Read / Update / Delete işlemleri mevcuttur.
Kitap eklenirken yazar ve yayınevi bilgileri aynı anda kaydedilir.

🌐 Google Books API Entegrasyonu

Feign Client kullanılmıştır.
Google Books API üzerinden kitap adına göre arama yapılabilir.

🗄️ Veritabanı: PostgreSQL (local)

🧪 Testler

Projede iki adet unit test bulunmaktadır. 
BookControllerTest, Stream API kullanılarak yalnızca A/a harfiyle başlayan kitapları döndüren endpoint’in doğru çalıştığını doğrular. 
BookRepositoryTest ise 2023 yılından sonra basılan kitapları filtreleyen JPA query’sinin veritabanı seviyesinde doğru sonuç ürettiğini test eder.

📖 API Dokümantasyonu

Uygulama Swagger üzerinden test edilebilir.
