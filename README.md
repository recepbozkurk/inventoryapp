# Inventory App
## Envanter Yönetim Uygulaması

##Kullanılan Teknolojiler
Java, Spring Boot, MySql

##Açıklamalar
Product
Ürün kayıt etme, ekleme, çıkarma, silme ve düzenleme işlemleri yapılabilir.
Tüm işlemler sırasında History tablosunda log kaydı tutulur.
Save endpointlerine gönderilen body içerisinde (id) bulunursa düzenleme, bulunmazsa yeni kayıt işlemi yapılır.
Yeni bir ürün eklendiğinde eğer Threshold tablosunda kaydı yoksa default olarak 1 değeriyle birlikte yeni kayıt otomatik olarak oluşturulur.

Ürün çıkarma esnasında belirtilen eşik değerinin altına düşülürse kullanıcıya mail gönderilir.

Search
Ürün adı aratıldığında ürünün bulunduğu depoların listesi dönülür.

Filter
Depo ve Kategori bilgilerine göre(veya her ikisi birlikte) filtreleme yapılarak ürün listesi dönülür.
