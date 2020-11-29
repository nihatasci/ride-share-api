# ride-share-api

Proje maven olarak build alınmaktadır. “mvn clean install” şeklinde docker image olarak ayağa kaldırılabilir.
Swagger api desteği eklenmiştir ve aşağıdaki adresten erişilebilir.
http://localhost:8080/swagger-ui/index.html#/


Açıklama;

Zaman konusunda yaşadığım bir talihsizliği telafi etmek için yapıyı hızlı kurgulamam gerekti. Bu aşamada H2Database ile relational in memory database çözümüne başvurdum. Entityler bu sayede direk sql şemasını oluşturarak hız kazandırdı. 

Sistem 1. adımda istenen tüm CRUD işlemlerini yapıyor ama 2. adımı gerçekleştirmek için bazı değişiklikler yapmam gerekti ve zamanım yetmediğinden bozmamak adına girişmedim.

Servisler;
	add: Yolculuk planı ekler.
	publish: Planın yayınlanıp yayınlanmaması seçimini yapar.
	search: Yolculuk araması gerçekleştirir.
	join: Plana katılmayı sağlar.
	getCityMap: Şehir haritasını 2 boyutlu dizi olarak döner.

Yapılabilecekler;
	-Tablolar sql komutlarıyla oluşturulursa docker-compose üzerinden ayrı image olarak çalıştırılabilir.
-User tablosu çok basit oluşturuldu. Geliştirilmeli ve user bu apiyi JWT ile kullanabilmeli.
-Gidilecek güzergahtaki şehirleri planEntity içinde bir liste olarak tutmak search algoritmasını 2. adımda istenilen şekle getirecektir.
-Null check adımları tam olarak eklenmelidir.
